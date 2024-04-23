package UserRoleFrames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import LoanerFramePanels.MarketplacePanel;
import MerchantFramePanels.AddProductPanel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class LoanerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	//For tracking the current panel;
	static JPanel currentPanel = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoanerFrame frame = new LoanerFrame();
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
	public LoanerFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1202, 740);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Profile");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Edit Profile");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu marketplaceMenu = new JMenu("Marketplace");
		menuBar.add(marketplaceMenu);
		
		JMenuItem shopProductsMenuItem = new JMenuItem("Shop Products");
		shopProductsMenuItem.addActionListener(e -> showMarketPlacePanel());
		marketplaceMenu.add(shopProductsMenuItem);
		
		JMenu mnNewMenu_2 = new JMenu("Manage Loans");
		menuBar.add(mnNewMenu_2);
		
		JMenu mnNewMenu_3 = new JMenu("Settings");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("FAQS");
		mnNewMenu_3.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("About HomeCredit");
		mnNewMenu_3.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	//For changing from one menu to another
		private void showMarketPlacePanel() {
			
			if (currentPanel != null) {
				contentPane.remove(currentPanel);		
			}
			MarketplacePanel marketplacePanel = new MarketplacePanel();
			currentPanel = marketplacePanel;
			marketplacePanel.setBounds(0,0,1200,700);
			contentPane.add(marketplacePanel);
			contentPane.revalidate();
			contentPane.repaint();
		}
}
