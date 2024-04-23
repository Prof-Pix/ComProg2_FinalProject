package UserRoleFrames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import AuthFrames.LoginPage;
import LoanerFramePanels.ApprovedLoansPanel;
import LoanerFramePanels.MarketplacePanel;
import LoanerFramePanels.PendingLoansPanel;
import LoanerFramePanels.RejectedLoansPanel;
import LoanerFramePanels.SeeDetailsProductPanel;
import MerchantFramePanels.AddProductPanel;
import Utilities.HelperUtility;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class LoanerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	//For tracking the current panel;
	static JPanel currentPanel = null;
	
	private static int LOANER_ID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoanerFrame frame = new LoanerFrame(LOANER_ID);
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
	public LoanerFrame(int loanerId) {
		LoanerFrame.LOANER_ID = loanerId;
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1202, 740);
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Profile");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Edit Profile");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem logoutMenuItem = new JMenuItem("Log out");
		logoutMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
				
				if(choice == JOptionPane.YES_OPTION) {
					logout();
				}
				
			}
			
		});
		mnNewMenu.add(logoutMenuItem);
		
		JMenu marketplaceMenu = new JMenu("Marketplace");
		menuBar.add(marketplaceMenu);
		
		JMenuItem shopProductsMenuItem = new JMenuItem("Shop Products");
		shopProductsMenuItem.addActionListener(e -> showMarketPlacePanel());
		marketplaceMenu.add(shopProductsMenuItem);
		
		JMenu mnNewMenu_2 = new JMenu("Manage Loans");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem pendingLoanReqMenuItem = new JMenuItem("Pending Loan Requests");
		pendingLoanReqMenuItem.addActionListener(e -> showPendingLoanPanel());
		mnNewMenu_2.add(pendingLoanReqMenuItem);
		
		JMenuItem approveLoanReqMenuItem = new JMenuItem("Approved Loan Requests");
		approveLoanReqMenuItem.addActionListener(e -> showApprovedLoanPanel());
		mnNewMenu_2.add(approveLoanReqMenuItem);
		
		JMenuItem rejectedLoanReqMenuItem = new JMenuItem("Rejected Loan Requests");
		rejectedLoanReqMenuItem.addActionListener(e -> showRejectedLoanPanel());
		mnNewMenu_2.add(rejectedLoanReqMenuItem);
		
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
		
		JMenuItem menuItem = new JMenuItem("New menu item");
		menuItem.setBounds(-34, 0, 61, -11);
		contentPane.add(menuItem);
	}
	
	//For changing from one menu to another
		private void showMarketPlacePanel() {
			
			if (currentPanel != null) {
				contentPane.remove(currentPanel);		
			}
			MarketplacePanel marketplacePanel = new MarketplacePanel(LOANER_ID);
			currentPanel = marketplacePanel;
			marketplacePanel.setBounds(0,0,1200,700);
			contentPane.add(marketplacePanel);
			contentPane.revalidate();
			contentPane.repaint();
		}
		
		private void showPendingLoanPanel() {
			if (currentPanel != null) {
				contentPane.remove(currentPanel);		
			}
			PendingLoansPanel pendingLoanPanel = new PendingLoansPanel(LOANER_ID);
			currentPanel = pendingLoanPanel;
			pendingLoanPanel.setBounds(0,0,1200,700);
			contentPane.add(pendingLoanPanel);
			contentPane.revalidate();
			contentPane.repaint();
		}
		
		private void showApprovedLoanPanel() {
			if (currentPanel != null) {
				contentPane.remove(currentPanel);		
			}
			ApprovedLoansPanel approvedLoanPanel = new ApprovedLoansPanel(LOANER_ID);
			currentPanel = approvedLoanPanel;
			approvedLoanPanel.setBounds(0,0,1200,700);
			contentPane.add(approvedLoanPanel);
			contentPane.revalidate();
			contentPane.repaint();
		}
		
		private void showRejectedLoanPanel() {
			if (currentPanel != null) {
				contentPane.remove(currentPanel);		
			}
			RejectedLoansPanel rejectedLoanPanel = new RejectedLoansPanel(LOANER_ID);
			currentPanel = rejectedLoanPanel;
			rejectedLoanPanel.setBounds(0,0,1200,700);
			contentPane.add(rejectedLoanPanel);
			contentPane.revalidate();
			contentPane.repaint();
		}
	//Logout
		private void logout() {
			HelperUtility.closePage(LoanerFrame.this);
			LoginPage login = new LoginPage();
			login.setVisible(true);
		}
}
