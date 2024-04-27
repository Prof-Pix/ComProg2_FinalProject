package MerchantFramePanels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import Database.DatabaseManager;
import User.Merchant;
import Utilities.HelperUtility;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Dialog.ModalityType;

public class ViewMerchantProfileDialog extends JDialog {

	DatabaseManager dbManager = new DatabaseManager();
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblMerchantOwnerInformation;

	private static Merchant merchant;
	private static int MERCHANT_ID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewMerchantProfileDialog dialog = new ViewMerchantProfileDialog(MERCHANT_ID);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewMerchantProfileDialog(int merchantId) {
		setResizable(false);
		
		ViewMerchantProfileDialog.MERCHANT_ID = merchantId;
		try {
			dbManager.connect();
			merchant = dbManager.getMerchantData(MERCHANT_ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Merchant Profile");
		setBounds(100, 100, 701, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(37, 102, 112));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel merchantIcon = new JLabel("");
		merchantIcon.setBorder(new LineBorder(new Color(237, 250, 139)));
		merchantIcon.setBounds(33, 21, 150, 150);
		merchantIcon.setIcon(HelperUtility.resizeImage(HelperUtility.defaultMerchantImageFilePath, merchantIcon.getWidth(), merchantIcon.getHeight()));

		
		contentPanel.add(merchantIcon);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1.setBounds(224, 155, 73, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Name:");
		lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1_2.setBounds(224, 180, 73, 14);
		contentPanel.add(lblNewLabel_1_2);
		
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Gender:");
		lblNewLabel_1_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1_2_1.setBounds(224, 205, 73, 14);
		contentPanel.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Birthday:");
		lblNewLabel_1_2_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1_2_1_1.setBounds(224, 230, 73, 14);
		contentPanel.add(lblNewLabel_1_2_1_1);
		

		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("Age:");
		lblNewLabel_1_2_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2_1_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1_2_1_1_1.setBounds(224, 255, 73, 14);
		contentPanel.add(lblNewLabel_1_2_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1_1 = new JLabel("Email:");
		lblNewLabel_1_2_1_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1_2_1_1_1_1.setBounds(224, 280, 73, 14);
		contentPanel.add(lblNewLabel_1_2_1_1_1_1);
		
		
		JLabel lblNewLabel_1_2_1_1_1_1_1 = new JLabel("Phone Number:");
		lblNewLabel_1_2_1_1_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2_1_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1_2_1_1_1_1_1.setBounds(224, 305, 98, 14);
		contentPanel.add(lblNewLabel_1_2_1_1_1_1_1);
		
		
		JLabel merchantNameLabel = new JLabel("Merchant Name:");
		merchantNameLabel.setForeground(new Color(255, 255, 255));
		merchantNameLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		merchantNameLabel.setBounds(223, 46, 98, 14);
		contentPanel.add(merchantNameLabel);
		
		JLabel merchantCategLabel = new JLabel("Category:");
		merchantCategLabel.setForeground(new Color(255, 255, 255));
		merchantCategLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		merchantCategLabel.setBounds(223, 71, 98, 14);
		contentPanel.add(merchantCategLabel);
		
		JLabel merchantLocationLabel = new JLabel("Location:");
		merchantLocationLabel.setForeground(new Color(255, 255, 255));
		merchantLocationLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		merchantLocationLabel.setBounds(223, 96, 98, 14);
		contentPanel.add(merchantLocationLabel);
		
		JLabel lblMerchantDetails = new JLabel("Merchant Details");
		lblMerchantDetails.setForeground(Color.WHITE);
		lblMerchantDetails.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMerchantDetails.setBounds(213, 21, 153, 14);
		contentPanel.add(lblMerchantDetails);
		
		JLabel merchantName = new JLabel(merchant.getMerchantName());
		merchantName.setForeground(new Color(255, 255, 255));
		merchantName.setFont(new Font("Dialog", Font.PLAIN, 12));
		merchantName.setBounds(331, 46, 344, 14);
		contentPanel.add(merchantName);
		
		JLabel category = new JLabel(HelperUtility.capitalizeWords(merchant.getMerchantCategory()));
		category.setForeground(new Color(255, 255, 255));
		category.setFont(new Font("Dialog", Font.PLAIN, 12));
		category.setBounds(331, 72, 344, 14);
		contentPanel.add(category);
		
		JLabel location = new JLabel(merchant.getMerchantAddress());
		location.setForeground(new Color(255, 255, 255));
		location.setFont(new Font("Dialog", Font.PLAIN, 12));
		location.setBounds(331, 97, 344, 14);
		contentPanel.add(location);
		
		lblMerchantOwnerInformation = new JLabel("Owner Details:");
		lblMerchantOwnerInformation.setForeground(Color.WHITE);
		lblMerchantOwnerInformation.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMerchantOwnerInformation.setBounds(213, 130, 181, 14);
		contentPanel.add(lblMerchantOwnerInformation);
		
		JLabel username = new JLabel(merchant.getUsername());
		username.setForeground(new Color(255, 255, 255));
		username.setFont(new Font("Dialog", Font.PLAIN, 12));
		username.setBounds(331, 155, 344, 14);
		contentPanel.add(username);
		
		JLabel fullname = new JLabel(merchant.getFullName());
		fullname.setForeground(new Color(255, 255, 255));
		fullname.setFont(new Font("Dialog", Font.PLAIN, 12));
		fullname.setBounds(331, 181, 344, 14);
		contentPanel.add(fullname);
		
		JLabel gender = new JLabel(merchant.getGender());
		gender.setForeground(new Color(255, 255, 255));
		gender.setFont(new Font("Dialog", Font.PLAIN, 12));
		gender.setBounds(331, 206, 344, 14);
		contentPanel.add(gender);
		
		JLabel birthdate = new JLabel(merchant.getBirthdate().toString());
		birthdate.setForeground(new Color(255, 255, 255));
		birthdate.setFont(new Font("Dialog", Font.PLAIN, 12));
		birthdate.setBounds(331, 230, 344, 14);
		contentPanel.add(birthdate);
		
		JLabel age = new JLabel(String.valueOf(merchant.getAge()));
		age.setForeground(new Color(255, 255, 255));
		age.setFont(new Font("Dialog", Font.PLAIN, 12));
		age.setBounds(331, 256, 344, 14);
		contentPanel.add(age);
		
		JLabel email = new JLabel(merchant.getEmail());
		email.setForeground(new Color(255, 255, 255));
		email.setFont(new Font("Dialog", Font.PLAIN, 12));
		email.setBounds(331, 281, 344, 14);
		contentPanel.add(email);
		
		JLabel phonenumber = new JLabel(merchant.getPhoneNumber());
		phonenumber.setForeground(new Color(255, 255, 255));
		phonenumber.setFont(new Font("Dialog", Font.PLAIN, 12));
		phonenumber.setBounds(331, 306, 344, 14);
		contentPanel.add(phonenumber);
	}
}
