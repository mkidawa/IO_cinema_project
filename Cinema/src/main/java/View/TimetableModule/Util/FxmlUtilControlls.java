package View.TimetableModule.Util;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class FxmlUtilControlls {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/

    /**
     * METHOD CHECKS IF SPINNERS ARE FILLED IF SO RETURN TRUE, IF NOT RETURN FALSE
     *
     * @param spinners
     * @return
     */
    public boolean isSpinnersFilled(Spinner... spinners) {
        for (Spinner it : spinners) {
            if (it.getValue() == null) {
                return false;
            }
        }

        return true;
    }

    /**
     * CHECK IF EXACTLY ONE RADIO BUTTON IS SELECTED
     *
     * @param radioButtons
     * @return - IF ONE RETURN TRUE, ELSE RETURN FALSE
     */
    public boolean isOneRadioButtonSelected(RadioButton... radioButtons) {
        int numberOfSelectedRadioButtons = 0;

        for (RadioButton it : radioButtons) {
            if (it.isSelected()) {
                numberOfSelectedRadioButtons++;
            }
        }

        if (numberOfSelectedRadioButtons == 1) {
            return true;
        }

        return false;
    }

    /**
     * CHECKS IF COMBOBOXES ARE FILLED
     *
     * @param comboBoxes
     * @return
     */
    public boolean isComboBoxesFilled(ComboBox... comboBoxes) {
        for (ComboBox it : comboBoxes) {
            if (it.getValue() == null) {
                return false;
            }
        }

        return true;
    }

    /**
     * METHOD SET FLAG VALUE, IF SELECTED SET VALUE TO ONE, IF NOT TO 0
     *
     * @param radioButton
     * @return
     */
    public short setFlagValue(RadioButton radioButton) {
        if (radioButton.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * METHOD SET MIN, MAX, INITIAL VALUE AND SET STEP IN SPINNER
     *
     * @param spinner
     * @param minValue
     * @param maxValue
     * @param initialValue
     * @param step
     */
    public void setIntegerSpinner(Spinner spinner, Integer minValue,
                                  Integer maxValue, Integer initialValue, Integer step) {
        spinner.setValueFactory(new SpinnerValueFactory
                .IntegerSpinnerValueFactory(minValue, maxValue, initialValue, step));
    }

    /**
     * METHOD SET CHECKBOX DEPENDING ON THE VALUE OF FIRST PARAMETER - short flag
     *
     * @param flag
     * @param checkBox
     */
    public void setCheckBox(short flag, CheckBox checkBox) {
        if (flag > 0) {
            checkBox.setSelected(true);
        } else {
            checkBox.setSelected(false);
        }
    }
}
