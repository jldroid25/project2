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
import com.expenseapp.expenseapp.pojo.ReimbursementPojo;
import com.expenseapp.expenseapp.service.ReimbursementService;

@RestController
//This is optional
@RequestMapping("api")
public class ReimbursementController {

	/*
	 * If we want to creating Object/beans below in Hibernate we must use @Autowired.
	 * BookService bookService = new BookServiceImpl();
	 * 
	 * with @autowired we are telling the framework to inject a bookServiceImpl
	 * object to the field/property bookService
	 */
	@Autowired
	ReimbursementService reimbService;
	
	// Post endPoint Api
	// http://localhost:7777/api/reimbursements
	// @Cross origin line is to prevent backend from being block by F.E Angular
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("reimbursements")
	ReimbursementPojo addReimbursement(@RequestBody ReimbursementPojo reimbPojo) throws ApplicationException {
		return reimbService.createReimbursementService(reimbPojo);
	}

	// Put endPoint Api - for updating a reimb by id
	// http://localhost:7777/api/reimbursements/2
	// @Cross origin line is to prevent backend from being block by F.E Angular
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("reimbursements/{rid}")
	ReimbursementPojo updateReimbursement(@RequestBody ReimbursementPojo reimbPojo) throws ApplicationException {
		return reimbService.updateReimbursementService(reimbPojo);
	}

	// Delete endPoint Api - for deleting a reimb by id
	// http://localhost:7777/api/reimbursements/5
	// @Cross origin line is to prevent backend from being block by F.E Angular
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("reimbursements/{rid}")
	boolean deleteReimbursement(@PathVariable("rid") int reimbId) throws ApplicationException {
		return reimbService.deleteReimbursementService(reimbId);
	}

	// Get endPoint Api - for retrieving a reimb by id
	// http://localhost:7777/api/reimbursements/8
	// @Cross origin line is to prevent backend from being block by F.E Angular
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("reimbursements/{rid}")
	ReimbursementPojo getAReimbursement(@PathVariable("rid") int reimbId) throws ApplicationException {
		return reimbService.retrieveAReimbursementService(reimbId);
	}

	// Get endPoint Api - for retrieving all reimbursements
	// http://localhost:7777/api/reimbursements
	// @Cross origin line is to prevent backend from being block by F.E Angular
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("reimbursements")
	List<ReimbursementPojo> getAllReimbursements() throws ApplicationException {
		return reimbService.retrieveAllReimbursementsService();
	}

	//------------User Specific Reimbursements Pending , Resolved, & Based on User_id ones
	
	// Get A User Specific Reimbursements based on user_id
	// http://localhost:7777/api/reimbursementsuser/21     // -------To fix it returned all reimb
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("reimbursementsuser/{rid}")
	List<ReimbursementPojo> getUserReimbursment(@PathVariable("rid") int userId) throws ApplicationException {
		return reimbService.getAUserReimbursementService(userId);
	}

	// Get A User Pending Reimbursement based on user_id
	// http://localhost:7777/api/reimbursementspending/8
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("reimbursementspending/{rid}")
	List<ReimbursementPojo> getUserPendingreimbursment(@PathVariable("rid") int userId) throws ApplicationException {
		return reimbService.getAUserPendingReimbursementService(userId);
	}

	// Get A User Resolved Reimbursement based on user_id
	// http://localhost:7777/api/reimbursementsresolved/8
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("reimbursementsresolved/{rid}")
	List<ReimbursementPojo> getUserResolvedReimbursment(@PathVariable("rid") int userId) throws ApplicationException {
		return reimbService.getAUserResolvedReimbursementService(userId);
	}

	// Get endPoint Api - for retrieving all Pending reimbursements
	// http://localhost:7777/api/reimbursementspending
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("reimbursementspending")
	List<ReimbursementPojo> getAllPendingReimbursements() throws ApplicationException {
		return reimbService.getAllPendingReimbursementService();
	}

	// Get endPoint Api - for retrieving all Resolved reimbursements
	// http://localhost:7777/api/reimbursementspending
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("reimbursementsresolved")
	List<ReimbursementPojo> getAllResolvedReimbursements() throws ApplicationException {
		return reimbService.getAllResolvedReimbursementService();
	}

}
