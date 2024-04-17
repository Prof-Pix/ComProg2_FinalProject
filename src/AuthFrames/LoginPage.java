package AuthFrames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import Components.InvalidInputPopup;
import Database.DatabaseManager;
import Products.Merchant;
import Products.MerchantProduct_View;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import UserEnums.UserRoles;
import UserRoleFrames.AdminFrame;
import UserRoleFrames.LoanerFrame;
import UserRoleFrames.MerchantFrame;

public class LoginPage extends JFrame {

	DatabaseManager dbManager = new DatabaseManager();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField usernameField;
	private static JTextField passwordField;


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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Username/Email:");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel.setBounds(473, 86, 155, 16);
		contentPane.add(lblNewLabel);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPassword.setBounds(473, 165, 109, 16);
		contentPane.add(lblPassword);

		usernameField = new JTextField();
		usernameField.setBounds(473, 103, 235, 30);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		passwordField = new JTextField();
		passwordField.setBounds(473, 182, 235, 30);
		contentPane.add(passwordField);
		passwordField.setColumns(10);


		JComboBox userRoleComboBox = new JComboBox();
		userRoleComboBox.setModel(new DefaultComboBoxModel(UserRoles.values()));
		userRoleComboBox.setBounds(473, 237, 120, 30);
		contentPane.add(userRoleComboBox);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usernameEmailText = usernameField.getText().trim();
				String passwordText = passwordField.getText().trim();
				String userType = userRoleComboBox.getSelectedItem().toString().toLowerCase();


				if (usernameEmailText == null || usernameEmailText.isEmpty()  || passwordText == null || passwordText.isEmpty()) {
					String errorText = "Please check all the required fields.";

					InvalidInputPopup popup = new InvalidInputPopup(LoginPage.this, errorText);
					popup.setVisible(true);
				} else {
					dbManager.connect();
					int returnId = dbManager.verifyLoginInput(userType, usernameEmailText, passwordText);

					if (returnId != 0) {

						switch(userType) {
						case "admin" : {
							AdminFrame adFrame = new AdminFrame();
							adFrame.setVisible(true);
							break;
						}
						case "merchant": {
							MerchantFrame merchFrame = new MerchantFrame(returnId);
							merchFrame.setVisible(true);
							break;
						}
						case "loaner": {
							LoanerFrame lnFrame = new LoanerFrame();
							lnFrame.setVisible(true);
							break;
						}

						}

					} else {
						String errorText = "Incorrect Credentials";

						InvalidInputPopup popup = new InvalidInputPopup(LoginPage.this, errorText);
						popup.setVisible(true);
					}
				}
			}
		});
		btnNewButton.setBounds(473, 289, 235, 30);
		contentPane.add(btnNewButton);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 0));
		panel.setBounds(0, 0, 450, 461);
		contentPane.add(panel);
		
	}
}
