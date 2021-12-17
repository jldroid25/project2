package com.expenseapp.expenseapp.pojo;

import java.util.Date;

public class ReimbursementPojo {
	private int reimbId;
	private Date reimbDate;
	private String reimbReason;
	private float reimbAmount;
	private String reimbStatus;
	boolean reimbRemoved;
	private int userId; 

	public ReimbursementPojo() {
		super();
	}

	
	public ReimbursementPojo(int reimbId, Date reimbDate, String reimbReason, float reimbAmount, String reimbStatus,
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
