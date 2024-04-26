package AdminFramePanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;

public class ViewLoansDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewLoansDialog dialog = new ViewLoansDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewLoansDialog() {
		setBounds(100, 100, 1180, 641);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(34, 42, 1093, 517);
			contentPanel.add(scrollPane);
			
			for(int i = 0; i < 3 ; i++) {
				JPanel loansPanel = new JPanel();
				scrollPane.setViewportView(loansPanel);
				loansPanel.setLayout(new BoxLayout(loansPanel, BoxLayout.Y_AXIS));
				
				JPanel loanRequestPanel = new JPanel();
				loansPanel.add(loanRequestPanel);
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
		
				JButton btnNewButton = new JButton("Pay Downpayment");
				loanRequestPanel.add(btnNewButton);
		
				Component rigidArea = Box.createRigidArea(new Dimension(10, 0));
				loanRequestPanel.add(rigidArea);
		
				JButton btnNewButton_1 = new JButton("Cancel Loan");
				loanRequestPanel.add(btnNewButton_1);
		
				loanRequestPanel.add(Box.createRigidArea(new Dimension(10,0)));
				
			}
			
			
		}
	}

}
