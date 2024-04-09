package AuthFrames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RegisterMerchantPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField rmUserField;
	private JTextField rmFNameField;
	private JTextField rmMiddleNameField;
	private JLabel raLastNameLabel;
	private JTextField rmLastNameField;
	private JLabel raAgeLabel;
	private JTextField rmPhoneNumberField;
	private JLabel raEmailLabel;
	private JTextField rmAgeField;
	private JLabel raPhoneNumberLabel;
	private JTextField rmEmailField;
	private JLabel raPasswordLabel;
	private JTextField rmPasswordField;
	private JButton registerLoanerButton;
	private JLabel raMonthLabel;
	private JLabel raDayLabel;
	private JLabel raYearLabel;
	private JTextField rmDayField;
	private JTextField rmYearField;
	private JLabel lblAccountDetails;
	private JSeparator separator;
	private JLabel lblPersonalInformation;
	private JLabel lblNewLabel;
	private JSeparator separator_1;
	private JLabel lblMerchantInformation;
	private JLabel lblMerchantName;
	private JTextField rmMerchantNameField;
	private JLabel lblCategory;
	private JComboBox<?> rmMerchantCategory;
	private JLabel lblMerchantAddress;
	private JTextField rmMerchantAddress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterMerchantPage frame = new RegisterMerchantPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterMerchantPage() {
		setTitle("Merchant Registration Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(434, 0, 450, 601);
		panel.setBackground(new Color(128, 0, 0));
		contentPane.add(panel);
		
		JLabel raUserLabel = new JLabel("Username:");
		raUserLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raUserLabel.setBounds(40, 53, 107, 14);
		contentPane.add(raUserLabel);
		
		rmUserField = new JTextField();
		rmUserField.setBounds(147, 51, 192, 20);
		contentPane.add(rmUserField);
		rmUserField.setColumns(10);
		
		JLabel raFNameLabel = new JLabel("First Name:");
		raFNameLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raFNameLabel.setBounds(40, 154, 97, 14);
		contentPane.add(raFNameLabel);
		
		rmFNameField = new JTextField();
		rmFNameField.setBounds(147, 152, 192, 20);
		rmFNameField.setColumns(10);
		contentPane.add(rmFNameField);
		
		JLabel lblMiddlename = new JLabel("Middle Name:");
		lblMiddlename.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblMiddlename.setBounds(40, 185, 97, 14);
		contentPane.add(lblMiddlename);
		
		rmMiddleNameField = new JTextField();
		rmMiddleNameField.setBounds(147, 183, 192, 20);
		rmMiddleNameField.setColumns(10);
		contentPane.add(rmMiddleNameField);
		
		raLastNameLabel = new JLabel("Last Name:");
		raLastNameLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raLastNameLabel.setBounds(40, 216, 97, 14);
		contentPane.add(raLastNameLabel);
		
		rmLastNameField = new JTextField();
		rmLastNameField.setBounds(147, 214, 192, 20);
		rmLastNameField.setColumns(10);
		contentPane.add(rmLastNameField);
		
		raAgeLabel = new JLabel("Age:");
		raAgeLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raAgeLabel.setBounds(40, 276, 97, 18);
		contentPane.add(raAgeLabel);
		
		rmPhoneNumberField = new JTextField();
		rmPhoneNumberField.setBounds(147, 339, 192, 20);
		rmPhoneNumberField.setColumns(10);
		contentPane.add(rmPhoneNumberField);
		
		raEmailLabel = new JLabel("Email:");
		raEmailLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raEmailLabel.setBounds(40, 311, 97, 14);
		contentPane.add(raEmailLabel);
		
		rmAgeField = new JTextField();
		rmAgeField.setBounds(147, 276, 46, 20);
		rmAgeField.setColumns(10);
		contentPane.add(rmAgeField);
		
		raPhoneNumberLabel = new JLabel("Phone Number:");
		raPhoneNumberLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raPhoneNumberLabel.setBounds(40, 341, 97, 14);
		contentPane.add(raPhoneNumberLabel);
		
		rmEmailField = new JTextField();
		rmEmailField.setBounds(147, 307, 192, 20);
		rmEmailField.setColumns(10);
		contentPane.add(rmEmailField);
		
		raPasswordLabel = new JLabel("Password:");
		raPasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raPasswordLabel.setBounds(40, 84, 107, 14);
		contentPane.add(raPasswordLabel);
		
		rmPasswordField = new JTextField();
		rmPasswordField.setColumns(10);
		rmPasswordField.setBounds(147, 82, 192, 20);
		contentPane.add(rmPasswordField);
		
		registerLoanerButton = new JButton("Register Merchant Account");
		registerLoanerButton.setFont(new Font("Dialog", Font.PLAIN, 11));
		registerLoanerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		registerLoanerButton.setBounds(147, 508, 192, 48);
		contentPane.add(registerLoanerButton);
		
		JLabel lblBirthday = new JLabel("Birthday:");
		lblBirthday.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblBirthday.setBounds(40, 248, 97, 14);
		contentPane.add(lblBirthday);
		
		raMonthLabel = new JLabel("MM");
		raMonthLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		raMonthLabel.setBounds(147, 248, 16, 14);
		contentPane.add(raMonthLabel);
		
		raDayLabel = new JLabel("DD");
		raDayLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		raDayLabel.setBounds(215, 249, 16, 14);
		contentPane.add(raDayLabel);
		
		raYearLabel = new JLabel("YY");
		raYearLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		raYearLabel.setBounds(283, 248, 16, 14);
		contentPane.add(raYearLabel);
		
		JTextField rmMonthField = new JTextField();
		rmMonthField.setBounds(167, 245, 38, 20);
		contentPane.add(rmMonthField);
		rmMonthField.setColumns(10);
		
		rmDayField = new JTextField();
		rmDayField.setColumns(10);
		rmDayField.setBounds(235, 245, 38, 20);
		contentPane.add(rmDayField);
		
		rmYearField = new JTextField();
		rmYearField.setColumns(10);
		rmYearField.setBounds(303, 245, 38, 20);
		contentPane.add(rmYearField);
		
		lblAccountDetails = new JLabel("Account Details");
		lblAccountDetails.setFont(new Font("Dialog", Font.BOLD, 15));
		lblAccountDetails.setBounds(20, 26, 153, 14);
		contentPane.add(lblAccountDetails);
		
		separator = new JSeparator();
		separator.setBounds(20, 114, 335, 2);
		contentPane.add(separator);
		
		lblPersonalInformation = new JLabel("Personal Information");
		lblPersonalInformation.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPersonalInformation.setBounds(20, 127, 153, 14);
		contentPane.add(lblPersonalInformation);
		
		lblNewLabel = new JLabel("Already a member? Login");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setBackground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 11));
		lblNewLabel.setBounds(171, 560, 147, 14);
		contentPane.add(lblNewLabel);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(20, 371, 335, 2);
		contentPane.add(separator_1);
		
		lblMerchantInformation = new JLabel("Merchant Information");
		lblMerchantInformation.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMerchantInformation.setBounds(20, 384, 153, 14);
		contentPane.add(lblMerchantInformation);
		
		lblMerchantName = new JLabel("Merchant Name:");
		lblMerchantName.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblMerchantName.setBounds(40, 409, 97, 14);
		contentPane.add(lblMerchantName);
		
		rmMerchantNameField = new JTextField();
		rmMerchantNameField.setColumns(10);
		rmMerchantNameField.setBounds(147, 407, 192, 20);
		contentPane.add(rmMerchantNameField);
		
		lblCategory = new JLabel("Category:");
		lblCategory.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCategory.setBounds(40, 442, 97, 14);
		contentPane.add(lblCategory);
		
		rmMerchantCategory = new JComboBox<Object>();
		rmMerchantCategory.setFont(new Font("Dialog", Font.PLAIN, 11));
		rmMerchantCategory.setModel(new DefaultComboBoxModel(new String[] {"Gadgets", "Electronics", "Appliances", "Sports", "Fashion", "Furniture", "Vehicles"}));
		rmMerchantCategory.setSelectedIndex(0);
		rmMerchantCategory.setBounds(147, 439, 192, 22);
		contentPane.add(rmMerchantCategory);
		
		lblMerchantAddress = new JLabel("Address:");
		lblMerchantAddress.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblMerchantAddress.setBounds(40, 473, 107, 14);
		contentPane.add(lblMerchantAddress);
		
		rmMerchantAddress = new JTextField();
		rmMerchantAddress.setColumns(10);
		rmMerchantAddress.setBounds(147, 471, 192, 20);
		contentPane.add(rmMerchantAddress);
	}
}
