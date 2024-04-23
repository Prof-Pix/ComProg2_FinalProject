package LoanRequest;

import java.time.LocalDate;

public class LoanRequest {
	
	private int merchantId;
	private int loanerId;
	private int productId;
	private String loanedProductName;
	private float loanedProductPrice;
	private int loanedProductMonthsToPay;
	private float loanedProductInterestRate;
	private String loanerName;
	private LocalDate loanRequestDate;
	private LocalDate loanApproveDate;
	private LocalDate loanRejectDate;
	private boolean isPending;
	private boolean isRejected;
	private boolean isApproved;
	
	
	//For Approved/Reject
	public LoanRequest(int merchantId, int loanerId, int productId, String loanedProductName, float loanedProductPrice,
			int loanedProductMonthsToPay, float loanedProductInterestRate, String loanerName, LocalDate loanRequestDate,
			LocalDate loanApproveDate, LocalDate loanRejectDate, boolean isPending, boolean isRejected,
			boolean isApproved) {
		super();
		this.merchantId = merchantId;
		this.loanerId = loanerId;
		this.productId = productId;
		this.loanedProductName = loanedProductName;
		this.loanedProductPrice = loanedProductPrice;
		this.loanedProductMonthsToPay = loanedProductMonthsToPay;
		this.loanedProductInterestRate = loanedProductInterestRate;
		this.loanerName = loanerName;
		this.loanRequestDate = loanRequestDate;
		this.loanApproveDate = loanApproveDate;
		this.loanRejectDate = loanRejectDate;
		this.isPending = isPending;
		this.isRejected = isRejected;
		this.isApproved = isApproved;
	}
	
	//For Pending
	public LoanRequest(int merchantId, int loanerId, int productId, String loanedProductName, float loanedProductPrice,
			int loanedProductMonthsToPay, float loanedProductInterestRate, String loanerName, LocalDate loanRequestDate,
			boolean isPending) {
		this.merchantId = merchantId;
		this.loanerId = loanerId;
		this.productId = productId;
		this.loanedProductName = loanedProductName;
		this.loanedProductPrice = loanedProductPrice;
		this.loanedProductMonthsToPay = loanedProductMonthsToPay;
		this.loanedProductInterestRate = loanedProductInterestRate;
		this.loanerName = loanerName;
		this.loanRequestDate = loanRequestDate;
		this.isPending = isPending;
	}
	

	public int getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}
	public int getLoanerId() {
		return loanerId;
	}
	public void setLoanerId(int loanerId) {
		this.loanerId = loanerId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getLoanedProductName() {
		return loanedProductName;
	}
	public void setLoanedProductName(String loanedProductName) {
		this.loanedProductName = loanedProductName;
	}
	public float getLoanedProductPrice() {
		return loanedProductPrice;
	}
	public void setLoanedProductPrice(float loanedProductPrice) {
		this.loanedProductPrice = loanedProductPrice;
	}
	public int getLoanedProductMonthsToPay() {
		return loanedProductMonthsToPay;
	}
	public void setLoanedProductMonthsToPay(int loanedProductMonthsToPay) {
		this.loanedProductMonthsToPay = loanedProductMonthsToPay;
	}
	public float getLoanedProductInterestRate() {
		return loanedProductInterestRate;
	}
	public void setLoanedProductInterestRate(float loanedProductInterestRate) {
		this.loanedProductInterestRate = loanedProductInterestRate;
	}
	public String getLoanerName() {
		return loanerName;
	}
	public void setLoanerName(String loanerName) {
		this.loanerName = loanerName;
	}
	public LocalDate getLoanRequestDate() {
		return loanRequestDate;
	}
	public void setLoanRequestDate(LocalDate loanRequestDate) {
		this.loanRequestDate = loanRequestDate;
	}
	public LocalDate getLoanApproveDate() {
		return loanApproveDate;
	}
	public void setLoanApproveDate(LocalDate loanApproveDate) {
		this.loanApproveDate = loanApproveDate;
	}
	public LocalDate getLoanRejectDate() {
		return loanRejectDate;
	}
	public void setLoanRejectDate(LocalDate loanRejectDate) {
		this.loanRejectDate = loanRejectDate;
	}
	public boolean isPending() {
		return isPending;
	}
	public void setPending(boolean isPending) {
		this.isPending = isPending;
	}
	public boolean isRejected() {
		return isRejected;
	}
	public void setRejected(boolean isRejected) {
		this.isRejected = isRejected;
	}
	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
}
