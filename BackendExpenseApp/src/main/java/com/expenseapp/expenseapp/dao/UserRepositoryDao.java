package com.expenseapp.expenseapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.expenseapp.expenseapp.entity.User;
import com.expenseapp.expenseapp.exception.ApplicationException;
import com.expenseapp.expenseapp.pojo.UserPojo;

@Repository
public interface UserRepositoryDao extends JpaRepository<User, Integer>{
	
	//--------Add more if needed 
		
	// Similar to Select * from user_site_data where user_removed=false
    //@Query("update  User  set user_removed=true")
	//public void softUserDelete(int userId); 

}