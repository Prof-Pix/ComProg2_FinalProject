package Loan;

import java.time.LocalDate;
import java.util.ArrayList;

public class Loan {

	private String loanId;
	private int loanRequestId;
	private int merchantId;
	private int productId;
	private int loanerId;
	private LocalDate loanStartDate;
	private float monthlyPayment;
	private int remainingMonthsToPay;
	private float remainingBalance;
	private int paidMonths;
	private float paidBalance;
	private String loanStatus;
	private ArrayList<LoanSchedule> loanSchedule;
	
	public Loan(String loanId, int loanRequestId, int merchantId, int productId, int loanerId, LocalDate loanStartDate, float monthlyPayment,
			int remainingMonthsToPay, float remainingBalance, int paidMonths, float paidBalance,
			String loanStatus, ArrayList<LoanSchedule> loanSchedule) {
		super();
		this.loanId = loanId;
		this.loanRequestId = loanRequestId;
		this.merchantId = merchantId;
		this.productId = productId;
		this.loanerId = loanerId;
		this.loanStartDate = loanStartDate;
		this.monthlyPayment = monthlyPayment;
		this.remainingMonthsToPay = remainingMonthsToPay;
		this.remainingBalance = remainingBalance;
		this.paidMonths = paidMonths;
		this.paidBalance = paidBalance;
		this.loanStatus = loanStatus;
		this.loanSchedule = loanSchedule;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getLoanerId() {
		return loanerId;
	}

	public void setLoanerId(int loanerId) {
		this.loanerId = loanerId;
	}

	public LocalDate getLoanStartDate() {
		return loanStartDate;
	}

	public void setLoan_start_date(LocalDate loanStartDate) {
		this.loanStartDate = loanStartDate;
	}

	public int getRemainingMonthsToPay() {
		return remainingMonthsToPay;
	}

	public void setRemainingMonthsToPay(int remainingMonthsToPay) {
		this.remainingMonthsToPay = remainingMonthsToPay;
	}

	public float getRemainingBalance() {
		return remainingBalance;
	}

	public void setRemainingBalance(float remainingBalance) {
		this.remainingBalance = remainingBalance;
	}

	public int getPaid_months() {
		return paidMonths;
	}

	public void setPaid_months(int paidMonths) {
		this.paidMonths = paidMonths;
	}

	public float getPaidBalance() {
		return paidBalance;
	}

	public void setPaid_balance(float paidBalance) {
		this.paidBalance = paidBalance;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public float getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(float monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	public int getLoanRequestId() {
		return loanRequestId;
	}

	public void setLoanRequestId(int loanRequestId) {
		this.loanRequestId = loanRequestId;
	}

	public int getPaidMonths() {
		return paidMonths;
	}

	public void setPaidMonths(int paidMonths) {
		this.paidMonths = paidMonths;
	}

	public ArrayList<LoanSchedule> getLoanSchedule() {
		return loanSchedule;
	}

	public void setLoanSchedule(ArrayList<LoanSchedule> loanSchedule) {
		this.loanSchedule = loanSchedule;
	}
	
	
}
