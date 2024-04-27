package UserRoleFrames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import AdminFramePanels.ManageLoanerPanel;
import AdminFramePanels.ManageMerchantPanel;
import AdminFramePanels.ViewAdminProfileDialog;
import ChatBot.HelpCenterChatBotDialog;
import Database.DatabaseManager;
import LoanerFramePanels.RejectedLoansPanel;
import LoanerFramePanels.ViewLoanerProfileDialog;
import User.Admin;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Dimension;

public class AdminFrame extends JFrame {

	DatabaseManager dbManager = new DatabaseManager();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	//For tracking the current panel;
	static JPanel currentPanel = null;
	
	private static int ADMIN_ID;
	private static Admin ADMIN_DATA;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame frame = new AdminFrame(ADMIN_ID);
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
	public AdminFrame(int adminId) {
		AdminFrame.ADMIN_ID = adminId;
		
		try {
			dbManager.connect();
			AdminFrame.ADMIN_DATA = dbManager.getAdminData(ADMIN_ID);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		setTitle(ADMIN_DATA.getFullName());
		
		

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1180, 650);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Profile");
		menuBar.add(mnNewMenu);
		
		JMenuItem viewProfileMenuItem = new JMenuItem("View Profile");
		viewProfileMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ViewAdminProfileDialog adminProfileDialog = new ViewAdminProfileDialog(ADMIN_ID);
				adminProfileDialog.setVisible(true);
				
			}
			
		} );
		mnNewMenu.add(viewProfileMenuItem);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Logout");
		mnNewMenu.add(mntmNewMenuItem_4);
		
		JMenuItem manageMerchantsMenuItem = new JMenuItem("Manage Merchants");
		manageMerchantsMenuItem.addActionListener(e -> showManageMerchant());
		manageMerchantsMenuItem.setMaximumSize(new Dimension(160, 32767));
		menuBar.add(manageMerchantsMenuItem);
		
		JMenuItem manageLoanersMenuItem = new JMenuItem("Manage Loaner");
		manageLoanersMenuItem.addActionListener(e -> showManageLoaner());
		manageLoanersMenuItem.setMaximumSize(new Dimension(160, 32767));
		manageLoanersMenuItem.setPreferredSize(new Dimension(20, 26));
		manageLoanersMenuItem.setSize(new Dimension(20, 0));
		menuBar.add(manageLoanersMenuItem);
		
		JMenu mnNewMenu_2 = new JMenu("Settings");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem faqsMenuItem = new JMenuItem("FAQS");
		faqsMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				HelpCenterChatBotDialog chatBot = new HelpCenterChatBotDialog("admin");
				chatBot.setVisible(true);
			}
			
		});
		mnNewMenu_2.add(faqsMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		showManageMerchant();
	}
	
	private void showManageMerchant() {
		if (currentPanel != null) {
			contentPane.remove(currentPanel);		
		}
		ManageMerchantPanel manageMerchantPanel = new ManageMerchantPanel();
		currentPanel = manageMerchantPanel;
		manageMerchantPanel.setBounds(0,0,1200,700);
		contentPane.add(manageMerchantPanel);
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	private void showManageLoaner() {
		if (currentPanel != null) {
			contentPane.remove(currentPanel);		
		}
		ManageLoanerPanel manageLoanerPanel = new ManageLoanerPanel();
		currentPanel = manageLoanerPanel;
		manageLoanerPanel.setBounds(0,0,1200,700);
		contentPane.add(manageLoanerPanel);
		contentPane.revalidate();
		contentPane.repaint();
	}
}
