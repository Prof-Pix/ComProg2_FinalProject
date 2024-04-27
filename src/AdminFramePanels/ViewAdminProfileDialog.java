package AdminFramePanels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import Database.DatabaseManager;
import User.Admin;
import User.Loaner;
import User.Merchant;
import Utilities.HelperUtility;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Dialog.ModalityType;

public class ViewAdminProfileDialog extends JDialog {

	DatabaseManager dbManager = new DatabaseManager();
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblMerchantOwnerInformation;

	private static Admin admin;
	private static int ADMIN_ID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewAdminProfileDialog dialog = new ViewAdminProfileDialog(ADMIN_ID);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewAdminProfileDialog(int loanerId) {
		setResizable(false);
		
		ViewAdminProfileDialog.ADMIN_ID = loanerId;
		try {
			dbManager.connect();
			admin = dbManager.getAdminData(ADMIN_ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Merchant Profile");
		setBounds(100, 100, 600, 293);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(37, 102, 112));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel merchantIcon = new JLabel("");
		merchantIcon.setBorder(new LineBorder(new Color(237, 250, 139)));
		merchantIcon.setBounds(33, 21, 150, 150);
		merchantIcon.setIcon(HelperUtility.resizeImage(HelperUtility.defaultAdminPicturePath, merchantIcon.getWidth(), merchantIcon.getHeight()));

		
		contentPanel.add(merchantIcon);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1.setBounds(224, 46, 73, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Name:");
		lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1_2.setBounds(224, 71, 73, 14);
		contentPanel.add(lblNewLabel_1_2);
		
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Gender:");
		lblNewLabel_1_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1_2_1.setBounds(224, 96, 73, 14);
		contentPanel.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Birthday:");
		lblNewLabel_1_2_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1_2_1_1.setBounds(224, 121, 73, 14);
		contentPanel.add(lblNewLabel_1_2_1_1);
		

		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("Age:");
		lblNewLabel_1_2_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2_1_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1_2_1_1_1.setBounds(224, 146, 73, 14);
		contentPanel.add(lblNewLabel_1_2_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1_1 = new JLabel("Email:");
		lblNewLabel_1_2_1_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1_2_1_1_1_1.setBounds(224, 171, 73, 14);
		contentPanel.add(lblNewLabel_1_2_1_1_1_1);
		
		
		JLabel lblNewLabel_1_2_1_1_1_1_1 = new JLabel("Phone Number:");
		lblNewLabel_1_2_1_1_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2_1_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1_2_1_1_1_1_1.setBounds(224, 196, 98, 14);
		contentPanel.add(lblNewLabel_1_2_1_1_1_1_1);
		
		lblMerchantOwnerInformation = new JLabel("Admin Details:");
		lblMerchantOwnerInformation.setForeground(Color.WHITE);
		lblMerchantOwnerInformation.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMerchantOwnerInformation.setBounds(213, 21, 181, 14);
		contentPanel.add(lblMerchantOwnerInformation);
		
		JLabel username = new JLabel(admin.getUsername());
		username.setForeground(new Color(255, 255, 255));
		username.setFont(new Font("Dialog", Font.PLAIN, 12));
		username.setBounds(340, 47, 234, 14);
		contentPanel.add(username);
		
		JLabel fullname = new JLabel(admin.getFullName());
		fullname.setForeground(new Color(255, 255, 255));
		fullname.setFont(new Font("Dialog", Font.PLAIN, 12));
		fullname.setBounds(340, 73, 234, 14);
		contentPanel.add(fullname);
		
		JLabel gender = new JLabel(admin.getGender());
		gender.setForeground(new Color(255, 255, 255));
		gender.setFont(new Font("Dialog", Font.PLAIN, 12));
		gender.setBounds(340, 98, 234, 14);
		contentPanel.add(gender);
		
		JLabel birthdate = new JLabel(admin.getBirthdate().toString());
		birthdate.setForeground(new Color(255, 255, 255));
		birthdate.setFont(new Font("Dialog", Font.PLAIN, 12));
		birthdate.setBounds(340, 122, 234, 14);
		contentPanel.add(birthdate);
		
		JLabel age = new JLabel(String.valueOf(admin.getAge()));
		age.setForeground(new Color(255, 255, 255));
		age.setFont(new Font("Dialog", Font.PLAIN, 12));
		age.setBounds(340, 148, 234, 14);
		contentPanel.add(age);
		
		JLabel email = new JLabel(admin.getEmail());
		email.setForeground(new Color(255, 255, 255));
		email.setFont(new Font("Dialog", Font.PLAIN, 12));
		email.setBounds(340, 173, 234, 14);
		contentPanel.add(email);
		
		JLabel phonenumber = new JLabel(admin.getPhoneNumber());
		phonenumber.setForeground(new Color(255, 255, 255));
		phonenumber.setFont(new Font("Dialog", Font.PLAIN, 12));
		phonenumber.setBounds(340, 198, 234, 14);
		contentPanel.add(phonenumber);
	}
}
