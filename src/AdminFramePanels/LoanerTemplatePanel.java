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
import Loan.Loan;
import LoanerFramePanels.SeeDetailsProductPanel;
import Products.Product;
import Products.ProductLoanTerm;
import User.Loaner;
import User.Merchant;
import UserRoleFrames.MerchantFrame;
import Utilities.HelperUtility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class LoanerTemplatePanel extends JPanel{
	
	DatabaseManager dbManager = new DatabaseManager();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Product productToDisplay;

	ArrayList<ProductLoanTerm> prodLoanTerms = new ArrayList<>();
	
	public LoanerTemplatePanel(Loaner loaner) {
		setBackground(new Color(237, 250, 139));
		
		setPreferredSize(new Dimension(250, 320));
		setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(null);
		
		JLabel loanerName = new JLabel(loaner.getFullName());
		loanerName.setFont(new Font("Dialog", Font.BOLD, 13));
		loanerName.setBounds(15, 182, 225, 14);
		add(loanerName);
		
		dbManager.connect();
		ArrayList<Loan> getOngoingLoans = dbManager.getOngoingLoans(loaner.getLoanerId());
		JLabel productsAvailable = new JLabel(getOngoingLoans.size() + " Ongoing Loans");
		productsAvailable.setFont(new Font("Dialog", Font.BOLD, 12));
		productsAvailable.setBounds(15, 207, 225, 14);
		add(productsAvailable);
		
		JLabel productImageLabel = new JLabel("");
		productImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));

		productImageLabel.setBounds(15, 32, 140, 140);
		productImageLabel.setIcon(HelperUtility.resizeImage(HelperUtility.defaultLoanerImageFilePath, productImageLabel.getWidth(), productImageLabel.getHeight()));

		add(productImageLabel);
		
		JButton btnNewButton = new JButton("See details");
		btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 11));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(64, 112, 86));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				SeeMerchantProductDetails merchantProductPanel = new SeeMerchantProductDetails(merch.getMerchantId());
//				merchantProductPanel.setVisible(true);

			}
		});
		btnNewButton.setBounds(126, 275, 114, 23);
		add(btnNewButton);
	}
}
