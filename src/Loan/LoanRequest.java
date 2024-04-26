package Loan;

import java.time.LocalDate;

import javax.swing.ImageIcon;

import Products.Product;
import User.Loaner;
import User.Merchant;

public class LoanRequest {
	
	private int loanRequestId;
	private int merchantId;
	private int loanerId;
	private int productId;
	
	private Merchant merchantLoanData;
	private Product productToLoanData;
	private Loaner loanerLoanData;
	
	private int loanedProductMonthsToPay;
	private float loanedProductInterestRate;

	private LocalDate loanRequestDate;
	private LocalDate loanApproveDate;
	private LocalDate loanRejectDate;
	
	private boolean isPending;
	private boolean isRejected;
	private boolean isApproved;
	
	private boolean isDownPaymentPending;
	private boolean isDownPaymentPaid;
	private boolean isCancelled;
	
//	private String merchantName;
//	private String loanedProductName;
//	private String loanedProductBrand;
//	private ImageIcon loanedProductPicture;
//	private float loanedProductPrice;
//	private String loanerName;
	
	//Auto-compute on each constructor
	private float downPaymentAmount;
	private float monthlyPayment;

		
	//For Payment/
	public LoanRequest(int loanRequestId, int merchantId, 
			int loanerId, 
			int productId, 
			Merchant merchantLoanData, 
			Product productToLoanData, 
			Loaner loanerLoanData , 
			int monthsToPay, 
			float interestRate, 
			LocalDate loanRequestDate,
			LocalDate loanApproveDate, 
			LocalDate loanRejectDate, 
			boolean isPending, 
			boolean isRejected,
			boolean isApproved,
			boolean isDownPaymentPending,
			boolean isDownPaymentPaid,
			boolean isCancelled) {

		this.loanRequestId = loanRequestId;
		this.merchantId = merchantId;
		this.loanerId = loanerId;
		this.productId = productId;
		this.merchantLoanData = merchantLoanData;
		this.productToLoanData = productToLoanData;
		this.loanerLoanData = loanerLoanData;
		this.loanedProductMonthsToPay = monthsToPay;
		this.loanedProductInterestRate = interestRate;
		this.loanRequestDate = loanRequestDate;
		this.loanApproveDate = loanApproveDate;
		this.loanRejectDate = loanRejectDate;
		this.isPending = isPending;
		this.isRejected = isRejected;
		this.isApproved = isApproved;
		this.isDownPaymentPending = isDownPaymentPending;
		this.isDownPaymentPaid = isDownPaymentPaid;
		this.isCancelled = isCancelled;
		
		//20% of the product price
		this.downPaymentAmount = (float) (productToLoanData.getPrice() * 0.20);
		//Price - downpayment divide by months multiply by interest rate 
		this.monthlyPayment = ((productToLoanData.getPrice() - downPaymentAmount) / loanedProductMonthsToPay)  + (((productToLoanData.getPrice() - downPaymentAmount) / loanedProductMonthsToPay) *  loanedProductInterestRate);
		
	}
	
	//For Pending
	public LoanRequest(int merchantId, int loanerId, int productId, Merchant merchantLoanData, Product productToLoanData, Loaner loanerLoanData , int monthsToPay, float interestRate, LocalDate loanRequestDate,
			boolean isPending) {
		this.merchantId = merchantId;
		this.loanerId = loanerId;
		this.productId = productId;
		this.merchantLoanData = merchantLoanData;
		this.productToLoanData = productToLoanData;
		this.loanerLoanData = loanerLoanData;
		this.loanedProductMonthsToPay = monthsToPay;
		this.loanedProductInterestRate = interestRate;
		this.loanRequestDate = loanRequestDate;
		this.isPending = isPending;
	}
	
	public int getLoanRequestId() {
		return loanRequestId;
	}

	public void setLoanRequestId(int loanRequestId) {
		this.loanRequestId = loanRequestId;
	}

	//For downpayment
	public float getDownPayment() {
		return downPaymentAmount;
	}
	
	public void setDownPayment(float downPaymentAmount) {
		this.downPaymentAmount = downPaymentAmount;
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
//	public String getLoanedProductName() {
//		return loanedProductName;
//	}
//	public void setLoanedProductName(String loanedProductName) {
//		this.loanedProductName = loanedProductName;
//	}
//	public float getLoanedProductPrice() {
//		return loanedProductPrice;
//	}
//	public void setLoanedProductPrice(float loanedProductPrice) {
//		this.loanedProductPrice = loanedProductPrice;
//	}
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
//	public String getLoanerName() {
//		return loanerName;
//	}
//	public void setLoanerName(String loanerName) {
//		this.loanerName = loanerName;
//	}
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

//	public String getMerchantName() {
//		return merchantName;
//	}
//
//	public void setMerchantName(String merchantName) {
//		this.merchantName = merchantName;
//	}
//
//	public String getLoanedProductBrand() {
//		return loanedProductBrand;
//	}
//
//	public void setLoanedProductBrand(String loanedProductBrand) {
//		this.loanedProductBrand = loanedProductBrand;
//	}
//
//	public ImageIcon getLoanedProductPicture() {
//		return loanedProductPicture;
//	}
//
//	public void setLoanedProductPicture(ImageIcon loanedProductPicture) {
//		this.loanedProductPicture = loanedProductPicture;
//	}

	public float getDownPaymentAmount() {
		return downPaymentAmount;
	}

	public void setDownPaymentAmount(float downPaymentAmount) {
		this.downPaymentAmount = downPaymentAmount;
	}

	public float getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(float monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	public Merchant getMerchantLoanData() {
		return merchantLoanData;
	}

	public void setMerchantLoanData(Merchant merchantLoanData) {
		this.merchantLoanData = merchantLoanData;
	}

	public Product getProductToLoanData() {
		return productToLoanData;
	}

	public void setProductToLoanData(Product productToLoanData) {
		this.productToLoanData = productToLoanData;
	}

	public Loaner getLoanerLoanData() {
		return loanerLoanData;
	}

	public void setLoanerLoanData(Loaner loanerLoanData) {
		this.loanerLoanData = loanerLoanData;
	}

	public boolean isDownPaymentPending() {
		return isDownPaymentPending;
	}

	public void setDownPaymentPending(boolean isDownPaymentPending) {
		this.isDownPaymentPending = isDownPaymentPending;
	}

	public boolean isDownPaymentPaid() {
		return isDownPaymentPaid;
	}

	public void setDownPaymentPaid(boolean isDownPaymentPaid) {
		this.isDownPaymentPaid = isDownPaymentPaid;
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}
}
