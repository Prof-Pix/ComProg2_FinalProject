package Products;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.FlowLayout;

public class UserProduct_View extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	
	static ArrayList<Product> productList = new ArrayList<Product>();
	
	static Connection con;
	static ResultSet rs;
	
	//Update the product list
	
	public static void updateProductList() throws SQLException {
		Statement st = con.createStatement();
		rs = st.executeQuery("SELECT * FROM product");
		
		//empty the list first
		productList.clear();
		while(rs.next()) {
			String name = rs.getString("name");
			int serialNum = rs.getInt("serialNum");
			float price = rs.getFloat("price");
			String category = rs.getString("category");
			
			productList.add(new Product(name, serialNum, price, category));
		}
		  // Update GUI with the new product data
        contentPane.removeAll(); // Remove existing components
        for (Product product : productList) {
            ProductTemplate newProd = null;
			try {
				newProd = new ProductTemplate(product.getName(), product.getSerialNum(),
				          product.getPrice(), product.getCategory(), con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            newProd.setVisible(true);
            contentPane.add(newProd);
        }
        contentPane.revalidate(); // Revalidate the content pane
        contentPane.repaint(); // Repaint the content pane
		
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "homecredit123");
			Statement st=con.createStatement();
			rs = st.executeQuery("select * from product");
			
			while(rs.next()) {
				String name = rs.getString("name");
				int serialNum = rs.getInt("serialNum");
				float price = rs.getFloat("price");
				String category = rs.getString("category");
				
				productList.add(new Product(name, serialNum, price, category));
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserProduct_View frame = new UserProduct_View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public UserProduct_View() throws Exception {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 967, 561);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
//		contentPane.setLayout(null);
		
		for(Product product: productList) {
			ProductTemplate newProd = new ProductTemplate(product.getName(), product.getSerialNum(), product.getPrice(), product.getCategory(), con);
			newProd.setVisible(true);
			contentPane.add(newProd);
		}
		
		// Schedule task to update GUI every 5 seconds
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
            	try {
					updateProductList();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // Fetch data from the database and update GUI
            }
        }, 0, 2, TimeUnit.SECONDS);
		

	}
}
