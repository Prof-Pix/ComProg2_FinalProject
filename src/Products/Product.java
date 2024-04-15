package Products;

public class Product {
	
	private int merchantOwnerId;
	private String name;
	private String brand;
	private String description;
	private String specifications;
	private String condition;
	private float price;
	private int stocksAvailable;
	private String category;
	
	public Product(int merchantOwnerId, String name, String brand, String description, String specifications,
			String condition, float price, int stocksAvailable, String category) {
		super();
		this.merchantOwnerId = merchantOwnerId;
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.specifications = specifications;
		this.condition = condition;
		this.price = price;
		this.stocksAvailable = stocksAvailable;
		this.category = category;
	}
	
	public int getMerchantOwnerId() {
		return merchantOwnerId;
	}
	public void setMerchantOwnerId(int merchantOwnerId) {
		this.merchantOwnerId = merchantOwnerId;
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
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
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
	
	
}
