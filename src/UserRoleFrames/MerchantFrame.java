package UserRoleFrames;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database.DatabaseManager;
import Products.ProductLoan;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.Label;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSpinner;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MerchantFrame extends JFrame {
	
	private DatabaseManager dbManager = new DatabaseManager();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField mProdPriceField;
	
	private static int merchantUserId;
	private JTextField mInterestRateField;
	
	private ArrayList<ProductLoan> productLoans = new ArrayList<>();
	
	DefaultTableModel model;
	Object[][] data = {
			 			
	};
	
	String irColumnNames[] = {"Product Price", "Months to pay", "Interest Rate", "Potential Interest", "Remove Button"};
	private JTable table;
	
	JSpinner monthsToPaySpinner;
	
	JButton addProdLoanBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MerchantFrame frame = new MerchantFrame(merchantUserId);
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
	public MerchantFrame(int merchantUserId) {
		
		MerchantFrame.merchantUserId = merchantUserId;
		System.out.println(merchantUserId);
		dbManager.connect();
		dbManager.getUserdata(merchantUserId);
		
		setTitle("Merchant Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu_1 = new JMenu("Profile");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Edit Profile");
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_2 = new JMenu("Manage Products");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Add Product");
		mnNewMenu_2.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_3 = new JMenu("Manage Loans");
		menuBar.add(mnNewMenu_3);
		
		JMenu mnNewMenu_4 = new JMenu("Settings");
		menuBar.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("FAQS");
		mnNewMenu_4.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("About HomeCredit");
		mnNewMenu_4.add(mntmNewMenuItem_5);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(289, 32, 124, 131);
		panel_2.setBackground(new Color(255, 255, 0));
		contentPane.add(panel_2);
		
		JButton btnNewButton_1 = new JButton("Upload Picture");
		btnNewButton_1.setBounds(289, 171, 124, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Product Name:");
		lblNewLabel.setBounds(433, 32, 86, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(517, 29, 99, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblProductBrand = new JLabel("Product Brand:");
		lblProductBrand.setBounds(433, 60, 86, 14);
		contentPane.add(lblProductBrand);
		
		textField_1 = new JTextField();
		textField_1.setBounds(517, 57, 99, 20);
		textField_1.setColumns(10);
		contentPane.add(textField_1);
		
		JLabel lblStocks = new JLabel("Stocks Available:");
		lblStocks.setBounds(433, 88, 86, 14);
		contentPane.add(lblStocks);
		
		textField_2 = new JTextField();
		textField_2.setBounds(517, 85, 99, 20);
		textField_2.setColumns(10);
		contentPane.add(textField_2);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(289, 222, 86, 14);
		contentPane.add(lblPrice);
		
		mProdPriceField = new JTextField();
		mProdPriceField.setBounds(373, 219, 99, 20);
		mProdPriceField.setColumns(10);
		contentPane.add(mProdPriceField);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setBounds(433, 138, 86, 14);
		contentPane.add(lblCategory);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(517, 138, 99, 22);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Interest Rates @ Months to Pay");
		lblNewLabel_1.setBounds(289, 250, 168, 14);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 0));
		panel.setBounds(0, 0, 250, 639);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel profilePicture = new JLabel();
		profilePicture.setBounds(67, 23, 100, 100);
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("D:\\APPLICATIONS\\Home_Credit\\src\\Images\\default_avatar.jpg"));
			Image scaledImage = image.getScaledInstance(profilePicture.getWidth(), profilePicture.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(scaledImage);
			profilePicture.setIcon(imageIcon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		panel.add(profilePicture);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(300, 370, 227, 0);
		contentPane.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(356, 284, 0, 0);
		contentPane.add(scrollPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(292, 316, 494, 214);
		contentPane.add(scrollPane_2);
		
		// Table Setup
		model = new DefaultTableModel(irColumnNames, 0); 
        table = new JTable(model);
		scrollPane_2.setViewportView(table);
		
		JLabel lblMonthsToPay = new JLabel("Months to Pay:");
		lblMonthsToPay.setBounds(289, 275, 86, 14);
		contentPane.add(lblMonthsToPay);
		
		JLabel lblProductBrand_1 = new JLabel("@");
		lblProductBrand_1.setBounds(483, 275, 19, 14);
		contentPane.add(lblProductBrand_1);
		
		mInterestRateField = new JTextField();
		mInterestRateField.setColumns(10);
		mInterestRateField.setBounds(503, 272, 51, 20);
		contentPane.add(mInterestRateField);
		
		monthsToPaySpinner = new JSpinner();
		monthsToPaySpinner.setBounds(373, 272, 100, 20);
		contentPane.add(monthsToPaySpinner);
		
		JLabel lblProductBrand_1_1 = new JLabel("%");
		lblProductBrand_1_1.setBounds(556, 275, 19, 14);
		contentPane.add(lblProductBrand_1_1);
		
		addProdLoanBtn = new JButton("Add");
		addProdLoanBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
			}
		});
		addProdLoanBtn.setBounds(580, 271, 65, 23);
		contentPane.add(addProdLoanBtn);
	}
	
	private void updateTable() {
		try {
			model.setRowCount(0);
			
			float interestRate = Float.parseFloat(mInterestRateField.getText()) / 100;
			int monthsToPay = (Integer) monthsToPaySpinner.getValue();

			ProductLoan productLoan = new ProductLoan(monthsToPay, interestRate);
			
			productLoans.add(productLoan);
			
			Collections.sort(productLoans, (o1, o2) -> Integer.compare(o1.getMonthsToPay(), o2.getMonthsToPay()));
			for(ProductLoan prodLoan : productLoans) {
				float price = Float.parseFloat(mProdPriceField.getText());
				float potentialInterest = calculateInterest(price, prodLoan.getInterestRate(), prodLoan.getMonthsToPay());
				model.addRow(new Object[] {"₱ " +price, prodLoan.getMonthsToPay(), prodLoan.getInterestRate() * 100 + "%","₱ " + potentialInterest, new JButton()});
			}
			

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
	
	private float calculateInterest(float productPrice, float interestRate, int monthsToPay) {
        return (productPrice * interestRate) * monthsToPay; //
    }
	
	
}
