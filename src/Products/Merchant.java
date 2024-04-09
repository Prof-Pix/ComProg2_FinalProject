package Products;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Merchant extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	static Statement st;
	static Connection con;
	static ResultSet rs;
	static ArrayList<Product> productList = new ArrayList<Product>();
	
	private int merchantId;
	
	public voic Merchant(int merchantId) {
		merchantId = merchantId;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "homecredit123");		
			st = con.createStatement();
			rs = st.executeQuery("SELECT * from product");
			
			while(rs.next()) {
				productList.add(new Product(rs.getString("name"), rs.getInt("serialNum"), rs.getFloat("price"), rs.getString("Category")));
			}
		
		}catch(Exception e) {
			System.out.println(e);
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Merchant frame = new Merchant();
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
	public Merchant() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		for (Product i: productList) {
			try {
				ProductTemplate prodTemp = new ProductTemplate(i.getName(), i.getSerialNum(), i.getPrice(), i.getCategory(), con);
				contentPane.add(prodTemp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		setContentPane(contentPane);
	}

}
