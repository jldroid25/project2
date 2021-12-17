package com.expenseapp.expenseapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expenseapp.expenseapp.entity.User;

@Repository
public interface UserRepositoryDao extends JpaRepository<User, Integer>{
	
	//--------Add more if needed 

}