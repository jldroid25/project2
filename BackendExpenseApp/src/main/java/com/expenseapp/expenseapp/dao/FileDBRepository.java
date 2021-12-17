package com.expenseapp.expenseapp.dao;

import com.expenseapp.expenseapp.entity.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

   public  Optional<FileDB> findByRid(int rid);

}
