package MerchantFramePanels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.border.LineBorder;

import Database.DatabaseManager;
import Products.Product;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JSeparator;
import javax.swing.JScrollPane;

public class ViewProductsPanel extends JPanel{
	
	DatabaseManager dbManager = new DatabaseManager();
	ArrayList<Product> merchantProducts;
	
	ArrayList<Product> filteredProductList;
	
	JComboBox sortByComboBox;
	JComboBox filterCategComboBox;
	JPanel productsPanel;
	
	static int MERCHANT_ID;
	
	public ViewProductsPanel(int merchantId) {
		setBackground(new Color(30, 66, 94));
		ViewProductsPanel.MERCHANT_ID = merchantId;
		//Establish a connection
		try {
			dbManager.connect();
			merchantProducts = dbManager.getMerchantProductData(merchantId);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		this.filteredProductList = merchantProducts;
		
		setLayout(null);

		productsPanel = new JPanel();
		productsPanel.setBackground(new Color(30, 66, 94));
		productsPanel.setBorder(null);
		productsPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 15, 15)); // Adjust gaps as needed

		// Create the scrollpane and add productsPanel INTO it
		JScrollPane scrollPane = new JScrollPane(productsPanel); 
		scrollPane.setBorder(null);
		scrollPane.setBounds(10, 60, 1163, 570);
		add(scrollPane); 

		JLabel lblNewLabel = new JLabel("Sort by price:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(710, 30, 83, 14);
		add(lblNewLabel);

		sortByComboBox = new JComboBox();
		sortByComboBox.setBackground(new Color(237, 250, 139));
		sortByComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		sortByComboBox.setModel(new DefaultComboBoxModel(new String[] {"Low to High", "High to Low"}));
		sortByComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sortByFunction();
			}
			
		});
		sortByComboBox.setBounds(791, 27, 104, 22);
		add(sortByComboBox);

		JLabel lblFilterByCategory = new JLabel("Filter by category:");
		lblFilterByCategory.setForeground(new Color(255, 255, 255));
		lblFilterByCategory.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFilterByCategory.setBounds(905, 29, 109, 14);
		add(lblFilterByCategory);

		filterCategComboBox = new JComboBox();
		filterCategComboBox.setBackground(new Color(237, 250, 139));
		filterCategComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		filterCategComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				filterByCategory();
				
			}
			
		});	
		filterCategComboBox.setModel(new DefaultComboBoxModel(new String[] {"--- Select a category ---", "Gadgets", "Electronics", "Appliances", "Sports", "Fashion", "Furniture", "Vehicles"}));
		filterCategComboBox.setBounds(1010, 27, 163, 22);
		add(filterCategComboBox);
		
		
		//Adding the products
		sortByFunction();
	}
	
	public void sortByFunction() {
		
		int sortBySelectedIndex = sortByComboBox.getSelectedIndex();
		
		productsPanel.removeAll();
		
		switch(sortBySelectedIndex) {
		case 0: {
			//Sort the arraylist based on the months to pay (ascending order)
			Collections.sort(filteredProductList, (o1, o2) -> Float.compare(o1.getPrice(), o2.getPrice()));
			break;
		}
		case 1: {
			//Sort the arraylist based on the months to pay (descending order)
			Collections.sort(filteredProductList, (o1, o2) -> Float.compare(o2.getPrice(), o1.getPrice()));
			break;
		}
			
		}
		
		for(Product product: filteredProductList) {
			ProductTemplatePanel prodPanel = new ProductTemplatePanel(MERCHANT_ID, product);
			productsPanel.add(prodPanel);
		}
		
		productsPanel.revalidate();
        productsPanel.repaint();
		
	}
	
	public void filterByCategory() {
		
		int filterCategoryIndex = filterCategComboBox.getSelectedIndex();
		
		
		
		if (filterCategoryIndex == 0) { //No selected Category
			filteredProductList = merchantProducts;
			sortByFunction();
			return;
		} 
		
		//Selected a category
		String filterCategorySelected = (String) filterCategComboBox.getSelectedItem();
		String filterCategory = filterCategorySelected.toLowerCase();
		
		
		ArrayList<Product> tempFilteredList = new ArrayList<>();
		
		for (Product product : merchantProducts) {
			if(product.getCategory().equals(filterCategory)) {
				tempFilteredList.add(product);
			}
		}
		filteredProductList = tempFilteredList;
		sortByFunction();
		
	}
}







