package JComboBoxRenderers;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import UserEnums.MerchantCategory;
import Utilities.HelperUtility;

public class MerchantCategoryRenderer extends JLabel implements ListCellRenderer<MerchantCategory> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Component getListCellRendererComponent(JList<? extends MerchantCategory> list, 
			MerchantCategory value, 
                                                  int index, 
                                                  boolean isSelected, 
                                                  boolean cellHasFocus) {

		String displayText;
		
		if (value == null) {
			displayText = "--- Select merchant category ---";
			setText(displayText);
			return this;
		}
		
		displayText = HelperUtility.capitalizeWords(value.name().toLowerCase().replace('_', ' '));
        setText(displayText); // Customize display text
        return this;
    }
}
