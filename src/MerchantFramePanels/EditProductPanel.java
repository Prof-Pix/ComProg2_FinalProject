package MerchantFramePanels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import JComboBoxRenderers.MerchantCategoryRenderer;
import Products.Product;
import Products.ProductLoanTerm;
import Products.ProductRegistrationData;
import User.Merchant;
import UserEnums.MerchantCategory;
import Utilities.HelperUtility;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Dialog.ModalityType;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditProductPanel extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField editPNameField;
	private JTextField editPBrandField;
	private JTextField editPPriceField;
	private JTextField interestRateField;
	private JTextField monthsToPayField;
	private JTextField editPStocksField;
	private JComboBox editPCategoryComboBox;
	private JTextArea editPDescriptionField;
	private JTextArea editPSpecificationsField;
	
	JButton saveChangesButton;
	
	
	JLabel productPicture;
	
	private ArrayList<ProductLoanTerm> productLoanTerms = new ArrayList<>();

	static String ORIGINAL_PRODUCT_NAME;
	static Product productData;
	static int MERCHANT_ID;
	
	boolean changesMade;
	boolean hasUploadedNewPicture = false;
	
	private JTable loanInterestTable;
	DefaultTableModel loanTableModel;
	private String irColumnNames[] = { "Months to pay", "Interest Rate", "Potential Interest"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			EditProductPanel dialog = new EditProductPanel();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditProductPanel() {
		EditProductPanel.ORIGINAL_PRODUCT_NAME = productData.getName();
		setTitle("Edit Product");

		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 1184, 680);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		productPicture = new JLabel();
		productPicture.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		productPicture.setBounds(23, 15, 150, 150);
		Image originalImage = productData.getProductImage().getImage();
		Image scaledImage = originalImage.getScaledInstance(productPicture.getWidth(), productPicture.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
		productPicture.setIcon(scaledImageIcon);
		contentPanel.add(productPicture);

		JLabel lblNewLabel = new JLabel("Product Name:");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(197, 31, 95, 14);
		contentPanel.add(lblNewLabel);

		editPNameField = new JTextField();
		editPNameField.setText(productData.getName());
		editPNameField.setColumns(10);
		editPNameField.setBounds(302, 29, 249, 20);
		contentPanel.add(editPNameField);

		JLabel lblProductBrand = new JLabel("Product Brand:");
		lblProductBrand.setFont(new Font("Dialog", Font.BOLD, 12));
		lblProductBrand.setBounds(197, 62, 95, 14);
		contentPanel.add(lblProductBrand);

		editPBrandField = new JTextField();
		editPBrandField.setText(productData.getBrand());
		editPBrandField.setColumns(10);
		editPBrandField.setBounds(302, 60, 249, 20);
		contentPanel.add(editPBrandField);

		JLabel lblStocks = new JLabel("Stocks Available:");
		lblStocks.setFont(new Font("Dialog", Font.BOLD, 12));
		lblStocks.setBounds(197, 93, 99, 14);
		contentPanel.add(lblStocks);

		editPStocksField = new JTextField();
		editPStocksField.setText(String.valueOf(productData.getStocksAvailable()));
		editPStocksField.setColumns(10);
		editPStocksField.setBounds(302, 91, 86, 20);
		contentPanel.add(editPStocksField);

		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCategory.setBounds(197, 125, 86, 14);
		contentPanel.add(lblCategory);

		editPCategoryComboBox = new JComboBox();

		DefaultComboBoxModel<MerchantCategory> categModel = new DefaultComboBoxModel<>();
		categModel.addElement(null);		

		//Getting the product's category
		String prodCategory = productData.getCategory();
		int selectedIndex = 0;
		int counter = 1;
		for (MerchantCategory categ: MerchantCategory.values()) {

			if(prodCategory.equals(categ.toString().toLowerCase())) {
				selectedIndex = counter;
			}
			counter++;
			categModel.addElement(categ);
		}

		editPCategoryComboBox.setModel(categModel);
		editPCategoryComboBox.setRenderer(new MerchantCategoryRenderer());

		editPCategoryComboBox.setSelectedIndex(selectedIndex);

		editPCategoryComboBox.setBounds(302, 122, 150, 22);
		contentPanel.add(editPCategoryComboBox);




		JButton uploadPicButton = new JButton("Upload Picture");
		uploadPicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uploadImage();
			}
		});
		uploadPicButton.setBounds(23, 176, 150, 23);
		contentPanel.add(uploadPicButton);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDescription.setBounds(23, 222, 86, 14);
		contentPanel.add(lblDescription);

		editPDescriptionField = new JTextArea();
		editPDescriptionField.setText(productData.getDescription());
		editPDescriptionField.setBounds(24, 246, 526, 148);
		contentPanel.add(editPDescriptionField);

		JLabel lblSpecifications = new JLabel("Specifications:");
		lblSpecifications.setFont(new Font("Dialog", Font.BOLD, 12));
		lblSpecifications.setBounds(23, 416, 86, 14);
		contentPanel.add(lblSpecifications);

		editPSpecificationsField = new JTextArea();
		editPSpecificationsField.setText(productData.getSpecifications());
		editPSpecificationsField.setBounds(24, 442, 526, 148);
		contentPanel.add(editPSpecificationsField);

		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPrice.setBounds(622, 31, 40, 14);
		contentPanel.add(lblPrice);

		JLabel lblProductBrand_1_1_1 = new JLabel("₱");
		lblProductBrand_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblProductBrand_1_1_1.setBounds(665, 31, 19, 14);
		contentPanel.add(lblProductBrand_1_1_1);

		editPPriceField = new JTextField();
		editPPriceField.setText(String.valueOf(productData.getPrice()));
		editPPriceField.setColumns(10);
		editPPriceField.setBounds(680, 29, 99, 20);
		contentPanel.add(editPPriceField);

		JLabel lblNewLabel_1 = new JLabel("Interest Rates @ Months to Pay");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_1.setBounds(622, 80, 196, 23);
		contentPanel.add(lblNewLabel_1);

		JButton refreshButton = new JButton("Refresh Table");
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshLoanTermsTable();
			}
		});
		refreshButton.setBounds(946, 80, 121, 23);
		contentPanel.add(refreshButton);

		JPanel loanInterestTablePanel = new JPanel();
		loanInterestTablePanel.setLayout(null);
		loanInterestTablePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		loanInterestTablePanel.setBounds(622, 114, 445, 305);
		contentPanel.add(loanInterestTablePanel);

		// Table Setup
		loanTableModel = new DefaultTableModel(irColumnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // All cells are non-editable
			}
		}; 

		JScrollPane loanInterestTableScrollPane = new JScrollPane();
		loanInterestTableScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		loanInterestTableScrollPane.setBounds(0, 0, 445, 229);
		loanInterestTablePanel.add(loanInterestTableScrollPane);

		loanInterestTable = new JTable(loanTableModel);
		//For changing the alignment of each row
		DefaultTableCellRenderer loanInterestTablerRenderer  = new DefaultTableCellRenderer();
		loanInterestTablerRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

		//Disbable Column Reordering
		JTableHeader loanInterestTableHeader = loanInterestTable.getTableHeader();
		loanInterestTableHeader.setReorderingAllowed(false);
		loanInterestTable.setDefaultRenderer(Object.class, loanInterestTablerRenderer);
		
		for(ProductLoanTerm prodLoanTerm : productData.getProductLoans()) {
			
			int monthsToPay = prodLoanTerm.getMonthsToPay();
			float interestRate = prodLoanTerm.getInterestRate();
			
			productLoanTerms.add(new ProductLoanTerm(monthsToPay, interestRate));
		}
		//Sort the arraylist based on the months to pay (ascending order)
		Collections.sort(productLoanTerms, (o1, o2) -> Integer.compare(o1.getMonthsToPay(), o2.getMonthsToPay()));

		//Populate the table with the sorted arraylist
		for(ProductLoanTerm prodLoan : productLoanTerms) {	
			float potentialInterest = calculateInterest(productData.getPrice(), prodLoan.getInterestRate(), prodLoan.getMonthsToPay());
			loanTableModel.addRow(new Object[] { prodLoan.getMonthsToPay(), prodLoan.getInterestRate() * 100 + "%","₱ " + potentialInterest});
		}
		loanInterestTableScrollPane.setViewportView(loanInterestTable);



		JLabel lblMonthsToPay = new JLabel("Months to Pay:");
		lblMonthsToPay.setFont(new Font("Dialog", Font.BOLD, 12));
		lblMonthsToPay.setBounds(24, 243, 94, 14);
		loanInterestTablePanel.add(lblMonthsToPay);

		interestRateField = new JTextField();
		interestRateField.setColumns(10);
		interestRateField.setBounds(117, 271, 33, 20);
		loanInterestTablePanel.add(interestRateField);

		JLabel lblProductBrand_1_1 = new JLabel("%");
		lblProductBrand_1_1.setBounds(152, 274, 19, 14);
		loanInterestTablePanel.add(lblProductBrand_1_1);

		JButton addProdLoanBtn = new JButton("Add New Row");
		addProdLoanBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addLoanRowToTable();
			}
		});
		addProdLoanBtn.setBounds(237, 240, 160, 23);
		loanInterestTablePanel.add(addProdLoanBtn);

		JButton deleteButton = new JButton("Delete Selected Row/s");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteRow();
			}
		});
		deleteButton.setBounds(238, 271, 161, 23);
		loanInterestTablePanel.add(deleteButton);

		JLabel lblInterestRate = new JLabel("Interest Rate:");
		lblInterestRate.setFont(new Font("Dialog", Font.BOLD, 12));
		lblInterestRate.setBounds(24, 275, 94, 14);
		loanInterestTablePanel.add(lblInterestRate);

		monthsToPayField = new JTextField();
		monthsToPayField.setColumns(10);
		monthsToPayField.setBounds(117, 240, 64, 20);
		loanInterestTablePanel.add(monthsToPayField);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(587, 0, 5, 601);
		contentPanel.add(separator);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				saveChangesButton = new JButton("Save Product Changes");
				saveChangesButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String productNameText = editPNameField.getText().trim();
						String productBrandText = editPBrandField.getText().trim();
						String productStocksText = editPStocksField.getText().trim();
						int categorySelectedIndex = editPCategoryComboBox.getSelectedIndex();
						String descriptionText = editPDescriptionField.getText().trim();
						String specificationsText = editPSpecificationsField.getText().trim();
						String productPriceText = editPPriceField.getText().trim();
						int interestRateArrayListLength = productLoanTerms.size();
						
						if(isInputEmpty() || !hasChangesMade()) {
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
							} else if (!hasChangesMade()) {
								JOptionPane.showMessageDialog(null, "No changes have been detected. Please modify at least one field to continue.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
								return;
							}
						}
						
						//Convert each string to text to their corresponding data type
						int productStocks = Integer.parseInt(productStocksText);
						String categorySelectedText = editPCategoryComboBox.getSelectedItem().toString();
						float productPrice = Float.parseFloat(productPriceText);
						
						ImageIcon productImage = productData.getProductImage();
						
						Product prod = new Product(MERCHANT_ID, 
								productImage,
								productNameText, 
								productBrandText, 
								descriptionText, 
								specificationsText, 
								productPrice, 
								productStocks, 
								categorySelectedText,
								productLoanTerms);

						//Check if the inputs are invalid
						if(!prod.isProductInputValid() || !Merchant.checkDuplicateProductName(MERCHANT_ID, productNameText).equals("ok") || !changesMade) {
							String statusText = Merchant.checkDuplicateProductName(MERCHANT_ID, productNameText);

							if (productPrice <= 0){
								JOptionPane.showMessageDialog(null, "Please enter a valid price to continue", "Invalid Price", JOptionPane.WARNING_MESSAGE);
								return;
							}
							else if (productStocks < 0) {
								JOptionPane.showMessageDialog(null, "Please enter a valid product stocks to continue", "Invalid Interest Rate", JOptionPane.WARNING_MESSAGE);
								return;
							} else if (!statusText.equals("ok")) {
								if(!productNameText.equals(productData.getName()) ) {
									JOptionPane.showMessageDialog(null, statusText, "Existing Product!", JOptionPane.WARNING_MESSAGE);
									return;
								}

							} 
						} 
						
						//Proceed in sending the data to database

						ProductRegistrationData productRegData = new ProductRegistrationData(MERCHANT_ID, 
								productImage, 
								productNameText, 
								productBrandText, 
								descriptionText, 
								specificationsText, 
								productPrice, 
								productStocks, 
								categorySelectedText,
								productLoanTerms);
						
						if(Merchant.editProduct(MERCHANT_ID, productRegData, productData.getProductId())) {
							System.out.println(productData.getProductId());
							JOptionPane.showMessageDialog(null, "Your product is successfully edit!", "Product Edited", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Please try again later.", "Server Error!", JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
				saveChangesButton.setActionCommand("Cancel");
				buttonPane.add(saveChangesButton);
			}
		}
	}
	
	private void addLoanRowToTable() {
		try {

			//Check first if the price is empty
			String productPriceText = editPPriceField.getText().trim();
			String interestRateText = interestRateField.getText().trim();
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
			interestRateField.setText("");

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
		String productPriceText = editPPriceField.getText().trim();

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
	
	private float calculateInterest(float productPrice, float interestRate, int monthsToPay) {
		return (productPrice * interestRate) * monthsToPay; 
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
				float price = Float.parseFloat(editPPriceField.getText().trim());
				float potentialInterest = calculateInterest(price, prodLoan.getInterestRate(), prodLoan.getMonthsToPay());
				loanTableModel.addRow(new Object[] { prodLoan.getMonthsToPay(), prodLoan.getInterestRate() * 100 + "%","₱ " + potentialInterest});
			}

		}
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

		int result = fileChooser.showOpenDialog(EditProductPanel.this);
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
		hasUploadedNewPicture = true;
	}
	
	//For validating each inputs
		private boolean isInputEmpty() {
			String productNameText = editPNameField.getText().trim();
			String productBrandText = editPBrandField.getText().trim();
			String productStocks = editPStocksField.getText().trim();
			int categorySelectedIndex = editPCategoryComboBox.getSelectedIndex();
			String descriptionText = editPDescriptionField.getText().trim();
			String specificationsText = editPSpecificationsField.getText().trim();
			String productPriceText = editPPriceField.getText().trim();
			int interestRateArrayListLength = productLoanTerms.size();

			return HelperUtility.isInputFieldEmpty(productNameText) 
					|| HelperUtility.isInputFieldEmpty(productBrandText) 
					|| HelperUtility.isInputFieldEmpty(productStocks) 
					|| categorySelectedIndex == -1 
					|| HelperUtility.isInputFieldEmpty(descriptionText) 
					|| HelperUtility.isInputFieldEmpty(specificationsText) 
					|| HelperUtility.isInputFieldEmpty(productPriceText) 
					|| interestRateArrayListLength == 0;
		}

		private boolean hasChangesMade() {
			String productNameText = editPNameField.getText().trim();
			String productBrandText = editPBrandField.getText().trim();
			String productStocksText = editPStocksField.getText().trim();
			String categorySelectedText = editPCategoryComboBox.getSelectedItem().toString().toLowerCase();
			String descriptionText = editPDescriptionField.getText().trim();
			String specificationsText = editPSpecificationsField.getText().trim();
			String productPriceText = editPPriceField.getText().trim();
			
			if (!productNameText.equals(productData.getName()) ||
					!productBrandText.equals(productData.getBrand()) ||
					!productStocksText.equals(String.valueOf(productData.getStocksAvailable())) ||
					!categorySelectedText.equals(productData.getCategory()) ||
					!descriptionText.equals(productData.getDescription()) ||
					!specificationsText.equals(productData.getSpecifications()) ||
					!productPriceText.equals(String.valueOf(productData.getPrice())) ||
					productLoanTerms.size() != productData.getProductLoans().size()
					) {

				return true;
				
			}
			
			boolean prodLoanTermsModified = false;
			
			//if same size, check for changes
			for(int i = 0; i < productLoanTerms.size() ; i++) {
				if (productLoanTerms.get(i).getMonthsToPay() != productLoanTerms.get(i).getMonthsToPay() ||
						(productLoanTerms.get(i).getInterestRate() != productLoanTerms.get(i).getInterestRate())) {
					System.out.println(productLoanTerms.get(i).getMonthsToPay() != productLoanTerms.get(i).getMonthsToPay() ||
						(productLoanTerms.get(i).getInterestRate() != productLoanTerms.get(i).getInterestRate()));
					prodLoanTermsModified = true;
					break;
				}
						
			}
			
			if (prodLoanTermsModified == true) {
				return true;
			}
			
			if (hasUploadedNewPicture == true) {
				return true;
			}
			
			return false;
		}
}
