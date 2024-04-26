package LoanerFramePanels;

import java.util.ArrayList;

import javax.swing.JPanel;

import Database.DatabaseManager;
import MerchantFramePanels.ProductTemplatePanel;
import Products.Product;
import javax.swing.border.LineBorder;

import AdminFramePanels.MerchantTemplatePanel;

import java.awt.Color;
import java.awt.FlowLayout;

public class MarketplacePanel extends JPanel {
	
	DatabaseManager dbManager = new DatabaseManager();
	ArrayList<Product> merchantProducts;
	
	ArrayList<Product> filteredProductList;

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public MarketplacePanel(int loanerId) {
		
		try {
			dbManager.connect();
			merchantProducts = dbManager.getAllMerchantProducts();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		this.filteredProductList = merchantProducts;
		setLayout(null);
		
		JPanel marketPlaceProductsPanel = new JPanel();
		marketPlaceProductsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		marketPlaceProductsPanel.setBorder(null);
		marketPlaceProductsPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 15, 15)); // Adjust gaps as needed
		
		marketPlaceProductsPanel.setBounds(32, 39, 1117, 578);
		add(marketPlaceProductsPanel);
		
		for(Product product: filteredProductList) {
			MarketPlaceProductTemplatePanel prodPanel = new MarketPlaceProductTemplatePanel(loanerId, product);
			marketPlaceProductsPanel.add(prodPanel);
		}
		
		marketPlaceProductsPanel.revalidate();
		marketPlaceProductsPanel.repaint();

	}
}
