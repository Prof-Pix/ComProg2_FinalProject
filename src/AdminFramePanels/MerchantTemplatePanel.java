package AdminFramePanels;

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
import LoanerFramePanels.SeeDetailsProductPanel;
import Products.Product;
import Products.ProductLoanTerm;
import User.Merchant;
import UserRoleFrames.MerchantFrame;
import Utilities.HelperUtility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;

public class MerchantTemplatePanel extends JPanel{
	
	DatabaseManager dbManager = new DatabaseManager();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Product productToDisplay;

	ArrayList<ProductLoanTerm> prodLoanTerms = new ArrayList<>();
	
	public MerchantTemplatePanel(Merchant merch) {
		
		setPreferredSize(new Dimension(250, 320));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		
		JLabel merchantName = new JLabel(merch.getMerchantName());
		merchantName.setFont(new Font("Dialog", Font.BOLD, 13));
		merchantName.setBounds(15, 182, 225, 14);
		add(merchantName);
		
		dbManager.connect();
		ArrayList<Product> merchantProducts = dbManager.getMerchantProductData(merch.getMerchantId());
		JLabel productsAvailable = new JLabel( merchantProducts.size() + " Products");
		productsAvailable.setFont(new Font("Dialog", Font.BOLD, 12));
		productsAvailable.setBounds(15, 207, 225, 14);
		add(productsAvailable);
		
		JLabel merchantAddress = new JLabel(merch.getMerchantAddress());
		merchantAddress.setFont(new Font("Dialog", Font.ITALIC, 12));
		merchantAddress.setBounds(15, 242, 225, 14);
		add(merchantAddress);
		
		JLabel productImageLabel = new JLabel("");
		productImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
//		productImageLabel.setIcon(productData.getProductImage());
		productImageLabel.setBounds(15, 32, 140, 140);
//		//Scaling the image
//				Image originalImage = productData.getProductImage().getImage();
//				Image scaledImage = originalImage.getScaledInstance(productImageLabel.getWidth(), productImageLabel.getHeight(), Image.SCALE_SMOOTH);
//				ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
//				productImageLabel.setIcon(scaledImageIcon);
		add(productImageLabel);
		
		String category = HelperUtility.capitalizeWords(merch.getMerchantCategory());
		JLabel categoryLabel = new JLabel("Sports");
		categoryLabel.setOpaque(true);
		categoryLabel.setBackground(Color.BLACK);
		
		categoryLabel.setForeground(new Color(255, 255, 255));
		categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		categoryLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		categoryLabel.setBounds(5, 5, 80, 18);
		add(categoryLabel);
		
		JButton btnNewButton = new JButton("See details");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeeMerchantProductDetails merchantProductPanel = new SeeMerchantProductDetails(merch.getMerchantId());
				merchantProductPanel.setVisible(true);

			}
		});
		btnNewButton.setBounds(126, 280, 114, 23);
		add(btnNewButton);
	}
}
