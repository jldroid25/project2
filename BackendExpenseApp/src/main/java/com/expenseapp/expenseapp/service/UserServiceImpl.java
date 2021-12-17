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
	
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserRepositoryDao 	userRepositoryDao ;
	
	 public UserServiceImpl() {}

	@Override
	public UserPojo addUserService(UserPojo userInfo) throws ApplicationException {
		logger.info("Entered addUserService() in service.");
		
		User newUser = new User(userInfo.getUserId(), userInfo.getFirstname(), userInfo.getLastname(),
				userInfo.getEmail(), userInfo.getUsername(), userInfo.getPassword(),  
				userInfo.getAccessLevel(), userInfo.isUserRemoved());
		// We use the Spring Data Jpa Built-in method saveAndFlush()
		User returnUser = userRepositoryDao.saveAndFlush(newUser);
		userInfo.setUserId(returnUser.getUserId());
		logger.info("Left  addUserService() in service.");
		return userInfo;
	}

	@Override
	public UserPojo getAUserService(int userId) throws ApplicationException {
		logger.info("Entered getUserService() in service.");
		UserPojo userPojo = null;
		/*
		 * We are calling the Spring Data Jpa Built-in findById method to fetch a record
		 * by ID. FindById returns java.util.Optional which contains the book entity .
		 */
		Optional<User> optional = this.userRepositoryDao.findById(userId);
		if (optional.isPresent()) { 
			
			// Let's take out the reimbursement entity from the optional and store in a
			// Reimbursement reference.
			User user = optional.get();
			userPojo = new UserPojo(user.getUserId(), user.getFirstname(), user.getLastname(),
					user.getEmail(), user.getUsername(), user.getPassword(),  
					user.getAccessLevel(), user.isUserRemoved());
		}
		logger.info("Left  getUserService() in service.");
		return userPojo;
	}

	@Override
	public UserPojo updateUserService(UserPojo userInfo) throws ApplicationException {
		logger.info("Entered updateUserService() in service.");
		 User updateUser = new User(userInfo.getUserId(), userInfo.getFirstname(), userInfo.getLastname(),
				userInfo.getEmail(), userInfo.getUsername(), userInfo.getPassword(),  
				userInfo.getAccessLevel(), userInfo.isUserRemoved());
		User returnUser = userRepositoryDao.save(updateUser);
		logger.info("Left  updateUserService() in service.");		
		return userInfo;
	}

	@Override
	public boolean deleteUserService(int userId) throws ApplicationException {
		logger.info("Entered deleteUserService() in service.");
		// We use the Spring Data Jpa Built-in method deleteById() for performing delete
		this.userRepositoryDao.deleteById(userId);
		logger.info("Left  deleteUserService() in service.");
		return true;
	}

	@Override
	public List<UserPojo> getAllUserService() throws ApplicationException {
		logger.info("Entered getAllUserService() in service.");
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
		logger.info("Left  getAllUserService() in service.");		
		return allUserPojo;
	}
}