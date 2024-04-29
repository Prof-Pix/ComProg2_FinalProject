package AuthFrames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database.DatabaseManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import UserEnums.UserRoles;
import UserRoleFrames.AdminFrame;
import UserRoleFrames.LoanerFrame;
import UserRoleFrames.MerchantFrame;
import Utilities.HelperUtility;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.SwingConstants;

public class LoginPage extends JFrame {

	DatabaseManager dbManager = new DatabaseManager();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField usernameField;
	private static JTextField passwordField;
	JComboBox userRoleRegisterComboBox;


	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
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
	public LoginPage() {
		setResizable(false);
		setTitle("Login Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(30, 66, 94));
		panel.setBounds(0, 0, 433, 461);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel logoIcon = new JLabel("");
		logoIcon.setBounds(10, 11, 413, 439);
		panel.add(logoIcon);

		//Scaling the image
		ImageIcon imageIcon = new ImageIcon("D:\\APPLICATIONS\\Home_Credit\\src\\Images\\loanpal_logo.png");
		Image originalImage = imageIcon.getImage();
		Image scaledImage = originalImage.getScaledInstance(logoIcon.getWidth(), logoIcon.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
		logoIcon.setIcon(scaledImageIcon);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(237, 250, 139));
		panel_1.setBounds(431, 0, 453, 461);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Username/Email:");
		lblNewLabel.setBounds(111, 63, 155, 16);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 13));

		usernameField = new JTextField();
		usernameField.setFont(new Font("Dialog", Font.PLAIN, 12));
		usernameField.setBounds(111, 84, 235, 30);
		panel_1.add(usernameField);
		usernameField.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(111, 125, 109, 16);
		panel_1.add(lblPassword);
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 13));

		passwordField = new JTextField();
		passwordField.setFont(new Font("Dialog", Font.PLAIN, 12));
		passwordField.setBounds(111, 146, 235, 30);
		panel_1.add(passwordField);
		passwordField.setColumns(10);


		JComboBox userRoleComboBox = new JComboBox();
		userRoleComboBox.setBounds(112, 200, 120, 30);
		panel_1.add(userRoleComboBox);
		userRoleComboBox.setModel(new DefaultComboBoxModel(UserRoles.values()));

		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBorder(new CompoundBorder());
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton.setBackground(new Color(64, 112, 86));
		btnNewButton.setBounds(175, 258, 133, 30);
		panel_1.add(btnNewButton);

		JLabel lblOr = new JLabel("or");
		lblOr.setHorizontalAlignment(SwingConstants.CENTER);
		lblOr.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblOr.setBounds(175, 299, 133, 16);
		panel_1.add(lblOr);




		userRoleRegisterComboBox = new JComboBox();
		userRoleRegisterComboBox.setModel(new DefaultComboBoxModel(UserRoles.values()));
		userRoleRegisterComboBox.setBounds(240, 325, 120, 22);
		panel_1.add(userRoleRegisterComboBox);
		
		JButton btnRegisterAs = new JButton("Register as");
		btnRegisterAs.setForeground(Color.WHITE);
		btnRegisterAs.setFont(new Font("Dialog", Font.BOLD, 12));
		btnRegisterAs.setBorder(new CompoundBorder());
		btnRegisterAs.setBackground(new Color(37, 102, 112));
		btnRegisterAs.setBounds(150, 324, 80, 24);
		panel_1.add(btnRegisterAs);
		btnRegisterAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userType = userRoleRegisterComboBox.getSelectedItem().toString().toLowerCase();
				
				switch(userType) {
				case "admin": {
					RegisterAdminPage regAdmin = new RegisterAdminPage();
					regAdmin.setVisible(true);
					HelperUtility.closePage(LoginPage.this);
					break;
				} 
				case "loaner": {
					RegisterLoanerPage regLoaner = new RegisterLoanerPage();
					regLoaner.setVisible(true);
					HelperUtility.closePage(LoginPage.this);
					break;
				}
				case "merchant": {
					RegisterMerchantPage regMerchant = new RegisterMerchantPage();
					regMerchant.setVisible(true);
					HelperUtility.closePage(LoginPage.this);
					break;
				}
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usernameEmailText = usernameField.getText().trim();
				String passwordText = passwordField.getText().trim();
				String userType = userRoleComboBox.getSelectedItem().toString().toLowerCase();


				if (HelperUtility.isInputFieldEmpty(usernameEmailText)) {
					JOptionPane.showMessageDialog(null, "Please enter a username to continue.", "Missing Username!", JOptionPane.ERROR_MESSAGE);

				}  else if (HelperUtility.isInputFieldEmpty(passwordText)) {
					JOptionPane.showMessageDialog(null, "Please enter a password to continue.", "Missing Password!", JOptionPane.ERROR_MESSAGE);
				} else {
					dbManager.connect();
					int returnId = dbManager.verifyLoginInput(userType, usernameEmailText, passwordText);

					if (returnId != 0) {

						switch(userType) {
						case "admin" : {
							HelperUtility.closePage(LoginPage.this);
							AdminFrame adFrame = new AdminFrame(returnId);
							adFrame.setVisible(true);
							break;
						}
						case "merchant": {
							HelperUtility.closePage(LoginPage.this);
							MerchantFrame merchFrame = new MerchantFrame(returnId);
							merchFrame.setVisible(true);
							break;
						}
						case "loaner": {
							HelperUtility.closePage(LoginPage.this);
							LoanerFrame lnFrame = new LoanerFrame(returnId);
							lnFrame.setVisible(true);
							break;
						}

						}

					} else {
						JOptionPane.showMessageDialog(null, "Please enter a correct credentials to continue.", "Incorrect Credentials!", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});


	}
}
