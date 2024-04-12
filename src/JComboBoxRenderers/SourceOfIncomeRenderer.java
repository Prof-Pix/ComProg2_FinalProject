package JComboBoxRenderers;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import UserEnums.SourceOfIncome;
import Utilities.HelperUtility;

public class SourceOfIncomeRenderer extends JLabel implements ListCellRenderer<SourceOfIncome> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public Component getListCellRendererComponent(JList<? extends SourceOfIncome> list, 
    		SourceOfIncome value, 
                                                  int index, 
                                                  boolean isSelected, 
                                                  boolean cellHasFocus) {

		String displayText;
		
		if (value == null) {
			displayText = "--- Select source of income ---";
			setText(displayText);
			return this;
		}
		
		displayText = HelperUtility.capitalizeWords(value.name().toLowerCase().replace('_', ' '));
        setText(displayText); // Customize display text
        return this;
    }
}
