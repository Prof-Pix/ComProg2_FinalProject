package Products;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Product {
	
	private int productId;
	private int merchantOwnerId;
	private String merchantName;
	private ImageIcon productImage;
	private String imageFilePath;
	private String name;
	private String brand;
	private String description;
	private String specifications;
	private float price;
	private int stocksAvailable;
	private String category;
	private ArrayList<ProductLoanTerm> productLoans;
	
	//All attributes constructor
	public Product(int productId, int merchantOwnerId, String merchantName, ImageIcon productImage,
			 String name, String brand, String description, String specifications, float price,
			int stocksAvailable, String category, ArrayList<ProductLoanTerm> productLoans) {
		super();
		this.productId = productId;
		this.merchantOwnerId = merchantOwnerId;
		this.merchantName = merchantName;
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

	public Product(int merchantOwnerId, String imageFilePath, String name, String brand, String description, String specifications,
		 float price, int stocksAvailable, String category, ArrayList<ProductLoanTerm> productLoans) {
		super();
		this.merchantOwnerId = merchantOwnerId;
		this.imageFilePath = imageFilePath;
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.specifications = specifications;
		this.price = price;
		this.stocksAvailable = stocksAvailable;
		this.category = category;
		this.productLoans = productLoans;
	}
	
	public Product(int merchantOwnerId, ImageIcon productImage, String name, String brand, String description, String specifications,
			 float price, int stocksAvailable, String category, ArrayList<ProductLoanTerm> productLoans) {
			super();
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
	
	public Product(int merchantOwnerId, String merchantName, ImageIcon productImage, String name, String brand, String description, String specifications,
			 float price, int stocksAvailable, String category, ArrayList<ProductLoanTerm> productLoans) {
			super();
			this.merchantOwnerId = merchantOwnerId;
			this.merchantName = merchantName;
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
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getMerchantOwnerId() {
		return merchantOwnerId;
	}
	public void setMerchantOwnerId(int merchantOwnerId) {
		this.merchantOwnerId = merchantOwnerId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getImageFilePath() {
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}

	public ImageIcon getProductImage() {
		return productImage;
	}

	public void setProductImage(ImageIcon productImage) {
		this.productImage = productImage;
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
