package LoanerFramePanels;

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

import Database.DatabaseManager;
import Products.Product;
import Products.ProductLoanTerm;
import User.Merchant;
import UserRoleFrames.MerchantFrame;
import Utilities.HelperUtility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class MarketPlaceProductTemplatePanel extends JPanel{
	DatabaseManager dbManager = new DatabaseManager();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Product productToDisplay;

	ArrayList<ProductLoanTerm> prodLoanTerms = new ArrayList<>();
	
	public MarketPlaceProductTemplatePanel(int loanerId, Product productData) {
		setBackground(new Color(237, 250, 139));
		MarketPlaceProductTemplatePanel.productToDisplay = productData;
		setPreferredSize(new Dimension(250, 350));
		setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
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
		
		
		JLabel lblNewLabel_2 = new JLabel("Merchant:");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_2.setBounds(15, 252, 64, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel(productData.getMerchantName());
		lblNewLabel_2_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_2_1.setBounds(77, 252, 173, 14);
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
		categoryLabel.setBackground(new Color(173, 226, 138));
		
		categoryLabel.setForeground(new Color(0, 0, 0));
		categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		categoryLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		categoryLabel.setBounds(5, 5, 80, 18);
		add(categoryLabel);
		
		JButton btnNewButton = new JButton("See details");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(64, 112, 86));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeeDetailsProductPanel.productData = productData;
				SeeDetailsProductPanel.LOANER_ID = loanerId;
				SeeDetailsProductPanel seeDetails = new SeeDetailsProductPanel();
				seeDetails.setVisible(true);
			}
		});
		btnNewButton.setBounds(126, 316, 114, 23);
		add(btnNewButton);
		
		JLabel itemsLeft = new JLabel(String.valueOf(productData.getStocksAvailable()) + " items left"  );
		itemsLeft.setHorizontalAlignment(SwingConstants.RIGHT);
		itemsLeft.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		itemsLeft.setBounds(113, 8, 127, 14);
		add(itemsLeft);
		
		dbManager.connect();
		Merchant merch = dbManager.getMerchantData(productData.getMerchantOwnerId()) ;
		JLabel lblNewLabel_2_1_1 = new JLabel(merch.getMerchantAddress());
		lblNewLabel_2_1_1.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblNewLabel_2_1_1.setBounds(10, 271, 230, 14);
		add(lblNewLabel_2_1_1);
	}
}
