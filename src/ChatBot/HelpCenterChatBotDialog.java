package ChatBot;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;

import Database.DatabaseManager;

import javax.swing.border.BevelBorder;
import java.awt.Dialog.ModalityType;

public class HelpCenterChatBotDialog extends JDialog {

	DatabaseManager dbManager = new DatabaseManager();
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private ArrayList<Faqs> faqs = new ArrayList<>();
	private static String USER_TYPE;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HelpCenterChatBotDialog dialog = new HelpCenterChatBotDialog(USER_TYPE);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public HelpCenterChatBotDialog(String userType) {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Pixel Chat Bot");
		HelpCenterChatBotDialog.USER_TYPE = userType;
		try {
			dbManager.connect();
			faqs = dbManager.getAllFaqs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setBounds(100, 100, 445, 620);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setPreferredSize(new Dimension(10, 40));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblHelpCenter = new JLabel("HELP CENTER");
		lblHelpCenter.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelpCenter.setFont(new Font("Dialog", Font.BOLD, 20));
		lblHelpCenter.setBounds(0, 11, 434, 19);
		contentPanel.add(lblHelpCenter);
		
		JScrollPane chatBoxScrollPane = new JScrollPane();
		chatBoxScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		chatBoxScrollPane.setBounds(0, 60, 434, 520);
		contentPanel.add(chatBoxScrollPane);
		
		JPanel chatPanel = new JPanel();
		chatBoxScrollPane.setViewportView(chatPanel);
		chatPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		chatPanel.setPreferredSize(new Dimension(10, 300));
		chatPanel.setMaximumSize(new Dimension(800, 300));
		chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		chatPanel.add(panel_1_1);
		panel_1_1.setMaximumSize(new Dimension(860, 55));
		panel_1_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_1_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_1 = new JPanel();
		panel_1.setMinimumSize(new Dimension(10, 100));
		panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_1_1.add(panel_1);
		panel_1.setPreferredSize(new Dimension(320, 50));
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setMaximumSize(new Dimension(860, 40));
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setMaximumSize(new Dimension(35, 35));
		panel_2.setPreferredSize(new Dimension(35, 35));
		panel_2.setBackground(new Color(0, 128, 0));
		panel_2.setBounds(10, 7, 35, 33);
		panel_1.add(panel_2);
		
		JLabel lblNewLabel = new JLabel("Hi. I am Pixels. How can I help you today?");
		lblNewLabel.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblNewLabel.setBounds(63, 0, 262, 50);
		panel_1.add(lblNewLabel);
		
		Component rigidArea_1_1_1 = Box.createRigidArea(new Dimension(90, 0));
		panel_1_1.add(rigidArea_1_1_1);
		
		Component rigidArea = Box.createRigidArea(new Dimension(0, 10));
		chatPanel.add(rigidArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(null);
		scrollPane_1.setAlignmentY(Component.TOP_ALIGNMENT);
		scrollPane_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		chatPanel.add(scrollPane_1);
		scrollPane_1.setMaximumSize(new Dimension(251, 150));
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_1.setViewportView(panel_3);
		panel_3.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_3.setPreferredSize(new Dimension(251, 150));
		panel_3.setMaximumSize(new Dimension(251, 150));
		FlowLayout fl_panel_3 = new FlowLayout(FlowLayout.LEADING, 5, 5);
		panel_3.setLayout(fl_panel_3);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(0, 10));
		chatPanel.add(rigidArea_1);
		JPanel panel = new JPanel();
		panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		chatPanel.add(panel);
		panel.setMaximumSize(new Dimension(800, 30));
		panel.setPreferredSize(new Dimension(100, 50));
		panel.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 5));
		
		
		Component rigidArea_1_1 = Box.createRigidArea(new Dimension(0, 10));
		chatPanel.add(rigidArea_1_1);
		JLabel loanTitleQuery = new JLabel("Select a query");
		loanTitleQuery.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		loanTitleQuery.setFont(new Font("Dialog", Font.BOLD, 12));
		loanTitleQuery.setPreferredSize(new Dimension(245, 20));
		loanTitleQuery.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(loanTitleQuery);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
		chatPanel.add(scrollPane);
		scrollPane.setMaximumSize(new Dimension(425, 257));
		
		JPanel panel_5 = new JPanel();
		panel_5.setPreferredSize(new Dimension(10, 215));
		scrollPane.setViewportView(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
						
								JPanel panel_4 = new JPanel();
								panel_5.add(panel_4);
								panel_4.setPreferredSize(new Dimension(350, 225));
								panel_4.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
								panel_4.setMaximumSize(new Dimension(100, 32767));
								panel_4.setLayout(null);
								
										JLabel answerText = new JLabel("Select a query first.");
										answerText.setBounds(10, 11, 330, 210);
										panel_4.add(answerText);
										answerText.setBorder(null);
										answerText.setVerticalAlignment(SwingConstants.TOP);
										answerText.setFont(new Font("Dialog", Font.ITALIC, 12));
										
										Component rigidArea_1_1_1_1 = Box.createRigidArea(new Dimension(90, 0));
										rigidArea_1_1_1_1.setPreferredSize(new Dimension(50, 0));
										panel_5.add(rigidArea_1_1_1_1);
		
		for(Faqs faq : faqs) {
			
			if(USER_TYPE.equals(faq.getFaqUserType())) {
				JButton loanTitleButton = new JButton(faq.getFaqTitle());
				loanTitleButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						loanTitleQuery.setText(faq.getFaqTitle());
						answerText.setText(faq.getFaqAnswer());
						
					}
					
				});
				panel_3.add(loanTitleButton);
			}

		}
		


		

		

		
	}
}
