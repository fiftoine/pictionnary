package com.fiftoine.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Model of a drawing on the canvas
 *
 * Consist of a list of lines
 *
 */
public class DrawingInfos implements Serializable{

    /**
     * List of lines on the drawing
     */
    private final List<Line> lines;

    /**
     * Creates an instance of DrawingInfos.
     */
    public DrawingInfos() {
        lines = new ArrayList<>();
    }

    /**
     * Adds a Line to the list.
     *
     * @param line a Line to be added.
     */
    public void add(Line line) {
        lines.add(line);
    }

    /**
     * Returns the list of LineInfos.
     *
     * @return the list of LineInfos.
     */
    public List<Line> getLines() {
        return lines;
    }

    /**
     * Clears the list of LineInfo's.
     */
    public void clear(){
        lines.clear();
    }

    @Override
    public String toString() {
        return "DrawingInfos{" +
                "infos=" + lines +
                '}';
    }
}
