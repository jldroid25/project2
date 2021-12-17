package com.expenseapp.expenseapp.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

//Add The mandatory @Entity annotation
@Entity

/*
 * Provide the database table for the data
 * Adding the  annotation Table step  is optional if the entity class names and the table name match.
 * However, it is preferred  to specify it even if Entity & table names in db  match.
 * 
 * */
@Table(name="reimb_info")
public class Reimbursement {
	// Hibernate expects us to specify the primary key column
	@Id
	
	// we specify the primary key auto generation strategy used in the underlying DB 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
    // @Column  is optional if the variable name and the  column  names match.
	// However, it is always preferred  to specify  them even if variable & column names in db  match.
	@Column(name="rb_id")
	private int reimbId;

	@Column(name="rb_date", columnDefinition = "DATE DEFAULT CURRENT_DATE")
	private Date reimbDate;
	
	@Column(name="rb_reason")
	private String reimbReason;
	
	@Column(name="rb_amount")
	private float reimbAmount;
	
	@Column(name="rb_status")
	private String reimbStatus;
	
	@Column(name="reimb_removed")
	boolean reimbRemoved;
	
	@Column(name="user_id")
	private int userId; 

	// hibernate expects a default constructor for your entity class
	public Reimbursement() {
		super();
	}

	
	public Reimbursement(int reimbId, Date reimbDate, String reimbReason, float reimbAmount, String reimbStatus,
			boolean reimbRemoved, int userId) {
		super();
		this.reimbId = reimbId;
		this.reimbDate = reimbDate;
		this.reimbReason = reimbReason;
		this.reimbAmount = reimbAmount;
		this.reimbStatus = reimbStatus;
		this.reimbRemoved = reimbRemoved;
		this.userId = userId;
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public Date getReimbDate() {
		return reimbDate;
	}

	public void setReimbDate(Date reimbDate) {
		this.reimbDate = reimbDate;
	}

	public String getReimbReason() {
		return reimbReason;
	}

	public void setReimbReason(String reimbReason) {
		this.reimbReason = reimbReason;
	}

	public float getReimbAmount() {
		return reimbAmount;
	}

	public void setReimbAmount(float reimbAmount) {
		this.reimbAmount = reimbAmount;
	}

	public String getReimbStatus() {
		return reimbStatus;
	}

	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}

	public boolean isReimbRemoved() {
		return reimbRemoved;
	}

	public void setReimbRemoved(boolean reimbRemoved) {
		this.reimbRemoved = reimbRemoved;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	
	@Override
	public String toString() {
		return "ReimbursementPojo [reimbId=" + reimbId + ", reimbDate=" + reimbDate + ", reimbReason=" + reimbReason
				+ ", reimbAmount=" + reimbAmount + ", reimbStatus=" + reimbStatus + ", reimbRemoved=" + reimbRemoved
				+ ", userId=" + userId + "]";
	}
	
}
