package AdminFramePanels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Database.DatabaseManager;
import Products.Product;
import User.Loaner;
import User.Merchant;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Dimension;

public class ManageLoanerPanel extends JPanel {

	DatabaseManager dbManager = new DatabaseManager();
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Loaner> allLoaner = new ArrayList<>();

	/**
	 * Create the panel.
	 */
	public ManageLoanerPanel() {
		setBackground(new Color(30, 66, 94));
		
		try {
			dbManager.connect();
			allLoaner = dbManager.getAllLoaner();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(173, 226, 138)));
		scrollPane.setBounds(37, 33, 1111, 542);
		add(scrollPane);
		
		JPanel loanerPanel = new JPanel();
		loanerPanel.setBackground(new Color(30, 66, 94));
		scrollPane.setViewportView(loanerPanel);
		loanerPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 15, 15));
		
		JLabel lblNewLabel_5 = new JLabel("LIST OF " + allLoaner.size() +  " LOANER/s");
		lblNewLabel_5.setMaximumSize(new Dimension(175, 14));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_5.setBounds(37, 11, 256, 14);
		add(lblNewLabel_5);
		
		for(Loaner loaner: allLoaner) {
			LoanerTemplatePanel merchPanel = new LoanerTemplatePanel(loaner);
			loanerPanel.add(merchPanel);
		}
		
		loanerPanel.revalidate();
		loanerPanel.repaint();

	}
}
