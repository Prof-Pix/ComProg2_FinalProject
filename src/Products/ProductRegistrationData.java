package Products;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class ProductRegistrationData extends Product{


	private int merchantOwnerId;
	private ImageIcon productImage;
	private String name;
	private String brand;
	private String description;
	private String specifications;
	private float price;
	private int stocksAvailable;
	private String category;
	private ArrayList<ProductLoanTerm> productLoans;
	
	public ProductRegistrationData(int merchantOwnerId, ImageIcon productImage, String name, String brand,
			String description, String specifications, float price, int stocksAvailable, String category,
			ArrayList<ProductLoanTerm> productLoans) {
		super(merchantOwnerId, productImage, name, brand, description, specifications, price, stocksAvailable, category,
				productLoans);
		this.merchantOwnerId = merchantOwnerId;
		this.productImage = productImage;
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
