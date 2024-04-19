package Products;

import java.util.ArrayList;

public class Product {
	
	private int merchantOwnerId;
	private String productImagePath;
	private String name;
	private String brand;
	private String description;
	private String specifications;
	private float price;
	private int stocksAvailable;
	private String category;
	private ArrayList<ProductLoanTerm> productLoans;
	
	public Product(int merchantOwnerId, String productImagePath, String name, String brand, String description, String specifications,
		 float price, int stocksAvailable, String category, ArrayList<ProductLoanTerm> productLoans) {
		super();
		this.merchantOwnerId = merchantOwnerId;
		this.productImagePath = productImagePath;
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.specifications = specifications;
		this.price = price;
		this.stocksAvailable = stocksAvailable;
		this.category = category;
		this.productLoans = productLoans;
	}
	
	public int getMerchantOwnerId() {
		return merchantOwnerId;
	}
	public void setMerchantOwnerId(int merchantOwnerId) {
		this.merchantOwnerId = merchantOwnerId;
	}
	
	public String getProductImagePath() {
		return productImagePath;
	}

	public void setProductImagePath(String productImagePath) {
		this.productImagePath = productImagePath;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getStocksAvailable() {
		return stocksAvailable;
	}
	public void setStocksAvailable(int stocksAvailable) {
		this.stocksAvailable = stocksAvailable;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public ArrayList<ProductLoanTerm> getProductLoans() {
		return productLoans;
	}

	public void setProductLoans(ArrayList<ProductLoanTerm> productLoans) {
		this.productLoans = productLoans;
	}

	public boolean isProductInputValid() {
		return isStocksAvailableValid() && isPriceValid();
		
	}
	
	//For Validations
	private boolean isStocksAvailableValid() {
		return stocksAvailable > 0;
	}
	
	private boolean isPriceValid() {
		return price > 0;
	}
	
}
