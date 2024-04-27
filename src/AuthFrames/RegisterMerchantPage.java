package AuthFrames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Components.GoToLoginPage;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import UserEnums.MerchantCategory;
import Utilities.HelperUtility;
import JComboBoxRenderers.MerchantCategoryRenderer;
import PH_Locations.PHLocationsClass;
import User.Merchant;
import User.MerchantRegistrationData;
import javax.swing.JRadioButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
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
	
	String selectedGenderText;

	//For Locations
	private HashMap<String, HashMap<String, HashMap<String, List<String>>>> phLocations;
	private JTextField rmStreetLocField;


	String selectedRegion; 
	String selectedProvince;
	String selectedCity;
	String selectedBarangay;
	private JLabel raGenderLabel;
	private JRadioButton maleRadioButton;
	private JRadioButton femaleRadioButton;
	private JRadioButton othersRadioButton;

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
		setResizable(false);

		setTitle("Merchant Registration Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 897, 560);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(37, 102, 112));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel raUserLabel = new JLabel("Username:");
		raUserLabel.setForeground(new Color(255, 255, 255));
		raUserLabel.setBackground(new Color(255, 255, 255));
		raUserLabel.setBounds(69, 51, 107, 14);
		raUserLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		contentPane.add(raUserLabel);

		rmUserField = new JTextField();
		rmUserField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		rmUserField.setFont(new Font("Dialog", Font.PLAIN, 12));
		rmUserField.setBackground(new Color(237, 250, 139));
		rmUserField.setBounds(200, 48, 192, 20);
		contentPane.add(rmUserField);
		rmUserField.setColumns(10);

		JLabel raFNameLabel = new JLabel("First Name:");
		raFNameLabel.setForeground(new Color(255, 255, 255));
		raFNameLabel.setBackground(new Color(255, 255, 255));
		raFNameLabel.setBounds(69, 152, 97, 14);
		raFNameLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		contentPane.add(raFNameLabel);

		rmFNameField = new JTextField();
		rmFNameField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		rmFNameField.setFont(new Font("Dialog", Font.PLAIN, 12));
		rmFNameField.setBackground(new Color(237, 250, 139));
		rmFNameField.setBounds(200, 149, 192, 20);
		rmFNameField.setColumns(10);
		contentPane.add(rmFNameField);

		JLabel lblMiddlename = new JLabel("Middle Name:");
		lblMiddlename.setForeground(new Color(255, 255, 255));
		lblMiddlename.setBackground(new Color(255, 255, 255));
		lblMiddlename.setBounds(69, 183, 97, 14);
		lblMiddlename.setFont(new Font("Dialog", Font.PLAIN, 12));
		contentPane.add(lblMiddlename);

		rmMiddleNameField = new JTextField();
		rmMiddleNameField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		rmMiddleNameField.setFont(new Font("Dialog", Font.PLAIN, 12));
		rmMiddleNameField.setBackground(new Color(237, 250, 139));
		rmMiddleNameField.setBounds(200, 180, 192, 20);
		rmMiddleNameField.setColumns(10);
		contentPane.add(rmMiddleNameField);

		raLastNameLabel = new JLabel("Last Name:");
		raLastNameLabel.setForeground(new Color(255, 255, 255));
		raLastNameLabel.setBackground(new Color(255, 255, 255));
		raLastNameLabel.setBounds(69, 214, 97, 14);
		raLastNameLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		contentPane.add(raLastNameLabel);

		rmLastNameField = new JTextField();
		rmLastNameField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		rmLastNameField.setFont(new Font("Dialog", Font.PLAIN, 12));
		rmLastNameField.setBackground(new Color(237, 250, 139));
		rmLastNameField.setBounds(200, 211, 192, 20);
		rmLastNameField.setColumns(10);
		contentPane.add(rmLastNameField);

		raAgeLabel = new JLabel("Age:");
		raAgeLabel.setForeground(new Color(255, 255, 255));
		raAgeLabel.setBackground(new Color(255, 255, 255));
		raAgeLabel.setBounds(69, 274, 97, 18);
		raAgeLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		contentPane.add(raAgeLabel);

		rmPhoneNumberField = new JTextField();
		rmPhoneNumberField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		rmPhoneNumberField.setFont(new Font("Dialog", Font.PLAIN, 12));
		rmPhoneNumberField.setBackground(new Color(237, 250, 139));
		rmPhoneNumberField.setBounds(200, 336, 192, 20);
		rmPhoneNumberField.setColumns(10);
		contentPane.add(rmPhoneNumberField);

		raEmailLabel = new JLabel("Email:");
		raEmailLabel.setForeground(new Color(255, 255, 255));
		raEmailLabel.setBackground(new Color(255, 255, 255));
		raEmailLabel.setBounds(69, 309, 97, 14);
		raEmailLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		contentPane.add(raEmailLabel);

		rmAgeField = new JTextField();
		rmAgeField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		rmAgeField.setFont(new Font("Dialog", Font.PLAIN, 12));
		rmAgeField.setBackground(new Color(237, 250, 139));
		rmAgeField.setBounds(200, 273, 46, 20);
		rmAgeField.setColumns(10);
		contentPane.add(rmAgeField);

		raPhoneNumberLabel = new JLabel("Phone Number:");
		raPhoneNumberLabel.setForeground(new Color(255, 255, 255));
		raPhoneNumberLabel.setBackground(new Color(255, 255, 255));
		raPhoneNumberLabel.setBounds(69, 339, 97, 14);
		raPhoneNumberLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		contentPane.add(raPhoneNumberLabel);

		rmEmailField = new JTextField();
		rmEmailField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		rmEmailField.setFont(new Font("Dialog", Font.PLAIN, 12));
		rmEmailField.setBackground(new Color(237, 250, 139));
		rmEmailField.setBounds(200, 304, 192, 20);
		rmEmailField.setColumns(10);
		contentPane.add(rmEmailField);

		raPasswordLabel = new JLabel("Password:");
		raPasswordLabel.setForeground(new Color(255, 255, 255));
		raPasswordLabel.setBackground(new Color(255, 255, 255));
		raPasswordLabel.setBounds(69, 82, 107, 14);
		raPasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		contentPane.add(raPasswordLabel);

		rmPasswordField = new JTextField();
		rmPasswordField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		rmPasswordField.setFont(new Font("Dialog", Font.PLAIN, 12));
		rmPasswordField.setBackground(new Color(237, 250, 139));
		rmPasswordField.setBounds(200, 79, 192, 20);
		rmPasswordField.setColumns(10);
		contentPane.add(rmPasswordField);

		JLabel lblBirthday = new JLabel("Birthday (MM/DD/YYYY):");
		lblBirthday.setForeground(new Color(255, 255, 255));
		lblBirthday.setBackground(new Color(255, 255, 255));
		lblBirthday.setBounds(69, 246, 133, 14);
		lblBirthday.setFont(new Font("Dialog", Font.PLAIN, 12));
		contentPane.add(lblBirthday);

		raYearLabel = new JLabel("/");
		raYearLabel.setBounds(290, 246, 16, 14);
		raYearLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		contentPane.add(raYearLabel);

		JTextField rmMonthField = new JTextField();
		rmMonthField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		rmMonthField.setFont(new Font("Dialog", Font.PLAIN, 12));
		rmMonthField.setBackground(new Color(237, 250, 139));
		rmMonthField.setBounds(200, 243, 38, 20);
		contentPane.add(rmMonthField);
		rmMonthField.setColumns(10);

		rmDayField = new JTextField();
		rmDayField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		rmDayField.setFont(new Font("Dialog", Font.PLAIN, 12));
		rmDayField.setBackground(new Color(237, 250, 139));
		rmDayField.setBounds(248, 243, 38, 20);
		rmDayField.setColumns(10);
		contentPane.add(rmDayField);

		rmYearField = new JTextField();
		rmYearField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		rmYearField.setFont(new Font("Dialog", Font.PLAIN, 12));
		rmYearField.setBackground(new Color(237, 250, 139));
		rmYearField.setBounds(297, 243, 38, 20);
		rmYearField.setColumns(10);
		contentPane.add(rmYearField);

		lblAccountDetails = new JLabel("Account Details");
		lblAccountDetails.setForeground(new Color(255, 255, 255));
		lblAccountDetails.setBackground(new Color(255, 255, 255));
		lblAccountDetails.setBounds(49, 24, 153, 14);
		lblAccountDetails.setFont(new Font("Dialog", Font.BOLD, 15));
		contentPane.add(lblAccountDetails);

		separator = new JSeparator();
		separator.setBounds(20, 114, 815, 2);
		contentPane.add(separator);

		lblPersonalInformation = new JLabel("Personal Information");
		lblPersonalInformation.setForeground(new Color(255, 255, 255));
		lblPersonalInformation.setBackground(new Color(255, 255, 255));
		lblPersonalInformation.setBounds(49, 125, 153, 14);
		lblPersonalInformation.setFont(new Font("Dialog", Font.BOLD, 15));
		contentPane.add(lblPersonalInformation);

		lblMerchantInformation = new JLabel("Merchant Information");
		lblMerchantInformation.setForeground(new Color(255, 255, 255));
		lblMerchantInformation.setBackground(new Color(255, 255, 255));
		lblMerchantInformation.setBounds(484, 125, 153, 14);
		lblMerchantInformation.setFont(new Font("Dialog", Font.BOLD, 15));
		contentPane.add(lblMerchantInformation);

		lblMerchantName = new JLabel("Merchant Name:");
		lblMerchantName.setForeground(new Color(255, 255, 255));
		lblMerchantName.setBackground(new Color(255, 255, 255));
		lblMerchantName.setBounds(504, 150, 97, 14);
		lblMerchantName.setFont(new Font("Dialog", Font.PLAIN, 12));
		contentPane.add(lblMerchantName);

		rmMerchantNameField = new JTextField();
		rmMerchantNameField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		rmMerchantNameField.setFont(new Font("Dialog", Font.PLAIN, 12));
		rmMerchantNameField.setBackground(new Color(237, 250, 139));
		rmMerchantNameField.setBounds(635, 147, 192, 20);
		rmMerchantNameField.setColumns(10);
		contentPane.add(rmMerchantNameField);

		lblCategory = new JLabel("Category:");
		lblCategory.setForeground(new Color(255, 255, 255));
		lblCategory.setBackground(new Color(255, 255, 255));
		lblCategory.setBounds(504, 183, 97, 14);
		lblCategory.setFont(new Font("Dialog", Font.PLAIN, 12));
		contentPane.add(lblCategory);

		lblMerchantAddress = new JLabel("Province:");
		lblMerchantAddress.setForeground(new Color(255, 255, 255));
		lblMerchantAddress.setBackground(new Color(255, 255, 255));
		lblMerchantAddress.setBounds(530, 270, 107, 14);
		lblMerchantAddress.setFont(new Font("Dialog", Font.PLAIN, 12));
		contentPane.add(lblMerchantAddress);

		merchantCategoryComboBox = new JComboBox();
		merchantCategoryComboBox.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		merchantCategoryComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		merchantCategoryComboBox.setBackground(new Color(237, 250, 139));
		merchantCategoryComboBox.setBounds(635, 178, 192, 22);

		//For choices

		//For choices
		DefaultComboBoxModel<MerchantCategory> categModel = new DefaultComboBoxModel<>();
		categModel.addElement(null);		

		for (MerchantCategory categ: MerchantCategory.values()) {
			categModel.addElement(categ);
		}

		merchantCategoryComboBox.setModel(categModel);
		merchantCategoryComboBox.setRenderer(new MerchantCategoryRenderer());
		contentPane.add(merchantCategoryComboBox);

		JLabel lblLocation = new JLabel("LOCATION");
		lblLocation.setForeground(new Color(255, 255, 255));
		lblLocation.setBackground(new Color(255, 255, 255));
		lblLocation.setBounds(514, 214, 107, 14);
		lblLocation.setFont(new Font("Dialog", Font.BOLD, 12));
		contentPane.add(lblLocation);

		JLabel lblCity = new JLabel("City:");
		lblCity.setForeground(new Color(255, 255, 255));
		lblCity.setBackground(new Color(255, 255, 255));
		lblCity.setBounds(530, 301, 107, 14);
		lblCity.setFont(new Font("Dialog", Font.PLAIN, 12));
		contentPane.add(lblCity);

		JLabel lblStreet = new JLabel("Street:");
		lblStreet.setForeground(new Color(255, 255, 255));
		lblStreet.setBackground(new Color(255, 255, 255));
		lblStreet.setBounds(530, 363, 107, 14);
		lblStreet.setFont(new Font("Dialog", Font.PLAIN, 12));
		contentPane.add(lblStreet);

		lblRegion = new JLabel("Region:");
		lblRegion.setForeground(new Color(255, 255, 255));
		lblRegion.setBackground(new Color(255, 255, 255));
		lblRegion.setBounds(530, 239, 107, 14);
		lblRegion.setFont(new Font("Dialog", Font.PLAIN, 12));
		contentPane.add(lblRegion);

		lblBarangay = new JLabel("Barangay:");
		lblBarangay.setForeground(new Color(255, 255, 255));
		lblBarangay.setBackground(new Color(255, 255, 255));
		lblBarangay.setBounds(530, 333, 107, 14);
		lblBarangay.setFont(new Font("Dialog", Font.PLAIN, 12));
		contentPane.add(lblBarangay);

		locProvinceComboBox = new JComboBox<String>();
		locProvinceComboBox.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		locProvinceComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		locProvinceComboBox.setBackground(new Color(237, 250, 139));
		locProvinceComboBox.setBounds(635, 268, 192, 22);
		contentPane.add(locProvinceComboBox);

		locCityComboBox = new JComboBox<String>();
		locCityComboBox.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		locCityComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		locCityComboBox.setBackground(new Color(237, 250, 139));
		locCityComboBox.setBounds(635, 299, 192, 22);
		contentPane.add(locCityComboBox);

		locBarangayComboBox = new JComboBox<String>();
		locBarangayComboBox.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		locBarangayComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		locBarangayComboBox.setBackground(new Color(237, 250, 139));
		locBarangayComboBox.setBounds(635, 330, 192, 22);
		contentPane.add(locBarangayComboBox);

		rmStreetLocField = new JTextField();
		rmStreetLocField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		rmStreetLocField.setFont(new Font("Dialog", Font.PLAIN, 12));
		rmStreetLocField.setBackground(new Color(237, 250, 139));
		rmStreetLocField.setBounds(635, 361, 192, 20);
		rmStreetLocField.setColumns(10);
		contentPane.add(rmStreetLocField);
		
		locRegionComboBox = new JComboBox<String>();
		locRegionComboBox.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		locRegionComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		locRegionComboBox.setBackground(new Color(237, 250, 139));
		locRegionComboBox.setBounds(635, 237, 192, 22);

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
		contentPane.add(locRegionComboBox);
		
		raGenderLabel = new JLabel("Gender:");
		raGenderLabel.setForeground(new Color(255, 255, 255));
		raGenderLabel.setBackground(new Color(255, 255, 255));
		raGenderLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		raGenderLabel.setBounds(69, 363, 97, 18);
		contentPane.add(raGenderLabel);
		
		maleRadioButton = new JRadioButton("Male");
		maleRadioButton.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		maleRadioButton.setBackground(new Color(237, 250, 139));
		maleRadioButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		maleRadioButton.setBounds(200, 364, 54, 23);
		contentPane.add(maleRadioButton);
		
		femaleRadioButton = new JRadioButton("Female");
		femaleRadioButton.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		femaleRadioButton.setBackground(new Color(237, 250, 139));
		femaleRadioButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		femaleRadioButton.setBounds(261, 364, 74, 23);
		contentPane.add(femaleRadioButton);
		
		othersRadioButton = new JRadioButton("Others");
		othersRadioButton.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		othersRadioButton.setBackground(new Color(237, 250, 139));
		othersRadioButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		othersRadioButton.setBounds(342, 364, 64, 23);
		contentPane.add(othersRadioButton);
		
		maleRadioButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (maleRadioButton.isSelected()) {
		            // Code to execute when radioButton1 is selected
		            selectedGenderText = maleRadioButton.getText();
		        }
		    }
		});
		femaleRadioButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (femaleRadioButton.isSelected()) {
		            // Code to execute when radioButton1 is selected
		            selectedGenderText = femaleRadioButton.getText();
		        }
		    }
		});
		othersRadioButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (othersRadioButton.isSelected()) {
		            // Code to execute when radioButton1 is selected
		            selectedGenderText = othersRadioButton.getText();
		        }
		    }
		});
		
		
		final ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(maleRadioButton);
		genderGroup.add(femaleRadioButton);
		genderGroup.add(othersRadioButton);


		registerLoanerButton = new JButton("Register Merchant Account");
		registerLoanerButton.setBackground(new Color(173, 226, 138));
		registerLoanerButton.setForeground(new Color(0, 0, 0));
		registerLoanerButton.setBounds(366, 426, 192, 48);
		registerLoanerButton.setFont(new Font("Dialog", Font.BOLD, 12));
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
							selectedGenderText,
							ageText,
							emailText,
							phoneNumberText) || isSpecificFieldsEmpty()) {	

						if (HelperUtility.isInputFieldEmpty(usernameText)) {
							JOptionPane.showMessageDialog(null, "Please enter a username to continue.", "Missing Username!", JOptionPane.ERROR_MESSAGE);
						} else if (HelperUtility.isInputFieldEmpty(passwordText)) {
							JOptionPane.showMessageDialog(null, "Please enter a password to continue.", "Missing Password!", JOptionPane.ERROR_MESSAGE);
						} else if (HelperUtility.isInputFieldEmpty(firstNameText)) {
							JOptionPane.showMessageDialog(null, "Please enter a first name to continue.", "Missing First Name!", JOptionPane.ERROR_MESSAGE);
						} else if (HelperUtility.isInputFieldEmpty(middleNameText)) {
							JOptionPane.showMessageDialog(null, "Please enter a middle name to continue.", "Missing Middle Name!", JOptionPane.ERROR_MESSAGE);
						} else if (HelperUtility.isInputFieldEmpty(lastNameText)) {
							JOptionPane.showMessageDialog(null, "Please enter a last name to continue.", "Missing Last Name!", JOptionPane.ERROR_MESSAGE);
						} else if (HelperUtility.isInputFieldEmpty(monthText)) {
							JOptionPane.showMessageDialog(null, "Please enter a birth month to continue.", "Missing Birth Month!", JOptionPane.ERROR_MESSAGE);
						} else if (HelperUtility.isInputFieldEmpty(dayText)) {
							JOptionPane.showMessageDialog(null, "Please enter a birth day to continue.", "Missing Birth Day!", JOptionPane.ERROR_MESSAGE);
						} else if (HelperUtility.isInputFieldEmpty(yearText)) {
							JOptionPane.showMessageDialog(null, "Please enter a birth year to continue.", "Missing Birth Year!", JOptionPane.ERROR_MESSAGE);
						} else if (HelperUtility.isInputFieldEmpty(selectedGenderText)) {
							JOptionPane.showMessageDialog(null, "Please enter a gender to continue.", "Missing Gender!", JOptionPane.ERROR_MESSAGE);
						} else if (HelperUtility.isInputFieldEmpty(ageText)) {
							JOptionPane.showMessageDialog(null, "Please enter an age to continue.", "Missing Age!", JOptionPane.ERROR_MESSAGE);
						} else if (HelperUtility.isInputFieldEmpty(emailText)) {
							JOptionPane.showMessageDialog(null, "Please enter an email to continue.", "Missing Age!", JOptionPane.ERROR_MESSAGE);
						} else if (HelperUtility.isInputFieldEmpty(phoneNumberText)) {
							JOptionPane.showMessageDialog(null, "Please enter a phone number to continue.", "Missing Phone Number!", JOptionPane.ERROR_MESSAGE);
						}

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
								selectedGenderText,
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
									selectedGenderText,
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
								JOptionPane.showMessageDialog(null, "Please try again later.", "Server Error!", JOptionPane.ERROR_MESSAGE);
							} 
						}else {

							String errorText = merch.checkErrorInputRegistration();

							if (errorText == null) {
								errorText = merch.checkDuplicateInput();
							}

							JOptionPane.showMessageDialog(null, errorText, "Invalid Input!", JOptionPane.ERROR_MESSAGE);
						}

					}
				} catch (NumberFormatException numberEx) {
					JOptionPane.showMessageDialog(null, "Please enter only a number value for age and birthday.", "Invalid Input!", JOptionPane.ERROR_MESSAGE);
				}


			}
		});
		contentPane.add(registerLoanerButton);

		JLabel raYearLabel_1 = new JLabel("/");
		raYearLabel_1.setBounds(242, 246, 16, 14);
		raYearLabel_1.setFont(new Font("Dialog", Font.BOLD, 12));
		contentPane.add(raYearLabel_1);

		JButton btnAlreadyAMerchant = new JButton("Already a merchant? Login");
		btnAlreadyAMerchant.setFont(new Font("Dialog", Font.PLAIN, 11));
		btnAlreadyAMerchant.setBackground(new Color(237, 250, 139));
		btnAlreadyAMerchant.setBounds(366, 474, 192, 23);
		btnAlreadyAMerchant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage login = new LoginPage();
				login.setVisible(true);

				//For Closing Page
				HelperUtility.closePage(thisFrame);
			}
		});
		btnAlreadyAMerchant.setForeground(new Color(237, 250, 139));
		btnAlreadyAMerchant.setContentAreaFilled(false);
		btnAlreadyAMerchant.setBorderPainted(false);
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
