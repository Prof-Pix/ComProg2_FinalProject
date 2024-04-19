package UserRoleFrames;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database.DatabaseManager;
import JComboBoxRenderers.MerchantCategoryRenderer;
import Products.Product;
import Products.ProductLoanTerm;
import Products.ProductRegistrationData;
import User.Merchant;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.awt.Label;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import UserEnums.MerchantCategory;
import Utilities.HelperUtility;

public class MerchantFrame extends JFrame {

	private DatabaseManager dbManager = new DatabaseManager();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField mfProductNameField;
	private JTextField mfProductBrandField;
	private JTextField mfProductStocksField;
	private JTextField mfProductPriceField;

	private static int MERCHANT_USER_ID;
	private JTextField mInterestRateField;

	private ArrayList<ProductLoanTerm> productLoanTerms = new ArrayList<>();

	private String irColumnNames[] = { "Months to pay", "Interest Rate", "Potential Interest"};
	private final String DEFAULT_PRODUCT_PICTURE_PATH = "D:\\APPLICATIONS\\Home_Credit\\src\\Images\\default_product_picture.jpg";
	DefaultTableModel loanTableModel;

	private JTable loanInterestTable;

	JButton addProdLoanBtn;

	JLabel productPicture;

	JComboBox mfCategoryComboBox;
	JTextArea mfDescriptionField;
	JTextArea mfSpecificationsField;
	private JTextField monthsToPayField;

	//Manage Products Panel
	JPanel addProductPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MerchantFrame frame = new MerchantFrame(MERCHANT_USER_ID);
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
		setResizable(false);

		MerchantFrame.MERCHANT_USER_ID = merchantUserId;
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

		JMenu manageProductMenu = new JMenu("Manage Products");
		menuBar.add(manageProductMenu);

		JMenuItem addProductMenuItem = new JMenuItem("Add Product");
		manageProductMenu.add(addProductMenuItem);
		addProductMenuItem.addActionListener(e -> showPanel(addProductPanel));

		JMenuItem mntmNewMenuItem = new JMenuItem("View Products");
		manageProductMenu.add(mntmNewMenuItem);

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

		DefaultComboBoxModel<MerchantCategory> categModel = new DefaultComboBoxModel<>();
		categModel.addElement(null);		

		for (MerchantCategory categ: MerchantCategory.values()) {
			categModel.addElement(categ);
		}
		BufferedImage image = null;

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(300, 370, 227, 0);
		contentPane.add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(356, 284, 0, 0);
		contentPane.add(scrollPane_1);
		// Table Setup
		loanTableModel = new DefaultTableModel(irColumnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // All cells are non-editable
			}
		}; 

		//For changing the alignment of each row
		DefaultTableCellRenderer loanInterestTablerRenderer  = new DefaultTableCellRenderer();
		loanInterestTablerRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

		addProductPanel = new JPanel();
		addProductPanel.setBounds(0, 0, 1184, 639);
		contentPane.add(addProductPanel);
		addProductPanel.setLayout(null);
		addProductPanel.setVisible(false);

		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(627, 51, 40, 14);
		addProductPanel.add(lblPrice);
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 12));

		JLabel lblProductBrand_1_1_1 = new JLabel("₱");
		lblProductBrand_1_1_1.setBounds(670, 51, 19, 14);
		addProductPanel.add(lblProductBrand_1_1_1);
		lblProductBrand_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));

		mfProductPriceField = new JTextField();
		mfProductPriceField.setBounds(685, 49, 99, 20);
		addProductPanel.add(mfProductPriceField);
		mfProductPriceField.setColumns(10);


		JPanel loanInterestTablePanel = new JPanel();
		loanInterestTablePanel.setBounds(627, 134, 445, 305);
		addProductPanel.add(loanInterestTablePanel);
		loanInterestTablePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		loanInterestTablePanel.setLayout(null);

		JScrollPane loanInterestTableScrollPane = new JScrollPane();
		loanInterestTableScrollPane.setBounds(0, 0, 445, 229);
		loanInterestTablePanel.add(loanInterestTableScrollPane);
		loanInterestTableScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		loanInterestTableScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		loanInterestTable = new JTable(loanTableModel);

		//Disbable Column Reordering
		JTableHeader loanInterestTableHeader = loanInterestTable.getTableHeader();
		loanInterestTableHeader.setReorderingAllowed(false);
		loanInterestTable.setDefaultRenderer(Object.class, loanInterestTablerRenderer);

		loanInterestTableScrollPane.setViewportView(loanInterestTable);

		JLabel lblMonthsToPay = new JLabel("Months to Pay:");
		lblMonthsToPay.setBounds(24, 243, 94, 14);
		lblMonthsToPay.setFont(new Font("Dialog", Font.BOLD, 12));
		loanInterestTablePanel.add(lblMonthsToPay);

		mInterestRateField = new JTextField();
		mInterestRateField.setBounds(117, 271, 33, 20);
		loanInterestTablePanel.add(mInterestRateField);
		mInterestRateField.setColumns(10);

		JLabel lblProductBrand_1_1 = new JLabel("%");
		lblProductBrand_1_1.setBounds(152, 274, 19, 14);
		loanInterestTablePanel.add(lblProductBrand_1_1);

		addProdLoanBtn = new JButton("Add New Row");
		addProdLoanBtn.setBounds(237, 240, 160, 23);
		addProdLoanBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addLoanRowToTable();
			}

		});
		loanInterestTablePanel.add(addProdLoanBtn);

		JButton deleteButton = new JButton("Delete Selected Row/s");
		deleteButton.setBounds(238, 271, 161, 23);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteRow();
			}
		});
		loanInterestTablePanel.add(deleteButton);

		JLabel lblInterestRate = new JLabel("Interest Rate:");
		lblInterestRate.setBounds(24, 275, 94, 14);
		lblInterestRate.setFont(new Font("Dialog", Font.BOLD, 12));
		loanInterestTablePanel.add(lblInterestRate);

		monthsToPayField = new JTextField();
		monthsToPayField.setBounds(117, 240, 64, 20);
		loanInterestTablePanel.add(monthsToPayField);
		monthsToPayField.setColumns(10);

		JButton addProductButton = new JButton("Add Product");
		addProductButton.setBounds(967, 564, 196, 57);
		addProductPanel.add(addProductButton);

		JLabel lblNewLabel_1 = new JLabel("Interest Rates @ Months to Pay");
		lblNewLabel_1.setBounds(627, 100, 196, 23);
		addProductPanel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));

		productPicture = new JLabel();
		productPicture.setBounds(28, 35, 150, 150);
		//Scaling the image
		ImageIcon imageIcon = new ImageIcon(DEFAULT_PRODUCT_PICTURE_PATH);
		Image originalImage = imageIcon.getImage();
		Image scaledImage = originalImage.getScaledInstance(productPicture.getWidth(), productPicture.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
		productPicture.setIcon(scaledImageIcon);
		addProductPanel.add(productPicture);
		productPicture.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		JButton uploadPicButton = new JButton("Upload Picture");
		uploadPicButton.setBounds(28, 196, 150, 23);
		addProductPanel.add(uploadPicButton);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(28, 242, 86, 14);
		addProductPanel.add(lblDescription);
		lblDescription.setFont(new Font("Dialog", Font.BOLD, 12));

		JLabel lblSpecifications = new JLabel("Specifications:");
		lblSpecifications.setBounds(28, 436, 86, 14);
		addProductPanel.add(lblSpecifications);
		lblSpecifications.setFont(new Font("Dialog", Font.BOLD, 12));

		mfProductNameField = new JTextField();
		mfProductNameField.setBounds(307, 49, 249, 20);
		addProductPanel.add(mfProductNameField);
		mfProductNameField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Product Name:");
		lblNewLabel.setBounds(202, 51, 95, 14);
		addProductPanel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));

		JLabel lblProductBrand = new JLabel("Product Brand:");
		lblProductBrand.setBounds(202, 82, 95, 14);
		addProductPanel.add(lblProductBrand);
		lblProductBrand.setFont(new Font("Dialog", Font.BOLD, 12));

		mfProductBrandField = new JTextField();
		mfProductBrandField.setBounds(307, 80, 249, 20);
		addProductPanel.add(mfProductBrandField);
		mfProductBrandField.setColumns(10);

		JLabel lblStocks = new JLabel("Stocks Available:");
		lblStocks.setBounds(202, 113, 99, 14);
		addProductPanel.add(lblStocks);
		lblStocks.setFont(new Font("Dialog", Font.BOLD, 12));

		mfProductStocksField = new JTextField();
		mfProductStocksField.setBounds(307, 111, 86, 20);
		addProductPanel.add(mfProductStocksField);
		mfProductStocksField.setColumns(10);

		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setBounds(202, 145, 86, 14);
		addProductPanel.add(lblCategory);
		lblCategory.setFont(new Font("Dialog", Font.BOLD, 12));

		mfCategoryComboBox = new JComboBox();
		mfCategoryComboBox.setBounds(307, 142, 150, 22);
		addProductPanel.add(mfCategoryComboBox);

		mfCategoryComboBox.setModel(categModel);
		mfCategoryComboBox.setRenderer(new MerchantCategoryRenderer());

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(592, 20, 5, 601);
		addProductPanel.add(separator);

		JButton refreshButton = new JButton("Refresh Table");
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshLoanTermsTable();
			}
		});
		refreshButton.setBounds(951, 100, 121, 23);
		addProductPanel.add(refreshButton);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(28, 289, 99, 0);
		addProductPanel.add(scrollPane_2);

		JScrollPane loanInterestTableScrollPane_1 = new JScrollPane();
		loanInterestTableScrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		loanInterestTableScrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		loanInterestTableScrollPane_1.setBounds(28, 265, 528, 150);
		addProductPanel.add(loanInterestTableScrollPane_1);

		mfDescriptionField = new JTextArea();
		loanInterestTableScrollPane_1.setViewportView(mfDescriptionField);

		JScrollPane loanInterestTableScrollPane_1_1 = new JScrollPane();
		loanInterestTableScrollPane_1_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		loanInterestTableScrollPane_1_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		loanInterestTableScrollPane_1_1.setBounds(28, 461, 528, 150);
		addProductPanel.add(loanInterestTableScrollPane_1_1);

		mfSpecificationsField = new JTextArea();
		loanInterestTableScrollPane_1_1.setViewportView(mfSpecificationsField);
		uploadPicButton.addActionListener(e -> uploadImage());
		addProductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(MERCHANT_USER_ID);

				try {
					String productNameText = mfProductNameField.getText().trim();
					String productBrandText = mfProductBrandField.getText().trim();
					String productStocksText = mfProductStocksField.getText().trim();
					int categorySelectedIndex = mfCategoryComboBox.getSelectedIndex();
					String descriptionText = mfDescriptionField.getText().trim();
					String specificationsText = mfSpecificationsField.getText().trim();
					String productPriceText = mfProductPriceField.getText().trim();
					int interestRateArrayListLength = productLoanTerms.size();

					//Check first if one of the input is invalid
					if (isInputEmpty()) {

						//Check for the specific field that is empty
						if (HelperUtility.isInputFieldEmpty(productNameText)) {
							JOptionPane.showMessageDialog(null, "Please enter a product name to continue.", "Missing Product Name!", JOptionPane.ERROR_MESSAGE);
							return;
						} else if (HelperUtility.isInputFieldEmpty(productBrandText)) {
							JOptionPane.showMessageDialog(null, "Please enter a product brand to continue.", "Missing Product Brand!", JOptionPane.ERROR_MESSAGE);
							return;
						} else if (HelperUtility.isInputFieldEmpty(productStocksText)) {
							JOptionPane.showMessageDialog(null, "Please enter the product stocks to continue.", "Missing Product Stocks!", JOptionPane.ERROR_MESSAGE);
							return;
						} else if(categorySelectedIndex == -1) {
							JOptionPane.showMessageDialog(null, "Please choose a category to continue.", "Missing Product Category!", JOptionPane.ERROR_MESSAGE);
							return;
						} else if (HelperUtility.isInputFieldEmpty(descriptionText)) {
							JOptionPane.showMessageDialog(null, "Please enter a description to continue.", "Missing Product Description!", JOptionPane.ERROR_MESSAGE);
							return;
						} else if (HelperUtility.isInputFieldEmpty(specificationsText)) {
							JOptionPane.showMessageDialog(null, "Please enter a specification to continue.", "Missing Product Specification!", JOptionPane.ERROR_MESSAGE);
							return;
						} else if (HelperUtility.isInputFieldEmpty(productPriceText)) {
							JOptionPane.showMessageDialog(null, "Please enter a product price to continue.", "Missing Product Price!", JOptionPane.ERROR_MESSAGE);
							return;
						} else if (interestRateArrayListLength == 0) {
							JOptionPane.showMessageDialog(null, "Please enter at least one interest rate with months to pay to continue.", "Missing Product Loan Term!", JOptionPane.ERROR_MESSAGE);
							return;
						}

					} 

					//Convert each string to text to their corresponding data type
					int productStocks = Integer.parseInt(productStocksText);
					String categorySelectedText = mfCategoryComboBox.getSelectedItem().toString();
					float productPrice = Float.parseFloat(productPriceText);

					Product prod = new Product(MERCHANT_USER_ID, 
							DEFAULT_PRODUCT_PICTURE_PATH,
							productNameText, 
							productBrandText, 
							descriptionText, 
							specificationsText, 
							productPrice, 
							productStocks, 
							categorySelectedText,
							productLoanTerms);

					//Check if the inputs are invalid
					if(!prod.isProductInputValid()) {
						if (productPrice <= 0){
							JOptionPane.showMessageDialog(null, "Please enter a valid price to continue", "Invalid Price", JOptionPane.WARNING_MESSAGE);
							return;
						}
						else if (productStocks < 0) {
							JOptionPane.showMessageDialog(null, "Please enter a valid product stocks to continue", "Invalid Interest Rate", JOptionPane.WARNING_MESSAGE);
							return;
						} 
					} 

					//Check if the merchant has uploaded a product picture
					int choice;
					String finalProductImagePath;
					if (productPicture.getClientProperty("imagePath") != null) {
						//Get the uploaded picture path	
						finalProductImagePath = (String) productPicture.getClientProperty("imagePath");
						
					} else {
						choice = JOptionPane.showConfirmDialog(null, "You didn't upload any product picture. The system will use the default picture for your product. \n Do you wish to continue?", "Confirm Action", JOptionPane.YES_NO_OPTION);

						//If the user chooses no, go out of the function
						if (choice == JOptionPane.NO_OPTION) {
							return;
						} 
						
						//The user clicked yes
						//Use the default picture
						finalProductImagePath = DEFAULT_PRODUCT_PICTURE_PATH;	
					}
					
					//Proceed in sending the data to database

					ProductRegistrationData productData = new ProductRegistrationData(MERCHANT_USER_ID, 
							finalProductImagePath, 
							productNameText, 
							productBrandText, 
							descriptionText, 
							specificationsText, 
							productPrice, 
							productStocks, 
							categorySelectedText,
							productLoanTerms);

					
					
					
					if(Merchant.addProduct(MERCHANT_USER_ID, productData)) {
						JOptionPane.showMessageDialog(null, "Your product is successfully added!", "Product Added", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Please try again later.", "Server Error!", JOptionPane.ERROR_MESSAGE);
					}
					



				} catch (NumberFormatException numberEx) {
					JOptionPane.showMessageDialog(null, "Please enter only a number value for product price & stocks", "Invalid Input!", JOptionPane.ERROR_MESSAGE);
				}


			}
		});


	}

	private void addLoanRowToTable() {
		try {

			//Check first if the price is empty
			String productPriceText = mfProductPriceField.getText().trim();
			String interestRateText = mInterestRateField.getText().trim();
			String monthsToPayText = monthsToPayField.getText().trim();

			//Checks if it empty first
			if(productPriceText.isEmpty() 
					|| productPriceText == null ) {
				JOptionPane.showMessageDialog(null, "Please indicate the price first before adding new interest rates.", "Missing Product Price", JOptionPane.WARNING_MESSAGE);
				return;
			}else if(monthsToPayText.isEmpty() 
					|| monthsToPayText == null) {
				JOptionPane.showMessageDialog(null, "Please enter the months to pay to continue", "Missing Months To Pay", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (interestRateText.isEmpty() 
					|| interestRateText == null ) {
				JOptionPane.showMessageDialog(null, "Please enter a interest rate to continue", "Missing Interest Rate", JOptionPane.WARNING_MESSAGE);
				return;
			}  

			//Convert the value from the field
			float price = Float.parseFloat(productPriceText);
			float interestRate = Float.parseFloat(interestRateText) / 100;
			int monthsToPay = Integer.parseInt(monthsToPayText);

			//Checks if the price, months to pay, interest rate is valid
			if (price <= 0){
				JOptionPane.showMessageDialog(null, "Please enter a valid price to continue", "Invalid Price", JOptionPane.WARNING_MESSAGE);
				return;
			}
			else if (interestRate < 0) {
				JOptionPane.showMessageDialog(null, "Please enter a valid interest rate to continue", "Invalid Interest Rate", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (monthsToPay <= 0) {
				JOptionPane.showMessageDialog(null, "Please enter a valid months to pay to continue", "Invalid Input", JOptionPane.WARNING_MESSAGE);
				return;
			}

			//Then proceed here if all the inputs are not empty and valid
			loanTableModel.setRowCount(0); // First, empty the whole table of loan terms
			//Instantiate a product loan class
			ProductLoanTerm productLoanTerm = new ProductLoanTerm(monthsToPay, interestRate);
			//Add the object to the arraylist of ProductLoan
			productLoanTerms.add(productLoanTerm);

			//Empty the two fields after adding the new loan term
			monthsToPayField.setText("");
			mInterestRateField.setText("");

			//Sort the arraylist based on the months to pay (ascending order)
			Collections.sort(productLoanTerms, (o1, o2) -> Integer.compare(o1.getMonthsToPay(), o2.getMonthsToPay()));

			//Populate the table with the sorted arraylist
			for(ProductLoanTerm prodLoan : productLoanTerms) {	
				float potentialInterest = calculateInterest(price, prodLoan.getInterestRate(), prodLoan.getMonthsToPay());
				loanTableModel.addRow(new Object[] { prodLoan.getMonthsToPay(), prodLoan.getInterestRate() * 100 + "%","₱ " + potentialInterest});
			}

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Please enter only a number value for months to pay, interest rate & price.", "Invalid Input!", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void refreshLoanTermsTable() {

		//Check first if the price is empty
		String productPriceText = mfProductPriceField.getText().trim();

		//Checks if it empty first
		if(productPriceText.isEmpty() 
				|| productPriceText == null ) {
			JOptionPane.showMessageDialog(null, "Please indicate the price first before refreshing the table.", "Missing Product Price", JOptionPane.WARNING_MESSAGE);
			return;
		} 

		//Convert the value from the field
		float price = Float.parseFloat(productPriceText);

		if (price < 0) {
			JOptionPane.showMessageDialog(null, "Please enter a valid price to continue", "Invalid Product Price", JOptionPane.WARNING_MESSAGE);
			return;
		}

		//Then proceed here if all the inputs are not empty and valid
		loanTableModel.setRowCount(0); // First, empty the whole table of loan terms
		//Instantiate a product loan class

		//Sort the arraylist based on the months to pay (ascending order)
		Collections.sort(productLoanTerms, (o1, o2) -> Integer.compare(o1.getMonthsToPay(), o2.getMonthsToPay()));

		//Populate the table with the sorted arraylist
		for(ProductLoanTerm prodLoan : productLoanTerms) {	
			float potentialInterest = calculateInterest(price, prodLoan.getInterestRate(), prodLoan.getMonthsToPay());
			loanTableModel.addRow(new Object[] { prodLoan.getMonthsToPay(), prodLoan.getInterestRate() * 100 + "%","₱ " + potentialInterest});
		}

	}



	private void deleteRow() {
		int[] selectedRowIndices = loanInterestTable.getSelectedRows();


		if(selectedRowIndices.length > 0) {

			Arrays.sort(selectedRowIndices);
			for (int i = selectedRowIndices.length - 1; i >= 0  ; i--) {
				int rowIndex = selectedRowIndices[i];
				productLoanTerms.remove(rowIndex);
			}

			loanTableModel.setRowCount(0);

			Collections.sort(productLoanTerms, (o1, o2) -> Integer.compare(o1.getMonthsToPay(), o2.getMonthsToPay()));
			for(ProductLoanTerm prodLoan : productLoanTerms) {
				float price = Float.parseFloat(mfProductPriceField.getText());
				float potentialInterest = calculateInterest(price, prodLoan.getInterestRate(), prodLoan.getMonthsToPay());
				loanTableModel.addRow(new Object[] { prodLoan.getMonthsToPay(), prodLoan.getInterestRate() * 100 + "%","₱ " + potentialInterest});
			}

		}
	}


	private float calculateInterest(float productPrice, float interestRate, int monthsToPay) {
		return (productPrice * interestRate) * monthsToPay; //
	}

	//For uploading images
	private void uploadImage() {

		//For scaling the file
		int profPicLabelWidth = productPicture.getWidth();
		int profPicLabelHeight = productPicture.getHeight();

		//Open as model dialog
		JFileChooser fileChooser = new JFileChooser();

		//Filtering the file based on their file extensions
		fileChooser.setFileFilter(new FileNameExtensionFilter("jpg/png/gif", "jpg", "png", "gif"));

		int result = fileChooser.showOpenDialog(MerchantFrame.this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());

			//Scaling the image
			Image originalImage = imageIcon.getImage();
			Image scaledImage = originalImage.getScaledInstance(profPicLabelWidth, profPicLabelHeight, Image.SCALE_SMOOTH);
			ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
			productPicture.setIcon(scaledImageIcon);
			productPicture.putClientProperty("imagePath", selectedFile.getAbsolutePath());
		}
	}


	//For validating each inputs
	private boolean isInputEmpty() {
		String productNameText = mfProductNameField.getText().trim();
		String productBrandText = mfProductBrandField.getText().trim();
		String productStocks = mfProductStocksField.getText().trim();
		int categorySelectedIndex = mfCategoryComboBox.getSelectedIndex();
		String descriptionText = mfDescriptionField.getText().trim();
		String specificationsText = mfSpecificationsField.getText().trim();
		String productPriceText = mfProductPriceField.getText().trim();
		int interestRateArrayListLength = productLoanTerms.size();

		return productNameText.isEmpty() || productNameText == null 
				|| productBrandText.isEmpty() || productBrandText == null
				|| productStocks.isEmpty() || productStocks == null
				|| categorySelectedIndex == -1 
				|| descriptionText.isEmpty() || descriptionText == null
				|| specificationsText.isEmpty() || specificationsText == null 
				|| productPriceText.isEmpty() || productPriceText == null
				|| interestRateArrayListLength == 0;
	}

	//For changing from one menu to another
	private void showPanel(JPanel panel) {
		panel.setVisible(true);
	}
}
