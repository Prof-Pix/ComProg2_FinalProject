package UserRoleFrames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import AdminFramePanels.ManageMerchantPanel;
import AdminFramePanels.ViewProfilePanel;
import LoanerFramePanels.RejectedLoansPanel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class AdminFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	//For tracking the current panel;
	static JPanel currentPanel = null;
	
	private static int ADMIN_ID;

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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1180, 643);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Profile");
		menuBar.add(mnNewMenu);
		
		JMenuItem viewProfileMenuItem = new JMenuItem("View Profile");
		viewProfileMenuItem.addActionListener(e -> showViewProfilePanel());
		mnNewMenu.add(viewProfileMenuItem);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Edit Profile");
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Logout");
		mnNewMenu.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_1 = new JMenu("Manage Users");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem manageMerchantMenuItem = new JMenuItem("Manage Merchants");
		manageMerchantMenuItem.addActionListener(e -> showManageMerchant());
		mnNewMenu_1.add(manageMerchantMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Manage Loaners");
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_2 = new JMenu("Manage FAQS");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Edit FAQS");
		mnNewMenu_2.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	
	private void showViewProfilePanel() {
		if (currentPanel != null) {
			contentPane.remove(currentPanel);		
		}
		ViewProfilePanel viewProfilePanel = new ViewProfilePanel(ADMIN_ID);
		currentPanel = viewProfilePanel;
		viewProfilePanel.setBounds(0,0,1200,700);
		contentPane.add(viewProfilePanel);
		contentPane.revalidate();
		contentPane.repaint();
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
}
