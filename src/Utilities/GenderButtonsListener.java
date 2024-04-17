package Utilities;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;

public class GenderButtonsListener implements ItemListener {
	
	private String selectedGenderText;
	 
    public void itemStateChanged(ItemEvent ev) {
        boolean selected = (ev.getStateChange() == ItemEvent.SELECTED);
        AbstractButton button = (AbstractButton) ev.getItemSelectable();
        if (selected) {
           this.selectedGenderText = button.getText();
        } 
    }
}
