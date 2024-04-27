package AdminFramePanels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Database.DatabaseManager;
import Products.Product;
import User.Merchant;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Dimension;

public class ManageMerchantPanel extends JPanel {

	DatabaseManager dbManager = new DatabaseManager();
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Merchant> allMerchant = new ArrayList<>();

	/**
	 * Create the panel.
	 */
	public ManageMerchantPanel() {
		setBackground(new Color(30, 66, 94));
		
		try {
			dbManager.connect();
			allMerchant = dbManager.getAllMerchants();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 33, 1111, 539);
		add(scrollPane);
		
		JPanel merchantsPanel = new JPanel();
		merchantsPanel.setBackground(new Color(30, 66, 94));
		scrollPane.setViewportView(merchantsPanel);
		merchantsPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 15, 15));
		
		JLabel lblNewLabel_5 = new JLabel("LIST OF " + allMerchant.size() +  " MERCHANT/s");
		lblNewLabel_5.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_5.setBounds(37, 8, 256, 14);
		add(lblNewLabel_5);
		
		for(Merchant merchant: allMerchant) {
			MerchantTemplatePanel merchPanel = new MerchantTemplatePanel(merchant);
			merchantsPanel.add(merchPanel);
		}
		
		merchantsPanel.revalidate();
		merchantsPanel.repaint();

	}
}
