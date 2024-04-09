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

public class RegisterLoanerPage extends JFrame {

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
	private JLabel raMonthLabel;
	private JLabel raDayLabel;
	private JLabel raYearLabel;
	private JTextField rlDayField;
	private JTextField rlYearField;
	private JLabel lblAccountDetails;
	private JSeparator separator;
	private JLabel lblPersonalInformation;
	private JLabel lblSourceOfIncome;
	private JLabel lblOccupation;
	private JLabel lblMonthlyIncome;
	private JTextField rlSourceOfIncome;
	private JTextField rlOccupation;
	private JTextField rlMonthlyIncome;
	private JLabel lblNewLabel;

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
		rlUserField.setBounds(147, 51, 192, 20);
		contentPane.add(rlUserField);
		rlUserField.setColumns(10);
		
		JLabel raFNameLabel = new JLabel("First Name:");
		raFNameLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raFNameLabel.setBounds(40, 154, 97, 14);
		contentPane.add(raFNameLabel);
		
		rlFNameField = new JTextField();
		rlFNameField.setBounds(147, 152, 192, 20);
		rlFNameField.setColumns(10);
		contentPane.add(rlFNameField);
		
		JLabel lblMiddlename = new JLabel("Middle Name:");
		lblMiddlename.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblMiddlename.setBounds(40, 185, 97, 14);
		contentPane.add(lblMiddlename);
		
		rlMiddleNameField = new JTextField();
		rlMiddleNameField.setBounds(147, 183, 192, 20);
		rlMiddleNameField.setColumns(10);
		contentPane.add(rlMiddleNameField);
		
		raLastNameLabel = new JLabel("Last Name:");
		raLastNameLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raLastNameLabel.setBounds(40, 216, 97, 14);
		contentPane.add(raLastNameLabel);
		
		rlLastNameField = new JTextField();
		rlLastNameField.setBounds(147, 214, 192, 20);
		rlLastNameField.setColumns(10);
		contentPane.add(rlLastNameField);
		
		raAgeLabel = new JLabel("Age:");
		raAgeLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raAgeLabel.setBounds(40, 276, 97, 18);
		contentPane.add(raAgeLabel);
		
		rlPhoneNumberField = new JTextField();
		rlPhoneNumberField.setBounds(147, 339, 192, 20);
		rlPhoneNumberField.setColumns(10);
		contentPane.add(rlPhoneNumberField);
		
		raEmailLabel = new JLabel("Email:");
		raEmailLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raEmailLabel.setBounds(40, 311, 97, 14);
		contentPane.add(raEmailLabel);
		
		rlAgeField = new JTextField();
		rlAgeField.setBounds(147, 276, 46, 20);
		rlAgeField.setColumns(10);
		contentPane.add(rlAgeField);
		
		raPhoneNumberLabel = new JLabel("Phone Number:");
		raPhoneNumberLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raPhoneNumberLabel.setBounds(40, 341, 97, 14);
		contentPane.add(raPhoneNumberLabel);
		
		rlEmailField = new JTextField();
		rlEmailField.setBounds(147, 307, 192, 20);
		rlEmailField.setColumns(10);
		contentPane.add(rlEmailField);
		
		raPasswordLabel = new JLabel("Password:");
		raPasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raPasswordLabel.setBounds(40, 84, 107, 14);
		contentPane.add(raPasswordLabel);
		
		rlPasswordField = new JTextField();
		rlPasswordField.setColumns(10);
		rlPasswordField.setBounds(147, 82, 192, 20);
		contentPane.add(rlPasswordField);
		
		registerLoanerButton = new JButton("Register Loaner Account");
		registerLoanerButton.setFont(new Font("Dialog", Font.PLAIN, 11));
		registerLoanerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		registerLoanerButton.setBounds(147, 469, 192, 48);
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
		
		JTextField rlMonthField = new JTextField();
		rlMonthField.setBounds(167, 245, 38, 20);
		contentPane.add(rlMonthField);
		rlMonthField.setColumns(10);
		
		rlDayField = new JTextField();
		rlDayField.setColumns(10);
		rlDayField.setBounds(235, 245, 38, 20);
		contentPane.add(rlDayField);
		
		rlYearField = new JTextField();
		rlYearField.setColumns(10);
		rlYearField.setBounds(303, 245, 38, 20);
		contentPane.add(rlYearField);
		
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
		
		rlSourceOfIncome = new JTextField();
		rlSourceOfIncome.setColumns(10);
		rlSourceOfIncome.setBounds(147, 370, 192, 20);
		contentPane.add(rlSourceOfIncome);
		
		rlOccupation = new JTextField();
		rlOccupation.setColumns(10);
		rlOccupation.setBounds(147, 401, 192, 20);
		contentPane.add(rlOccupation);
		
		rlMonthlyIncome = new JTextField();
		rlMonthlyIncome.setColumns(10);
		rlMonthlyIncome.setBounds(147, 432, 192, 20);
		contentPane.add(rlMonthlyIncome);
		
		lblNewLabel = new JLabel("Already a member? Login");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setBackground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 11));
		lblNewLabel.setBounds(172, 520, 147, 14);
		contentPane.add(lblNewLabel);
	}
}
