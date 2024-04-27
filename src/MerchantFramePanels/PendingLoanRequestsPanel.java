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
import Loan.LoanRequest;
import Products.Product;
import User.Loaner;
import Utilities.HelperUtility;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingConstants;

//DONE GUI
public class PendingLoanRequestsPanel extends JPanel {
	
	DatabaseManager dbManager = new DatabaseManager();

	private static final long serialVersionUID = 1L;
	private static int MERCHANT_ID;
	
	private ArrayList<LoanRequest> merchantLoanRequests = new ArrayList<>();
	

	/**
	 * Create the panel.
	 */
	public PendingLoanRequestsPanel(int merchantId) {
		setBackground(new Color(37, 102, 112));
		setLayout(null);
		MERCHANT_ID = merchantId;
		
		refreshPendingLoanRequestPanel();
		
		
		JLabel lblNewLabel_5 = new JLabel("Product ");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5.setBounds(65, 20, 162, 14);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("Product Price");
		lblNewLabel_5_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_5_1.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1.setBounds(251, 20, 121, 14);
		add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("Product Term");
		lblNewLabel_5_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_5_1_1.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_1.setBounds(396, 20, 143, 14);
		add(lblNewLabel_5_1_1);
		
		JLabel lblNewLabel_5_1_1_1 = new JLabel("Loaner's Name");
		lblNewLabel_5_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_5_1_1_1.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_1_1.setBounds(565, 20, 194, 14);
		add(lblNewLabel_5_1_1_1);
		
		JLabel lblNewLabel_5_1_1_2 = new JLabel("Loan Date");
		lblNewLabel_5_1_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_5_1_1_2.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1_2.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_1_2.setBounds(783, 20, 115, 14);
		add(lblNewLabel_5_1_1_2);
		
		JLabel lblNewLabel_5_1_1_2_1 = new JLabel("Status");
		lblNewLabel_5_1_1_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_5_1_1_2_1.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1_1_2_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_1_2_1.setBounds(952, 20, 162, 14);
		add(lblNewLabel_5_1_1_2_1);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(35, 45, 1105, 553);
		add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(37, 102, 112));
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		

		int i = 0;
		for(LoanRequest loanReq :  merchantLoanRequests) {
			
			if(loanReq.isPending()) {
				JPanel loanRequestPanel = new JPanel();
				
				panel.add(loanRequestPanel);
				loanRequestPanel.setMaximumSize(new Dimension(2210, 150));
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
				
				JLabel productImage = new JLabel();
				productImage.setBorder(new LineBorder(new Color(0, 0, 0)));
				productImage.setBounds(10, 8, 109, 109);
				productImage.setIcon(HelperUtility.resizeImage(loanReq.getProductToLoanData().getProductImage(), productImage.getWidth(), productImage.getHeight()));
				panel_1.add(productImage);
				
				JLabel lblNewLabel_1_1 = new JLabel(loanReq.getProductToLoanData().getName());
				lblNewLabel_1_1.setMaximumSize(new Dimension(125, 14));
				lblNewLabel_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
				lblNewLabel_1_1.setBounds(10, 123, 125, 14);
				panel_1.add(lblNewLabel_1_1);
				
				loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
				JLabel lblNewLabel_1 = new JLabel("₱ " + String.valueOf(loanReq.getProductToLoanData().getPrice()));
				lblNewLabel_1.setMaximumSize(new Dimension(125, 14));
				lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 12));
				loanRequestPanel.add(lblNewLabel_1);
				loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
				JLabel lblNewLabel_3 = new JLabel(String.valueOf(loanReq.getLoanedProductMonthsToPay()) + " months @ " + String.valueOf(loanReq.getLoanedProductInterestRate() * 100)+ "%" );
				lblNewLabel_3.setMaximumSize(new Dimension(150, 14));
				lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 12));
				loanRequestPanel.add(lblNewLabel_3);
				loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
				JLabel lblNewLabel_2 = new JLabel(loanReq.getLoanerLoanData().getFullName());
				lblNewLabel_2.setMaximumSize(new Dimension(200, 14));
				lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 12));
				loanRequestPanel.add(lblNewLabel_2);
				loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
				JLabel lblNewLabel_4 = new JLabel(loanReq.getLoanRequestDate().toString());
				lblNewLabel_4.setMaximumSize(new Dimension(125, 14));
				lblNewLabel_4.setFont(new Font("Dialog", Font.PLAIN, 12));
				loanRequestPanel.add(lblNewLabel_4);
				loanRequestPanel.add(Box.createRigidArea(new Dimension(40,0)));
				
				JButton approveButton = new JButton("Approve");
				approveButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							dbManager.connect();
							
							if(dbManager.approvedRequest(merchantId, loanReq.getLoanerId(), loanReq.getProductId(), "approve")) {
								int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to approve this loan?", "Confirm Action", JOptionPane.YES_NO_OPTION);
								
								refreshPendingLoanRequestPanel();
								if (choice == JOptionPane.YES_OPTION) {
									JOptionPane.showMessageDialog(null, "Loan Approved!", "Loan Approval Success", JOptionPane.INFORMATION_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null, "Server Error. Please try again later.", "Server Error", JOptionPane.ERROR_MESSAGE);
							}
							
						} catch(Exception ex) {
							ex.printStackTrace();
						}
						
					}
					
				});
				
				approveButton.setFont(new Font("Dialog", Font.PLAIN, 12));
				approveButton.setForeground(new Color(255, 255, 255));
				approveButton.setBackground(new Color(64, 112, 86));
				loanRequestPanel.add(approveButton);
				loanRequestPanel.add(Box.createRigidArea(new Dimension(15,0)));
				JButton rejectButton = new JButton("Reject");
				rejectButton.setBackground(Color.RED);
				rejectButton.setForeground(Color.white);
				rejectButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						try {
							dbManager.connect();
							
							if(dbManager.rejectRequest(merchantId, loanReq.getLoanerId(), loanReq.getProductId(), "reject")) {
								int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to reject this loan?", "Confirm Action", JOptionPane.YES_NO_OPTION);
								
								refreshPendingLoanRequestPanel();
								if (choice == JOptionPane.YES_OPTION) {
									JOptionPane.showMessageDialog(null, "Loan Rejected!", "Loan Reject Success", JOptionPane.INFORMATION_MESSAGE);
								}
								
							} else {
								JOptionPane.showMessageDialog(null, "Server Error. Please try again later.", "Server Error", JOptionPane.ERROR_MESSAGE);
							}
							
						} catch(Exception ex) {
							ex.printStackTrace();
						}
					}
					
				});
				
				rejectButton.setFont(new Font("Dialog", Font.PLAIN, 12));
				loanRequestPanel.add(rejectButton);
				
				loanRequestPanel.add(Box.createRigidArea(new Dimension(20,0)));
			}
			i++;
			
		}
		
	}
	
	public void refreshPendingLoanRequestPanel() {
		try {
			dbManager.connect();
			merchantLoanRequests = dbManager.getPendingLoanRequest(MERCHANT_ID);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
