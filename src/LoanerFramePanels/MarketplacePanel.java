package LoanerFramePanels;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

import Database.DatabaseManager;
import MerchantFramePanels.ProductTemplatePanel;
import Products.Product;
import javax.swing.border.LineBorder;

import AdminFramePanels.MerchantTemplatePanel;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class MarketplacePanel extends JPanel {
	
	DatabaseManager dbManager = new DatabaseManager();
	ArrayList<Product> merchantProducts;
	
	ArrayList<Product> filteredProductList;
	
	JComboBox sortByComboBox;
	JPanel marketPlaceProductsPanel;
	JComboBox filterCategComboBox;
	
	private static int LOANER_ID;

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public MarketplacePanel(int loanerId) {
		
		MarketplacePanel.LOANER_ID = loanerId;
		setBackground(new Color(30, 66, 94));
		
		try {
			dbManager.connect();
			merchantProducts = dbManager.getAllMerchantProducts();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		this.filteredProductList = merchantProducts;
		setLayout(null);
		
		JLabel lblNewLabel_5_2 = new JLabel(merchantProducts.size() + " Available Products");
		lblNewLabel_5_2.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_2.setForeground(Color.WHITE);
		lblNewLabel_5_2.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_5_2.setBounds(32, 16, 279, 14);
		add(lblNewLabel_5_2);
		
		JLabel lblNewLabel = new JLabel("Sort by price:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(644, 14, 83, 14);
		add(lblNewLabel);
		
		sortByComboBox = new JComboBox();
		sortByComboBox.setModel(new DefaultComboBoxModel(new String[] {"Low to High", "High to Low"}));
		sortByComboBox.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sortByFunction();
			}
		});
		sortByComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		sortByComboBox.setBackground(new Color(237, 250, 139));
		sortByComboBox.setBounds(725, 11, 104, 22);
		add(sortByComboBox);
		
		JLabel lblFilterByCategory = new JLabel("Filter by category:");
		lblFilterByCategory.setForeground(Color.WHITE);
		lblFilterByCategory.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFilterByCategory.setBounds(839, 13, 109, 14);
		add(lblFilterByCategory);
		
		filterCategComboBox = new JComboBox();
		filterCategComboBox.addActionListener(e -> filterByCategory());		
		filterCategComboBox.setModel(new DefaultComboBoxModel(new String[] {"--- Select a category ---", "Gadgets", "Electronics", "Appliances", "Sports", "Fashion", "Furniture", "Vehicles"}));
		filterCategComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		filterCategComboBox.setBackground(new Color(237, 250, 139));
		filterCategComboBox.setBounds(944, 11, 163, 22);
		add(filterCategComboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(32, 56, 1075, 578);
		add(scrollPane);
		
		marketPlaceProductsPanel = new JPanel();
		scrollPane.setViewportView(marketPlaceProductsPanel);
		marketPlaceProductsPanel.setBackground(new Color(30, 66, 94));
		marketPlaceProductsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		marketPlaceProductsPanel.setBorder(null);
		marketPlaceProductsPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 15, 15));
		
		marketPlaceProductsPanel.revalidate();
		marketPlaceProductsPanel.repaint();
		
		sortByFunction();

	}
	
	public void sortByFunction() {
		
		int sortBySelectedIndex = sortByComboBox.getSelectedIndex();
		System.out.println(sortBySelectedIndex);
		
		marketPlaceProductsPanel.removeAll();
		
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
			MarketPlaceProductTemplatePanel prodPanel = new MarketPlaceProductTemplatePanel(LOANER_ID , product);
			marketPlaceProductsPanel.add(prodPanel);
		}
		
		marketPlaceProductsPanel.revalidate();
		marketPlaceProductsPanel.repaint();
		
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
