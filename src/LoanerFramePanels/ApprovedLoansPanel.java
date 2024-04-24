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
import LoanRequest.LoanRequest;
import Products.Product;
import User.Loaner;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingConstants;

public class ApprovedLoansPanel extends JPanel {

	DatabaseManager dbManager = new DatabaseManager();

	private static final long serialVersionUID = 1L;
	private static int LOANER_ID;

	private ArrayList<LoanRequest> loanerLoanRequests = new ArrayList<>();


	/**
	 * Create the panel.
	 */
	public ApprovedLoansPanel(int loanerId) {
		setLayout(null);
		LOANER_ID = loanerId;

		refreshPendingLoanPanel();


		JLabel lblNewLabel_5 = new JLabel("Product Name");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5.setBounds(54, 20, 93, 14);
		add(lblNewLabel_5);

		JLabel lblNewLabel_5_1 = new JLabel("Product Price");
		lblNewLabel_5_1.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1.setBounds(205, 20, 93, 14);
		add(lblNewLabel_5_1);

		JLabel lblNewLabel_5_1_1 = new JLabel("Product Term");
		lblNewLabel_5_1_1.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_1.setBounds(323, 20, 93, 14);
		add(lblNewLabel_5_1_1);

		JLabel lblNewLabel_5_1_1_1 = new JLabel("Loaner's Name");
		lblNewLabel_5_1_1_1.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_1_1.setBounds(470, 20, 93, 14);
		add(lblNewLabel_5_1_1_1);

		JLabel lblNewLabel_5_1_1_2 = new JLabel("Loan Date");
		lblNewLabel_5_1_1_2.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1_2.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_1_2.setBounds(622, 20, 93, 14);
		add(lblNewLabel_5_1_1_2);
		
		JLabel lblNewLabel_5_1_1_2_2 = new JLabel("Approval Date");
		lblNewLabel_5_1_1_2_2.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1_1_2_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1_2_2.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_1_2_2.setBounds(743, 20, 93, 14);
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
//		loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
//
//		JButton btnNewButton = new JButton("Pay Downpayment");
//		loanRequestPanel.add(btnNewButton);
//
//		Component rigidArea = Box.createRigidArea(new Dimension(10, 0));
//		loanRequestPanel.add(rigidArea);
//
//		JButton btnNewButton_1 = new JButton("Cancel Loan");
//		loanRequestPanel.add(btnNewButton_1);
//
//		loanRequestPanel.add(Box.createRigidArea(new Dimension(10,0)));



				for(LoanRequest loanReq :  loanerLoanRequests) {
					
					if(!loanReq.isPending() && loanReq.isApproved() ) {
						JPanel loanRequestPanel = new JPanel();
						panel.add(loanRequestPanel);
						loanRequestPanel.setMaximumSize(new Dimension(2210, 70));
						loanRequestPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
						loanRequestPanel.setLayout(new BoxLayout(loanRequestPanel, BoxLayout.X_AXIS));

						loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
						JLabel lblNewLabel = new JLabel("Samsung A54");
						lblNewLabel.setMaximumSize(new Dimension(130, 14));
						lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
						loanRequestPanel.add(lblNewLabel);

						loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
						JLabel lblNewLabel_1 = new JLabel("₱ 9000");
						lblNewLabel_1.setMaximumSize(new Dimension(100, 14));
						lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 12));
						loanRequestPanel.add(lblNewLabel_1);
						loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
						JLabel lblNewLabel_3 = new JLabel("6 months @ 3%");
						lblNewLabel_3.setMaximumSize(new Dimension(125, 14));
						lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 12));
						loanRequestPanel.add(lblNewLabel_3);
						loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
						JLabel lblNewLabel_2 = new JLabel("Rhem Gorgs Salvador");
						lblNewLabel_2.setMaximumSize(new Dimension(150, 14));
						lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 12));
						loanRequestPanel.add(lblNewLabel_2);
						loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
						JLabel lblNewLabel_4 = new JLabel("02-02-2024");
						lblNewLabel_4.setMaximumSize(new Dimension(100, 14));
						lblNewLabel_4.setFont(new Font("Dialog", Font.PLAIN, 12));
						loanRequestPanel.add(lblNewLabel_4);

						Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 0));
						loanRequestPanel.add(rigidArea_1);

						JLabel lblNewLabel_4_1 = new JLabel("02-02-2024");
						lblNewLabel_4_1.setMaximumSize(new Dimension(125, 14));
						lblNewLabel_4_1.setFont(new Font("Dialog", Font.PLAIN, 12));
						loanRequestPanel.add(lblNewLabel_4_1);
						loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));

						JButton payDownpaymentButton = new JButton("Pay Downpayment");
						payDownpaymentButton.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								DownPaymentLoanerPanel dpLoaner = new DownPaymentLoanerPanel(loanReq);
								dpLoaner.setVisible(true);
								
							}
							
						});
						loanRequestPanel.add(payDownpaymentButton);

						Component rigidArea = Box.createRigidArea(new Dimension(10, 0));
						loanRequestPanel.add(rigidArea);

						JButton btnNewButton_1 = new JButton("Cancel Loan");
						loanRequestPanel.add(btnNewButton_1);

						loanRequestPanel.add(Box.createRigidArea(new Dimension(10,0)));
					}
					
				}

	}

	public void refreshPendingLoanPanel() {
		try {
			dbManager.connect();
			loanerLoanRequests = dbManager.getApprovedRejectedLoans(LOANER_ID);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
