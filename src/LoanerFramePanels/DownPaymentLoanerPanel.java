package LoanerFramePanels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

import Database.DatabaseManager;
import LoanRequest.LoanRequest;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSeparator;

public class DownPaymentLoanerPanel extends JDialog {
	
	DatabaseManager dbManager = new DatabaseManager();

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	JPanel selectEWalletPanel;
	JPanel eWalletInformationPanel;
	JPanel cardInformationPanel;
	
	static LoanRequest LOAN_DETAILS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DownPaymentLoanerPanel dialog = new DownPaymentLoanerPanel(LOAN_DETAILS);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DownPaymentLoanerPanel(LoanRequest loanDetails) {
		//Get more information about the loan 
		
		try {
			dbManager.connect();
			DownPaymentLoanerPanel.LOAN_DETAILS = dbManager.getLoanRequestInformation(loanDetails);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		setBounds(100, 100, 1184, 640);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(630, 39, 485, 95);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel selectPaymentMethodPanel = new JLabel("Select a payment method:");
		selectPaymentMethodPanel.setFont(new Font("Dialog", Font.BOLD, 12));
		selectPaymentMethodPanel.setBounds(10, 11, 217, 14);
		panel.add(selectPaymentMethodPanel);
		
		final ButtonGroup eWalletButtonGroup = new ButtonGroup();
		
		JRadioButton eWalletRadButton = new JRadioButton("E-Wallet");
		eWalletRadButton.addActionListener(e -> showEWalletSelection());
		
		eWalletRadButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		eWalletRadButton.setBounds(55, 49, 109, 23);
		panel.add(eWalletRadButton);
		
		JRadioButton bankPaymentRadButton = new JRadioButton("Bank Payment");
		bankPaymentRadButton.addActionListener(e -> showCardInformation());
		bankPaymentRadButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		bankPaymentRadButton.setBounds(258, 49, 127, 23);
		panel.add(bankPaymentRadButton);
		
		eWalletButtonGroup.add(eWalletRadButton);
		eWalletButtonGroup.add(bankPaymentRadButton);
		
		cardInformationPanel = new JPanel();
		cardInformationPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		cardInformationPanel.setBounds(630, 145, 259, 232);
		contentPanel.add(cardInformationPanel);
		cardInformationPanel.setLayout(null);
		
		JLabel lblCardNumber = new JLabel("Card Number:");
		lblCardNumber.setHorizontalAlignment(SwingConstants.LEFT);
		lblCardNumber.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCardNumber.setBounds(20, 27, 100, 14);
		cardInformationPanel.add(lblCardNumber);
		
		textField = new JTextField();
		textField.setBounds(20, 48, 213, 20);
		cardInformationPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblExpirationDatemmyy = new JLabel("Expiration date (MM/YY):");
		lblExpirationDatemmyy.setHorizontalAlignment(SwingConstants.LEFT);
		lblExpirationDatemmyy.setFont(new Font("Dialog", Font.BOLD, 12));
		lblExpirationDatemmyy.setBounds(20, 86, 213, 14);
		cardInformationPanel.add(lblExpirationDatemmyy);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(20, 107, 213, 20);
		cardInformationPanel.add(textField_1);
		
		JLabel lblSecurityCode = new JLabel("Security Code: (CVV/CVC)");
		lblSecurityCode.setHorizontalAlignment(SwingConstants.LEFT);
		lblSecurityCode.setFont(new Font("Dialog", Font.BOLD, 12));
		lblSecurityCode.setBounds(20, 148, 213, 14);
		cardInformationPanel.add(lblSecurityCode);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(20, 169, 213, 20);
		cardInformationPanel.add(textField_2);
		
		eWalletInformationPanel = new JPanel();
		eWalletInformationPanel.setLayout(null);
		eWalletInformationPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		eWalletInformationPanel.setBounds(630, 355, 259, 155);
		contentPanel.add(eWalletInformationPanel);
		
		JLabel lblMobileNumber = new JLabel("Mobile Number:");
		lblMobileNumber.setHorizontalAlignment(SwingConstants.LEFT);
		lblMobileNumber.setFont(new Font("Dialog", Font.BOLD, 12));
		lblMobileNumber.setBounds(20, 27, 100, 14);
		eWalletInformationPanel.add(lblMobileNumber);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(20, 48, 213, 20);
		eWalletInformationPanel.add(textField_3);
		
		JLabel lblPinCode = new JLabel("PIN Code:");
		lblPinCode.setHorizontalAlignment(SwingConstants.LEFT);
		lblPinCode.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPinCode.setBounds(20, 86, 213, 14);
		eWalletInformationPanel.add(lblPinCode);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(20, 107, 213, 20);
		eWalletInformationPanel.add(textField_4);
		
		selectEWalletPanel = new JPanel();
		selectEWalletPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		selectEWalletPanel.setBounds(630, 145, 485, 187);
		contentPanel.add(selectEWalletPanel);
		selectEWalletPanel.setLayout(null);
		
		JLabel lblSelectEwallet = new JLabel("Select e-wallet:");
		lblSelectEwallet.setFont(new Font("Dialog", Font.BOLD, 12));
		lblSelectEwallet.setBounds(10, 11, 217, 14);
		selectEWalletPanel.add(lblSelectEwallet);
		
		JLabel mayaPicture = new JLabel("");
		mayaPicture.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		mayaPicture.setBounds(67, 36, 100, 100);
		//Scaling the image
				ImageIcon mayaImageIcon = new ImageIcon("D:\\APPLICATIONS\\Home_Credit\\src\\Images\\maya.jpg");
				Image originalMayaImage = mayaImageIcon.getImage();
				Image scaledMayaImage = originalMayaImage.getScaledInstance(mayaPicture.getWidth(), mayaPicture.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon scaledMayaImageIcon = new ImageIcon(scaledMayaImage);
				mayaPicture.setIcon(scaledMayaImageIcon);
		selectEWalletPanel.add(mayaPicture);
		
		JLabel gcashPicture = new JLabel("");
		gcashPicture.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		gcashPicture.setBounds(269, 36, 100, 100);
		//Scaling the image
		ImageIcon gcashImageIcon = new ImageIcon("D:\\APPLICATIONS\\Home_Credit\\src\\Images\\gcash.png");
		Image originalGcashImage = gcashImageIcon.getImage();
		Image scaledGcashImage = originalGcashImage.getScaledInstance(gcashPicture.getWidth(), gcashPicture.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledGcashImageIcon = new ImageIcon(scaledGcashImage);
		gcashPicture.setIcon(scaledGcashImageIcon);
		selectEWalletPanel.add(gcashPicture);
		
		JRadioButton mayaRadButton = new JRadioButton("Maya");
		mayaRadButton.addActionListener(e -> showEWalletInformation());
		mayaRadButton.setHorizontalAlignment(SwingConstants.CENTER);
		mayaRadButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		mayaRadButton.setBounds(67, 143, 100, 23);
		selectEWalletPanel.add(mayaRadButton);
		
		JRadioButton gcashRadButton = new JRadioButton("Gcash");
		gcashRadButton.addActionListener(e -> showEWalletInformation());
		gcashRadButton.setHorizontalAlignment(SwingConstants.CENTER);
		gcashRadButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		gcashRadButton.setBounds(269, 143, 100, 23);
		selectEWalletPanel.add(gcashRadButton);
		
		final ButtonGroup eWalletButtonSelectRadButtons = new ButtonGroup();
		eWalletButtonSelectRadButtons.add(gcashRadButton);
		eWalletButtonSelectRadButtons.add(mayaRadButton);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(560, 11, 2, 535);
		contentPanel.add(separator);
		
		JLabel productImageLabel = new JLabel("");
		productImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		productImageLabel.setBounds(31, 24, 220, 220);
		//Scaling the image
		Image originalImage = LOAN_DETAILS.getLoanedProductPicture().getImage();
		Image scaledImage = originalImage.getScaledInstance(productImageLabel.getWidth(), productImageLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
		productImageLabel.setIcon(scaledImageIcon);
		contentPanel.add(productImageLabel);
		
		JLabel lblProductName = new JLabel(LOAN_DETAILS.getLoanedProductName());
		lblProductName.setFont(new Font("Dialog", Font.BOLD, 15));
		lblProductName.setBounds(291, 24, 235, 14);
		contentPanel.add(lblProductName);
		
		JLabel lblNewLabel_1 = new JLabel(LOAN_DETAILS.getLoanedProductBrand());
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(291, 41, 235, 19);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblPrice = new JLabel("₱ " + String.valueOf(LOAN_DETAILS.getLoanedProductPrice()));
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPrice.setBounds(291, 79, 235, 14);
		contentPanel.add(lblPrice);
		
		JLabel lblNewLabel_2 = new JLabel("Merchant:");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_2.setBounds(291, 104, 89, 14);
		contentPanel.add(lblNewLabel_2);
		
		JLabel merchantName = new JLabel(LOAN_DETAILS.getMerchantName());
		merchantName.setFont(new Font("Dialog", Font.ITALIC, 14));
		merchantName.setBounds(370, 104, 173, 14);
		contentPanel.add(merchantName);
		
		JLabel lblNewLabel_2_1 = new JLabel("Downpayment Price:");
		lblNewLabel_2_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(31, 316, 153, 16);
		contentPanel.add(lblNewLabel_2_1);
		
		JLabel downPaymentPrice = new JLabel("₱ " + LOAN_DETAILS.getDownPaymentAmount());
		downPaymentPrice.setFont(new Font("Dialog", Font.BOLD, 14));
		downPaymentPrice.setBounds(196, 316, 235, 14);
		contentPanel.add(downPaymentPrice);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Monthly Payments:");
		lblNewLabel_2_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_2_1_1.setBounds(31, 355, 153, 16);
		contentPanel.add(lblNewLabel_2_1_1);
		
		JLabel lblPrice_1_1 = new JLabel("₱ " + LOAN_DETAILS.getMonthlyPayment() + " x " + LOAN_DETAILS.getLoanedProductMonthsToPay() + " months");
		lblPrice_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPrice_1_1.setBounds(196, 355, 235, 14);
		contentPanel.add(lblPrice_1_1);
		
		
		selectEWalletPanel.setVisible(false);
		eWalletInformationPanel.setVisible(false);
		cardInformationPanel.setVisible(false);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Continue Payment");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
	}
	private void showCardInformation() {
		selectEWalletPanel.setVisible(false);
		eWalletInformationPanel.setVisible(false);
		cardInformationPanel.setVisible(true);
	}
	
	private void showEWalletInformation() {
		eWalletInformationPanel.setVisible(true);
		cardInformationPanel.setVisible(false);
		selectEWalletPanel.setVisible(true);
	}
	
	private void showEWalletSelection() {
		eWalletInformationPanel.setVisible(false);
		cardInformationPanel.setVisible(false);
		selectEWalletPanel.setVisible(true);
	}
}
