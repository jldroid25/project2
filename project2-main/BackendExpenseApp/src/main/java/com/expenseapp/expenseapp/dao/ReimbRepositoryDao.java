package com.expenseapp.expenseapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.expenseapp.expenseapp.entity.Reimbursement;

/*The Dao is extending to JpaRepository. 
 * Therefore, we  must passing our Entity with a Generic Integer Wrapper class Type.
 * By extending JpaRepository we automatically get the 5 CRUD operations method &
 * can start using them in the reimbursementService 
 * 
 * 
 * This is the file where you defined you  methods the service will need 
 * & the controller will need  to create the endpoints.
 * Therefore define these methods must be done right here.
*/
@Repository
public interface ReimbRepositoryDao  extends JpaRepository<Reimbursement, Integer>{
	
	
    //------------- Custom Spring Data JPA Queries ------------
	
	// For Finding all Reimbursements Approved, Pending & Denied
	@Query("FROM Reimbursement WHERE reimb_removed = FALSE ORDER BY rb_id asc")
	List<Reimbursement> getAllReimbursements();

	//For finding all  Pending Reimbursement s only 
	@Query("FROM Reimbursement WHERE reimb_removed = FALSE AND rb_status='Pending'")
	List<Reimbursement> getAllPendingReimbursements();

	//For finding All Resolved  Reimbursements only
	@Query("FROM Reimbursement WHERE reimb_removed = FALSE AND rb_status='Approved' OR rb_status='Denied' ")
	List<Reimbursement> getAllResolvedReimbursements();
	
	// ---------- Fix Query they return for all users 
	//For finding a Resolved Reimbursement by user  id 
	@Query("FROM Reimbursement WHERE reimb_removed=FALSE AND rb_status='Approved'  AND  userId = :userId ")
	List<Reimbursement> getUserResolvedReimb(int userId);
	
	//For finding a Pending Reimbursement by user id 
	@Query("FROM Reimbursement WHERE reimb_removed=FALSE AND rb_status='Pending' AND  userId = :userId ")
    List<Reimbursement> getUserPendingReimb(int userId);
	
	//For finding all Specific Reimbursements  for one user by user id 
	@Query("FROM Reimbursement WHERE reimb_removed = FALSE AND userId = :userId ORDER BY rb_id asc")
	List<Reimbursement> getUserAllReimb(int userId);
	
}
