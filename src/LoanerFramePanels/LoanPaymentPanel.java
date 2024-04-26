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
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

import Database.DatabaseManager;
import Loan.Loan;
import Loan.LoanRequest;
import PaymentAccounts.BankAccount;
import PaymentAccounts.EWalletAccount;
import Utilities.HelperUtility;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;

public class LoanPaymentPanel extends JDialog {
	
	DatabaseManager dbManager = new DatabaseManager();

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField cardNumberField;
	private JTextField cardExpDateField;
	private JTextField mobileNumberField;
	private JTextField pinCodeField;
	JLabel totalPaymentText;
	JScrollPane payInAdvanceScrollPane;
	JLabel lblMonth;
	JLabel lblAmountDue;
	
	JPanel payInAdvancePanel;
	
	JPanel selectEWalletPanel;
	JPanel eWalletInformationPanel;
	JPanel cardSecurityCodeFIeld;
	
	static LoanRequest LOAN_REQUEST_DETAILS;
	static Loan LOAN_DETAILS;
	
	String paymentMethod;
	float totalPayment;
	
	//For advance pay
	ArrayList<JCheckBox> monthCheckBoxes = new ArrayList<>();
	private JLabel remainingBalance;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoanPaymentPanel dialog = new LoanPaymentPanel(LOAN_REQUEST_DETAILS, LOAN_DETAILS);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoanPaymentPanel(LoanRequest loanReqDetails, Loan loanDetails) {
		setModalityType(ModalityType.APPLICATION_MODAL);
		
		//Get more information about the loan 
		LoanPaymentPanel.LOAN_REQUEST_DETAILS = loanReqDetails;
		LoanPaymentPanel.LOAN_DETAILS = loanDetails;
	
		
		setBounds(100, 100, 1184, 640);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(630, 75, 485, 95);
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
		
		eWalletInformationPanel = new JPanel();
		eWalletInformationPanel.setLayout(null);
		eWalletInformationPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		eWalletInformationPanel.setBounds(630, 391, 259, 155);
		contentPanel.add(eWalletInformationPanel);
		
		JLabel lblMobileNumber = new JLabel("Mobile Number:");
		lblMobileNumber.setHorizontalAlignment(SwingConstants.LEFT);
		lblMobileNumber.setFont(new Font("Dialog", Font.BOLD, 12));
		lblMobileNumber.setBounds(20, 27, 100, 14);
		eWalletInformationPanel.add(lblMobileNumber);
		
		mobileNumberField = new JTextField();
		mobileNumberField.setColumns(10);
		mobileNumberField.setBounds(20, 48, 213, 20);
		eWalletInformationPanel.add(mobileNumberField);
		
		JLabel lblPinCode = new JLabel("PIN Code:");
		lblPinCode.setHorizontalAlignment(SwingConstants.LEFT);
		lblPinCode.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPinCode.setBounds(20, 86, 213, 14);
		eWalletInformationPanel.add(lblPinCode);
		
		pinCodeField = new JTextField();
		pinCodeField.setColumns(10);
		pinCodeField.setBounds(20, 107, 213, 20);
		eWalletInformationPanel.add(pinCodeField);
		
		selectEWalletPanel = new JPanel();
		selectEWalletPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		selectEWalletPanel.setBounds(630, 181, 485, 187);
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
		Image originalImage = LOAN_REQUEST_DETAILS.getProductToLoanData().getProductImage().getImage();
		Image scaledImage = originalImage.getScaledInstance(productImageLabel.getWidth(), productImageLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
		productImageLabel.setIcon(scaledImageIcon);
		contentPanel.add(productImageLabel);
		
		JLabel lblProductName = new JLabel(LOAN_REQUEST_DETAILS.getProductToLoanData().getName());
		lblProductName.setFont(new Font("Dialog", Font.BOLD, 15));
		lblProductName.setBounds(291, 24, 235, 14);
		contentPanel.add(lblProductName);
		
		JLabel lblNewLabel_1 = new JLabel(LOAN_REQUEST_DETAILS.getProductToLoanData().getBrand());
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(291, 41, 235, 19);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblPrice = new JLabel("₱ " + String.valueOf(LOAN_REQUEST_DETAILS.getProductToLoanData().getPrice()));
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPrice.setBounds(291, 79, 235, 14);
		contentPanel.add(lblPrice);
		
		JLabel lblNewLabel_2 = new JLabel("Merchant:");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_2.setBounds(291, 104, 89, 14);
		contentPanel.add(lblNewLabel_2);
		
		JLabel merchantName = new JLabel(LOAN_REQUEST_DETAILS.getMerchantLoanData().getMerchantName());
		merchantName.setFont(new Font("Dialog", Font.ITALIC, 14));
		merchantName.setBounds(370, 104, 173, 14);
		contentPanel.add(merchantName);
		
		JLabel lblNewLabel_2_1 = new JLabel("Remaining Balance:");
		lblNewLabel_2_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(31, 255, 153, 16);
		contentPanel.add(lblNewLabel_2_1);
		
		remainingBalance = new JLabel("₱ " + LOAN_DETAILS.getRemainingBalance());
		remainingBalance.setFont(new Font("Dialog", Font.BOLD, 14));
		remainingBalance.setBounds(252, 256, 235, 14);
		contentPanel.add(remainingBalance);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Remaining Months to Pay:");
		lblNewLabel_2_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_2_1_1.setBounds(31, 282, 201, 16);
		contentPanel.add(lblNewLabel_2_1_1);
		
		JLabel monthsToPay = new JLabel(String.valueOf(LOAN_DETAILS.getRemainingMonthsToPay()));
		monthsToPay.setFont(new Font("Dialog", Font.BOLD, 14));
		monthsToPay.setBounds(252, 283, 235, 14);
		contentPanel.add(monthsToPay);
		
		cardSecurityCodeFIeld = new JPanel();
		cardSecurityCodeFIeld.setBounds(630, 181, 259, 232);
		contentPanel.add(cardSecurityCodeFIeld);
		cardSecurityCodeFIeld.setBorder(new LineBorder(new Color(0, 0, 0)));
		cardSecurityCodeFIeld.setLayout(null);
		
		JLabel lblCardNumber = new JLabel("Card Number:");
		lblCardNumber.setHorizontalAlignment(SwingConstants.LEFT);
		lblCardNumber.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCardNumber.setBounds(20, 27, 100, 14);
		cardSecurityCodeFIeld.add(lblCardNumber);
		
		cardNumberField = new JTextField();
		cardNumberField.setBounds(20, 48, 213, 20);
		cardSecurityCodeFIeld.add(cardNumberField);
		cardNumberField.setColumns(10);
		
		JLabel lblExpirationDatemmyy = new JLabel("Expiration date (MM/YY):");
		lblExpirationDatemmyy.setHorizontalAlignment(SwingConstants.LEFT);
		lblExpirationDatemmyy.setFont(new Font("Dialog", Font.BOLD, 12));
		lblExpirationDatemmyy.setBounds(20, 86, 213, 14);
		cardSecurityCodeFIeld.add(lblExpirationDatemmyy);
		
		cardExpDateField = new JTextField();
		cardExpDateField.setColumns(10);
		cardExpDateField.setBounds(20, 107, 213, 20);
		cardSecurityCodeFIeld.add(cardExpDateField);
		
		JLabel lblSecurityCode = new JLabel("Security Code: (CVV/CVC)");
		lblSecurityCode.setHorizontalAlignment(SwingConstants.LEFT);
		lblSecurityCode.setFont(new Font("Dialog", Font.BOLD, 12));
		lblSecurityCode.setBounds(20, 148, 213, 14);
		cardSecurityCodeFIeld.add(lblSecurityCode);
		
		JTextField cardSecurityCodeField = new JTextField();
		cardSecurityCodeField.setColumns(10);
		cardSecurityCodeField.setBounds(20, 169, 213, 20);
		cardSecurityCodeFIeld.add(cardSecurityCodeField);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Amount to pay:");
		lblNewLabel_2_1_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_2_1_2.setBounds(630, 41, 122, 16);
		contentPanel.add(lblNewLabel_2_1_2);
		
		totalPaymentText = new JLabel("₱ " + String.valueOf(LOAN_REQUEST_DETAILS.getMonthlyPayment()));
		totalPaymentText.setFont(new Font("Dialog", Font.BOLD, 14));
		totalPaymentText.setBounds(762, 43, 235, 14);
		contentPanel.add(totalPaymentText);
		
		payInAdvanceScrollPane = new JScrollPane();
		payInAdvanceScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		payInAdvanceScrollPane.setBounds(31, 356, 300, 180);
		contentPanel.add(payInAdvanceScrollPane);
		
		payInAdvancePanel = new JPanel();
		payInAdvanceScrollPane.setViewportView(payInAdvancePanel);
		payInAdvancePanel.setLayout(new BoxLayout(payInAdvancePanel, BoxLayout.Y_AXIS));
		
		int counter = 0;

		for(int  i = LOAN_DETAILS.getPaidMonths() + 1; i <= LOAN_REQUEST_DETAILS.getLoanedProductMonthsToPay() ; i++) {
			
			JPanel panel_2 = new JPanel();
			panel_2.setPreferredSize(new Dimension(298, 40));
			panel_2.setMinimumSize(new Dimension(700, 40));
			payInAdvancePanel.add(panel_2);
			panel_2.setMaximumSize(new Dimension(700, 40));
			panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
			panel_2.add(Box.createRigidArea(new Dimension(20,0)));
			
			JCheckBox payCheckBox = new JCheckBox("");
			payCheckBox.setHorizontalAlignment(SwingConstants.LEFT);
			payCheckBox.setFont(new Font("Dialog", Font.PLAIN, 12));
			monthCheckBoxes.add(payCheckBox);
			
			if(counter == 0) {
				payCheckBox.setSelected(true);
			} else if (counter == 1) {
				payCheckBox.setEnabled(true);
			}
			else {
				payCheckBox.setEnabled(false);
			}
			
			payCheckBox.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JCheckBox clickedCheckBox = (JCheckBox) e.getSource();
					int clickedIndex = findIndexOfClickedCheckbox(clickedCheckBox);
					
					
					if(monthCheckBoxes.get(clickedIndex).isSelected()) {
						for(int i = clickedIndex + 1; i >= 0 ; i--) {
							if(clickedIndex != monthCheckBoxes.size() && clickedIndex != monthCheckBoxes.size() - 1) {
								monthCheckBoxes.get(i).setEnabled(true);
							}
						}
					} else {
						for(int i = clickedIndex + 1; i < monthCheckBoxes.size(); i++) {
							
							if(clickedIndex != monthCheckBoxes.size()) {
								monthCheckBoxes.get(i).setEnabled(false);
								
								monthCheckBoxes.get(i).setSelected(false);
							}
							
							
						}
					}
					updateTotalPayment();
					
					
				}
				
			});
			
			panel_2.add(monthCheckBoxes.get(counter));
			panel_2.add(Box.createRigidArea(new Dimension(20,0)));
			
			JLabel payMonthLabel = new JLabel(String.valueOf(i));
			payMonthLabel.setMaximumSize(new Dimension(30, 14));
			payMonthLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
			panel_2.add(payMonthLabel);
			panel_2.add(Box.createRigidArea(new Dimension(50,0)));
			
			
			JLabel payAmountLabel = new JLabel("₱ " + LOAN_REQUEST_DETAILS.getMonthlyPayment());
			payAmountLabel.setMaximumSize(new Dimension(100, 14));
			payAmountLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
			panel_2.add(payAmountLabel);
			panel_2.add(Box.createRigidArea(new Dimension(20,0)));

			counter++;
		}
		
		
		lblMonth = new JLabel("Month");
		
		lblMonth.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblMonth.setBounds(98, 331, 37, 14);
		contentPanel.add(lblMonth);
		
		lblAmountDue = new JLabel("Amount Due");
		
		lblAmountDue.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblAmountDue.setBounds(177, 331, 74, 14);
		contentPanel.add(lblAmountDue);
			
		cardSecurityCodeFIeld.setVisible(false);
		
		
		selectEWalletPanel.setVisible(false);
		eWalletInformationPanel.setVisible(false);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Continue Payment");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println(loanReqDetails.getLoanerId());
						if(paymentMethod.equals("bank")) {
							
							String cardNumberText = cardNumberField.getText();
							String cardExpDateText = cardExpDateField.getText();
							String cardSecurityCodeText = cardSecurityCodeField.getText();
							
							
							//Check for empty inputs
							boolean isInputEmpty = HelperUtility.isInputFieldEmpty(cardNumberText) || 
									HelperUtility.isInputFieldEmpty(cardExpDateText) || 
									HelperUtility.isInputFieldEmpty(cardSecurityCodeText); 
							
							if(isInputEmpty) {
								if(HelperUtility.isInputFieldEmpty(cardNumberText)) {
									JOptionPane.showMessageDialog(null, "Please enter a card number to continue", "Missing Card Number", JOptionPane.WARNING_MESSAGE);
									return;
								} else if (HelperUtility.isInputFieldEmpty(cardExpDateText)) {
									JOptionPane.showMessageDialog(null, "Please enter a card expiration date to continue", "Missing Card Expiration Date", JOptionPane.WARNING_MESSAGE);
									return;
								} else if (HelperUtility.isInputFieldEmpty(cardSecurityCodeText)) {
									JOptionPane.showMessageDialog(null, "Please enter a card security code to continue", "Missing Card Security Code", JOptionPane.WARNING_MESSAGE);
									return;
								}
							}
							
							String CARD_NUMBER_REGEX = "^\\d{11}$";
							String CARD_EXP_DATE_REGEX = "^(0[1-9]|1[0-2])/[1-9][1-9]$";
							String CARD_SEC_CODE_REGEX = "^\\d{3}$";
							boolean isInputValid = cardNumberText.matches(CARD_NUMBER_REGEX) && cardExpDateText.matches(CARD_EXP_DATE_REGEX) && cardSecurityCodeText.matches(CARD_SEC_CODE_REGEX);
							
							if(!isInputValid) {
								
								JOptionPane.showMessageDialog(null, "Invalid Input", "Invalid Action", JOptionPane.ERROR_MESSAGE);
								return;

							} 
							
							//Verify Credentials
							
							boolean isCredentialsValid = cardNumberText.equals(BankAccount.cardNumber) && cardExpDateText.equals(BankAccount.expirationDate) && cardSecurityCodeText.equals(BankAccount.cvvc);
							
							if(!isCredentialsValid) {
								JOptionPane.showMessageDialog(null, "Please make sure that the credentials you entered are correct.", "Invalid Credentials", JOptionPane.ERROR_MESSAGE);
								return;
							}
							
							JOptionPane.showMessageDialog(null, "Payment Successful", "Invalid Action", JOptionPane.INFORMATION_MESSAGE);
							
							HelperUtility.closeDialog(LoanPaymentPanel.this);
						
						} else if(paymentMethod.equals("ewallet")) {
							String mobileNumberText = mobileNumberField.getText();
							String pinCodeText = pinCodeField.getText();
	
							//Check for empty inputs
							boolean isInputEmpty = HelperUtility.isInputFieldEmpty(mobileNumberText) || 
									HelperUtility.isInputFieldEmpty(pinCodeText); 
							
							if(isInputEmpty) {
								if(HelperUtility.isInputFieldEmpty(mobileNumberText)) {
									JOptionPane.showMessageDialog(null, "Please enter a mobile number to continue", "Missing Mobile Number", JOptionPane.WARNING_MESSAGE);
									return;
								} else if (HelperUtility.isInputFieldEmpty(pinCodeText)) {
									JOptionPane.showMessageDialog(null, "Please enter a pin code to continue", "Missing Pin Code", JOptionPane.WARNING_MESSAGE);
									return;
								} 
							}
							
							String MOBILE_NUMBER_REGEX = "^\\d{11}$";
							String PIN_CODE_REGEX = "^\\d{4}$";
							boolean isInputValid = mobileNumberText.matches(MOBILE_NUMBER_REGEX) && pinCodeText.matches(PIN_CODE_REGEX);
							
							
							if(!isInputValid) {
								JOptionPane.showMessageDialog(null, "Invalid Input", "Invalid Action", JOptionPane.ERROR_MESSAGE);
								return;

							} 
							
							//Verify Credentials
							
							boolean isCredentialsValid = mobileNumberText.equals(EWalletAccount.phoneNumber) && pinCodeText.equals(EWalletAccount.pinCode);
							
							if(!isCredentialsValid) {
								JOptionPane.showMessageDialog(null, "Please make sure that the credentials you entered are correct.", "Invalid Credentials", JOptionPane.ERROR_MESSAGE);
								return;
							}
							
							//check for months paid
							int monthsPaidInAdvance = 0;
							
							for(JCheckBox checkBox : monthCheckBoxes) {
								if (checkBox.isSelected()) {
									monthsPaidInAdvance++;
								}
							}
										
							dbManager.connect();
							if(dbManager.payOnGoingLoan(LOAN_DETAILS, monthsPaidInAdvance)) {
								JOptionPane.showMessageDialog(null, "Payment Successful", "Invalid Action", JOptionPane.INFORMATION_MESSAGE);
								HelperUtility.closeDialog(LoanPaymentPanel.this);
							} else {
								JOptionPane.showMessageDialog(null, "Server Error", "Invalid Credentials", JOptionPane.ERROR_MESSAGE);
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
	private void showCardInformation() {
		selectEWalletPanel.setVisible(false);
		eWalletInformationPanel.setVisible(false);
		cardSecurityCodeFIeld.setVisible(true);
		paymentMethod = "bank";
	}
	
	private void showEWalletInformation() {
		eWalletInformationPanel.setVisible(true);
		cardSecurityCodeFIeld.setVisible(false);
		selectEWalletPanel.setVisible(true);
		paymentMethod = "ewallet";
	}
	
	private void showEWalletSelection() {
		eWalletInformationPanel.setVisible(false);
		cardSecurityCodeFIeld.setVisible(false);
		selectEWalletPanel.setVisible(true);
		paymentMethod = "ewallet";
	}
	
	private int findIndexOfClickedCheckbox(JCheckBox clickedCheckbox) {
	    for (int i = 0; i < monthCheckBoxes.size(); i++) {
	        if (monthCheckBoxes.get(i) == clickedCheckbox) {
	            return i;
	        }
	    }
	    return -1; // Error handling if checkbox not found
	}
	
	public void updateTotalPayment() {
		
		int checkBoxChecked = 0;
		for(JCheckBox checkbox : monthCheckBoxes) {
			if(checkbox != null && checkbox.isSelected()) {
				checkBoxChecked ++;
			}
		}
		totalPayment = LOAN_REQUEST_DETAILS.getMonthlyPayment() * checkBoxChecked;
		totalPaymentText.setText("₱ " + String.valueOf(totalPayment)); ;
		
	}
	
}
