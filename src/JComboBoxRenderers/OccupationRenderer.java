package JComboBoxRenderers;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import UserEnums.Occupation;
import Utilities.HelperUtility;

public class OccupationRenderer extends JLabel implements ListCellRenderer<Occupation> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public Component getListCellRendererComponent(JList<? extends Occupation> list, 
    		Occupation value, 
                                                  int index, 
                                                  boolean isSelected, 
                                                  boolean cellHasFocus) {

		String displayText;
		
		if (value == null) {
			displayText = "--- Select an occupation ---";
			setText(displayText);
			return this;
		}
		
		displayText = HelperUtility.capitalizeWords(value.name().toLowerCase().replace('_', ' '));
        setText(displayText); // Customize display text
        return this;
    }
}
