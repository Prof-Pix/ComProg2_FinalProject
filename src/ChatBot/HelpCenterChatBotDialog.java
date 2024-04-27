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
import Utilities.HelperUtility;

import javax.swing.border.BevelBorder;
import java.awt.Dialog.ModalityType;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

public class HelpCenterChatBotDialog extends JDialog {

	DatabaseManager dbManager = new DatabaseManager();
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private ArrayList<Faqs> faqs = new ArrayList<>();
	private static String USER_TYPE;
	
	private boolean hasSelectedQuery;
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
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Pixel Chat Bot");
		HelpCenterChatBotDialog.USER_TYPE = userType;
		try {
			dbManager.connect();
			faqs = dbManager.getAllFaqs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setBounds(100, 100, 448, 620);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(30, 66, 94));
		contentPanel.setPreferredSize(new Dimension(10, 40));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblHelpCenter = new JLabel("HELP CENTER");
		lblHelpCenter.setForeground(new Color(255, 255, 255));
		lblHelpCenter.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelpCenter.setFont(new Font("Dialog", Font.BOLD, 20));
		lblHelpCenter.setBounds(0, 22, 434, 19);
		contentPanel.add(lblHelpCenter);
		
		JScrollPane chatBoxScrollPane = new JScrollPane();
		chatBoxScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		chatBoxScrollPane.setBounds(0, 60, 434, 520);
		contentPanel.add(chatBoxScrollPane);
		
		JPanel chatPanel = new JPanel();
		chatPanel.setBackground(new Color(30, 66, 94));
		chatBoxScrollPane.setViewportView(chatPanel);
		chatPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		chatPanel.setPreferredSize(new Dimension(10, 300));
		chatPanel.setMaximumSize(new Dimension(800, 290));
		chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(30, 66, 94));
		panel_1_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		chatPanel.add(panel_1_1);
		panel_1_1.setMaximumSize(new Dimension(860, 55));
		panel_1_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_1_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(173, 226, 138));
		panel_1.setMinimumSize(new Dimension(10, 100));
		panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_1_1.add(panel_1);
		panel_1.setPreferredSize(new Dimension(320, 50));
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setMaximumSize(new Dimension(860, 40));
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hi. I am Pixel. How can I help you today?");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel.setBounds(63, 0, 262, 50);
		panel_1.add(lblNewLabel);
		
		JLabel logoIcon = new JLabel("");
		logoIcon.setBounds(10, 6, 40, 40);
		String filePath = "D:\\APPLICATIONS\\Home_Credit\\src\\Images\\loanpal_logo.png";
		logoIcon.setIcon(HelperUtility.resizeImage(filePath, logoIcon.getWidth(), logoIcon.getHeight()));
		
		
		panel_1.add(logoIcon);
		
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
		panel_3.setBackground(new Color(37, 102, 112));
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
		panel.setBackground(new Color(30, 66, 94));
		panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		chatPanel.add(panel);
		panel.setMaximumSize(new Dimension(800, 30));
		panel.setPreferredSize(new Dimension(100, 50));
		panel.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 5));
		
		JPanel answerPanel = new JPanel();
		answerPanel.setPreferredSize(new Dimension(450, 10));
		answerPanel.setMaximumSize(new Dimension(860, 460));
		answerPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		answerPanel.setBackground(new Color(30, 66, 94));
		answerPanel.setAlignmentX(1.0f);
		chatPanel.add(answerPanel);
		answerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_2 = new JPanel();
		panel_2.setVisible(false);
		panel_2.setBackground(new Color(173, 226, 138));
		panel_2.setPreferredSize(new Dimension(350, 230));
		panel_2.setMaximumSize(new Dimension(350, 400));
		answerPanel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel logoIcon_1 = new JLabel("");
		logoIcon_1.setBounds(10, 11, 40, 40);
		String filePath1 = "D:\\APPLICATIONS\\Home_Credit\\src\\Images\\loanpal_logo.png";
		logoIcon_1.setIcon(HelperUtility.resizeImage(filePath1, logoIcon_1.getWidth(), logoIcon_1.getHeight()));
		panel_2.add(logoIcon_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setMinimumSize(new Dimension(250, 23));
		scrollPane_2.setMaximumSize(new Dimension(250, 250));
		scrollPane_2.setPreferredSize(new Dimension(100, 2));
		scrollPane_2.setBounds(60, 11, 280, 208);
		panel_2.add(scrollPane_2);
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setBorder(null);
		
		JTextArea answerTextArea = new JTextArea();
		scrollPane_2.setViewportView(answerTextArea);
		answerTextArea.setBorder(null);
		answerTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
		answerTextArea.setEditable(false);
		answerTextArea.setLineWrap(true);
		answerTextArea.setRows(8);
		answerTextArea.setRequestFocusEnabled(false);
		answerTextArea.setWrapStyleWord(true);
		answerTextArea.setPreferredSize(new Dimension(230, 120));
		answerTextArea.setMaximumSize(new Dimension(230, 130));
		answerTextArea.setFont(new Font("Dialog", Font.ITALIC, 13));
		answerTextArea.setBackground(new Color(173, 226, 138));
		
		Component rigidArea_1_1_1_1 = Box.createRigidArea(new Dimension(90, 0));
		rigidArea_1_1_1_1.setPreferredSize(new Dimension(50, 0));
		answerPanel.add(rigidArea_1_1_1_1);
		
		
		Component rigidArea_1_1 = Box.createRigidArea(new Dimension(0, 10));
		chatPanel.add(rigidArea_1_1);
		JLabel loanTitleQuery = new JLabel("Select a query");
		loanTitleQuery.setForeground(new Color(255, 255, 255));
		loanTitleQuery.setBackground(new Color(37, 102, 112));
		loanTitleQuery.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		loanTitleQuery.setFont(new Font("Dialog", Font.BOLD, 12));
		loanTitleQuery.setPreferredSize(new Dimension(245, 20));
		loanTitleQuery.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(loanTitleQuery);
		
//		JButton loanTitleButton = new JButton("sad");
//		loanTitleButton.setBackground(new Color(237, 250, 139));
//		loanTitleButton.setFont(new Font("Dialog", Font.PLAIN, 11));
//		
//		loanTitleButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				loanTitleQuery.setText("dsfsf");
//				answerTextArea.setText("dfsdf");
//				
//			}
//			
//		});
//		panel_3.add(loanTitleButton);
		
		for(Faqs faq : faqs) {
			
			if(USER_TYPE.equals(faq.getFaqUserType())) {
				JButton loanTitleButton = new JButton(faq.getFaqTitle());
				loanTitleButton.setBackground(new Color(237, 250, 139));
				loanTitleButton.setFont(new Font("Dialog", Font.PLAIN, 11));
				loanTitleButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						loanTitleQuery.setText(faq.getFaqTitle());
						answerTextArea.setText(faq.getFaqAnswer());
						panel_2.setVisible(true);;
						
					}
					
				});
				panel_3.add(loanTitleButton);
			}

		}
		


		

		

		
	}
}
