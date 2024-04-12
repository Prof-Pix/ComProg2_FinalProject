package JComboBoxRenderers;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import UserEnums.MonthlyIncome;
import Utilities.HelperUtility;

public class MonthlyIncomeRenderer extends JLabel implements ListCellRenderer<MonthlyIncome> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Component getListCellRendererComponent(JList<? extends MonthlyIncome> list, 
    		MonthlyIncome value, 
                                                  int index, 
                                                  boolean isSelected, 
                                                  boolean cellHasFocus) {

		String displayText;
		
		if (value == null) {
			displayText = "--- Select monthly income ---";
			setText(displayText);
			return this;
		}
		
		displayText = HelperUtility.capitalizeWords(value.name().toLowerCase().replace('_', ' '));
        setText(displayText); // Customize display text
        return this;
    }
}
