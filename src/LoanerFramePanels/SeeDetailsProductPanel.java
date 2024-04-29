package LoanerFramePanels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Database.DatabaseManager;
import Products.Product;
import Products.ProductLoanTerm;
import User.Loaner;
import User.Merchant;
import Utilities.HelperUtility;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import java.awt.Dimension;
import java.awt.Dialog.ModalityType;

public class SeeDetailsProductPanel extends JDialog {

	DatabaseManager dbManager = new DatabaseManager();

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	static Product productData;
	static int LOANER_ID;
	
	//For months to pay and interest rate
	int selectedMonthsToPay;
	float selectedInterestRate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SeeDetailsProductPanel dialog = new SeeDetailsProductPanel();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SeeDetailsProductPanel() {
		setTitle("Product Details");
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 1184, 640);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(37, 102, 112));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		String category = HelperUtility.capitalizeWords(productData.getCategory());
		JLabel categoryLabel = new JLabel(category);
		categoryLabel.setBounds(10, 11, 80, 18);
		categoryLabel.setOpaque(true);
		categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		categoryLabel.setForeground(new Color(0, 0, 0));
		categoryLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		categoryLabel.setBackground(new Color(173, 226, 138));
		contentPanel.add(categoryLabel);
		
		JLabel productImageLabel = new JLabel("");
		productImageLabel.setBounds(20, 38, 140, 140);
		Image originalImage = productData.getProductImage().getImage();
		Image scaledImage = originalImage.getScaledInstance(productImageLabel.getWidth(), productImageLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
		productImageLabel.setIcon(scaledImageIcon);
		
		productImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPanel.add(productImageLabel);
		
		JLabel lblNewLabel = new JLabel(productData.getName());
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(189, 38, 235, 14);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(productData.getBrand());
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(189, 53, 235, 14);
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblPrice = new JLabel(String.valueOf("₱ " + productData.getPrice()));
		lblPrice.setForeground(new Color(255, 255, 255));
		lblPrice.setBounds(189, 83, 235, 14);
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 12));
		contentPanel.add(lblPrice);
		
		JLabel lblNewLabel_2 = new JLabel("Merchant:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(189, 108, 64, 14);
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 12));
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel(productData.getMerchantName());
		lblNewLabel_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_2_1.setBounds(251, 108, 173, 14);
		lblNewLabel_2_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		contentPanel.add(lblNewLabel_2_1);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setForeground(new Color(255, 255, 255));
		lblDescription.setBounds(20, 200, 86, 14);
		lblDescription.setFont(new Font("Dialog", Font.BOLD, 12));
		contentPanel.add(lblDescription);
		
		JLabel lblSpecifications = new JLabel("Specifications:");
		lblSpecifications.setForeground(new Color(255, 255, 255));
		lblSpecifications.setBounds(20, 370, 86, 14);
		lblSpecifications.setFont(new Font("Dialog", Font.BOLD, 12));
		contentPanel.add(lblSpecifications);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 359, 447, 0);
		contentPanel.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(21, 224, 526, 124);
		contentPanel.add(scrollPane_1);
		
		JLabel lblNewLabel_3 = new JLabel(productData.getDescription());
		lblNewLabel_3.setBackground(new Color(237, 250, 139));
		lblNewLabel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		scrollPane_1.setViewportView(lblNewLabel_3);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(20, 395, 526, 124);
		contentPanel.add(scrollPane_1_1);
		
		JLabel lblNewLabel_3_1 = new JLabel(productData.getSpecifications());
		lblNewLabel_3_1.setBackground(new Color(237, 250, 139));
		lblNewLabel_3_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_3_1.setVerticalAlignment(SwingConstants.TOP);
		scrollPane_1_1.setViewportView(lblNewLabel_3_1);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(582, 22, 2, 535);
		contentPanel.add(separator);
		
		JLabel lblLoanTerms = new JLabel("Loan Terms");
		lblLoanTerms.setForeground(new Color(255, 255, 255));
		lblLoanTerms.setFont(new Font("Dialog", Font.BOLD, 13));
		lblLoanTerms.setBounds(594, 38, 235, 14);
		contentPanel.add(lblLoanTerms);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(null);
		scrollPane_2.setBounds(594, 63, 360, 468);
		contentPanel.add(scrollPane_2);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBackground(new Color(37, 102, 112));
		scrollPane_2.setViewportView(panel);
		panel.setLayout(null);
		
		JPanel loanTermMainPanel = new JPanel();
		loanTermMainPanel.setBounds(28, 21, 297, 422);
		panel.add(loanTermMainPanel);
		loanTermMainPanel.setLayout(new BoxLayout(loanTermMainPanel, BoxLayout.Y_AXIS));
		loanTermMainPanel.setBackground(new Color(37, 102, 112));
		
		dbManager.connect();
		Merchant merch = dbManager.getMerchantData(productData.getMerchantOwnerId()) ;
		JLabel lblNewLabel_2_1_1 = new JLabel(merch.getMerchantAddress());
		lblNewLabel_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblNewLabel_2_1_1.setBounds(189, 127, 358, 14);
		contentPanel.add(lblNewLabel_2_1_1);
		
		
		
		
		ArrayList<ProductLoanTerm> productLoanTerms = productData.getProductLoans();
		final ButtonGroup loanTermsButtonGroup = new ButtonGroup();
		
		for(ProductLoanTerm prodLoanTerm : productLoanTerms) {
			JRadioButton loanTermRadButton = new JRadioButton(prodLoanTerm.getMonthsToPay() + " months x " +  String.valueOf(prodLoanTerm.getInterestRate()*100) + "%");
			loanTermRadButton.setBackground(new Color(237, 250, 139));
			
			
			JPanel loanTermPanel = new JPanel();
			loanTermPanel.setBackground(new Color(237, 250, 139));
			loanTermPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			loanTermPanel.setMaximumSize(new Dimension(250, 35));
			loanTermPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
			loanTermPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
			loanTermPanel.add(loanTermRadButton);		
			
			loanTermMainPanel.add(loanTermPanel);
			
			Component gap = Box.createRigidArea(new Dimension(0, 10));
			loanTermMainPanel.add(gap);
			loanTermsButtonGroup.add(loanTermRadButton);
			
			//For action listener
			
			loanTermRadButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					selectedMonthsToPay = prodLoanTerm.getMonthsToPay();
					selectedInterestRate = prodLoanTerm.getInterestRate();
					
				}
				
			});;
			
		}
				
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(37, 102, 112));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Apply a loan!");
				okButton.setForeground(new Color(0, 0, 0));
				okButton.setBackground(new Color(173, 226, 138));
				okButton.setFont(new Font("Dialog", Font.PLAIN, 13));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int choice = JOptionPane.showConfirmDialog(null, "Send a request to " + productData.getMerchantName() + " for approval request?", "Send Loan Request", JOptionPane.YES_NO_OPTION);
						
						if(choice == JOptionPane.YES_OPTION) {
							
							//Perform retrieval of data
							try {
								dbManager.connect();
								Loaner loanerData = dbManager.getLoanerData(LOANER_ID);
								Product productToLoan = productData;
								ProductLoanTerm prodLoanTerm = new ProductLoanTerm(selectedMonthsToPay, selectedInterestRate);
								
								if(dbManager.sendLoanRequest(loanerData, productToLoan, prodLoanTerm)) {
									
									JOptionPane.showMessageDialog(null, "Loan request sent to " + productData.getMerchantName()+ ". Please wait to for the approval of the merchant. Thank you.", "Loan Request Sent Successfully", JOptionPane.INFORMATION_MESSAGE);
									HelperUtility.closeDialog(SeeDetailsProductPanel.this);
								}
								
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(null, "Server error. Please try again later.", "Server Error", JOptionPane.INFORMATION_MESSAGE);
								ex.printStackTrace();
							}
							
							
							
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
