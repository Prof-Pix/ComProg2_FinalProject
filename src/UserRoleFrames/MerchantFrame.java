package UserRoleFrames;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import AuthFrames.LoginPage;
import ChatBot.HelpCenterChatBotDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database.DatabaseManager;
import JComboBoxRenderers.MerchantCategoryRenderer;
import MerchantFramePanels.AddProductPanel;
import MerchantFramePanels.EditProductPanel;
import MerchantFramePanels.OngoingLoansPanel;
import MerchantFramePanels.PendingLoanRequestsPanel;
import MerchantFramePanels.PendingLoansPanel;
import MerchantFramePanels.ViewMerchantProfileDialog;
import MerchantFramePanels.ViewProductsPanel;
import Products.Product;
import Products.ProductLoanTerm;
import Products.ProductRegistrationData;
import User.Merchant;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.awt.Label;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import UserEnums.MerchantCategory;
import Utilities.HelperUtility;

public class MerchantFrame extends JFrame {

	private DatabaseManager dbManager = new DatabaseManager();

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;

	private static int MERCHANT_ID;
	private static Merchant MERCHANT_DATA;

	//Manage Products Panel
	JPanel addProductPanel;

	//For tracking the current panel;
	static JPanel currentPanel = null;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MerchantFrame frame = new MerchantFrame(MERCHANT_ID);
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
	public MerchantFrame(int merchantId) {

		MerchantFrame.MERCHANT_ID = merchantId;

		try {
			dbManager.connect();
			MerchantFrame.MERCHANT_DATA = dbManager.getMerchantData(MERCHANT_ID);

		} catch(Exception e) {
			e.printStackTrace();
		}
		setTitle(MERCHANT_DATA.getFullName());

		setResizable(false);

		MerchantFrame.MERCHANT_ID = merchantId;
		dbManager.connect();

		setTitle("Merchant Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu_1 = new JMenu("Profile");
		menuBar.add(mnNewMenu_1);

		JMenuItem viewProfileMenuItem = new JMenuItem("View Profile");
		viewProfileMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				ViewMerchantProfileDialog merchProfileDialog = new ViewMerchantProfileDialog(MERCHANT_ID);
				merchProfileDialog.setVisible(true);

			}

		});
		mnNewMenu_1.add(viewProfileMenuItem);

		JMenuItem logoutMenuItem = new JMenuItem("Logout");
		logoutMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);

				if(choice == JOptionPane.YES_OPTION) {
					logout();
				}

			}

		});
		mnNewMenu_1.add(logoutMenuItem);

		JMenu manageProductMenu = new JMenu("Manage Products");
		menuBar.add(manageProductMenu);

		JMenuItem addProductMenuItem = new JMenuItem("Add Product");
		manageProductMenu.add(addProductMenuItem);
		addProductMenuItem.addActionListener(e -> showAddProductPanel());

		JMenuItem viewProductsMenuItem = new JMenuItem("View Products");
		viewProductsMenuItem.addActionListener(e -> showViewProductsPanel());
		manageProductMenu.add(viewProductsMenuItem);

		JMenu mnNewMenu_3 = new JMenu("Manage Loans");
		menuBar.add(mnNewMenu_3);

		JMenuItem loanRequestMenuItem = new JMenuItem("Loan Requests");
		loanRequestMenuItem.addActionListener(e -> showPendingLoanRequestPanel());
		mnNewMenu_3.add(loanRequestMenuItem);

		JMenuItem ongoingLoansMenuItem = new JMenuItem("Ongoing Loans");
		ongoingLoansMenuItem.addActionListener(e -> showOngoingLoansPanel());

		JMenuItem pendingLoansMenuItem = new JMenuItem("Pending Loans");
		pendingLoansMenuItem.addActionListener(e -> showPendingLoanPanel());
		mnNewMenu_3.add(pendingLoansMenuItem);
		mnNewMenu_3.add(ongoingLoansMenuItem);

		JMenu mnNewMenu_4 = new JMenu("Settings");
		menuBar.add(mnNewMenu_4);

		JMenuItem faqsMenuItem = new JMenuItem("FAQS");
		faqsMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				HelpCenterChatBotDialog chatBot = new HelpCenterChatBotDialog("merchant");
				chatBot.setVisible(true);
			}

		});
		mnNewMenu_4.add(faqsMenuItem);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		showViewProductsPanel();

	}
	//For changing from one menu to another
	public void showAddProductPanel() {

		if (currentPanel != null) {
			contentPane.remove(currentPanel);		
		}
		AddProductPanel addProductPanel = new AddProductPanel(MERCHANT_ID);
		currentPanel = addProductPanel;
		addProductPanel.setBounds(0,0,1200,700);
		contentPane.add(addProductPanel);
		contentPane.revalidate();
		contentPane.repaint();
	}

	public static void showViewProductsPanel() {

		if (currentPanel != null) {
			contentPane.remove(currentPanel);		
		}

		ViewProductsPanel viewProductsPanel = new ViewProductsPanel(MERCHANT_ID);
		currentPanel = viewProductsPanel;
		viewProductsPanel.setBounds(0,0,1200,700);
		contentPane.add(viewProductsPanel);
		contentPane.revalidate();
		contentPane.repaint();
	}

	public void showOngoingLoansPanel() {
		if(currentPanel != null) {
			contentPane.remove(currentPanel);
		}

		OngoingLoansPanel ongoingLoansPanel = new OngoingLoansPanel(MERCHANT_ID);
		currentPanel = ongoingLoansPanel;
		ongoingLoansPanel.setBounds(0,0,1200,700);
		contentPane.add(ongoingLoansPanel);
		contentPane.revalidate();
		contentPane.repaint();
	}

	public void showPendingLoanPanel() {
		if(currentPanel != null) {
			contentPane.remove(currentPanel);
		}

		PendingLoansPanel pendingLoansPanel = new PendingLoansPanel(MERCHANT_ID);
		currentPanel = pendingLoansPanel;
		pendingLoansPanel.setBounds(0,0,1200,700);
		contentPane.add(pendingLoansPanel);
		contentPane.revalidate();
		contentPane.repaint();
	}

	public static void showPendingLoanRequestPanel() {
		if(currentPanel != null) {
			contentPane.remove(currentPanel);
		}

		PendingLoanRequestsPanel pendingLoanRequestPanel = new PendingLoanRequestsPanel(MERCHANT_ID);
		currentPanel = pendingLoanRequestPanel;
		pendingLoanRequestPanel.setBounds(0,0,1200,700);
		contentPane.add(pendingLoanRequestPanel);
		contentPane.revalidate();
		contentPane.repaint();
	}

	//Logout
	private void logout() {
		HelperUtility.closePage(MerchantFrame.this);
		LoginPage login = new LoginPage();
		login.setVisible(true);
	}
}
