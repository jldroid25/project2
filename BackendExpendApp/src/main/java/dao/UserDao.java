package dao;

import java.util.List;

import exception.ApplicationException;
import pojo.UserPojo;

public interface UserDao {
	// same as add user
	UserPojo addUser(UserPojo userInfo) throws ApplicationException;

	// same as get one user
	UserPojo getUser(int userId) throws ApplicationException;

	UserPojo updateUser(UserPojo userInfo) throws ApplicationException;

	boolean deleteUser(int userId) throws ApplicationException;

	UserPojo retrieveAUser(int userId) throws ApplicationException;


	List<UserPojo> retrieveAllUsers() throws ApplicationException;

	void exitApplication();

}
