package com.expenseapp.expenseapp.pojo;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ReimbursementPojo {
	private int reimbId;
	@NotNull
	private Date reimbDate;
	@NotNull
	private String reimbReason;
	@Min(0)
	private float reimbAmount;
	@NotNull
	private String reimbStatus;
	boolean reimbRemoved;
	private int userId; 

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

}