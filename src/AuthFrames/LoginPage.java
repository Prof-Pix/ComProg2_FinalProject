package AuthFrames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

public class LoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField usernameField;
	private static JTextField passwordField;
	
	static Connection con;
	static ResultSet rs;
	
	static String loginQuery = "SELECT * FROM loanercredential WHERE username = ? and password = ?";

	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	
	public static void verifyCredentials() throws SQLException {
		PreparedStatement loginPrepSt = con.prepareStatement(loginQuery);
		loginPrepSt.setString(1, usernameField.getText());
		loginPrepSt.setString(2, passwordField.getText());
		
		rs = loginPrepSt.executeQuery();
		if (rs.next()) {
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(usernameField);
		} else {
			JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
		}
		
		usernameField.setText(null);
		passwordField.setText(null);
	}
	
	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "homecredit123");		
		}catch(Exception e) {
			System.out.println(e);
		}
		
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
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel.setBounds(473, 86, 109, 16);
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
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					verifyCredentials();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(473, 289, 235, 30);
		contentPane.add(btnNewButton);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Loaner", "Merchant"}));
		comboBox.setBounds(473, 237, 120, 30);
		contentPane.add(comboBox);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 0));
		panel.setBounds(0, 0, 450, 461);
		contentPane.add(panel);
	}
}
