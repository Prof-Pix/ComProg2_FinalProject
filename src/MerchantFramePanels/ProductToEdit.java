package MerchantFramePanels;

import Products.Product;

public class ProductToEdit {

	static public Product productToEdit;

	static public Product getProductToEdit() {
		return productToEdit;
	}

	static public void setProductToEdit(Product productToEdit) {
		ProductToEdit.productToEdit = productToEdit;
	}
}
