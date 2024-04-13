package AuthFrames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Components.GoToLoginPage;
import Components.InvalidInputPopup;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import UserEnums.MerchantCategory;
import Utilities.HelperUtility;
import JComboBoxRenderers.MerchantCategoryRenderer;
import User.Merchant;
import User.MerchantRegistrationData;
public class RegisterMerchantPage extends JFrame implements RegistrationPage{

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
	private JLabel raYearLabel;
	private JTextField rmDayField;
	private JTextField rmYearField;
	private JLabel lblAccountDetails;
	private JSeparator separator;
	private JLabel lblPersonalInformation;
	private JSeparator separator_1;
	private JLabel lblMerchantInformation;
	private JLabel lblMerchantName;
	private JTextField rmMerchantNameField;
	private JLabel lblCategory;
	private JLabel lblMerchantAddress;
	private JTextField rmMerchantAddress;
	private JComboBox merchantCategoryComboBox;
	
	//Getting a reference of the frame
	JFrame thisFrame = this;

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
		rmUserField.setBounds(171, 50, 192, 20);
		contentPane.add(rmUserField);
		rmUserField.setColumns(10);
		
		JLabel raFNameLabel = new JLabel("First Name:");
		raFNameLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raFNameLabel.setBounds(40, 154, 97, 14);
		contentPane.add(raFNameLabel);
		
		rmFNameField = new JTextField();
		rmFNameField.setBounds(171, 151, 192, 20);
		rmFNameField.setColumns(10);
		contentPane.add(rmFNameField);
		
		JLabel lblMiddlename = new JLabel("Middle Name:");
		lblMiddlename.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblMiddlename.setBounds(40, 185, 97, 14);
		contentPane.add(lblMiddlename);
		
		rmMiddleNameField = new JTextField();
		rmMiddleNameField.setBounds(171, 182, 192, 20);
		rmMiddleNameField.setColumns(10);
		contentPane.add(rmMiddleNameField);
		
		raLastNameLabel = new JLabel("Last Name:");
		raLastNameLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raLastNameLabel.setBounds(40, 216, 97, 14);
		contentPane.add(raLastNameLabel);
		
		rmLastNameField = new JTextField();
		rmLastNameField.setBounds(171, 213, 192, 20);
		rmLastNameField.setColumns(10);
		contentPane.add(rmLastNameField);
		
		raAgeLabel = new JLabel("Age:");
		raAgeLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raAgeLabel.setBounds(40, 276, 97, 18);
		contentPane.add(raAgeLabel);
		
		rmPhoneNumberField = new JTextField();
		rmPhoneNumberField.setBounds(171, 338, 192, 20);
		rmPhoneNumberField.setColumns(10);
		contentPane.add(rmPhoneNumberField);
		
		raEmailLabel = new JLabel("Email:");
		raEmailLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raEmailLabel.setBounds(40, 311, 97, 14);
		contentPane.add(raEmailLabel);
		
		rmAgeField = new JTextField();
		rmAgeField.setBounds(171, 275, 46, 20);
		rmAgeField.setColumns(10);
		contentPane.add(rmAgeField);
		
		raPhoneNumberLabel = new JLabel("Phone Number:");
		raPhoneNumberLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raPhoneNumberLabel.setBounds(40, 341, 97, 14);
		contentPane.add(raPhoneNumberLabel);
		
		rmEmailField = new JTextField();
		rmEmailField.setBounds(171, 306, 192, 20);
		rmEmailField.setColumns(10);
		contentPane.add(rmEmailField);
		
		raPasswordLabel = new JLabel("Password:");
		raPasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raPasswordLabel.setBounds(40, 84, 107, 14);
		contentPane.add(raPasswordLabel);
		
		rmPasswordField = new JTextField();
		rmPasswordField.setColumns(10);
		rmPasswordField.setBounds(171, 81, 192, 20);
		contentPane.add(rmPasswordField);
		
		JLabel lblBirthday = new JLabel("Birthday (MM/DD/YYYY):");
		lblBirthday.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblBirthday.setBounds(40, 248, 133, 14);
		contentPane.add(lblBirthday);
		
		raYearLabel = new JLabel("/");
		raYearLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		raYearLabel.setBounds(261, 248, 16, 14);
		contentPane.add(raYearLabel);
		
		JTextField rmMonthField = new JTextField();
		rmMonthField.setBounds(171, 245, 38, 20);
		contentPane.add(rmMonthField);
		rmMonthField.setColumns(10);
		
		rmDayField = new JTextField();
		rmDayField.setColumns(10);
		rmDayField.setBounds(219, 245, 38, 20);
		contentPane.add(rmDayField);
		
		rmYearField = new JTextField();
		rmYearField.setColumns(10);
		rmYearField.setBounds(268, 245, 38, 20);
		contentPane.add(rmYearField);
		
		lblAccountDetails = new JLabel("Account Details");
		lblAccountDetails.setFont(new Font("Dialog", Font.BOLD, 15));
		lblAccountDetails.setBounds(20, 26, 153, 14);
		contentPane.add(lblAccountDetails);
		
		separator = new JSeparator();
		separator.setBounds(20, 114, 372, 2);
		contentPane.add(separator);
		
		lblPersonalInformation = new JLabel("Personal Information");
		lblPersonalInformation.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPersonalInformation.setBounds(20, 127, 153, 14);
		contentPane.add(lblPersonalInformation);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(20, 371, 372, 2);
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
		rmMerchantNameField.setBounds(171, 406, 192, 20);
		contentPane.add(rmMerchantNameField);
		
		lblCategory = new JLabel("Category:");
		lblCategory.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCategory.setBounds(40, 442, 97, 14);
		contentPane.add(lblCategory);
		
		lblMerchantAddress = new JLabel("Address:");
		lblMerchantAddress.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblMerchantAddress.setBounds(40, 473, 107, 14);
		contentPane.add(lblMerchantAddress);
		
		rmMerchantAddress = new JTextField();
		rmMerchantAddress.setColumns(10);
		rmMerchantAddress.setBounds(171, 470, 192, 20);
		contentPane.add(rmMerchantAddress);
		
		merchantCategoryComboBox = new JComboBox();
		
		//For choices
		
		//For choices
		DefaultComboBoxModel<MerchantCategory> categModel = new DefaultComboBoxModel<>();
		categModel.addElement(null);		
		
		for (MerchantCategory categ: MerchantCategory.values()) {
			categModel.addElement(categ);
		}
		
		merchantCategoryComboBox.setModel(categModel);
		merchantCategoryComboBox.setRenderer(new MerchantCategoryRenderer());
		merchantCategoryComboBox.setBounds(171, 437, 192, 22);
		contentPane.add(merchantCategoryComboBox);
		
		registerLoanerButton = new JButton("Register Merchant Account");
		registerLoanerButton.setFont(new Font("Dialog", Font.PLAIN, 11));
		registerLoanerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String usernameText = rmUserField.getText().trim();
				String passwordText = rmPasswordField.getText().trim();
				String firstNameText = rmFNameField.getText().trim();
				String middleNameText = rmMiddleNameField.getText().trim();
				String lastNameText = rmLastNameField.getText().trim();
				String monthText = rmMonthField.getText().trim();
				String dayText = rmDayField.getText().trim();
				String yearText = rmYearField.getText().trim();
				String ageText = rmAgeField.getText().trim();
				String emailText = rmEmailField.getText().trim();
				String phoneNumberText = rmPhoneNumberField.getText().trim();
				String merchantNameText = rmMerchantNameField.getText().trim();
				String merchantAddressText = rmMerchantAddress.getText().trim();
				
				try {
					
					//Checks if one of the common inputs are invalid
					if (isCommonFieldsEmpty(usernameText,
							passwordText,
							firstNameText,
							middleNameText,
							lastNameText,
							monthText,
							dayText,
							yearText,
							ageText,
							emailText,
							phoneNumberText) || isSpecificFieldsEmpty()) {	
						
						String errorText = "Please check all the required fields.";
						InvalidInputPopup popup = new InvalidInputPopup(RegisterMerchantPage.this, errorText);
						popup.setVisible(true);
						
					}  else {
						
						String merchantCategoryText = merchantCategoryComboBox.getSelectedItem().toString().toLowerCase();
						//Proceeds if all of the inputs are not empty
						//Advanced Validation
						//For birthday
						LocalDate birthday = LocalDate.of(Integer.parseInt(yearText) , 
								Integer.parseInt(monthText) , 
								Integer.parseInt(dayText) );

						//For age
						int age = Integer.parseInt(ageText);
						
						//For phone number
						long phoneNumber = Long.parseLong(phoneNumberText);
					
						
//						
//						public Merchant(String username, String password, String firstName, String middleName, String lastName,
//								LocalDate birthdate, int age, String email, String phoneNumber, String merchantName,
//								MerchantCategory merchantCategory, String merchantAddress)
						
						Merchant merch = new Merchant(usernameText, 
								passwordText, 
								firstNameText, 
								middleNameText, 
								lastNameText, 
								birthday, 
								age, 
								emailText, 
								phoneNumberText, 
								merchantNameText, 
								merchantCategoryText, 
								merchantAddressText);
						
						if(merch.isUserInputValid() && merch.checkDuplicateInput().equals("ok")) {
							MerchantRegistrationData merchRegData = new MerchantRegistrationData(usernameText, 
								passwordText, 
								firstNameText, 
								middleNameText, 
								lastNameText, 
								birthday, 
								age, 
								emailText, 
								phoneNumberText, 
								merchantNameText, 
								merchantCategoryText, 
								merchantAddressText);
							
							//sending it to the data base
							if (merch.registerMerchant(merchRegData)) {
								GoToLoginPage login = new GoToLoginPage();
								login.setVisible(true);
							} else {
								String errorText= "";
								InvalidInputPopup popup = new InvalidInputPopup(RegisterMerchantPage.this, errorText);
								popup.setVisible(true);
							} 
						}else {
							
							String errorText = merch.checkErrorInputRegistration();
							
							if (errorText == null) {
								errorText = merch.checkDuplicateInput();
							}
							
							InvalidInputPopup popup = new InvalidInputPopup(RegisterMerchantPage.this, errorText);
							popup.setVisible(true);
						}
						
					}
				} catch (NumberFormatException numberEx) {
					String errorText = "Some fields only accepts integer values. Please try again.";

					InvalidInputPopup popup = new InvalidInputPopup(RegisterMerchantPage.this, errorText);
					popup.setVisible(true);
				}
				
				
			}
		});
		registerLoanerButton.setBounds(171, 507, 192, 48);
		contentPane.add(registerLoanerButton);
		
		JLabel raYearLabel_1 = new JLabel("/");
		raYearLabel_1.setFont(new Font("Dialog", Font.BOLD, 12));
		raYearLabel_1.setBounds(213, 248, 16, 14);
		contentPane.add(raYearLabel_1);
		
		JButton btnAlreadyAMerchant = new JButton("Already a merchant? Login");
		btnAlreadyAMerchant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage login = new LoginPage();
				login.setVisible(true);

				//For Closing Page
				HelperUtility.closePage(thisFrame);
			}
		});
		btnAlreadyAMerchant.setForeground(Color.BLUE);
		btnAlreadyAMerchant.setContentAreaFilled(false);
		btnAlreadyAMerchant.setBorderPainted(false);
		btnAlreadyAMerchant.setBounds(171, 553, 192, 23);
		contentPane.add(btnAlreadyAMerchant);
		
	}

	@Override
	public boolean isSpecificFieldsEmpty() {
		String merchantName = rmMerchantNameField.getText().trim();
		String merchantAddress = rmMerchantAddress.getText().trim();
		
		return merchantName == null || 
				merchantName.isEmpty() || 
				merchantAddress == null || 
				merchantAddress.isEmpty() || 
				merchantCategoryComboBox.getSelectedIndex() == -1;

		
	}
}
