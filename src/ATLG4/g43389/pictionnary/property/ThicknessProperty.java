package ATLG4.g43389.pictionnary.property;

import javafx.beans.property.ObjectPropertyBase;

/**
 * Javafx property for a Thickness (Integer)
 */
public class ThicknessProperty extends ObjectPropertyBase<Integer> {
    @Override
    public Object getBean() {
        return this;
    }

    @Override
    public String getName() {
        return "Thickness";
    }
}
