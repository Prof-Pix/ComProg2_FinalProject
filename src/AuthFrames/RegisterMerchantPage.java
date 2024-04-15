package AuthFrames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Components.GoToLoginPage;
import Components.InvalidInputPopup;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import UserEnums.MerchantCategory;
import Utilities.HelperUtility;
import JComboBoxRenderers.MerchantCategoryRenderer;
import PH_Locations.PHLocationsClass;
import User.Merchant;
import User.MerchantRegistrationData;
public class RegisterMerchantPage extends JFrame implements RegistrationPage{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField rmUserField;
	private JTextField rmFNameField;
	private JTextField rmMiddleNameField;
	private JLabel raLastNameLabel;
	private JTextField rmLastNameField;
	private JLabel raAgeLabel;
	private JTextField rmPhoneNumberField;
	private JLabel raEmailLabel;
	private JTextField rmAgeField;
	private JLabel raPhoneNumberLabel;
	private JTextField rmEmailField;
	private JLabel raPasswordLabel;
	private JTextField rmPasswordField;
	private JButton registerLoanerButton;
	private JLabel raYearLabel;
	private JTextField rmDayField;
	private JTextField rmYearField;
	private JLabel lblAccountDetails;
	private JSeparator separator;
	private JLabel lblPersonalInformation;
	private JSeparator separator_1;
	private JLabel lblMerchantInformation;
	private JLabel lblMerchantName;
	private JTextField rmMerchantNameField;
	private JLabel lblCategory;
	private JLabel lblMerchantAddress;
	private JComboBox<MerchantCategory> merchantCategoryComboBox;
	private JComboBox<String> locRegionComboBox;
	private JComboBox<String> locProvinceComboBox;
	private JComboBox<String> locCityComboBox;
	private JComboBox<String> locBarangayComboBox;

	private JLabel lblRegion;
	private JLabel lblBarangay;

	//Getting a reference of the frame
	JFrame thisFrame = this;

	//For Locations
	private HashMap<String, HashMap<String, HashMap<String, List<String>>>> phLocations;
	private JTextField rmStreetLocField;


	String selectedRegion; 
	String selectedProvince;
	String selectedCity;
	String selectedBarangay;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterMerchantPage frame = new RegisterMerchantPage();
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
	public RegisterMerchantPage() {

		setTitle("Merchant Registration Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(431, 0, 453, 721);
		panel.setBackground(new Color(128, 0, 0));
		contentPane.add(panel);

		JLabel raUserLabel = new JLabel("Username:");
		raUserLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raUserLabel.setBounds(40, 53, 107, 14);
		contentPane.add(raUserLabel);

		rmUserField = new JTextField();
		rmUserField.setBounds(171, 50, 192, 20);
		contentPane.add(rmUserField);
		rmUserField.setColumns(10);

		JLabel raFNameLabel = new JLabel("First Name:");
		raFNameLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raFNameLabel.setBounds(40, 154, 97, 14);
		contentPane.add(raFNameLabel);

		rmFNameField = new JTextField();
		rmFNameField.setBounds(171, 151, 192, 20);
		rmFNameField.setColumns(10);
		contentPane.add(rmFNameField);

		JLabel lblMiddlename = new JLabel("Middle Name:");
		lblMiddlename.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblMiddlename.setBounds(40, 185, 97, 14);
		contentPane.add(lblMiddlename);

		rmMiddleNameField = new JTextField();
		rmMiddleNameField.setBounds(171, 182, 192, 20);
		rmMiddleNameField.setColumns(10);
		contentPane.add(rmMiddleNameField);

		raLastNameLabel = new JLabel("Last Name:");
		raLastNameLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raLastNameLabel.setBounds(40, 216, 97, 14);
		contentPane.add(raLastNameLabel);

		rmLastNameField = new JTextField();
		rmLastNameField.setBounds(171, 213, 192, 20);
		rmLastNameField.setColumns(10);
		contentPane.add(rmLastNameField);

		raAgeLabel = new JLabel("Age:");
		raAgeLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raAgeLabel.setBounds(40, 276, 97, 18);
		contentPane.add(raAgeLabel);

		rmPhoneNumberField = new JTextField();
		rmPhoneNumberField.setBounds(171, 338, 192, 20);
		rmPhoneNumberField.setColumns(10);
		contentPane.add(rmPhoneNumberField);

		raEmailLabel = new JLabel("Email:");
		raEmailLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raEmailLabel.setBounds(40, 311, 97, 14);
		contentPane.add(raEmailLabel);

		rmAgeField = new JTextField();
		rmAgeField.setBounds(171, 275, 46, 20);
		rmAgeField.setColumns(10);
		contentPane.add(rmAgeField);

		raPhoneNumberLabel = new JLabel("Phone Number:");
		raPhoneNumberLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raPhoneNumberLabel.setBounds(40, 341, 97, 14);
		contentPane.add(raPhoneNumberLabel);

		rmEmailField = new JTextField();
		rmEmailField.setBounds(171, 306, 192, 20);
		rmEmailField.setColumns(10);
		contentPane.add(rmEmailField);

		raPasswordLabel = new JLabel("Password:");
		raPasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raPasswordLabel.setBounds(40, 84, 107, 14);
		contentPane.add(raPasswordLabel);

		rmPasswordField = new JTextField();
		rmPasswordField.setColumns(10);
		rmPasswordField.setBounds(171, 81, 192, 20);
		contentPane.add(rmPasswordField);

		JLabel lblBirthday = new JLabel("Birthday (MM/DD/YYYY):");
		lblBirthday.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblBirthday.setBounds(40, 248, 133, 14);
		contentPane.add(lblBirthday);

		raYearLabel = new JLabel("/");
		raYearLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		raYearLabel.setBounds(261, 248, 16, 14);
		contentPane.add(raYearLabel);

		JTextField rmMonthField = new JTextField();
		rmMonthField.setBounds(171, 245, 38, 20);
		contentPane.add(rmMonthField);
		rmMonthField.setColumns(10);

		rmDayField = new JTextField();
		rmDayField.setColumns(10);
		rmDayField.setBounds(219, 245, 38, 20);
		contentPane.add(rmDayField);

		rmYearField = new JTextField();
		rmYearField.setColumns(10);
		rmYearField.setBounds(268, 245, 38, 20);
		contentPane.add(rmYearField);

		lblAccountDetails = new JLabel("Account Details");
		lblAccountDetails.setFont(new Font("Dialog", Font.BOLD, 15));
		lblAccountDetails.setBounds(20, 26, 153, 14);
		contentPane.add(lblAccountDetails);

		separator = new JSeparator();
		separator.setBounds(20, 114, 372, 2);
		contentPane.add(separator);

		lblPersonalInformation = new JLabel("Personal Information");
		lblPersonalInformation.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPersonalInformation.setBounds(20, 127, 153, 14);
		contentPane.add(lblPersonalInformation);

		separator_1 = new JSeparator();
		separator_1.setBounds(20, 371, 372, 2);
		contentPane.add(separator_1);

		lblMerchantInformation = new JLabel("Merchant Information");
		lblMerchantInformation.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMerchantInformation.setBounds(20, 384, 153, 14);
		contentPane.add(lblMerchantInformation);

		lblMerchantName = new JLabel("Merchant Name:");
		lblMerchantName.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblMerchantName.setBounds(40, 409, 97, 14);
		contentPane.add(lblMerchantName);

		rmMerchantNameField = new JTextField();
		rmMerchantNameField.setColumns(10);
		rmMerchantNameField.setBounds(171, 406, 192, 20);
		contentPane.add(rmMerchantNameField);

		lblCategory = new JLabel("Category:");
		lblCategory.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCategory.setBounds(40, 442, 97, 14);
		contentPane.add(lblCategory);

		lblMerchantAddress = new JLabel("Province:");
		lblMerchantAddress.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblMerchantAddress.setBounds(66, 519, 107, 14);
		contentPane.add(lblMerchantAddress);

		merchantCategoryComboBox = new JComboBox();

		//For choices

		//For choices
		DefaultComboBoxModel<MerchantCategory> categModel = new DefaultComboBoxModel<>();
		categModel.addElement(null);		

		for (MerchantCategory categ: MerchantCategory.values()) {
			categModel.addElement(categ);
		}

		merchantCategoryComboBox.setModel(categModel);
		merchantCategoryComboBox.setRenderer(new MerchantCategoryRenderer());
		merchantCategoryComboBox.setBounds(171, 437, 192, 22);
		contentPane.add(merchantCategoryComboBox);

		JLabel lblLocation = new JLabel("LOCATION");
		lblLocation.setFont(new Font("Dialog", Font.BOLD, 12));
		lblLocation.setBounds(40, 470, 107, 14);
		contentPane.add(lblLocation);

		JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCity.setBounds(66, 550, 107, 14);
		contentPane.add(lblCity);

		JLabel lblStreet = new JLabel("Street:");
		lblStreet.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblStreet.setBounds(66, 612, 107, 14);
		contentPane.add(lblStreet);

		lblRegion = new JLabel("Region:");
		lblRegion.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblRegion.setBounds(66, 488, 107, 14);
		contentPane.add(lblRegion);

		lblBarangay = new JLabel("Barangay:");
		lblBarangay.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblBarangay.setBounds(66, 582, 107, 14);
		contentPane.add(lblBarangay);

		locProvinceComboBox = new JComboBox<String>();
		locProvinceComboBox.setBounds(171, 517, 192, 22);
		contentPane.add(locProvinceComboBox);

		locCityComboBox = new JComboBox<String>();
		locCityComboBox.setBounds(171, 548, 192, 22);
		contentPane.add(locCityComboBox);

		locBarangayComboBox = new JComboBox<String>();
		locBarangayComboBox.setBounds(171, 579, 192, 22);
		contentPane.add(locBarangayComboBox);

		rmStreetLocField = new JTextField();
		rmStreetLocField.setColumns(10);
		rmStreetLocField.setBounds(171, 610, 192, 20);
		contentPane.add(rmStreetLocField);
		
		locRegionComboBox = new JComboBox<String>();

		//For the elements of the location JComboBoxes
		PHLocationsClass phLocs = new PHLocationsClass();
		LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, List<String>>>> phLocations = phLocs.phLocations;

		locRegionComboBox.addItem("--- Select a region ---");
		for (String region : phLocations.keySet()) {
			locRegionComboBox.addItem(region);
		}

		locProvinceComboBox.addItem("--- Select a province ---");
		locProvinceComboBox.setEnabled(false);

		locCityComboBox.addItem("--- Select a city ---");
		locCityComboBox.setEnabled(false);

		locBarangayComboBox.addItem("--- Select a barangay ---");
		locBarangayComboBox.setEnabled(false);

		rmStreetLocField.setEnabled(false);

		locRegionComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRegionIndex = locRegionComboBox.getSelectedIndex();
				selectedRegion = (String) locRegionComboBox.getSelectedItem();

				if (selectedRegionIndex != 0) {

					locProvinceComboBox.removeAllItems();

					LinkedHashMap<String, LinkedHashMap<String, List<String>>> regionProvinces = phLocations.get(selectedRegion);
					locProvinceComboBox.addItem("--- Select a province ---");
					for (String provinces : regionProvinces.keySet()) {
						locProvinceComboBox.addItem(provinces);
					}
					locProvinceComboBox.setEnabled(true);

				} else {
					locProvinceComboBox.removeAllItems();
					locProvinceComboBox.addItem("--- Select a province ---");
					locProvinceComboBox.setEnabled(false);
				}

			}

		});

		locProvinceComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedProvinceIndex = locProvinceComboBox.getSelectedIndex();
				selectedProvince = (String) locProvinceComboBox.getSelectedItem();

				if (selectedProvinceIndex != 0 && selectedProvinceIndex != -1) {
					locCityComboBox.removeAllItems();

					LinkedHashMap<String, List<String>> provinceCities = phLocations.get(selectedRegion).get(selectedProvince);

					locCityComboBox.addItem("--- Select a city ---");
					for (String city : provinceCities.keySet()) {
						locCityComboBox.addItem(city);
					}
					locCityComboBox.setEnabled(true);

				} else {
					locCityComboBox.removeAllItems();
					locCityComboBox.addItem("--- Select a city ---");
					locCityComboBox.setEnabled(false);
				}

			}


		});

		locCityComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedCityIndex = locCityComboBox.getSelectedIndex();
				selectedCity = (String) locCityComboBox.getSelectedItem();

				if (selectedCityIndex != 0 && selectedCityIndex != -1) {
					locBarangayComboBox.removeAllItems();

					List<String> cityBarangays = phLocations.get(selectedRegion).get(selectedProvince).get(selectedCity);

					locBarangayComboBox.addItem("--- Select a barnagay ---");
					for (String barangay : cityBarangays) {
						locBarangayComboBox.addItem(barangay);
					}
					locBarangayComboBox.setEnabled(true);

				} else {
					locBarangayComboBox.removeAllItems();
					locBarangayComboBox.addItem("--- Select a barangay ---");
					locBarangayComboBox.setEnabled(false);
				}

			}

		});

		locBarangayComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedBarangayIndex = locBarangayComboBox.getSelectedIndex();
				selectedBarangay = (String) locBarangayComboBox.getSelectedItem();

				if (selectedBarangayIndex != 0 && selectedBarangayIndex != -1) {
					rmStreetLocField.setEnabled(true);

				} else {
					rmStreetLocField.setEnabled(false);
				}

			}

		});

		locRegionComboBox.setBounds(171, 486, 192, 22);
		contentPane.add(locRegionComboBox);


		registerLoanerButton = new JButton("Register Merchant Account");
		registerLoanerButton.setFont(new Font("Dialog", Font.PLAIN, 11));
		registerLoanerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				String usernameText = rmUserField.getText().trim();
				String passwordText = rmPasswordField.getText().trim();
				String firstNameText = rmFNameField.getText().trim();
				String middleNameText = rmMiddleNameField.getText().trim();
				String lastNameText = rmLastNameField.getText().trim();
				String monthText = rmMonthField.getText().trim();
				String dayText = rmDayField.getText().trim();
				String yearText = rmYearField.getText().trim();
				String ageText = rmAgeField.getText().trim();
				String emailText = rmEmailField.getText().trim();
				String phoneNumberText = rmPhoneNumberField.getText().trim();
				String merchantNameText = rmMerchantNameField.getText().trim();
				String merchantStreetLocationText = rmStreetLocField.getText().trim();

				try {

					//Checks if one of the common inputs are invalid
					if (isCommonFieldsEmpty(usernameText,
							passwordText,
							firstNameText,
							middleNameText,
							lastNameText,
							monthText,
							dayText,
							yearText,
							ageText,
							emailText,
							phoneNumberText) || isSpecificFieldsEmpty()) {	

						String errorText = "Please check all the required fields.";
						InvalidInputPopup popup = new InvalidInputPopup(RegisterMerchantPage.this, errorText);
						popup.setVisible(true);

					}  else {

						String merchantCategoryText = merchantCategoryComboBox.getSelectedItem().toString().toLowerCase();
						String merchantRegionLocationText= locRegionComboBox.getSelectedItem().toString().toLowerCase();
						String merchantProvinceLocationText = locProvinceComboBox.getSelectedItem().toString().toLowerCase();
						String merchantCityLocationText = locCityComboBox.getSelectedItem().toString().toLowerCase();
						String merchantBarangayLocationText = locBarangayComboBox.getSelectedItem().toString().toLowerCase();



						//Proceeds if all of the inputs are not empty
						//Advanced Validation
						//For birthday
						LocalDate birthday = LocalDate.of(Integer.parseInt(yearText) , 
								Integer.parseInt(monthText) , 
								Integer.parseInt(dayText) );

						//For age
						int age = Integer.parseInt(ageText);

						//For phone number
						long phoneNumber = Long.parseLong(phoneNumberText);


						//						
						//						public Merchant(String username, String password, String firstName, String middleName, String lastName,
						//								LocalDate birthdate, int age, String email, String phoneNumber, String merchantName,
						//								MerchantCategory merchantCategory, String merchantAddress)

						Merchant merch = new Merchant(usernameText, 
								passwordText, 
								firstNameText, 
								middleNameText, 
								lastNameText, 
								birthday, 
								age, 
								emailText, 
								phoneNumberText, 
								merchantNameText, 
								merchantCategoryText, 
								merchantRegionLocationText,
								merchantProvinceLocationText,
								merchantCityLocationText, 
								merchantBarangayLocationText,
								merchantStreetLocationText);

						if(merch.isUserInputValid() && merch.checkDuplicateInput().equals("ok")) {
							MerchantRegistrationData merchRegData = new MerchantRegistrationData(usernameText, 
									passwordText, 
									firstNameText, 
									middleNameText, 
									lastNameText, 
									birthday, 
									age, 
									emailText, 
									phoneNumberText, 
									merchantNameText, 
									merchantCategoryText, 
									merchantRegionLocationText,
									merchantProvinceLocationText,
									merchantCityLocationText, 
									merchantBarangayLocationText,
									merchantStreetLocationText);

							//sending it to the data base
							if (merch.registerMerchant(merchRegData)) {
								GoToLoginPage login = new GoToLoginPage();
								login.setVisible(true);
							} else {
								String errorText= "";
								InvalidInputPopup popup = new InvalidInputPopup(RegisterMerchantPage.this, errorText);
								popup.setVisible(true);
							} 
						}else {

							String errorText = merch.checkErrorInputRegistration();

							if (errorText == null) {
								errorText = merch.checkDuplicateInput();
							}

							InvalidInputPopup popup = new InvalidInputPopup(RegisterMerchantPage.this, errorText);
							popup.setVisible(true);
						}

					}
				} catch (NumberFormatException numberEx) {
					String errorText = "Some fields only accepts integer values. Please try again.";

					InvalidInputPopup popup = new InvalidInputPopup(RegisterMerchantPage.this, errorText);
					popup.setVisible(true);
				}


			}
		});
		registerLoanerButton.setBounds(171, 642, 192, 48);
		contentPane.add(registerLoanerButton);

		JLabel raYearLabel_1 = new JLabel("/");
		raYearLabel_1.setFont(new Font("Dialog", Font.BOLD, 12));
		raYearLabel_1.setBounds(213, 248, 16, 14);
		contentPane.add(raYearLabel_1);

		JButton btnAlreadyAMerchant = new JButton("Already a merchant? Login");
		btnAlreadyAMerchant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage login = new LoginPage();
				login.setVisible(true);

				//For Closing Page
				HelperUtility.closePage(thisFrame);
			}
		});
		btnAlreadyAMerchant.setForeground(Color.BLUE);
		btnAlreadyAMerchant.setContentAreaFilled(false);
		btnAlreadyAMerchant.setBorderPainted(false);
		btnAlreadyAMerchant.setBounds(171, 688, 192, 23);
		contentPane.add(btnAlreadyAMerchant);


	}

	@Override
	public boolean isSpecificFieldsEmpty() {
		String merchantNameText = rmMerchantNameField.getText().trim();
		int merchantRegionLocationIndex = locRegionComboBox.getSelectedIndex();
		int merchantProvinceLocationIndex = locProvinceComboBox.getSelectedIndex();
		int merchantCityLocationIndex = locCityComboBox.getSelectedIndex();
		int merchantBarangayLocationIndex = locBarangayComboBox.getSelectedIndex();
		String merchantStreetLocationText = rmStreetLocField.getText().trim();

		return merchantNameText == null || 
				merchantNameText.isEmpty() || 
				merchantCategoryComboBox.getSelectedIndex() == -1 ||
				merchantRegionLocationIndex == -1 ||
				merchantRegionLocationIndex == 0 ||
				merchantProvinceLocationIndex == -1 ||
				merchantProvinceLocationIndex == 0 ||
				merchantCityLocationIndex == -1 ||
				merchantCityLocationIndex == 0 ||
				merchantBarangayLocationIndex  == -1 ||
				merchantBarangayLocationIndex  == 0 ||
				merchantStreetLocationText == null ||
				merchantStreetLocationText.isEmpty();


	}
}
