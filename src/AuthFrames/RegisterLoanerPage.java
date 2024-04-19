package AuthFrames;

import JComboBoxRenderers.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Components.GoToLoginPage;
import User.Loaner;
import User.LoanerRegistrationData;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import Utilities.HelperUtility;
import UserEnums.Occupation;
import UserEnums.SourceOfIncome;
import UserEnums.MonthlyIncome;
import javax.swing.JRadioButton;

public class RegisterLoanerPage extends JFrame implements RegistrationPage{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField rlUserField;
	private JTextField rlFNameField;
	private JTextField rlMiddleNameField;
	private JLabel raLastNameLabel;
	private JTextField rlLastNameField;
	private JLabel raAgeLabel;
	private JTextField rlPhoneNumberField;
	private JLabel raEmailLabel;
	private JTextField rlAgeField;
	private JLabel raPhoneNumberLabel;
	private JTextField rlEmailField;
	private JLabel raPasswordLabel;
	private JTextField rlPasswordField;
	private JButton registerLoanerButton;
	private JTextField rlDayField;
	private JTextField rlYearField;
	private JLabel lblAccountDetails;
	private JSeparator separator;
	private JLabel lblPersonalInformation;
	private JLabel lblSourceOfIncome;
	private JLabel lblOccupation;
	private JLabel lblMonthlyIncome;
	private JLabel lblBirthday_1;
	private JLabel lblBirthday_2;

	JComboBox sourceOfIncomeComboBox = new JComboBox();
	JComboBox occupationComboBox = new JComboBox();
	JComboBox monthlyIncomeComboBox = new JComboBox();

	//Getting a reference of the frame
	JFrame thisFrame = this;

	String selectedGenderText;
	private JLabel raGenderLabel;
	private JRadioButton maleRadioButton;
	private JRadioButton femaleRadioButton;
	private JRadioButton othersRadioButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterLoanerPage frame = new RegisterLoanerPage();
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
	public RegisterLoanerPage() {
		setResizable(false);
		setTitle("Loaner Registration Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 622);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(434, 0, 450, 590);
		panel.setBackground(new Color(128, 0, 0));
		contentPane.add(panel);

		JLabel raUserLabel = new JLabel("Username:");
		raUserLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raUserLabel.setBounds(40, 53, 107, 14);
		contentPane.add(raUserLabel);

		rlUserField = new JTextField();
		rlUserField.setBounds(172, 49, 192, 20);
		contentPane.add(rlUserField);
		rlUserField.setColumns(10);

		JLabel raFNameLabel = new JLabel("First Name:");
		raFNameLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raFNameLabel.setBounds(40, 154, 97, 14);
		contentPane.add(raFNameLabel);

		rlFNameField = new JTextField();
		rlFNameField.setBounds(172, 150, 192, 20);
		rlFNameField.setColumns(10);
		contentPane.add(rlFNameField);

		JLabel lblMiddlename = new JLabel("Middle Name:");
		lblMiddlename.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblMiddlename.setBounds(40, 185, 97, 14);
		contentPane.add(lblMiddlename);

		rlMiddleNameField = new JTextField();
		rlMiddleNameField.setBounds(172, 181, 192, 20);
		rlMiddleNameField.setColumns(10);
		contentPane.add(rlMiddleNameField);

		raLastNameLabel = new JLabel("Last Name:");
		raLastNameLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raLastNameLabel.setBounds(40, 216, 97, 14);
		contentPane.add(raLastNameLabel);

		rlLastNameField = new JTextField();
		rlLastNameField.setBounds(172, 212, 192, 20);
		rlLastNameField.setColumns(10);
		contentPane.add(rlLastNameField);

		raAgeLabel = new JLabel("Age:");
		raAgeLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raAgeLabel.setBounds(40, 306, 97, 18);
		contentPane.add(raAgeLabel);

		rlPhoneNumberField = new JTextField();
		rlPhoneNumberField.setBounds(172, 367, 192, 20);
		rlPhoneNumberField.setColumns(10);
		contentPane.add(rlPhoneNumberField);

		raEmailLabel = new JLabel("Email:");
		raEmailLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raEmailLabel.setBounds(40, 341, 97, 14);
		contentPane.add(raEmailLabel);

		rlAgeField = new JTextField();
		rlAgeField.setBounds(172, 304, 46, 20);
		rlAgeField.setColumns(10);
		contentPane.add(rlAgeField);

		raPhoneNumberLabel = new JLabel("Phone Number:");
		raPhoneNumberLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raPhoneNumberLabel.setBounds(40, 371, 97, 14);
		contentPane.add(raPhoneNumberLabel);

		rlEmailField = new JTextField();
		rlEmailField.setBounds(172, 335, 192, 20);
		rlEmailField.setColumns(10);
		contentPane.add(rlEmailField);

		raPasswordLabel = new JLabel("Password:");
		raPasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raPasswordLabel.setBounds(40, 84, 107, 14);
		contentPane.add(raPasswordLabel);

		rlPasswordField = new JTextField();
		rlPasswordField.setColumns(10);
		rlPasswordField.setBounds(172, 80, 192, 20);
		contentPane.add(rlPasswordField);

		JLabel lblBirthday = new JLabel("Birthday (MM/DD/YYYY):");
		lblBirthday.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblBirthday.setBounds(40, 248, 133, 14);
		contentPane.add(lblBirthday);

		JTextField rlMonthField = new JTextField();
		rlMonthField.setBounds(172, 246, 38, 20);
		contentPane.add(rlMonthField);
		rlMonthField.setColumns(10);

		rlDayField = new JTextField();
		rlDayField.setColumns(10);
		rlDayField.setBounds(223, 246, 38, 20);
		contentPane.add(rlDayField);

		rlYearField = new JTextField();
		rlYearField.setColumns(10);
		rlYearField.setBounds(273, 246, 38, 20);
		contentPane.add(rlYearField);

		lblAccountDetails = new JLabel("Account Details");
		lblAccountDetails.setFont(new Font("Dialog", Font.BOLD, 15));
		lblAccountDetails.setBounds(20, 26, 153, 14);
		contentPane.add(lblAccountDetails);

		separator = new JSeparator();
		separator.setBounds(20, 114, 383, 2);
		contentPane.add(separator);

		lblPersonalInformation = new JLabel("Personal Information");
		lblPersonalInformation.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPersonalInformation.setBounds(20, 127, 153, 14);
		contentPane.add(lblPersonalInformation);

		lblSourceOfIncome = new JLabel("Source of Income: ");
		lblSourceOfIncome.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSourceOfIncome.setBounds(40, 401, 107, 14);
		contentPane.add(lblSourceOfIncome);

		lblOccupation = new JLabel("Occupation:");
		lblOccupation.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblOccupation.setBounds(40, 433, 97, 14);
		contentPane.add(lblOccupation);

		lblMonthlyIncome = new JLabel("Monthly Income:");
		lblMonthlyIncome.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblMonthlyIncome.setBounds(40, 464, 97, 14);
		contentPane.add(lblMonthlyIncome);

		//SOURCE OF INCOME CHOICES
		//For choices
		DefaultComboBoxModel<SourceOfIncome> socModel = new DefaultComboBoxModel<>();
		socModel.addElement(null);		

		for (SourceOfIncome soc: SourceOfIncome.values()) {
			socModel.addElement(soc);
		}

		sourceOfIncomeComboBox.setModel(socModel);
		sourceOfIncomeComboBox.setRenderer(new SourceOfIncomeRenderer());
		sourceOfIncomeComboBox.setBounds(172, 396, 192, 22);
		contentPane.add(sourceOfIncomeComboBox);

		//OCCUPATION CHOICES
		//For choices
		DefaultComboBoxModel<Occupation> occupationModel = new DefaultComboBoxModel<>();
		occupationModel.addElement(null);		

		for (Occupation occupation: Occupation.values()) {
			occupationModel.addElement(occupation);
		}
		occupationComboBox.setModel(occupationModel);
		occupationComboBox.setRenderer(new OccupationRenderer());
		occupationComboBox.setBounds(172, 428, 192, 22);
		contentPane.add(occupationComboBox);

		//MONTHLY INCOME CHOICES
		monthlyIncomeComboBox.setModel(new DefaultComboBoxModel(new String[] {"--- Select monthly income ---", "₱ 10,000 or less", "₱ 10,000 to ₱ 30,000", "₱ 30,000 to ₱ 50,000", "₱ 50,000 or above"}));
		monthlyIncomeComboBox.setBounds(172, 459, 192, 22);
		contentPane.add(monthlyIncomeComboBox);

		lblBirthday_1 = new JLabel("/");
		lblBirthday_1.setFont(new Font("Dialog", Font.BOLD, 13));
		lblBirthday_1.setBounds(214, 245, 11, 20);
		contentPane.add(lblBirthday_1);

		lblBirthday_2 = new JLabel("/");
		lblBirthday_2.setFont(new Font("Dialog", Font.BOLD, 13));
		lblBirthday_2.setBounds(265, 245, 11, 20);
		contentPane.add(lblBirthday_2);

		raGenderLabel = new JLabel("Gender:");
		raGenderLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raGenderLabel.setBounds(38, 277, 97, 18);
		contentPane.add(raGenderLabel);

		maleRadioButton = new JRadioButton("Male");
		maleRadioButton.setFont(new Font("Dialog", Font.PLAIN, 11));
		maleRadioButton.setBounds(172, 277, 54, 23);
		contentPane.add(maleRadioButton);

		femaleRadioButton = new JRadioButton("Female");
		femaleRadioButton.setFont(new Font("Dialog", Font.PLAIN, 11));
		femaleRadioButton.setBounds(223, 277, 64, 23);
		contentPane.add(femaleRadioButton);

		othersRadioButton = new JRadioButton("Others");
		othersRadioButton.setFont(new Font("Dialog", Font.PLAIN, 11));
		othersRadioButton.setBounds(283, 277, 64, 23);
		contentPane.add(othersRadioButton);

		final ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(maleRadioButton);
		genderGroup.add(femaleRadioButton);
		genderGroup.add(othersRadioButton);

		maleRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (maleRadioButton.isSelected()) {
					// Code to execute when radioButton1 is selected
					selectedGenderText = maleRadioButton.getText();
				}
			}
		});
		femaleRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (femaleRadioButton.isSelected()) {
					// Code to execute when radioButton1 is selected
					selectedGenderText = femaleRadioButton.getText();
				}
			}
		});
		othersRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (othersRadioButton.isSelected()) {
					// Code to execute when radioButton1 is selected
					selectedGenderText = othersRadioButton.getText();
				}
			}
		});

		registerLoanerButton = new JButton("Register Loaner Account");
		registerLoanerButton.setFont(new Font("Dialog", Font.PLAIN, 11));
		registerLoanerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String usernameText = rlUserField.getText().trim();
				String passwordText = rlPasswordField.getText().trim();
				String firstNameText = rlFNameField.getText().trim();
				String middleNameText = rlMiddleNameField.getText().trim();
				String lastNameText = rlLastNameField.getText().trim();
				String monthText = rlMonthField.getText().trim();
				String dayText = rlDayField.getText().trim();
				String yearText = rlYearField.getText().trim();
				String ageText = rlAgeField.getText().trim();
				String emailText = rlEmailField.getText().trim();
				String phoneNumberText = rlPhoneNumberField.getText().trim();
				String monthlyIncomeText = null;

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
							selectedGenderText,
							ageText,
							emailText,
							phoneNumberText) || isSpecificFieldsEmpty()) {	

						if (HelperUtility.isInputFieldEmpty(usernameText)) {
							JOptionPane.showMessageDialog(null, "Please enter a username to continue.", "Missing Username!", JOptionPane.ERROR_MESSAGE);
						} else if (HelperUtility.isInputFieldEmpty(passwordText)) {
							JOptionPane.showMessageDialog(null, "Please enter a password to continue.", "Missing Password!", JOptionPane.ERROR_MESSAGE);
						} else if (HelperUtility.isInputFieldEmpty(firstNameText)) {
							JOptionPane.showMessageDialog(null, "Please enter a first name to continue.", "Missing First Name!", JOptionPane.ERROR_MESSAGE);
						} else if (HelperUtility.isInputFieldEmpty(middleNameText)) {
							JOptionPane.showMessageDialog(null, "Please enter a middle name to continue.", "Missing Middle Name!", JOptionPane.ERROR_MESSAGE);
						} else if (HelperUtility.isInputFieldEmpty(lastNameText)) {
							JOptionPane.showMessageDialog(null, "Please enter a last name to continue.", "Missing Last Name!", JOptionPane.ERROR_MESSAGE);
						} else if (HelperUtility.isInputFieldEmpty(monthText)) {
							JOptionPane.showMessageDialog(null, "Please enter a birth month to continue.", "Missing Birth Month!", JOptionPane.ERROR_MESSAGE);
						} else if (HelperUtility.isInputFieldEmpty(dayText)) {
							JOptionPane.showMessageDialog(null, "Please enter a birth day to continue.", "Missing Birth Day!", JOptionPane.ERROR_MESSAGE);
						} else if (HelperUtility.isInputFieldEmpty(yearText)) {
							JOptionPane.showMessageDialog(null, "Please enter a birth year to continue.", "Missing Birth Year!", JOptionPane.ERROR_MESSAGE);
						} else if (HelperUtility.isInputFieldEmpty(selectedGenderText)) {
							JOptionPane.showMessageDialog(null, "Please enter a gender to continue.", "Missing Gender!", JOptionPane.ERROR_MESSAGE);
						} else if (HelperUtility.isInputFieldEmpty(ageText)) {
							JOptionPane.showMessageDialog(null, "Please enter an age to continue.", "Missing Age!", JOptionPane.ERROR_MESSAGE);
						} else if (HelperUtility.isInputFieldEmpty(emailText)) {
							JOptionPane.showMessageDialog(null, "Please enter an email to continue.", "Missing Age!", JOptionPane.ERROR_MESSAGE);
						} else if (HelperUtility.isInputFieldEmpty(phoneNumberText)) {
							JOptionPane.showMessageDialog(null, "Please enter a phone number to continue.", "Missing Phone Number!", JOptionPane.ERROR_MESSAGE);
						}

					} else {

						String sourceOfIncomeText = sourceOfIncomeComboBox.getSelectedItem().toString().toLowerCase();
						String occupationText = occupationComboBox.getSelectedItem().toString().toLowerCase();
						int monthlyIncomeIndex = monthlyIncomeComboBox.getSelectedIndex();

						//Proceeds if all of the inputs are not empty
						//Advanced Validation
						//For birthday
						LocalDate birthday = LocalDate.of(Integer.parseInt(yearText) , 
								Integer.parseInt(monthText) , 
								Integer.parseInt(dayText) );

						//For age
						int age = Integer.parseInt(ageText);

						//For the monthly income enums
						switch(monthlyIncomeIndex) {
						case 1: {
							monthlyIncomeText = UserEnums.MonthlyIncome.BELOW_10K.toString().toLowerCase();
							break;
						}
						case 2: {
							monthlyIncomeText = UserEnums.MonthlyIncome.TENK_TO_30K.toString().toLowerCase();
							break;
						}
						case 3: {
							monthlyIncomeText = UserEnums.MonthlyIncome.THIRTYK_TO_50K.toString().toLowerCase();
							break;
						}
						case 4: {
							monthlyIncomeText = UserEnums.MonthlyIncome.ABOVE_50K.toString().toLowerCase();
							break;
						}
						}


						Loaner ln = new Loaner(usernameText,
								passwordText,
								firstNameText,
								middleNameText,
								lastNameText,
								selectedGenderText,
								birthday ,
								age, 
								emailText,
								phoneNumberText,
								sourceOfIncomeText,
								occupationText,
								monthlyIncomeText
								);

						if(ln.isUserInputValid() && ln.checkDuplicateInput().equals("ok")) {
							LoanerRegistrationData lnRegData = new LoanerRegistrationData(usernameText,
									passwordText,
									firstNameText,
									middleNameText,
									lastNameText,
									selectedGenderText,
									birthday ,
									age, 
									emailText,
									phoneNumberText,
									sourceOfIncomeText,
									occupationText,
									monthlyIncomeText);

							//sending it to the data base
							if(ln.registerLoaner(lnRegData)) {
								GoToLoginPage login = new GoToLoginPage();
								login.setVisible(true);
							} else {
								JOptionPane.showMessageDialog(null, "Please try again later.", "Server Error!", JOptionPane.ERROR_MESSAGE);
							} 

						} else {
							String errorText = ln.checkErrorInputRegistration();

							if (errorText == null) {
								errorText = ln.checkDuplicateInput();
							}

							JOptionPane.showMessageDialog(null, errorText, "Invalid Input!", JOptionPane.ERROR_MESSAGE);
						}

					}

				} catch (NumberFormatException numberEx) {
					String errorText = "Some fields only accepts integer values. Please try again.";

					JOptionPane.showMessageDialog(null, "Please enter only a number value for age and birthday.", "Invalid Input!", JOptionPane.ERROR_MESSAGE);
				}


			}
		});
		registerLoanerButton.setBounds(172, 497, 192, 48);
		contentPane.add(registerLoanerButton);

		JButton alreadyAMemberButton = new JButton();
		alreadyAMemberButton = new JButton("Already a loaner? Login");
		alreadyAMemberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage login = new LoginPage();
				login.setVisible(true);

				//For Closing Page
				HelperUtility.closePage(thisFrame);
			}
		});
		alreadyAMemberButton.setForeground(Color.BLUE);
		alreadyAMemberButton.setContentAreaFilled(false);
		alreadyAMemberButton.setBorderPainted(false);
		alreadyAMemberButton.setBounds(172, 544, 192, 23);
		contentPane.add(alreadyAMemberButton);

	}

	@Override
	public boolean isSpecificFieldsEmpty() {
		int occupationComboBoxIndex = occupationComboBox.getSelectedIndex();
		int sourceOfIncomeComboBoxIndex = sourceOfIncomeComboBox.getSelectedIndex();
		int monthlyIncomeComboBoxIndex = monthlyIncomeComboBox.getSelectedIndex();

		return occupationComboBoxIndex == -1 || 
				sourceOfIncomeComboBoxIndex == -1 || 
				monthlyIncomeComboBoxIndex == 0;
	}
}
