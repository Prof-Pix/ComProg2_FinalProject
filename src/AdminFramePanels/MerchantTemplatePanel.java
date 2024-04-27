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
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class MerchantTemplatePanel extends JPanel{
	
	DatabaseManager dbManager = new DatabaseManager();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Product productToDisplay;

	ArrayList<ProductLoanTerm> prodLoanTerms = new ArrayList<>();
	
	public MerchantTemplatePanel(Merchant merch) {
		setBackground(new Color(237, 250, 139));
		
		setPreferredSize(new Dimension(250, 320));
		setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
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
		productImageLabel.setBounds(15, 32, 140, 140);
		productImageLabel.setIcon(HelperUtility.resizeImage(HelperUtility.defaultMerchantImageFilePath, productImageLabel.getWidth(), productImageLabel.getHeight()));
		add(productImageLabel);
		
		String category = HelperUtility.capitalizeWords(merch.getMerchantCategory());
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
				SeeMerchantProductDetails merchantProductPanel = new SeeMerchantProductDetails(merch.getMerchantId());
				merchantProductPanel.setVisible(true);

			}
		});
		btnNewButton.setBounds(126, 280, 114, 23);
		add(btnNewButton);
	}
}
