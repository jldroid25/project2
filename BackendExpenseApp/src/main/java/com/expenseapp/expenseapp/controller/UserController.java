package com.expenseapp.expenseapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expenseapp.expenseapp.exception.ApplicationException;
import com.expenseapp.expenseapp.pojo.UserPojo;
import com.expenseapp.expenseapp.service.UserService;

@RestController
//This is optional
@RequestMapping("api")
public class UserController {

	@Autowired
	UserService userService;

	// ----------Create all project apis endpoints in here-----//

	// Post endPoint Api
	// http://localhost:7777/api/users
	// @Cross origin line is to prevent backend from being block by F.E Angular
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("users")
	UserPojo addUser(@RequestBody UserPojo userPojo) throws ApplicationException {
		return userService.addUserService(userPojo);
	}

	// Put endPoint Api -
	// http://localhost:7777/api/users/2
	// @Cross origin line is to prevent backend from being block by F.E Angular
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("users/{uid}")
	UserPojo updateUser(@RequestBody UserPojo userPojo) throws ApplicationException {
		return userService.updateUserService(userPojo);
	}

	// Delete endPoint Api -
	// http://localhost:7777/api/users/6
	// @Cross origin line is to prevent backend from being block by F.E Angular
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("users/{uid}")
	boolean deleteUser(@PathVariable("uid") int userId) throws ApplicationException {
		return userService.deleteUserService(userId);
	}

	// Get endPoint Api - For retrieving One User
	// http://localhost:7777/api/users/6
	// @Cross origin line is to prevent backend from being block by F.E Angular
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("users/{uid}")
	UserPojo getAUser(@PathVariable("uid") int userId) throws ApplicationException {
		return userService.getAUserService(userId);
	}

	// Get endPoint Api - For retrieving All Users
	// http://localhost:7777/api/users
	// @Cross origin line is to prevent backend from being block by F.E Angular
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("users")
	List<UserPojo> getAllUsers() throws ApplicationException {
		return userService.getAllUserService();
	}

}
