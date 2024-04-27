package MerchantFramePanels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Component;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import Database.DatabaseManager;
import Loan.Loan;
import Loan.LoanRequest;
import Loan.LoanSchedule;
import Products.Product;
import User.Loaner;
import Utilities.HelperUtility;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.ScrollPaneConstants;

public class OngoingLoansPanel extends JPanel {

	DatabaseManager dbManager = new DatabaseManager();

	private static final long serialVersionUID = 1L;
	private static int MERCHANT_ID;

	private ArrayList<Loan> merchantOngoingLoans = new ArrayList<>();


	/**
	 * Create the panel.
	 */
	public OngoingLoansPanel(int merchantId) {
		setBackground(new Color(37, 102, 112));
		setLayout(null);
		MERCHANT_ID = merchantId;

		refreshPendingLoanRequestPanel();


		JLabel lblNewLabel_5 = new JLabel("Product Name");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5.setBounds(66, 20, 93, 14);
		add(lblNewLabel_5);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.setBounds(35, 45, 1105, 553);
		add(scrollPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(37, 102, 112));
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_5_1_2 = new JLabel("Months Paid");
		lblNewLabel_5_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_5_1_2.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_2.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_2.setBounds(196, 20, 93, 14);
		add(lblNewLabel_5_1_2);
		
		JLabel lblNewLabel_5_1_1_3 = new JLabel("Months Left");
		lblNewLabel_5_1_1_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_5_1_1_3.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1_1_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1_3.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_1_3.setBounds(339, 20, 93, 14);
		add(lblNewLabel_5_1_1_3);
		
		JLabel lblNewLabel_5_1_1_1_1 = new JLabel("Paid Balance");
		lblNewLabel_5_1_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_5_1_1_1_1.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_1_1_1.setBounds(497, 20, 93, 14);
		add(lblNewLabel_5_1_1_1_1);
		
		JLabel lblNewLabel_5_1_1_2_2 = new JLabel("Remaining Balance");
		lblNewLabel_5_1_1_2_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_5_1_1_2_2.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1_1_2_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1_2_2.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_1_2_2.setBounds(675, 20, 111, 14);
		add(lblNewLabel_5_1_1_2_2);
		
		JLabel lblNewLabel_5_1_1_2_2_1 = new JLabel("Next Due Date");
		lblNewLabel_5_1_1_2_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_5_1_1_2_2_1.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1_1_2_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1_2_2_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_1_2_2_1.setBounds(878, 20, 93, 14);
		add(lblNewLabel_5_1_1_2_2_1);
		
		JLabel lblNewLabel_5_1_1_2_2_1_1 = new JLabel("Status");
		lblNewLabel_5_1_1_2_2_1_1.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1_1_2_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1_2_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_5_1_1_2_2_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_1_2_2_1_1.setBounds(1055, 20, 93, 14);
		add(lblNewLabel_5_1_1_2_2_1_1);
		
//		JPanel loanRequestPanel = new JPanel();
//		panel.add(loanRequestPanel);
//		loanRequestPanel.setMaximumSize(new Dimension(2210, 150));
//		loanRequestPanel.setPreferredSize(new Dimension(1085, 150));
//		loanRequestPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
//		loanRequestPanel.setLayout(new BoxLayout(loanRequestPanel, BoxLayout.X_AXIS));
//
//		loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
//
//		JPanel panel_1 = new JPanel();
//		panel_1.setBorder(null);
//
////		if(i % 2 == 1) {
////			loanRequestPanel.setBackground(new Color(237, 250, 139));
////			panel_1.setBackground(new Color(237, 250, 139));
////		} else {
////			loanRequestPanel.setBackground(new Color(205, 248,141));
////			panel_1.setBackground(new Color(205, 248,141));
////		}
//
//		panel_1.setMaximumSize(new Dimension(175, 32767));
//		loanRequestPanel.add(panel_1);
//		panel_1.setLayout(null);
//
//		
//		JLabel productImage = new JLabel();
//		productImage.setBorder(new LineBorder(new Color(0, 0, 0)));
//		productImage.setBounds(10, 8, 109, 109);
////		productImage.setIcon(HelperUtility.resizeImage(loanReq.getProductToLoanData().getProductImage(), productImage.getWidth(), productImage.getHeight()));
//		panel_1.add(productImage);
//
//		JLabel lblNewLabel_1_1 = new JLabel("sadd");
//		lblNewLabel_1_1.setMaximumSize(new Dimension(125, 14));
//		lblNewLabel_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
//		lblNewLabel_1_1.setBounds(10, 123, 125, 14);
//		panel_1.add(lblNewLabel_1_1);
//
//
//		loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
//		JLabel lblNewLabel_1 = new JLabel("sadd");
//		lblNewLabel_1.setMaximumSize(new Dimension(100, 14));
//		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 12));
//		loanRequestPanel.add(lblNewLabel_1);
//		loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
//		JLabel lblNewLabel_3 = new JLabel("sadd" );
//		lblNewLabel_3.setMaximumSize(new Dimension(100, 14));
//		lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 12));
//		loanRequestPanel.add(lblNewLabel_3);
//		loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
//		JLabel lblNewLabel_2 = new JLabel("sadd");
//		lblNewLabel_2.setPreferredSize(new Dimension(18, 14));
//		lblNewLabel_2.setMaximumSize(new Dimension(150, 14));
//		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 12));
//		loanRequestPanel.add(lblNewLabel_2);
//		loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
//		JLabel lblNewLabel_4 = new JLabel("sadd");
//		lblNewLabel_4.setMaximumSize(new Dimension(125, 14));
//		lblNewLabel_4.setFont(new Font("Dialog", Font.PLAIN, 12));
//		loanRequestPanel.add(lblNewLabel_4);
//		loanRequestPanel.add(Box.createRigidArea(new Dimension(40,0)));
//		
//		JLabel lblNewLabel_4_1 = new JLabel("sssds");
//		lblNewLabel_4_1.setMaximumSize(new Dimension(125, 14));
//		lblNewLabel_4_1.setFont(new Font("Dialog", Font.PLAIN, 12));
//		loanRequestPanel.add(lblNewLabel_4_1);



		for(Loan loan :  merchantOngoingLoans) {

			int i = 0;
				JPanel loanRequestPanel = new JPanel();
				panel.add(loanRequestPanel);
				loanRequestPanel.setMaximumSize(new Dimension(2210, 150));
				loanRequestPanel.setPreferredSize(new Dimension(1085, 150));
				loanRequestPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
				loanRequestPanel.setLayout(new BoxLayout(loanRequestPanel, BoxLayout.X_AXIS));

				loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));

				JPanel panel_1 = new JPanel();
				panel_1.setBorder(null);

				if(i % 2 == 1) {
					loanRequestPanel.setBackground(new Color(237, 250, 139));
					panel_1.setBackground(new Color(237, 250, 139));
				} else {
					loanRequestPanel.setBackground(new Color(205, 248,141));
					panel_1.setBackground(new Color(205, 248,141));
				}

				panel_1.setMaximumSize(new Dimension(175, 32767));
				loanRequestPanel.add(panel_1);
				panel_1.setLayout(null);

				
				Product product = dbManager.getProductData(loan.getProductId());
				
				JLabel productImage = new JLabel();
				productImage.setBorder(new LineBorder(new Color(0, 0, 0)));
				productImage.setBounds(10, 8, 109, 109);
				productImage.setIcon(HelperUtility.resizeImage(product.getProductImage(), productImage.getWidth(), productImage.getHeight()));
				panel_1.add(productImage);

				JLabel lblNewLabel_1_1 = new JLabel(product.getName());
				lblNewLabel_1_1.setMaximumSize(new Dimension(125, 14));
				lblNewLabel_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
				lblNewLabel_1_1.setBounds(10, 123, 125, 14);
				panel_1.add(lblNewLabel_1_1);


				loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
				JLabel lblNewLabel_1 = new JLabel(String.valueOf(loan.getPaidMonths()) );
				lblNewLabel_1.setMaximumSize(new Dimension(125, 14));
				lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 12));
				loanRequestPanel.add(lblNewLabel_1);
				loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
				JLabel lblNewLabel_3 = new JLabel(String.valueOf(loan.getRemainingMonthsToPay()));
				lblNewLabel_3.setMaximumSize(new Dimension(150, 14));
				lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 12));
				loanRequestPanel.add(lblNewLabel_3);
				loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
				JLabel lblNewLabel_2 = new JLabel(String.valueOf(loan.getPaidBalance()) );
				lblNewLabel_2.setMaximumSize(new Dimension(200, 14));
				lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 12));
				loanRequestPanel.add(lblNewLabel_2);
				loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
				JLabel lblNewLabel_4 = new JLabel(String.valueOf(loan.getRemainingBalance()));
				lblNewLabel_4.setMaximumSize(new Dimension(125, 14));
				lblNewLabel_4.setFont(new Font("Dialog", Font.PLAIN, 12));
				loanRequestPanel.add(lblNewLabel_4);
				loanRequestPanel.add(Box.createRigidArea(new Dimension(60,0)));
				
				LocalDate dueDate = null;
				for(LoanSchedule loanSched : loan.getLoanSchedule()) {
					if(loanSched.getStatus().equals("notpaid") ) {
						dueDate = loanSched.getLoanScheduleDate();
						break;
					}
				}
				
				JLabel lblNewLabel_4_1 = new JLabel(dueDate.toString());
				lblNewLabel_4_1.setMaximumSize(new Dimension(125, 14));
				lblNewLabel_4_1.setFont(new Font("Dialog", Font.PLAIN, 12));
				loanRequestPanel.add(lblNewLabel_4_1);

				Component rigidArea = Box.createRigidArea(new Dimension(20, 0));
				loanRequestPanel.add(rigidArea);
				
				JLabel lblNewLabel_4_1_1 = new JLabel(loan.getLoanStatus());
				lblNewLabel_4_1_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_4_1_1.setMaximumSize(new Dimension(125, 14));
				lblNewLabel_4_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
				loanRequestPanel.add(lblNewLabel_4_1_1);
				
			i++;
		}

	}

	public void refreshPendingLoanRequestPanel() {
		try {
			dbManager.connect();
			merchantOngoingLoans = dbManager.getOngoingLoansMerchant(MERCHANT_ID);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
