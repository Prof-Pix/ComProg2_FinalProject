package AuthFrames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Components.GoToLoginPage;
import Components.InvalidInputPopup;
import User.Admin;
import User.AdminRegistrationData;
import UserEnums.UserRoles;
import Utilities.HelperUtility;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.UIManager;

public class RegisterAdminPage extends JFrame implements RegistrationPage {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField raUserField;
	private JTextField raFNameField;
	private JTextField raMiddleNameField;
	private JLabel raLastNameLabel;
	private JTextField raLastNameField;
	private JLabel raAgeLabel;
	private JTextField raPhoneNumberField;
	private JLabel raEmailLabel;
	private JTextField raAgeField;
	private JLabel raPhoneNumberLabel;
	private JTextField raEmailField;
	private JLabel raPasswordLabel;
	private JTextField raPasswordField;
	private JButton registerAdminButton;
	private JTextField raDayField;
	private JTextField raYearField;
	private JLabel lblAccountDetails;
	private JSeparator separator;
	private JLabel lblPersonalInformation;
	
	//Getting a reference of the frame
	JFrame thisFrame = this;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterAdminPage frame = new RegisterAdminPage();
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
	public RegisterAdminPage() {
		setResizable(false);
		setTitle("Admin Registration Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(434, 0, 450, 468);
		panel.setBackground(new Color(128, 0, 0));
		contentPane.add(panel);

		JLabel raUserLabel = new JLabel("Username:");
		raUserLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raUserLabel.setBounds(40, 53, 107, 14);
		contentPane.add(raUserLabel);

		raUserField = new JTextField();
		raUserField.setToolTipText("Must be at least eight (8) characters and start with an uppercase letter. No symbols are allowed.");
		raUserField.setBounds(194, 53, 192, 20);
		contentPane.add(raUserField);
		raUserField.setColumns(10);

		JLabel raFNameLabel = new JLabel("First Name:");
		raFNameLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raFNameLabel.setBounds(40, 154, 97, 14);
		contentPane.add(raFNameLabel);

		raFNameField = new JTextField();
		raFNameField.setToolTipText("Must start with an uppercase letter, and it cannot contain any symbols or numbers.");
		raFNameField.setBounds(194, 154, 192, 20);
		raFNameField.setColumns(10);
		contentPane.add(raFNameField);

		JLabel lblMiddlename = new JLabel("Middle Name:");
		lblMiddlename.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblMiddlename.setBounds(40, 185, 97, 14);
		contentPane.add(lblMiddlename);

		raMiddleNameField = new JTextField();
		raMiddleNameField.setToolTipText("Must start with an uppercase letter, and it cannot contain any symbols or numbers.");
		raMiddleNameField.setBounds(194, 185, 192, 20);
		raMiddleNameField.setColumns(10);
		contentPane.add(raMiddleNameField);

		raLastNameLabel = new JLabel("Last Name:");
		raLastNameLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raLastNameLabel.setBounds(40, 216, 97, 14);
		contentPane.add(raLastNameLabel);

		raLastNameField = new JTextField();
		raLastNameField.setToolTipText("Must start with an uppercase letter, and it cannot contain any symbols or numbers.");
		raLastNameField.setBounds(194, 216, 192, 20);
		raLastNameField.setColumns(10);
		contentPane.add(raLastNameField);

		raAgeLabel = new JLabel("Age:");
		raAgeLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raAgeLabel.setBounds(40, 276, 97, 18);
		contentPane.add(raAgeLabel);

		raPhoneNumberField = new JTextField();
		raPhoneNumberField.setToolTipText("Must be eleven (11) characters long.");
		raPhoneNumberField.setBounds(194, 341, 192, 20);
		raPhoneNumberField.setColumns(10);
		contentPane.add(raPhoneNumberField);

		raEmailLabel = new JLabel("Email:");
		raEmailLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raEmailLabel.setBounds(40, 311, 97, 14);
		contentPane.add(raEmailLabel);

		raAgeField = new JTextField();
		raAgeField.setToolTipText("Age must be at least 18 years old but not older than 60 years old.");
		raAgeField.setBounds(194, 278, 46, 20);
		raAgeField.setColumns(10);
		contentPane.add(raAgeField);

		raPhoneNumberLabel = new JLabel("Phone Number:");
		raPhoneNumberLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raPhoneNumberLabel.setBounds(40, 341, 97, 14);
		contentPane.add(raPhoneNumberLabel);

		raEmailField = new JTextField();
		raEmailField.setToolTipText("Sample Valid Email: xyz@hotmail.edu");
		raEmailField.setBounds(194, 309, 192, 20);
		raEmailField.setColumns(10);
		contentPane.add(raEmailField);

		raPasswordLabel = new JLabel("Password:");
		raPasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raPasswordLabel.setBounds(40, 84, 107, 14);
		contentPane.add(raPasswordLabel);

		raPasswordField = new JTextField();
		raPasswordField.setToolTipText("Must be eight (8) characters long.");
		raPasswordField.setColumns(10);
		raPasswordField.setBounds(194, 84, 192, 20);
		contentPane.add(raPasswordField);

		JLabel lblBirthday = new JLabel("Birthday (MM/DD/YYYY):");
		lblBirthday.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblBirthday.setBounds(40, 248, 129, 14);
		contentPane.add(lblBirthday);

		JTextField raMonthField = new JTextField();
		raMonthField.setToolTipText("Day of birthdate");
		raMonthField.setBounds(238, 247, 30, 20);
		contentPane.add(raMonthField);
		raMonthField.setColumns(10);

		raDayField = new JTextField();
		raDayField.setToolTipText("Month of Birthdate");
		raDayField.setColumns(10);
		raDayField.setBounds(194, 247, 30, 20);
		contentPane.add(raDayField);

		raYearField = new JTextField();
		raYearField.setToolTipText("Year of birthdate");
		raYearField.setColumns(10);
		raYearField.setBounds(282, 247, 38, 20);
		contentPane.add(raYearField);

		lblAccountDetails = new JLabel("Account Details");
		lblAccountDetails.setFont(new Font("Dialog", Font.BOLD, 15));
		lblAccountDetails.setBounds(20, 26, 153, 14);
		contentPane.add(lblAccountDetails);

		separator = new JSeparator();
		separator.setBounds(20, 114, 383, 7);
		contentPane.add(separator);

		lblPersonalInformation = new JLabel("Personal Information");
		lblPersonalInformation.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPersonalInformation.setBounds(20, 127, 153, 14);
		contentPane.add(lblPersonalInformation);

		registerAdminButton = new JButton("Register Admin Account");
		registerAdminButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String usernameText = raUserField.getText().trim();
				String passwordText = raPasswordField.getText().trim();
				String firstNameText = raFNameField.getText().trim();
				String middleNameText = raMiddleNameField.getText().trim();
				String lastNameText = raLastNameField.getText().trim();
				String monthText = raMonthField.getText().trim();
				String dayText = raDayField.getText().trim();
				String yearText = raYearField.getText().trim();
				String ageText = raAgeField.getText().trim();
				String emailText = raEmailField.getText().trim();
				String phoneNumberText = raPhoneNumberField.getText().trim();
				
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
						
						InvalidInputPopup popup = new InvalidInputPopup(RegisterAdminPage.this, errorText);
						popup.setVisible(true);
					}
					else { 
						
						//Proceeds if all of the inputs are not empty
						//Advanced Validation
						//For birthday
						LocalDate birthday = LocalDate.of(Integer.parseInt(yearText) , 
								Integer.parseInt(monthText) , 
								Integer.parseInt(dayText) );

						//For age
						int age = Integer.parseInt(ageText);

						//Instantiate the Admin Class
						Admin ad = new Admin(usernameText,
								passwordText,
								firstNameText,
								middleNameText,
								lastNameText,
								birthday ,
								age, 
								emailText,
								phoneNumberText);

						//Perform advance validations
						//Once the input are valid we now need to check if inputs values are already existing in the database
						if (ad.isUserInputValid() && ad.checkDuplicateInput().equals("ok")) {
							AdminRegistrationData adminRegData = new AdminRegistrationData(usernameText,
									passwordText,
									firstNameText,
									middleNameText,
									lastNameText,
									birthday ,
									age, 
									emailText,
									phoneNumberText);
							
							//If success send it to the database
							if(ad.registerAdmin(adminRegData)) {
								GoToLoginPage login = new GoToLoginPage();
								login.setVisible(true);
							} else {
								String errorText= "";
								InvalidInputPopup popup = new InvalidInputPopup(RegisterAdminPage.this, errorText);
								popup.setVisible(true);
							}

						} else {
							
							String errorText = ad.checkErrorInputRegistration();
							
							if (errorText == null) {
								errorText = ad.checkDuplicateInput();
							}
							
							InvalidInputPopup popup = new InvalidInputPopup(RegisterAdminPage.this, errorText);
							popup.setVisible(true);
						}

					}}

				catch (NumberFormatException numberEx){ //catch when use provides a non-integer value to some inputs that only accepts integer value
					String errorText = "Some fields only accepts integer values. Please try again.";
					
					InvalidInputPopup popup = new InvalidInputPopup(RegisterAdminPage.this, errorText);
					popup.setVisible(true);
				} 



			}		
		});
		registerAdminButton.setBounds(194, 378, 192, 48);
		contentPane.add(registerAdminButton);

		JLabel lblBirthday_1 = new JLabel("/");
		lblBirthday_1.setFont(new Font("Dialog", Font.BOLD, 13));
		lblBirthday_1.setBounds(229, 247, 11, 20);
		contentPane.add(lblBirthday_1);

		JLabel lblBirthday_1_1 = new JLabel("/");
		lblBirthday_1_1.setFont(new Font("Dialog", Font.BOLD, 13));
		lblBirthday_1_1.setBounds(273, 247, 11, 20);
		contentPane.add(lblBirthday_1_1);
		
		JButton alreadyAMemberButton = new JButton("Already an admin? Login");
		alreadyAMemberButton.setForeground(Color.BLUE);
		alreadyAMemberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage login = new LoginPage();
				login.setVisible(true);
				
				//For Closing Page
				HelperUtility.closePage(thisFrame);
				
			}
		});
		alreadyAMemberButton.setContentAreaFilled(false);
		alreadyAMemberButton.setBorderPainted(false); 
		alreadyAMemberButton.setBounds(194, 427, 192, 23);
		contentPane.add(alreadyAMemberButton);
	}

	@Override
	public boolean isSpecificFieldsEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}

