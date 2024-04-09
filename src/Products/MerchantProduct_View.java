package Products;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class MerchantProduct_View extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField prodNameField;
	private JTextField prodPriceField;
	private JTextField prodSerialNumField;
	private JTextField prodCategoryField;
	
	static Connection con;
	static ResultSet rs;
	
	//Query for adding products
	String addProdQuery = "INSERT INTO product VALUES (?,?,?,?, '2024-03-08')";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "homecredit123");
			Statement st=con.createStatement();
			rs = st.executeQuery("select * from product");
		}catch(Exception e) {
			System.out.println(e);
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MerchantProduct_View frame = new MerchantProduct_View();
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
	public MerchantProduct_View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1005, 578);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product Name:");
		lblNewLabel.setBounds(21, 29, 104, 14);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		contentPane.add(lblNewLabel);
		
		prodNameField = new JTextField();
		prodNameField.setBounds(147, 28, 203, 20);
		contentPane.add(prodNameField);
		prodNameField.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(21, 74, 41, 14);
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 15));
		contentPane.add(lblPrice);
		
		prodPriceField = new JTextField();
		prodPriceField.setBounds(147, 73, 203, 20);
		prodPriceField.setColumns(10);
		contentPane.add(prodPriceField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Serial Number:");
		lblNewLabel_1_1.setBounds(21, 126, 112, 14);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 15));
		contentPane.add(lblNewLabel_1_1);
		
		prodSerialNumField = new JTextField();
		prodSerialNumField.setBounds(147, 125, 203, 20);
		prodSerialNumField.setColumns(10);
		contentPane.add(prodSerialNumField);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Category:");
		lblNewLabel_1_1_1.setBounds(21, 173, 104, 19);
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Dialog", Font.BOLD, 15));
		contentPane.add(lblNewLabel_1_1_1);
		
		prodCategoryField = new JTextField();
		prodCategoryField.setBounds(147, 174, 203, 20);
		prodCategoryField.setColumns(10);
		contentPane.add(prodCategoryField);
		
		JButton btnNewButton = new JButton("Add Product");
		btnNewButton.setBounds(147, 231, 122, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PreparedStatement addProdPrepSt = con.prepareStatement(addProdQuery);
					addProdPrepSt.setString(1, prodNameField.getText() );
					addProdPrepSt.setInt(2, Integer.parseInt(prodSerialNumField.getText()));
					addProdPrepSt.setFloat(3, Float.parseFloat(prodPriceField.getText()));
					addProdPrepSt.setString(4, prodCategoryField.getText());
					
					addProdPrepSt.executeUpdate();
					
					prodNameField.setText(null);
					prodSerialNumField.setText(null);
					prodPriceField.setText(null);
					prodCategoryField.setText(null);
					
					JOptionPane.showMessageDialog(null, "Product Added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE, null);


				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		contentPane.add(btnNewButton);
	}
}
