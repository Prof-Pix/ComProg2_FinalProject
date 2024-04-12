
package Components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.SwingConstants;

public class InvalidInputPopup extends JDialog {
	
	private static JFrame relativePage;
	private static String errorText;

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton closeButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InvalidInputPopup dialog = new InvalidInputPopup(relativePage, errorText);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InvalidInputPopup(JFrame relativePage, String errorText) {
		InvalidInputPopup.relativePage = relativePage;
		InvalidInputPopup.errorText = errorText;
		setPreferredSize(new Dimension(300, 250));
		setTitle("Error!");
		setResizable(false);
		setType(Type.POPUP);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		//This allows a page to be disabled when this popup appears
		setModal(true);
		pack();		
		
		//Settings the location relative to the page that calls this popup window
		setLocationRelativeTo(relativePage);
		
		setBounds(100,100, 300, 219);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setPreferredSize(new Dimension(5, 10));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Invalid Input.");
			lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
			lblNewLabel.setBounds(23, 31, 75, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblPleaseCheckAll = new JLabel(errorText);
			lblPleaseCheckAll.setVerticalAlignment(SwingConstants.TOP);
			lblPleaseCheckAll.setFont(new Font("Dialog", Font.BOLD, 12));
			lblPleaseCheckAll.setBounds(23, 54, 240, 113);
			contentPanel.add(lblPleaseCheckAll);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				closeButton = new JButton("Close");
				closeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(closeButton);
				getRootPane().setDefaultButton(closeButton);
			}
		}
	}

}
