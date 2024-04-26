package AdminFramePanels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database.DatabaseManager;
import Products.Product;
import User.Merchant;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;

public class SeeMerchantProductDetails extends JDialog {
	
	DatabaseManager dbManager = new DatabaseManager();

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	private ArrayList<Product> merchantProducts = new ArrayList<>();
	private static int MERCHANT_ID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SeeMerchantProductDetails dialog = new SeeMerchantProductDetails(MERCHANT_ID);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SeeMerchantProductDetails(int merchantId) {
		setModalityType(ModalityType.APPLICATION_MODAL);
		SeeMerchantProductDetails.MERCHANT_ID = merchantId;
		
		try {
			dbManager.connect();
			merchantProducts = dbManager.getMerchantProductData(merchantId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		setBounds(100, 100, 1184, 641);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 31, 1075, 535);
		contentPanel.add(scrollPane);
		
		JPanel merchantProductsPanel = new JPanel();
		scrollPane.setViewportView(merchantProductsPanel);
		merchantProductsPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 15, 15));
		
		for(Product product: merchantProducts) {
			MerchantProductTemplate merchProdPanel = new MerchantProductTemplate(MERCHANT_ID, product);
			merchantProductsPanel.add(merchProdPanel);
		}
		
		merchantProductsPanel.revalidate();
		merchantProductsPanel.repaint();
		
		
	}
}
