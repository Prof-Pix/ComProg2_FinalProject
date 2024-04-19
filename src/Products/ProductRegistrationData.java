package Products;

import java.util.ArrayList;

public class ProductRegistrationData extends Product{


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
	
	public ProductRegistrationData(int merchantOwnerId, String productImagePath, String name, String brand,
			String description, String specifications, float price, int stocksAvailable, String category,
			ArrayList<ProductLoanTerm> productLoans) {
		super(merchantOwnerId, productImagePath, name, brand, description, specifications, price, stocksAvailable, category,
				productLoans);
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

}
