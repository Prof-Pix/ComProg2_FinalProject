package LoanerFramePanels;

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

public class OngoingLoansPanel extends JPanel {

	DatabaseManager dbManager = new DatabaseManager();

	private static final long serialVersionUID = 1L;
	private static int LOANER_ID;

	private ArrayList<Loan> loanerOngoingLoans = new ArrayList<>();


	/**
	 * Create the panel.
	 */
	public OngoingLoansPanel(int loanerId) {
		setBackground(new Color(37, 102, 112));

		setLayout(null);
		LOANER_ID = loanerId;

		refreshPendingLoanPanel();


		JLabel lblNewLabel_5 = new JLabel("Product Name");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5.setBounds(58, 20, 93, 14);
		add(lblNewLabel_5);

		JLabel lblNewLabel_5_1 = new JLabel("Months Paid");
		lblNewLabel_5_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_5_1.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1.setBounds(235, 20, 93, 14);
		add(lblNewLabel_5_1);

		JLabel lblNewLabel_5_1_1 = new JLabel("Months Left");
		lblNewLabel_5_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_5_1_1.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_1.setBounds(349, 20, 93, 14);
		add(lblNewLabel_5_1_1);

		JLabel lblNewLabel_5_1_1_1 = new JLabel("Paid Balance");
		lblNewLabel_5_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_5_1_1_1.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_1_1.setBounds(493, 20, 93, 14);
		add(lblNewLabel_5_1_1_1);

		JLabel lblNewLabel_5_1_1_2 = new JLabel("Remaining Balance");
		lblNewLabel_5_1_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_5_1_1_2.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1_2.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_1_2.setBounds(665, 20, 111, 14);
		add(lblNewLabel_5_1_1_2);

		JLabel lblNewLabel_5_1_1_2_2 = new JLabel("Next Due Date");
		lblNewLabel_5_1_1_2_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_5_1_1_2_2.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1_1_2_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1_2_2.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_1_2_2.setBounds(814, 20, 93, 14);
		add(lblNewLabel_5_1_1_2_2);

		JLabel lblNewLabel_5_1_1_2_1 = new JLabel("Action");
		lblNewLabel_5_1_1_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_5_1_1_2_1.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1_1_2_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_1_2_1.setBounds(986, 20, 93, 14);
		add(lblNewLabel_5_1_1_2_1);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(35, 45, 1105, 553);
		add(scrollPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(37, 102, 112));
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


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
		//		if(i % 2 == 1) {
		//			loanRequestPanel.setBackground(new Color(237, 250, 139));
		//			panel_1.setBackground(new Color(237, 250, 139));
		//		} else {
		//			loanRequestPanel.setBackground(new Color(205, 248,141));
		//			panel_1.setBackground(new Color(205, 248,141));
		//		}
		//
		//		panel_1.setMaximumSize(new Dimension(175, 32767));
		//		loanRequestPanel.add(panel_1);
		//		panel_1.setLayout(null);
		//
		//		
		//		Product product = dbManager.getProductData(ongoingLoan.getProductId());
		//		
		//		JLabel productImage = new JLabel();
		//		productImage.setBorder(new LineBorder(new Color(0, 0, 0)));
		//		productImage.setBounds(10, 8, 109, 109);
		//		productImage.setIcon(HelperUtility.resizeImage(product.getProductImage(), productImage.getWidth(), productImage.getHeight()));
		//		panel_1.add(productImage);
		//
		//		JLabel lblNewLabel_1_1 = new JLabel(product.getName());
		//		lblNewLabel_1_1.setMaximumSize(new Dimension(125, 14));
		//		lblNewLabel_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		//		lblNewLabel_1_1.setBounds(10, 123, 125, 14);
		//		panel_1.add(lblNewLabel_1_1);
		//		JLabel monthsPaid = new JLabel(String.valueOf(ongoingLoan.getPaid_months()));
		//		monthsPaid.setMaximumSize(new Dimension(100, 14));
		//		monthsPaid.setFont(new Font("Dialog", Font.PLAIN, 12));
		//		loanRequestPanel.add(monthsPaid);
		//		loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
		//		JLabel monthsLeft = new JLabel(String.valueOf(ongoingLoan.getRemainingMonthsToPay()));
		//		monthsLeft.setMaximumSize(new Dimension(125, 14));
		//		monthsLeft.setFont(new Font("Dialog", Font.PLAIN, 12));
		//		loanRequestPanel.add(monthsLeft);
		//		loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
		//		JLabel paidBalance = new JLabel(String.valueOf(ongoingLoan.getPaidBalance()));
		//		paidBalance.setMaximumSize(new Dimension(150, 14));
		//		paidBalance.setFont(new Font("Dialog", Font.PLAIN, 12));
		//		loanRequestPanel.add(paidBalance);
		//		loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
		//		JLabel remainingBalance = new JLabel(String.valueOf(ongoingLoan.getRemainingBalance()));
		//		remainingBalance.setMaximumSize(new Dimension(120, 14));
		//		remainingBalance.setFont(new Font("Dialog", Font.PLAIN, 12));
		//		loanRequestPanel.add(remainingBalance);
		//
		//		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 0));
		//		loanRequestPanel.add(rigidArea_1);
		//
		//		LocalDate dueDate = null;
		//		for(LoanSchedule loanSched : ongoingLoan.getLoanSchedule()) {
		//			if(loanSched.getStatus().equals("notpaid") ) {
		//				dueDate = loanSched.getLoanScheduleDate();
		//				break;
		//			}
		//		}
		//
		//		JLabel nextDueDate = new JLabel(dueDate.toString());
		//		nextDueDate.setMaximumSize(new Dimension(120, 14));
		//		nextDueDate.setFont(new Font("Dialog", Font.PLAIN, 12));
		//		loanRequestPanel.add(nextDueDate);
		//		loanRequestPanel.add(Box.createRigidArea(new Dimension(30,0)));
		//
		//		JButton payRemainingButton = new JButton("Pay Remaining Balance");
		//		payRemainingButton.setForeground(new Color(255, 255, 255));
		//		payRemainingButton.setBackground(new Color(64, 112, 86));
		//		payRemainingButton.addActionListener(new ActionListener() {
		//
		//			@Override
		//			public void actionPerformed(ActionEvent e) {
		//
		//				dbManager.connect();
		//				LoanRequest loanReq = dbManager.getLoanRequestData(loanerId, ongoingLoan.getLoanRequestId());
		//
		//				LoanPaymentPanel loanPayment = new LoanPaymentPanel(loanReq, ongoingLoan);
		//				loanPayment.setVisible(true);
		//
		//			}
		//
		//		});
		//
		//		loanRequestPanel.add(payRemainingButton);
		//
		//		loanRequestPanel.add(Box.createRigidArea(new Dimension(10,0)));


		int i = 0;
		for(Loan ongoingLoan :  loanerOngoingLoans) {


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


			Product product = dbManager.getProductData(ongoingLoan.getProductId());

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
			JLabel monthsPaid = new JLabel(String.valueOf(ongoingLoan.getPaid_months()));
			monthsPaid.setMaximumSize(new Dimension(100, 14));
			monthsPaid.setFont(new Font("Dialog", Font.PLAIN, 12));
			loanRequestPanel.add(monthsPaid);
			loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
			JLabel monthsLeft = new JLabel(String.valueOf(ongoingLoan.getRemainingMonthsToPay()));
			monthsLeft.setMaximumSize(new Dimension(125, 14));
			monthsLeft.setFont(new Font("Dialog", Font.PLAIN, 12));
			loanRequestPanel.add(monthsLeft);
			loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
			JLabel paidBalance = new JLabel("₱ " + String.valueOf(ongoingLoan.getPaidBalance()));
			paidBalance.setMaximumSize(new Dimension(150, 14));
			paidBalance.setFont(new Font("Dialog", Font.PLAIN, 12));
			loanRequestPanel.add(paidBalance);
			loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
			JLabel remainingBalance = new JLabel("₱ " +String.valueOf(ongoingLoan.getRemainingBalance()));
			remainingBalance.setMaximumSize(new Dimension(120, 14));
			remainingBalance.setFont(new Font("Dialog", Font.PLAIN, 12));
			loanRequestPanel.add(remainingBalance);

			Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 0));
			loanRequestPanel.add(rigidArea_1);

			LocalDate dueDate = null;
			for(LoanSchedule loanSched : ongoingLoan.getLoanSchedule()) {
				if(loanSched.getStatus().equals("notpaid") ) {
					dueDate = loanSched.getLoanScheduleDate();
					break;
				}
			}

			JLabel nextDueDate = new JLabel(dueDate.toString());
			nextDueDate.setMaximumSize(new Dimension(120, 14));
			nextDueDate.setFont(new Font("Dialog", Font.PLAIN, 12));
			loanRequestPanel.add(nextDueDate);
			loanRequestPanel.add(Box.createRigidArea(new Dimension(30,0)));

			JButton payRemainingButton = new JButton("Pay Remaining Balance");
			payRemainingButton.setForeground(new Color(255, 255, 255));
			payRemainingButton.setBackground(new Color(64, 112, 86));
			payRemainingButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					dbManager.connect();
					LoanRequest loanReq = dbManager.getLoanRequestData(loanerId, ongoingLoan.getLoanRequestId());

					LoanPaymentPanel loanPayment = new LoanPaymentPanel(loanReq, ongoingLoan);
					loanPayment.setVisible(true);

				}

			});

			loanRequestPanel.add(payRemainingButton);

			loanRequestPanel.add(Box.createRigidArea(new Dimension(10,0)));

			i++;
		}

	}

	public void refreshPendingLoanPanel() {
		try {
			dbManager.connect();
			loanerOngoingLoans = dbManager.getOngoingLoans(LOANER_ID);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
