package UserRoleFrames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;

public class MerchantFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MerchantFrame frame = new MerchantFrame();
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
	public MerchantFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 0));
		panel.setBounds(0, 0, 154, 661);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(28, 11, 94, 73);
		panel.add(panel_1);
		
		JButton btnNewButton = new JButton("Profile");
		btnNewButton.setBounds(28, 110, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnA = new JButton("Manage Products");
		btnA.setBounds(10, 154, 116, 23);
		panel.add(btnA);
		
		JButton btnManageLoans = new JButton("Manage Loans");
		btnManageLoans.setBounds(10, 194, 116, 23);
		panel.add(btnManageLoans);
	}
}
