package Loan;

import java.time.LocalDate;

public class LoanSchedule {
	
	private int loanScheduleId;
	private String loanId;
	private int loanMonth;
	private LocalDate loanScheduleDate;
	private float loanScheduleAmount;
	private LocalDate loanPaidDate;
	private float loanPenalty;
	private String status;
	private boolean isDue;
	
	public LoanSchedule(int loanScheduleId, String loanId, int loanMonth, LocalDate loanScheduleDate,
			float loanScheduleAmount, LocalDate loanPaidDate, float loanPenalty, String status, boolean isDue) {
		super();
		this.loanScheduleId = loanScheduleId;
		this.loanId = loanId;
		this.loanMonth = loanMonth;
		this.loanScheduleDate = loanScheduleDate;
		this.loanScheduleAmount = loanScheduleAmount;
		this.loanPaidDate = loanPaidDate;
		this.loanPenalty = loanPenalty;
		this.status = status;
		this.isDue = isDue;
	}
	
	public int getLoanScheduleId() {
		return loanScheduleId;
	}

	public void setLoanScheduleId(int loanScheduleId) {
		this.loanScheduleId = loanScheduleId;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public int getLoanMonth() {
		return loanMonth;
	}

	public void setLoanMonth(int loanMonth) {
		this.loanMonth = loanMonth;
	}

	public LocalDate getLoanScheduleDate() {
		return loanScheduleDate;
	}

	public void setLoanScheduleDate(LocalDate loanScheduleDate) {
		this.loanScheduleDate = loanScheduleDate;
	}

	public float getLoanScheduleAmount() {
		return loanScheduleAmount;
	}

	public void setLoanScheduleAmount(float loanScheduleAmount) {
		this.loanScheduleAmount = loanScheduleAmount;
	}

	public LocalDate getLoanPaidDate() {
		return loanPaidDate;
	}

	public void setLoanPaidDate(LocalDate loanPaidDate) {
		this.loanPaidDate = loanPaidDate;
	}

	public float getLoanPenalty() {
		return loanPenalty;
	}

	public void setLoanPenalty(float loanPenalty) {
		this.loanPenalty = loanPenalty;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isDue() {
		return isDue;
	}

	public void setDue(boolean isDue) {
		this.isDue = isDue;
	}

}
