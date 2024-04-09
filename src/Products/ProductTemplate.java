package Products;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductTemplate extends JPanel {

	private static final long serialVersionUID = 1L;
	
	String url = "jdbc:mysql://localhost:3306/mydb";
	String user = "root";
	String password = "homecredit123";
	static Connection con;
	static ResultSet rs;
	
	//For (DELETE) Prepared Statement Query
	String delProdQuery = "DELETE from product where serialNum = ?";

	/**
	 * Create the panel.
	 * @throws Exception 
	 */
	
	public ProductTemplate(String prodName, int prodSerialNum, float prodPrice, String prodCategory, Connection con) throws Exception {
		
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(28, 11, 120, 89);
		add(panel);
		
		JLabel lblNewLabel = new JLabel(prodName);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 117, 160, 20);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("₱" + Float.toString(prodPrice));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 143, 138, 14);
		add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Buy now!");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(128, 0, 0));
		btnNewButton.setBounds(48, 233, 89, 23);
		add(btnNewButton);
		
		setPreferredSize(new Dimension(228, 267)); 
		
		JLabel lblNewLabel_2 = new JLabel(Integer.toString(prodSerialNum));
		lblNewLabel_2.setBounds(10, 168, 138, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Category: " + prodCategory);
		lblNewLabel_3.setBounds(10, 191, 138, 14);
		add(lblNewLabel_3);
		
		JButton btnNewButton_1 = new JButton("DEL");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this product?", "Delete " + prodName, JOptionPane.YES_NO_OPTION );
				
				if (choice == JOptionPane.YES_OPTION) {
					try {
						
						PreparedStatement delPrepSt = con.prepareStatement(delProdQuery);
						delPrepSt.setInt(1, prodSerialNum);
						
						delPrepSt.executeUpdate();
						
						UserProduct_View.updateProductList();
					} catch(SQLException e1) {
						System.out.println(e1);
					}
				}
				else {
					
				}
				
			}
		});
		btnNewButton_1.setBounds(158, 11, 60, 23);
		add(btnNewButton_1);

	}
}
