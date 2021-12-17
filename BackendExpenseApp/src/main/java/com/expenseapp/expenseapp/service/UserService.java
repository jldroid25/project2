package com.expenseapp.expenseapp.service;

import java.util.List;

import com.expenseapp.expenseapp.exception.ApplicationException;
import com.expenseapp.expenseapp.pojo.UserPojo;

public interface UserService {

	UserPojo addUserService(UserPojo userInfo) throws ApplicationException;

	UserPojo getAUserService(int userId) throws ApplicationException;

	UserPojo updateUserService(UserPojo userInfo) throws ApplicationException;

	boolean deleteUserService(int userId) throws ApplicationException;

	List<UserPojo> getAllUserService() throws ApplicationException;

}
