package Products;

public class ProductLoanTerm {
	private int monthsToPay;
	private float interestRate;
	
	public ProductLoanTerm(int monthsToPay, float interestRate) {
		super();
		this.monthsToPay = monthsToPay;
		this.interestRate = interestRate;
	}
	
	public int getMonthsToPay() {
		return monthsToPay;
	}
	public void setMonthsToPay(int monthsToPay) {
		this.monthsToPay = monthsToPay;
	}
	public float getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}
	
}
