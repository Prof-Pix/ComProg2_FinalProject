package AuthFrames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Components.InvalidInputPopup;
import User.Loaner;

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
import UserEnums.SourceOfIncome;
import UserEnums.Occupation;
import UserEnums.MonthlyIncome;

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
	private JLabel lblNewLabel;
	private JLabel lblBirthday_1;
	private JLabel lblBirthday_2;

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
		setTitle("Loaner Registration Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(434, 0, 450, 546);
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
		raAgeLabel.setBounds(40, 276, 97, 18);
		contentPane.add(raAgeLabel);
		
		rlPhoneNumberField = new JTextField();
		rlPhoneNumberField.setBounds(172, 337, 192, 20);
		rlPhoneNumberField.setColumns(10);
		contentPane.add(rlPhoneNumberField);
		
		raEmailLabel = new JLabel("Email:");
		raEmailLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raEmailLabel.setBounds(40, 311, 97, 14);
		contentPane.add(raEmailLabel);
		
		rlAgeField = new JTextField();
		rlAgeField.setBounds(172, 274, 46, 20);
		rlAgeField.setColumns(10);
		contentPane.add(rlAgeField);
		
		raPhoneNumberLabel = new JLabel("Phone Number:");
		raPhoneNumberLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raPhoneNumberLabel.setBounds(40, 341, 97, 14);
		contentPane.add(raPhoneNumberLabel);
		
		rlEmailField = new JTextField();
		rlEmailField.setBounds(172, 305, 192, 20);
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
		lblSourceOfIncome.setBounds(40, 371, 107, 14);
		contentPane.add(lblSourceOfIncome);
		
		lblOccupation = new JLabel("Occupation:");
		lblOccupation.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblOccupation.setBounds(40, 403, 97, 14);
		contentPane.add(lblOccupation);
		
		lblMonthlyIncome = new JLabel("Monthly Income:");
		lblMonthlyIncome.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblMonthlyIncome.setBounds(40, 434, 97, 14);
		contentPane.add(lblMonthlyIncome);
		
		lblNewLabel = new JLabel("Already a member? Login");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setBackground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 11));
		lblNewLabel.setBounds(197, 518, 147, 14);
		contentPane.add(lblNewLabel);
		
		JComboBox sourceOfIncomeComboBox = new JComboBox();
		sourceOfIncomeComboBox.setModel(new DefaultComboBoxModel(SourceOfIncome.values()));
		sourceOfIncomeComboBox.setSelectedIndex(0);
		sourceOfIncomeComboBox.setBounds(172, 366, 192, 22);
		contentPane.add(sourceOfIncomeComboBox);
		
		JComboBox occupationComboBox = new JComboBox();
		occupationComboBox.setModel(new DefaultComboBoxModel(Occupation.values()));
		occupationComboBox.setSelectedIndex(0);
		occupationComboBox.setBounds(172, 398, 192, 22);
		contentPane.add(occupationComboBox);
		
		JComboBox monthlyIncomeComboBox = new JComboBox();
		monthlyIncomeComboBox.setModel(new DefaultComboBoxModel(MonthlyIncome.values()));
		monthlyIncomeComboBox.setSelectedIndex(0);
		monthlyIncomeComboBox.setBounds(172, 429, 192, 22);
		contentPane.add(monthlyIncomeComboBox);
		
		lblBirthday_1 = new JLabel("/");
		lblBirthday_1.setFont(new Font("Dialog", Font.BOLD, 13));
		lblBirthday_1.setBounds(214, 245, 11, 20);
		contentPane.add(lblBirthday_1);
		
		lblBirthday_2 = new JLabel("/");
		lblBirthday_2.setFont(new Font("Dialog", Font.BOLD, 13));
		lblBirthday_2.setBounds(265, 245, 11, 20);
		contentPane.add(lblBirthday_2);
		
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
				
				//Checks if one of the common inputs are invalid
				if (isCommonFieldsInvalid(usernameText,
						passwordText,
						firstNameText,
						middleNameText,
						lastNameText,
						monthText,
						dayText,
						yearText,
						ageText,
						emailText,
						phoneNumberText)) {	
					System.out.println(occupationComboBox.getSelectedItem());
					InvalidInputPopup popup = new InvalidInputPopup(RegisterLoanerPage.this);
					popup.setVisible(true);
				} else {
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
					
//					Loaner ln = new Loaner(usernameText,
//								passwordText,
//								firstNameText,
//								middleNameText,
//								lastNameText,
//								birthday ,
//								age, 
//								emailText,
//								phoneNumber,
//								);
				}
			}
		});
		registerLoanerButton.setBounds(172, 467, 192, 48);
		contentPane.add(registerLoanerButton);
	}

	@Override
	public boolean isSpecificFieldsInvalid() {
		// TODO Auto-generated method stub
		return false;
	}
}
