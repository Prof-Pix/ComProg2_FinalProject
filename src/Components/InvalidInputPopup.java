
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
import java.awt.Dialog.ModalityType;
import java.awt.Window.Type;
import java.awt.Rectangle;

public class InvalidInputPopup extends JDialog {
	
	private static JFrame relativePage;

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton closeButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InvalidInputPopup dialog = new InvalidInputPopup(relativePage);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InvalidInputPopup(JFrame relativePage) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setType(Type.POPUP);
		setModalityType(ModalityType.APPLICATION_MODAL);
		
		InvalidInputPopup.relativePage = relativePage;
		//This allows a page to be disabled when this popup appears
		setModal(true);
		pack();		
		
		//Settings the location relative to the page that calls this popup window
		setLocationRelativeTo(relativePage);
		
		setBounds(100,100, 355, 189);
		getContentPane().setLayout(new BorderLayout());
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
			JLabel lblPleaseCheckAll = new JLabel("Please check all the required fields.");
			lblPleaseCheckAll.setFont(new Font("Dialog", Font.BOLD, 12));
			lblPleaseCheckAll.setBounds(23, 54, 202, 14);
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
