package MerchantFramePanels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import Database.DatabaseManager;
import User.Merchant;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class ViewProfilePanel extends JPanel {
	
	DatabaseManager dbManager = new DatabaseManager();

	private static final long serialVersionUID = 1L;

	private static Merchant merchantData;
	/**
	 * Create the panel.
	 */
	public ViewProfilePanel(int merchantId) {
		
		try {
			dbManager.connect();
			merchantData = dbManager.getMerchantData(merchantId);
		} catch (Exception e ){
			e.printStackTrace();
		}
		
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Profile Picture");
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel.setBounds(32, 31, 150, 150);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(32, 252, 73, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel(merchantData.getUsername());
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(140, 252, 73, 14);
		add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Name:");
		lblNewLabel_1_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(32, 277, 73, 14);
		add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel(merchantData.getFullName());
		lblNewLabel_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_1_1.setBounds(140, 277, 124, 14);
		add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Gender:");
		lblNewLabel_1_2_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_2_1.setBounds(32, 308, 73, 14);
		add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel(merchantData.getGender());
		lblNewLabel_1_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1.setBounds(140, 308, 124, 14);
		add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Birthday:");
		lblNewLabel_1_2_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_2_1_1.setBounds(32, 336, 73, 14);
		add(lblNewLabel_1_2_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel(merchantData.getBirthdate().toString());
		lblNewLabel_1_1_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1_1.setBounds(140, 336, 124, 14);
		add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("Age:");
		lblNewLabel_1_2_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_2_1_1_1.setBounds(32, 361, 73, 14);
		add(lblNewLabel_1_2_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel(String.valueOf(merchantData.getAge()));
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1_1_1.setBounds(140, 361, 124, 14);
		add(lblNewLabel_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1_1 = new JLabel("Email:");
		lblNewLabel_1_2_1_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_2_1_1_1_1.setBounds(32, 392, 73, 14);
		add(lblNewLabel_1_2_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel(merchantData.getEmail());
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1_1_1_1.setBounds(140, 392, 150, 14);
		add(lblNewLabel_1_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1_1_1 = new JLabel("Phone Number:");
		lblNewLabel_1_2_1_1_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_2_1_1_1_1_1.setBounds(32, 423, 98, 14);
		add(lblNewLabel_1_2_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1_1 = new JLabel(merchantData.getPhoneNumber());
		lblNewLabel_1_1_1_1_1_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1_1_1_1_1.setBounds(140, 424, 150, 14);
		add(lblNewLabel_1_1_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1_1_1_1 = new JLabel("Admin since: ");
		lblNewLabel_1_2_1_1_1_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_2_1_1_1_1_1_1.setBounds(32, 192, 98, 14);
		add(lblNewLabel_1_2_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel("2024-03-01");
		lblNewLabel_1_1_1_1_1_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1_1_2.setBounds(140, 193, 124, 14);
		add(lblNewLabel_1_1_1_1_1_2);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(344, 11, 2, 588);
		add(separator);
		
		JLabel lblNewLabel_1_3 = new JLabel("Merchant Name:");
		lblNewLabel_1_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_3.setBounds(356, 99, 104, 14);
		add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel(merchantData.getMerchantName());
		lblNewLabel_1_3_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_3_1.setBounds(484, 99, 104, 14);
		add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Category:");
		lblNewLabel_1_3_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_3_2.setBounds(356, 124, 104, 14);
		add(lblNewLabel_1_3_2);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel(merchantData.getMerchantCategory());
		lblNewLabel_1_3_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_3_1_1.setBounds(484, 125, 104, 14);
		add(lblNewLabel_1_3_1_1);
		
		JLabel lblNewLabel_1_3_2_1 = new JLabel("Location:");
		lblNewLabel_1_3_2_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_3_2_1.setBounds(356, 149, 104, 14);
		add(lblNewLabel_1_3_2_1);
		
		JLabel lblNewLabel_1_3_1_1_1 = new JLabel(merchantData.getMerchantAddress());
		lblNewLabel_1_3_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_3_1_1_1.setBounds(484, 150, 104, 14);
		add(lblNewLabel_1_3_1_1_1);

	}
}
