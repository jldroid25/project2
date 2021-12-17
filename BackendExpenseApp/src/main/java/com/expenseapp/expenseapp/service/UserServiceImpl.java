package com.expenseapp.expenseapp.service;

import java.util.List;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expenseapp.expenseapp.dao.UserRepositoryDao;
import com.expenseapp.expenseapp.entity.User;
import com.expenseapp.expenseapp.exception.ApplicationException;
import com.expenseapp.expenseapp.pojo.UserPojo;

@Service
public class UserServiceImpl  implements UserService{
	
	
	
	@Autowired
	UserRepositoryDao 	userRepositoryDao ;
	
	 public UserServiceImpl() {}

	@Override
	public UserPojo addUserService(UserPojo userInfo) throws ApplicationException {
	
		
		User newUser = new User(userInfo.getUserId(), userInfo.getFirstname(), userInfo.getLastname(),
				userInfo.getEmail(), userInfo.getUsername(), userInfo.getPassword(),  
				userInfo.getAccessLevel(), userInfo.isUserRemoved());
		
		
		User returnUser = userRepositoryDao.saveAndFlush(newUser);
		userInfo.setUserId(returnUser.getUserId());
	
		return userInfo;
	}

	@Override
	public UserPojo getAUserService(int userId) throws ApplicationException {
	
		UserPojo userPojo = null;
		
		Optional<User> optional = this.userRepositoryDao.findById(userId);
		if (optional.isPresent()) { 
			
			
			User user = optional.get();
			userPojo = new UserPojo(user.getUserId(), user.getFirstname(), user.getLastname(),
					user.getEmail(), user.getUsername(), user.getPassword(),  
					user.getAccessLevel(), user.isUserRemoved());
		}
	
		return userPojo;
	}

	@Override
	public UserPojo updateUserService(UserPojo userInfo) throws ApplicationException {
	
		
		User updateUser = new User(userInfo.getUserId(), userInfo.getFirstname(), userInfo.getLastname(),
				userInfo.getEmail(), userInfo.getUsername(), userInfo.getPassword(),  
				userInfo.getAccessLevel(), userInfo.isUserRemoved());
		
		User returnUser = userRepositoryDao.save(updateUser);
			
		return userInfo;
	}

	@Override
	public boolean deleteUserService(int userId) throws ApplicationException {
	
		// We use the Spring Data Jpa Built-in method deleteById() for performing delete
		this.userRepositoryDao.deleteById(userId);
		//this.userRepositoryDao.softUserDelete(userId);
	
		return true;
	}

	@Override
	public List<UserPojo> getAllUserService() throws ApplicationException {

		List<User>allUserEntity = this.userRepositoryDao.findAll();
		List<UserPojo> allUserPojo = new ArrayList<UserPojo>();
		/*
		 * iterating through the collection of user entities(allUserEntity )
		 * and copying them into a collection of user pojo (allUserPojo)
		 */
		allUserEntity.forEach((user) -> {
			UserPojo userPojo = new UserPojo(user.getUserId(), user.getFirstname(), user.getLastname(),
				user.getEmail(), user.getUsername(), user.getPassword(),  
				user.getAccessLevel(), user.isUserRemoved());
			allUserPojo.add(userPojo);
		});
		
		
			
		return allUserPojo;
	}

	/*
	 * For exitApplication() no implementation required for closing connection here
	 * as Spring Data will take care of the connections.
	 */
	@Override
	public void exitApplication() {}

}
