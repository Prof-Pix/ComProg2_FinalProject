package Products;

public class ProductLoan {
	private int monthsToPay;
	private float interestRate;
	
	public ProductLoan(int monthsToPay, float interestRate) {
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
