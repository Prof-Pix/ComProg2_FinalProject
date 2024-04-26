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
		
		setLayout(null);
		LOANER_ID = loanerId;

		refreshPendingLoanPanel();


		JLabel lblNewLabel_5 = new JLabel("Product Name");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5.setBounds(58, 20, 93, 14);
		add(lblNewLabel_5);

		JLabel lblNewLabel_5_1 = new JLabel("Months Paid");
		lblNewLabel_5_1.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1.setBounds(205, 20, 93, 14);
		add(lblNewLabel_5_1);

		JLabel lblNewLabel_5_1_1 = new JLabel("Months Left");
		lblNewLabel_5_1_1.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_1.setBounds(323, 20, 93, 14);
		add(lblNewLabel_5_1_1);

		JLabel lblNewLabel_5_1_1_1 = new JLabel("Paid Balance");
		lblNewLabel_5_1_1_1.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_1_1.setBounds(470, 20, 93, 14);
		add(lblNewLabel_5_1_1_1);

		JLabel lblNewLabel_5_1_1_2 = new JLabel("Remaining Balance");
		lblNewLabel_5_1_1_2.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1_2.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_1_2.setBounds(622, 20, 111, 14);
		add(lblNewLabel_5_1_1_2);

		JLabel lblNewLabel_5_1_1_2_2 = new JLabel("Next Due Date");
		lblNewLabel_5_1_1_2_2.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1_1_2_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1_2_2.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_1_2_2.setBounds(776, 20, 93, 14);
		add(lblNewLabel_5_1_1_2_2);

		JLabel lblNewLabel_5_1_1_2_1 = new JLabel("Action");
		lblNewLabel_5_1_1_2_1.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1_1_2_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_1_2_1.setBounds(953, 20, 93, 14);
		add(lblNewLabel_5_1_1_2_1);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(35, 45, 1105, 553);
		add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


		//		JPanel loanRequestPanel = new JPanel();
		//		panel.add(loanRequestPanel);
		//		loanRequestPanel.setMaximumSize(new Dimension(2210, 70));
		//		loanRequestPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		//		loanRequestPanel.setLayout(new BoxLayout(loanRequestPanel, BoxLayout.X_AXIS));
		//
		//		loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
		//		JLabel lblNewLabel = new JLabel("Samsung A54");
		//		lblNewLabel.setMaximumSize(new Dimension(130, 14));
		//		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		//		loanRequestPanel.add(lblNewLabel);
		//
		//		loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
		//		JLabel lblNewLabel_1 = new JLabel("₱ 9000");
		//		lblNewLabel_1.setMaximumSize(new Dimension(100, 14));
		//		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		//		loanRequestPanel.add(lblNewLabel_1);
		//		loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
		//		JLabel lblNewLabel_3 = new JLabel("6 months @ 3%");
		//		lblNewLabel_3.setMaximumSize(new Dimension(125, 14));
		//		lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		//		loanRequestPanel.add(lblNewLabel_3);
		//		loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
		//		JLabel lblNewLabel_2 = new JLabel("Rhem Gorgs Salvador");
		//		lblNewLabel_2.setMaximumSize(new Dimension(150, 14));
		//		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		//		loanRequestPanel.add(lblNewLabel_2);
		//		loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
		//		JLabel lblNewLabel_4 = new JLabel("02-02-2024");
		//		lblNewLabel_4.setMaximumSize(new Dimension(100, 14));
		//		lblNewLabel_4.setFont(new Font("Dialog", Font.PLAIN, 12));
		//		loanRequestPanel.add(lblNewLabel_4);
		//
		//		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 0));
		//		loanRequestPanel.add(rigidArea_1);
		//
		//		JLabel lblNewLabel_4_1 = new JLabel("02-02-2024");
		//		lblNewLabel_4_1.setMaximumSize(new Dimension(125, 14));
		//		lblNewLabel_4_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		//		loanRequestPanel.add(lblNewLabel_4_1);
		//		Component rigidArea = Box.createRigidArea(new Dimension(20,0));
		//		rigidArea.setMaximumSize(new Dimension(50, 0));
		//		loanRequestPanel.add(rigidArea);
		//
		//		JButton btnNewButton = new JButton("Pay Remaining Balance");
		//		loanRequestPanel.add(btnNewButton);
		//
		//		loanRequestPanel.add(Box.createRigidArea(new Dimension(10,0)));



		for(Loan ongoingLoan :  loanerOngoingLoans) {


			JPanel loanRequestPanel = new JPanel();
			panel.add(loanRequestPanel);
			loanRequestPanel.setMaximumSize(new Dimension(2210, 70));
			loanRequestPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
			loanRequestPanel.setLayout(new BoxLayout(loanRequestPanel, BoxLayout.X_AXIS));

			loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
			dbManager.connect();
			JLabel productName = new JLabel(dbManager.getProductName(ongoingLoan.getProductId()));
			productName.setMaximumSize(new Dimension(130, 14));
			productName.setFont(new Font("Dialog", Font.PLAIN, 12));
			loanRequestPanel.add(productName);

			loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
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
			JLabel paidBalance = new JLabel(String.valueOf(ongoingLoan.getPaidBalance()));
			paidBalance.setMaximumSize(new Dimension(150, 14));
			paidBalance.setFont(new Font("Dialog", Font.PLAIN, 12));
			loanRequestPanel.add(paidBalance);
			loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
			JLabel remainingBalance = new JLabel(String.valueOf(ongoingLoan.getRemainingBalance()));
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
			nextDueDate.setMaximumSize(new Dimension(90, 14));
			nextDueDate.setFont(new Font("Dialog", Font.PLAIN, 12));
			loanRequestPanel.add(nextDueDate);
			loanRequestPanel.add(Box.createRigidArea(new Dimension(50,0)));

			JButton payRemainingButton = new JButton("Pay Remaining Balance");
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
