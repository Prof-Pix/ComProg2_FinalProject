package UserRoleFrames;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database.DatabaseManager;

import java.awt.Color;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
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
import java.awt.Label;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

public class MerchantFrame extends JFrame {
	
	DatabaseManager dbManager = new DatabaseManager();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	private static int merchantUserId;

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
		panel_2.setBounds(615, 228, 124, 131);
		panel_2.setBackground(new Color(255, 255, 0));
		contentPane.add(panel_2);
		
		JButton btnNewButton_1 = new JButton("Upload Picture");
		btnNewButton_1.setBounds(615, 367, 124, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Product Name:");
		lblNewLabel.setBounds(759, 228, 86, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(843, 225, 99, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblProductBrand = new JLabel("Product Brand:");
		lblProductBrand.setBounds(759, 256, 86, 14);
		contentPane.add(lblProductBrand);
		
		textField_1 = new JTextField();
		textField_1.setBounds(843, 253, 99, 20);
		textField_1.setColumns(10);
		contentPane.add(textField_1);
		
		JLabel lblStocks = new JLabel("Stocks Available:");
		lblStocks.setBounds(759, 284, 86, 14);
		contentPane.add(lblStocks);
		
		textField_2 = new JTextField();
		textField_2.setBounds(843, 281, 99, 20);
		textField_2.setColumns(10);
		contentPane.add(textField_2);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(759, 309, 86, 14);
		contentPane.add(lblPrice);
		
		textField_3 = new JTextField();
		textField_3.setBounds(843, 306, 99, 20);
		textField_3.setColumns(10);
		contentPane.add(textField_3);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setBounds(759, 334, 86, 14);
		contentPane.add(lblCategory);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(843, 334, 99, 22);
		contentPane.add(comboBox);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(352, 228, 86, 14);
		contentPane.add(lblDescription);
		
		JLabel lblNewLabel_1 = new JLabel("Interest Rates @ Months to Pay");
		lblNewLabel_1.setBounds(412, 438, 168, 14);
		contentPane.add(lblNewLabel_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(632, 417, 327, 177);
		contentPane.add(textArea);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(542, 47, 327, 146);
		contentPane.add(scrollPane);
		
		Label label = new Label("New label");
		label.setBounds(518, 517, 62, 22);
		contentPane.add(label);
		
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
	}
}
