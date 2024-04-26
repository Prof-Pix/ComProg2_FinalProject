package LoanerFramePanels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import Database.DatabaseManager;
import User.Loaner;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class ViewProfilePanel extends JPanel {
	
	DatabaseManager dbManager = new DatabaseManager();

	private static final long serialVersionUID = 1L;

	private Loaner loanerData;
	/**
	 * Create the panel.
	 */
	public ViewProfilePanel(int loanerId) {
		
		try {
			dbManager.connect();
			loanerData = dbManager.getLoanerData(loanerId);
		} catch (Exception e) {
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
		
		JLabel lblNewLabel_1_1 = new JLabel(loanerData.getUsername());
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(140, 252, 73, 14);
		add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Name:");
		lblNewLabel_1_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(32, 277, 73, 14);
		add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel(loanerData.getFullName());
		lblNewLabel_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_1_1.setBounds(140, 277, 124, 14);
		add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Gender:");
		lblNewLabel_1_2_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_2_1.setBounds(32, 308, 73, 14);
		add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel(loanerData.getGender());
		lblNewLabel_1_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1.setBounds(140, 308, 124, 14);
		add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Birthday:");
		lblNewLabel_1_2_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_2_1_1.setBounds(32, 336, 73, 14);
		add(lblNewLabel_1_2_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel(loanerData.getBirthdate().toString());
		lblNewLabel_1_1_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1_1.setBounds(140, 336, 124, 14);
		add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("Age:");
		lblNewLabel_1_2_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_2_1_1_1.setBounds(32, 361, 73, 14);
		add(lblNewLabel_1_2_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel(String.valueOf(loanerData.getAge()));
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1_1_1.setBounds(140, 361, 124, 14);
		add(lblNewLabel_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1_1 = new JLabel("Email:");
		lblNewLabel_1_2_1_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_2_1_1_1_1.setBounds(32, 392, 73, 14);
		add(lblNewLabel_1_2_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel(loanerData.getEmail());
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1_1_1_1.setBounds(140, 392, 150, 14);
		add(lblNewLabel_1_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1_1_1 = new JLabel("Phone Number:");
		lblNewLabel_1_2_1_1_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_2_1_1_1_1_1.setBounds(32, 423, 98, 14);
		add(lblNewLabel_1_2_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1_1 = new JLabel(loanerData.getPhoneNumber());
		lblNewLabel_1_1_1_1_1_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1_1_1_1_1.setBounds(140, 424, 150, 14);
		add(lblNewLabel_1_1_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1_1_1_1 = new JLabel("Loaner since: ");
		lblNewLabel_1_2_1_1_1_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_2_1_1_1_1_1_1.setBounds(32, 192, 98, 14);
		add(lblNewLabel_1_2_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel("2024-03-01");
		lblNewLabel_1_1_1_1_1_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1_1_2.setBounds(140, 193, 124, 14);
		add(lblNewLabel_1_1_1_1_1_2);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(374, 31, 2, 588);
		add(separator);
		
		JLabel lblNewLabel_1_3 = new JLabel("Source of Income:");
		lblNewLabel_1_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_3.setBounds(414, 166, 113, 14);
		add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel(loanerData.getSourceOfIncome());
		lblNewLabel_1_3_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_3_1.setBounds(541, 167, 113, 14);
		add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Occupation:");
		lblNewLabel_1_3_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_3_2.setBounds(414, 193, 113, 14);
		add(lblNewLabel_1_3_2);
		
		JLabel lblNewLabel_1_3_2_1 = new JLabel(loanerData.getOccupation());
		lblNewLabel_1_3_2_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_3_2_1.setBounds(541, 192, 113, 14);
		add(lblNewLabel_1_3_2_1);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("Monthy Income:");
		lblNewLabel_1_3_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_3_1_1.setBounds(414, 219, 113, 14);
		add(lblNewLabel_1_3_1_1);
		
		JLabel lblNewLabel_1_3_1_1_1 = new JLabel(loanerData.getMonthlyIncome());
		lblNewLabel_1_3_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_3_1_1_1.setBounds(541, 219, 113, 14);
		add(lblNewLabel_1_3_1_1_1);

	}
}
