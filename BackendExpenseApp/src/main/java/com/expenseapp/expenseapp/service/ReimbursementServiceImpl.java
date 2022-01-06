package com.expenseapp.expenseapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expenseapp.expenseapp.dao.ReimbRepositoryDao;
import com.expenseapp.expenseapp.entity.Reimbursement;
import com.expenseapp.expenseapp.exception.ApplicationException;
import com.expenseapp.expenseapp.pojo.ReimbursementPojo;

import javax.transaction.Transactional;

@Service
@Transactional
public class ReimbursementServiceImpl implements ReimbursementService {

	@Autowired
	ReimbRepositoryDao reimbRepositoryDao;

	// We leave the constructor empty but above @Autowire annotation will tell
	// the Spring Framework to inject this.reimbDao = new ReimbursementServiceImpl()
	public ReimbursementServiceImpl() {
	}

	@Override
	public ReimbursementPojo createReimbursementService(ReimbursementPojo reimbursementPojo)
			throws ApplicationException {

		Reimbursement newReimb = new Reimbursement(reimbursementPojo.getReimbId(), reimbursementPojo.getReimbDate(),
				reimbursementPojo.getReimbReason(), reimbursementPojo.getReimbAmount(),
				reimbursementPojo.getReimbStatus(), reimbursementPojo.isReimbRemoved(),
				reimbursementPojo.getUserId());
	
		// We use the Spring Data Jpa Built-in method saveAndFlush()
		Reimbursement returnReimb = reimbRepositoryDao.saveAndFlush(newReimb);
		// set the primary key into the pojo
		reimbursementPojo.setReimbId(returnReimb.getReimbId());
		return reimbursementPojo;
	}

	@Override
	public ReimbursementPojo updateReimbursementService(ReimbursementPojo reimbursementPojo)
			throws ApplicationException {

		Reimbursement updateReimb = new Reimbursement(reimbursementPojo.getReimbId(), reimbursementPojo.getReimbDate(),
				reimbursementPojo.getReimbReason(), reimbursementPojo.getReimbAmount(),
				reimbursementPojo.getReimbStatus(), reimbursementPojo.isReimbRemoved(),
				reimbursementPojo.getUserId());
		/*
		 * we use the Spring Data Jpa Built-in save() method below for the update. Also
		 * the primary key is used for Spring Data Jpa to figure out if crud operation
		 * is an insert or update. by spring. If the primary key exists it is an update
		 * otherwise it is insert.
		 */
		Reimbursement returnReimb = reimbRepositoryDao.save(updateReimb);
		return reimbursementPojo;
	}

	@Override
	public boolean deleteReimbursementService(int reimbursementId) throws ApplicationException {
		// We use the Spring Data Jpa Built-in method deleteById() for performing delete
		this.reimbRepositoryDao.deleteById(reimbursementId);
		return true;
	}

	@Override
	public ReimbursementPojo retrieveAReimbursementService(int reimbursementId) throws ApplicationException {
		ReimbursementPojo reimbursementPojo = null;
		/*
		 * We are calling the Spring Data Jpa Built-in findById method to fetch a record
		 * by ID. FindById returns java.util.Optional which contains the reimbursement entity .
		 */
		Optional<Reimbursement> optional = this.reimbRepositoryDao.findById(reimbursementId);

		//if it is present 
		if (optional.isPresent()) {
			/*
			 * Let's take out the reimbursement entity from the optional and store in a
			 * Reimbursement reference.
			 * 
			 * FIY : Optional is primarily intended for use as a method return type where
			 * there is a clear need to represent "no result," and where using {@code null}
			 * is likely to cause errors. A variable whose type is {@code Optional} should
			 * never itself be {@code null}; it should always point to an {@code Optional}
			 * instance.
			 */
			//take it out 
			Reimbursement reimbursement = optional.get();
			// Let's copy the entity into the Pojo
			reimbursementPojo = new ReimbursementPojo(reimbursement.getReimbId(), reimbursement.getReimbDate(),
					reimbursement.getReimbReason(), reimbursement.getReimbAmount(), reimbursement.getReimbStatus(),
					reimbursement.isReimbRemoved(), reimbursement.getUserId());
		}
		return reimbursementPojo;
	}

	@Override
	public List<ReimbursementPojo> retrieveAllReimbursementsService() throws ApplicationException {

		// We are using the Spring Data Jpa Built-in findAll method to fetch all the
		// records
		List<Reimbursement> allReimbEntity = this.reimbRepositoryDao.findAll();
		List<ReimbursementPojo> allReimbPojo = new ArrayList<ReimbursementPojo>();
		/*
		 * iterating through the collection of Reimbursement entities(allReimbEntity )
		 * and copying them into a collection of reimbursement pojos (allReimbPojo)
		 */
		allReimbEntity.forEach((reimbursement) -> {
			ReimbursementPojo reimbPojo = new ReimbursementPojo(reimbursement.getReimbId(),
					reimbursement.getReimbDate(), reimbursement.getReimbReason(), reimbursement.getReimbAmount(),
					reimbursement.getReimbStatus(), reimbursement.isReimbRemoved(), reimbursement.getUserId());
			allReimbPojo.add(reimbPojo);
		});
		return allReimbPojo;
	}

	// ------ Look at SQL on reimbursementDaoImpl to convert them here

	@Override
	public List<ReimbursementPojo> getAUserReimbursementService(int userId) throws ApplicationException {
		List<Reimbursement>AllUserReimbEntity = this.reimbRepositoryDao.getUserAllReimb(userId);
		List<ReimbursementPojo> allReimbPojo = new ArrayList<ReimbursementPojo>();
		AllUserReimbEntity.forEach((reimbursement) -> {
			ReimbursementPojo reimbPojo = new ReimbursementPojo(reimbursement.getReimbId(),
					reimbursement.getReimbDate(), reimbursement.getReimbReason(), reimbursement.getReimbAmount(),
					reimbursement.getReimbStatus(), reimbursement.isReimbRemoved(), reimbursement.getUserId());
			allReimbPojo.add(reimbPojo);
		});

		 return allReimbPojo  ;
	}

	@Override
	public List<ReimbursementPojo> getAUserPendingReimbursementService(int userId) throws ApplicationException {
		List<Reimbursement>AllUserPendingReimb = this.reimbRepositoryDao.getUserPendingReimb(userId);
		List<ReimbursementPojo> allReimbPojo = new ArrayList<ReimbursementPojo>();
		AllUserPendingReimb.forEach((reimbursement) -> {
			ReimbursementPojo reimbPojo = new ReimbursementPojo(reimbursement.getReimbId(),
					reimbursement.getReimbDate(), reimbursement.getReimbReason(), reimbursement.getReimbAmount(),
					reimbursement.getReimbStatus(), reimbursement.isReimbRemoved(), reimbursement.getUserId());
			allReimbPojo.add(reimbPojo);
		});
		 return allReimbPojo;
	}

	@Override
	public List<ReimbursementPojo> getAUserResolvedReimbursementService(int userId) throws ApplicationException {
		List<Reimbursement>AllUserResolvedReimb = this.reimbRepositoryDao.getUserResolvedReimb(userId);
		List<ReimbursementPojo> allReimbPojo = new ArrayList<ReimbursementPojo>();
		AllUserResolvedReimb.forEach((reimbursement) -> {
			ReimbursementPojo reimbPojo = new ReimbursementPojo(reimbursement.getReimbId(),
					reimbursement.getReimbDate(), reimbursement.getReimbReason(), reimbursement.getReimbAmount(),
					reimbursement.getReimbStatus(), reimbursement.isReimbRemoved(), reimbursement.getUserId());
			allReimbPojo.add(reimbPojo);
		});
		return allReimbPojo;
	}

	@Override
	public List<ReimbursementPojo> getAllResolvedReimbursementService() throws ApplicationException {
		
	List<Reimbursement>AllResolvedReimb = this.reimbRepositoryDao.getAllResolvedReimbursements();
		List<ReimbursementPojo> allReimbPojo = new ArrayList<ReimbursementPojo>();
		AllResolvedReimb.forEach((reimbursement) -> {
			ReimbursementPojo reimbPojo = new ReimbursementPojo(reimbursement.getReimbId(),
					reimbursement.getReimbDate(), reimbursement.getReimbReason(), reimbursement.getReimbAmount(),
					reimbursement.getReimbStatus(), reimbursement.isReimbRemoved(), reimbursement.getUserId());
			
			allReimbPojo.add(reimbPojo);
		});
		return allReimbPojo;
	}

	@Override
	public List<ReimbursementPojo> getAllPendingReimbursementService() throws ApplicationException {
	List<Reimbursement>AllPendingReimb = this.reimbRepositoryDao.getAllPendingReimbursements();
		
		List<ReimbursementPojo> allReimbPojo = new ArrayList<ReimbursementPojo>();
		
		AllPendingReimb.forEach((reimbursement) -> {
			
			ReimbursementPojo reimbPojo = new ReimbursementPojo(reimbursement.getReimbId(),
					reimbursement.getReimbDate(), reimbursement.getReimbReason(), reimbursement.getReimbAmount(),
					reimbursement.getReimbStatus(), reimbursement.isReimbRemoved(), reimbursement.getUserId());
			
			allReimbPojo.add(reimbPojo);
		});
		return allReimbPojo;
	}
}