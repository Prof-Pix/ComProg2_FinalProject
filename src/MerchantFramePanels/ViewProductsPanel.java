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
	
	JComboBox sortByComboBox;
	JComboBox filterCategComboBox;
	JPanel productsPanel;
	
	public ViewProductsPanel(int merchantId) {
		
		//Establish a connection
		try {
			dbManager.connect();
			merchantProducts = dbManager.getMerchantData(merchantId);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		setLayout(null);

		productsPanel = new JPanel();
		productsPanel.setBorder(null);
		productsPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 15, 15)); // Adjust gaps as needed

		// Create the scrollpane and add productsPanel INTO it
		JScrollPane scrollPane = new JScrollPane(productsPanel); 
		scrollPane.setBounds(10, 60, 1163, 570);
		add(scrollPane); 
		
		for(Product product: merchantProducts) {
			ProductTemplatePanel prodPanel = new ProductTemplatePanel(product);
			productsPanel.add(prodPanel);
		}

		JLabel lblNewLabel = new JLabel("Sort by price:");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(710, 30, 83, 14);
		add(lblNewLabel);

		sortByComboBox = new JComboBox();
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
		lblFilterByCategory.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFilterByCategory.setBounds(905, 29, 109, 14);
		add(lblFilterByCategory);

		filterCategComboBox = new JComboBox();
		filterCategComboBox.setModel(new DefaultComboBoxModel(new String[] {"--- Select a category ---", "Gadgets", "Electronics", "Appliances", "Sports", "Fashion", "Furniture", "Vehicles"}));
		filterCategComboBox.setBounds(1010, 27, 163, 22);
		add(filterCategComboBox);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 341, 10, 2);
		add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(1174, 341, 10, 2);
		add(separator_1);
	}
	
	public void sortByFunction() {
		
		int sortBySelectedIndex = sortByComboBox.getSelectedIndex();
		
		productsPanel.removeAll();
		
		switch(sortBySelectedIndex) {
		case 0: {
			//Sort the arraylist based on the months to pay (ascending order)
			Collections.sort(merchantProducts, (o1, o2) -> Float.compare(o1.getPrice(), o2.getPrice()));
			break;
		}
		case 1: {
			//Sort the arraylist based on the months to pay (descending order)
			Collections.sort(merchantProducts, (o1, o2) -> Float.compare(o2.getPrice(), o1.getPrice()));
			break;
		}
			
		}
		
		for(Product product: merchantProducts) {
			ProductTemplatePanel prodPanel = new ProductTemplatePanel(product);
			productsPanel.add(prodPanel);
		}
		
		productsPanel.revalidate();
        productsPanel.repaint();
		
			
	}
	
	public void filterByCategory() {
		
	}
}