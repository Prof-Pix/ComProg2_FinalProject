package AdminFramePanels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Database.DatabaseManager;
import Products.Product;
import User.Merchant;

import java.awt.FlowLayout;
import java.util.ArrayList;

public class ManageMerchantPanel extends JPanel {

	DatabaseManager dbManager = new DatabaseManager();
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Merchant> allMerchant = new ArrayList<>();

	/**
	 * Create the panel.
	 */
	public ManageMerchantPanel() {
		
		try {
			dbManager.connect();
			allMerchant = dbManager.getAllMerchants();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 33, 1111, 565);
		add(scrollPane);
		
		JPanel merchantsPanel = new JPanel();
		scrollPane.setViewportView(merchantsPanel);
		merchantsPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 15, 15));
		
		for(Merchant merchant: allMerchant) {
			MerchantTemplatePanel merchPanel = new MerchantTemplatePanel(merchant);
			merchantsPanel.add(merchPanel);
		}
		
		merchantsPanel.revalidate();
		merchantsPanel.repaint();

	}
}
