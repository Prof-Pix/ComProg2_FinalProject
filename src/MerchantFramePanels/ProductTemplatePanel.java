package MerchantFramePanels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Products.Product;
import Products.ProductLoanTerm;
import User.Merchant;
import UserRoleFrames.MerchantFrame;
import Utilities.HelperUtility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;

public class ProductTemplatePanel extends JPanel{
	private JTable loanInterestTable;
	DefaultTableModel loanInterestTableModel;
	
	static Product productToDisplay;
	
	String loanInterestTableColumns[] = {"Months to pay", "Interest Rate"};
	
	ArrayList<ProductLoanTerm> prodLoanTerms = new ArrayList<>();
	
	public ProductTemplatePanel(int merchantId, Product productData) {
		ProductTemplatePanel.productToDisplay = productData;
		setPreferredSize(new Dimension(275, 405));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel(productData.getName());
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel.setBounds(15, 182, 235, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(productData.getBrand());
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(15, 197, 235, 14);
		add(lblNewLabel_1);
		
		JLabel lblPrice = new JLabel("₱ " + String.valueOf(productData.getPrice()));
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPrice.setBounds(15, 227, 235, 14);
		add(lblPrice);
		
		JButton btnNewButton = new JButton("Edit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
				int merchantOwnerId = productData.getMerchantOwnerId();
				ImageIcon productImage = productData.getProductImage();
				String productName = productData.getName();
				String productBrand = productData.getBrand();
				String productDescription = productData.getDescription();
				String productSpecifications = productData.getSpecifications();
				float productPrice = productData.getPrice();
				int productStocks = productData.getStocksAvailable();
				String productCategory = productData.getCategory();
				ArrayList<ProductLoanTerm> productLoanTerms = new ArrayList<>(productData.getProductLoans());
				
				EditProductPanel.productData = new Product(merchantOwnerId, productImage, productName, productBrand , productDescription , productSpecifications , 
						productPrice, productStocks, productCategory, productLoanTerms);
				EditProductPanel.MERCHANT_ID = merchantId;
				System.out.println("size: " + productData.getProductLoans().size());
				EditProductPanel editProduct = new EditProductPanel();
				editProduct.setVisible(true);
			}
		});
		btnNewButton.setBounds(185, 32, 70, 23);
		add(btnNewButton);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, 
		                "Are you sure you want to delete this product?", 
		                "Confirm Deletion", 
		                JOptionPane.YES_NO_OPTION);

		if (result == JOptionPane.YES_OPTION) {
		   if(Merchant.deleteProduct(merchantId, productData.getName())) {
			   JOptionPane.showMessageDialog(null, productData.getName() + " deleted successfully.", "Product Deleted", JOptionPane.INFORMATION_MESSAGE);
			   MerchantFrame.showViewProductsPanel();
		   }
		   else {
			   JOptionPane.showMessageDialog(null, "Please try again later.", "Server Error!", JOptionPane.ERROR_MESSAGE);
		   }
			
		} 
			}
		});
		btnDelete.setBounds(185, 58, 70, 23);
		add(btnDelete);
		
		JScrollPane loanInterestTableScrollPane = new JScrollPane();
		loanInterestTableScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		loanInterestTableScrollPane.setBounds(15, 272, 235, 91);
		add(loanInterestTableScrollPane);
		
		// Table Setup
				loanInterestTableModel = new DefaultTableModel(loanInterestTableColumns, 0) {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false; // All cells are non-editable
					}
				}; 
		
		loanInterestTable = new JTable(loanInterestTableModel);
		loanInterestTableScrollPane.setViewportView(loanInterestTable);
		
		//For changing the alignment of each row
				DefaultTableCellRenderer loanInterestTablerRenderer  = new DefaultTableCellRenderer();
				loanInterestTablerRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

				//Disbable Column Reordering
				JTableHeader loanInterestTableHeader = loanInterestTable.getTableHeader();
				loanInterestTableHeader.setReorderingAllowed(false);
				loanInterestTable.setDefaultRenderer(Object.class, loanInterestTablerRenderer);
				
		prodLoanTerms = productData.getProductLoans();
		
		//Sort the arraylist based on the months to pay (ascending order)
		Collections.sort(prodLoanTerms, (o1, o2) -> Integer.compare(o1.getMonthsToPay(), o2.getMonthsToPay()));
		
		//Populate the table with the sorted arraylist
		for(ProductLoanTerm prodLoan : prodLoanTerms) {	
			loanInterestTableModel.addRow(new Object[] { prodLoan.getMonthsToPay(), prodLoan.getInterestRate() * 100 + "%"});
		}
		
		
		JLabel lblInterestRates = new JLabel("Interest Rates @ Months to Pay");
		lblInterestRates.setFont(new Font("Dialog", Font.ITALIC, 11));
		lblInterestRates.setBounds(15, 252, 181, 23);
		add(lblInterestRates);
		
		JLabel lblNewLabel_2 = new JLabel("Stocks Available: ");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_2.setBounds(15, 374, 105, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel(String.valueOf(productData.getStocksAvailable()));
		lblNewLabel_2_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_2_1.setBounds(122, 374, 133, 14);
		add(lblNewLabel_2_1);
		
		JLabel productImageLabel = new JLabel("");
		productImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		productImageLabel.setIcon(productData.getProductImage());
		productImageLabel.setBounds(15, 32, 140, 140);
		//Scaling the image
				Image originalImage = productData.getProductImage().getImage();
				Image scaledImage = originalImage.getScaledInstance(productImageLabel.getWidth(), productImageLabel.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
				productImageLabel.setIcon(scaledImageIcon);
		add(productImageLabel);
		
		String category = HelperUtility.capitalizeWords(productData.getCategory());
		JLabel categoryLabel = new JLabel(category);
		categoryLabel.setOpaque(true);
		categoryLabel.setBackground(Color.BLACK);
		
		categoryLabel.setForeground(new Color(255, 255, 255));
		categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		categoryLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		categoryLabel.setBounds(5, 5, 80, 18);
		add(categoryLabel);
	}
}
