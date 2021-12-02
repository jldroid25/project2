package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.ApplicationException;
import pojo.UserPojo;

public class UserDaoImpl implements UserDao {
	private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

	@Override
	public UserPojo addUser(UserPojo userInfo) throws ApplicationException {
		logger.info("Entered registerUser() is located in dao.");
		userInfo.setUserRemoved(false);

		Connection connection = DbUtil.makeConnection();
		try {
			Statement statement = connection.createStatement();

			String query = "insert into user_site_data(firstname, lastname, email, "
					+ "username, password, access_level, user_removed ) " + "values( '" + userInfo.getFirstname()
					+ " ', '" + userInfo.getLastname() + "', '" + userInfo.getEmail() + "', '" + userInfo.getUsername()
					+ "',  " + "'" + userInfo.getPassword() + "', '" + userInfo.getAccessLevel() + "'," + " "
					+ userInfo.isUserRemoved() + ") returning user_id ";
			ResultSet rs = statement.executeQuery(query);
			rs.next();
			userInfo.setUserId(rs.getInt(1));
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}

		logger.info("Left registerUser() is located in dao.");
		return userInfo;
	}

	@Override
	public UserPojo getUser(int userId) throws ApplicationException {
		logger.info("Entered  logingUser() is located in dao.");
		Connection connection = DbUtil.makeConnection();
		Statement statement;
		UserPojo userinformation = null;
		try {
			statement = connection.createStatement();
			String query = "SELECT * FROM user_site_data where user_id=" + userId + "AND user_removed=false";
			ResultSet rs = statement.executeQuery(query);
			if (rs.next()) {
				userinformation = new UserPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getBoolean(8));
			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}

		logger.info("Left  logingUser() is located in dao.");
		return userinformation;
	}

	@Override
	public UserPojo updateUser(UserPojo userInfo) throws ApplicationException {
		logger.info("Entered updateUser() is located in dao.");
		Connection connection = DbUtil.makeConnection();
		try {
			Statement statement = connection.createStatement();
			String query = "  update user_site_data set  password=" + userInfo.getPassword() + "WHERE user_id="
					+ userInfo.getUserId();

		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		logger.info("Left  updateUser() is located in dao.");
		return userInfo;
	}

	@Override
	public boolean deleteUser(int userId) throws ApplicationException {
		logger.info("Entered  deleteUser() is located in dao.");

		Connection connection = DbUtil.makeConnection();
		int rowsAffected = 0;
		try {
			Statement statement = connection.createStatement();
			String query = "update user_site_data  set user_removed=true WHERE user_id=" + userId;
			rowsAffected = statement.executeUpdate(query);
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		logger.info("Left  deleteUser() is located in dao.");

		return false;
	}

	@Override
	public List<UserPojo> retrieveAllUsers() throws ApplicationException {

		logger.info("Entered  retrieveAllUsers() is located in dao.");

		List<UserPojo> allusers = new ArrayList<UserPojo>();

		Connection connection = DbUtil.makeConnection();
		Statement statement;

		try {
			statement = connection.createStatement();

			String query = "select *  from  user_site_data where user_removed=false ORDER BY user_id asc";

			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				UserPojo userinformationPojo = new UserPojo(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getBoolean(8));

				allusers.add(userinformationPojo);
			}

		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}

		logger.info("Left  retrieveAllUsers() is located in dao.");
		return allusers;
	}

	@Override
	public UserPojo retrieveAUser(int userId) throws ApplicationException {

		logger.info("Entered  retrieveAUser() is located in dao.");
		Statement statement;
		UserPojo userInformationPojo = null;
		try {
			Connection connection = DbUtil.makeConnection();
			statement = connection.createStatement();

			String query = " select * from user_site_data where user_id=" + userId + "and user_removed=false";

			ResultSet rs = statement.executeQuery(query);
			if (rs.next()) {
				userInformationPojo = new UserPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getBoolean(8));
			}

		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}

		logger.info("Left  retrieveAUser() is located in dao.");

		return null;
	}

	@Override
	public void exitApplication() {
		logger.info("Entering This  exitApplication() located in dao .");
		DbUtil.closeConnection();
		logger.info("Exited exitApplication() in dao.");

	}
}
