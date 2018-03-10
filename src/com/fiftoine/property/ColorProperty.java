package com.fiftoine.property;

import javafx.beans.property.ObjectPropertyBase;
import javafx.scene.paint.Color;

/**
 * Javafx property for a Color
 */
public class ColorProperty extends ObjectPropertyBase<Color> {
    @Override
    public Object getBean() {
        return this;
    }

    @Override
    public String getName() {
        return "Color";
    }
}
