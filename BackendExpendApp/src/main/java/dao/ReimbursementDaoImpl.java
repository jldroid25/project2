package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.ApplicationException;
import pojo.ReimbursementPojo;
import pojo.UserPojo;

public class ReimbursementDaoImpl implements ReimbursementDao {
	private static final Logger logger = LogManager.getLogger(ReimbursementDaoImpl.class);

	@Override
	public ReimbursementPojo createReimbursement(ReimbursementPojo reimbursementPojo) throws ApplicationException {
		logger.info("Entered createReimbursment() is located in dao.");
		reimbursementPojo.setReimbRemoved(false);
		reimbursementPojo.setReimbStatus("Pending");
		UserPojo userPojo = new UserPojo();

		Connection connection = DbUtil.makeConnection();
		try {
			Statement statement = connection.createStatement();
			String query = "insert into reimb_info(rb_reason, rb_amount, rb_status, user_id, reimb_removed)" + " values('"
					+ reimbursementPojo.getReimbReason() + "',  '" + reimbursementPojo.getReimbAmount() + "',  '"
					+ reimbursementPojo.getReimbStatus() + "', '"  + reimbursementPojo.getUserId() +  "',   " + reimbursementPojo.isReimbRemoved() +  " ) returning  rb_id";

			ResultSet rs = statement.executeQuery(query);
			rs.next();
			
			reimbursementPojo.setReimbId(rs.getInt(1));

		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		logger.info("Left createReimbursement() located  in dao.");
		return reimbursementPojo;
	}

	@Override
	public ReimbursementPojo updateReimbursement(ReimbursementPojo reimbursementPojo) throws ApplicationException {
		logger.info("Entered updateReimbursment() is located in dao.");
		Connection connection = DbUtil.makeConnection();

		try {
			Statement statement = connection.createStatement();
			

			String query = "update reimb_info  set  rb_status= ' " +reimbursementPojo.getReimbStatus()+" '  where rb_id= "+reimbursementPojo.getReimbId();

			int rowsAffected = statement.executeUpdate(query);

		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		logger.info("Left  updateReimbursment() is located in dao.");
		return reimbursementPojo;
	}

	@Override
	public boolean deleteReimbursement(int reimbursementId) throws ApplicationException {
		logger.info("Entered  deleteReimbursment() is located in dao.");
		Connection connection = DbUtil.makeConnection();
		int rowsAffected = 0;
		try {
			Statement statement = connection.createStatement();
			String query = "update reimb_info set reimb_removed=true WHERE rb_id=" + reimbursementId;
			rowsAffected = statement.executeUpdate(query);

		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		logger.info("Left  deleteReimbursment() is located in dao.");
		return false;
	}

	@Override
	public ReimbursementPojo getAReimbursement(int reimbursementId) throws ApplicationException {
		logger.info("Entered  retrieveReimbursment() is located in dao.");
		Connection connection = DbUtil.makeConnection();
		Statement statement;
		ReimbursementPojo reimbursementPojo = null;

		try {
			statement = connection.createStatement();
			String query = "SELECT * FROM reimb_info where rb_id=" + reimbursementId + "AND reimb_removed=false";

			ResultSet rs = statement.executeQuery(query);
			if (rs.next()) {
				reimbursementPojo = new ReimbursementPojo(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getFloat(4),
						rs.getString(5), rs.getBoolean(6), rs.getInt(7));
			}
		} catch (SQLException e) {
			// Throw the application lException because we don't want JDBC exception
			// to cross the DAO layer
			throw new ApplicationException(e.getMessage());
		}
		logger.info("Left  retrieveReimbursment() is located in dao.");
		return reimbursementPojo;
	}

	@Override
	public List<ReimbursementPojo> getAllReimbursements() throws ApplicationException {
		logger.info("Entered retrieveAllReimbursements() located  in dao.");

		List<ReimbursementPojo> allReimbursements = new ArrayList<ReimbursementPojo>();
		
		Connection connection = DbUtil.makeConnection();
		Statement statement;
		try {
			statement = connection.createStatement();

			String query = "select * from reimb_info WHERE reimb_removed=false ORDER BY rb_id asc";

			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {

				ReimbursementPojo reimbursementPojo = new ReimbursementPojo(rs.getInt(1),
						new Date(rs.getDate(2).getTime()),rs.getString(3), rs.getFloat(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7));
				allReimbursements.add(reimbursementPojo);

			}
		} catch (SQLException e) {

			throw new ApplicationException(e.getMessage());
		}
		logger.info("Left retrieveAllReimbursements() located  in dao.");
		return allReimbursements;
	}

	
	//---------------------newly created  Reimb queries 

	@Override
	public List<ReimbursementPojo> getAUserReimbursement(int userId) throws ApplicationException {
		logger.info("Entered getAUserReimbursement() located  in dao.");
		
		List<ReimbursementPojo> userReimbs = new ArrayList<ReimbursementPojo>();
		
		Connection connection = DbUtil.makeConnection();
		
		Statement statement;
		try {
			statement = connection.createStatement();
			
			//String query = "select * from reimb_info  where user_id="+userId;
			String query = "SELECT * from reimb_info WHERE reimb_removed=false and user_id="+userId;
			
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {

				ReimbursementPojo reimbursementPojo = new ReimbursementPojo(rs.getInt(1),
						new Date(rs.getDate(2).getTime()),rs.getString(3), rs.getFloat(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7));
				userReimbs.add(reimbursementPojo);
			}

		}catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		
		logger.info("Left getAUserReimbursement() located  in dao.");
		
		return userReimbs;
	}
	

	@Override
	public List<ReimbursementPojo> getAUserPendingReimbursement(int userId) throws ApplicationException {
	logger.info("Entered getAUserPendingReimbursement() located  in dao.");
		
		List<ReimbursementPojo> userReimbs = new ArrayList<ReimbursementPojo>();
		
		Connection connection = DbUtil.makeConnection();
		
		Statement statement;
		try {
			statement = connection.createStatement();
			
			String query = "SELECT * from reimb_info  WHERE reimb_removed=false  and user_id="+userId+" and  rb_status='Pending' ";
			
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {

				ReimbursementPojo reimbursementPojo = new ReimbursementPojo(rs.getInt(1),
						new Date(rs.getDate(2).getTime()),rs.getString(3), rs.getFloat(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7));
				userReimbs.add(reimbursementPojo);
			}

		}catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		
		logger.info("Left getAUserPendingReimbursement() located  in dao.");
		
		return userReimbs;
	}

	@Override
	public List<ReimbursementPojo> getAUserResolvedReimbursement(int userId) throws ApplicationException {
	logger.info("Entered getAUseResolvedReimbursement() located  in dao.");
		
		List<ReimbursementPojo> userReimbs = new ArrayList<ReimbursementPojo>();
		
		Connection connection = DbUtil.makeConnection();
		
		Statement statement;
		try {
			statement = connection.createStatement();
			//			String query = " select * from reimb_info where user_id= "+userId+" and rb_status ='Approved' ";
			String query = " select * from reimb_info where reimb_removed=false and   user_id= "+userId+" and rb_status ='Approved'  or rb_status='Denied' ";
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {

				ReimbursementPojo reimbursementPojo = new ReimbursementPojo(rs.getInt(1),
						new Date(rs.getDate(2).getTime()),rs.getString(3), rs.getFloat(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7));
				userReimbs.add(reimbursementPojo);
			}

		}catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		
		logger.info("Left getAUserResolvedReimbursement() located  in dao.");
		
		return userReimbs;
	}

	@Override
	public List<ReimbursementPojo> getAllResolvedReimbursement() throws ApplicationException {
	logger.info("Entered getAllResolvedReimbursement() located  in dao.");
		
		List<ReimbursementPojo> userReimbs = new ArrayList<ReimbursementPojo>();
		
		Connection connection = DbUtil.makeConnection();
		
		Statement statement;
		try {
			statement = connection.createStatement();
			//String query = " select * from  reimb_info where rb_status='Approved' ";
			String query = " select * from  reimb_info  where reimb_removed=false and  rb_status='Approved' or rb_status='Denied' ";
			
			ResultSet rs = statement.executeQuery(query);
		
			
			while (rs.next()) {

				ReimbursementPojo reimbursementPojo = new ReimbursementPojo(rs.getInt(1),
						new Date(rs.getDate(2).getTime()),rs.getString(3), rs.getFloat(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7));
				userReimbs.add(reimbursementPojo);
			}
			

		}catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		
		logger.info("Left getAllResolvedReimbursement() located  in dao.");
		
		return userReimbs;
	}

	@Override
	public List<ReimbursementPojo> getAllPendingReimbursement() throws ApplicationException {
	logger.info("Entered getAllPendingReimbursement() located  in dao.");
		
		List<ReimbursementPojo> userReimbs = new ArrayList<ReimbursementPojo>();
		
		Connection connection = DbUtil.makeConnection();
		
		Statement statement;
		try {
			statement = connection.createStatement();
			//String query = "select * from reimb_info where  rb_status='Pending'";
			String query = "select * from reimb_info where reimb_removed=false and  rb_status='Pending' ";
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {

				ReimbursementPojo reimbursementPojo = new ReimbursementPojo(rs.getInt(1),
						new Date(rs.getDate(2).getTime()),rs.getString(3), rs.getFloat(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7));
				userReimbs.add(reimbursementPojo);
			}

		}catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		
		logger.info("Left getAllPendingReimbursement() located  in dao.");
		
		return userReimbs;
	}
	
	@Override
	public void exitApplication() {
		logger.info("Entering This  exitApplication() located in dao .");
		DbUtil.closeConnection();
		logger.info("Exited exitApplication() in dao.");
	}

}
