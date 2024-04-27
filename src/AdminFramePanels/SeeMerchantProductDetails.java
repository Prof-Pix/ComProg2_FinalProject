package AdminFramePanels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database.DatabaseManager;
import LoanerFramePanels.MarketPlaceProductTemplatePanel;
import Products.Product;
import User.Merchant;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class SeeMerchantProductDetails extends JDialog {

	DatabaseManager dbManager = new DatabaseManager();

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private ArrayList<Product> merchantProducts = new ArrayList<>();
	ArrayList<Product> filteredProductList;
	private static int MERCHANT_ID;

	JComboBox sortByComboBox;
	JComboBox filterCategComboBox;
	JPanel merchantProductsPanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SeeMerchantProductDetails dialog = new SeeMerchantProductDetails(MERCHANT_ID);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SeeMerchantProductDetails(int merchantId) {
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		SeeMerchantProductDetails.MERCHANT_ID = merchantId;

		try {
			dbManager.connect();
			merchantProducts = dbManager.getMerchantProductData(merchantId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.filteredProductList = merchantProducts;


		setBounds(100, 100, 1184, 641);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(37, 102, 112));
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 31, 1075, 535);
		contentPanel.add(scrollPane);

		merchantProductsPanel = new JPanel();
		merchantProductsPanel.setBorder(null);
		merchantProductsPanel.setBackground(new Color(37, 102, 112));
		scrollPane.setViewportView(merchantProductsPanel);
		merchantProductsPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 15, 15));

		JLabel lblNewLabel_5_2 = new JLabel(merchantProducts.size() + " Available Products");
		lblNewLabel_5_2.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_2.setForeground(Color.WHITE);
		lblNewLabel_5_2.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_5_2.setBounds(48, 11, 279, 14);
		contentPanel.add(lblNewLabel_5_2);

		JLabel lblNewLabel = new JLabel("Sort by price:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(659, 10, 83, 14);
		contentPanel.add(lblNewLabel);

		sortByComboBox = new JComboBox();
		sortByComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sortByFunction();
			}
			
		});
		sortByComboBox.setModel(new DefaultComboBoxModel(new String[] {"Low to High", "High to Low"}));
		sortByComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		sortByComboBox.setBackground(new Color(237, 250, 139));
		sortByComboBox.setBounds(741, 6, 104, 22);
		contentPanel.add(sortByComboBox);

		JLabel lblFilterByCategory = new JLabel("Filter by category:");
		lblFilterByCategory.setForeground(Color.WHITE);
		lblFilterByCategory.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFilterByCategory.setBounds(855, 8, 109, 14);
		contentPanel.add(lblFilterByCategory);

		filterCategComboBox = new JComboBox();
		filterCategComboBox.addActionListener(e -> filterByCategory());
		filterCategComboBox.setModel(new DefaultComboBoxModel(new String[] {"--- Select a category ---", "Gadgets", "Electronics", "Appliances", "Sports", "Fashion", "Furniture", "Vehicles"}));
		filterCategComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		filterCategComboBox.setBackground(new Color(237, 250, 139));
		filterCategComboBox.setBounds(960, 6, 163, 22);
		contentPanel.add(filterCategComboBox);

		sortByFunction();


	}

	public void sortByFunction() {

		int sortBySelectedIndex = sortByComboBox.getSelectedIndex();

		merchantProductsPanel.removeAll();

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
			MerchantProductTemplate merchProdPanel = new MerchantProductTemplate(MERCHANT_ID, product);
			merchantProductsPanel.add(merchProdPanel);
		}

		merchantProductsPanel.revalidate();
		merchantProductsPanel.repaint();

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
