package com.fiftoine.ui.components;

import com.fiftoine.model.DrawingInfos;
import com.fiftoine.model.Line;
import com.fiftoine.model.Vertice;
import com.fiftoine.property.ColorProperty;
import com.fiftoine.property.ThicknessProperty;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller of the Drawing Pane component
 */
public class DrawingPaneController implements IDrawing, Initializable {

    /**
     * Canvas component
     */
    @FXML
    private Canvas canvas;

    /**
     * Graphic context of the canvas
     */
    private GraphicsContext graphicsContext;

    /**
     * Autorise (or not) the edition of the drawing
     */
    private boolean updatable;

    /**
     * Drawing info of the current drawing on the canevas
     */
    private DrawingInfos drawingInfos;

    /**
     * Current line to add vertices to on the canvas
     */
    private Line currentLine;

    /**
     * Color property of the line to draw
     */
    private ColorProperty colorProperty;
    /**
     * Thickness property of the line to draw
     */
    private ThicknessProperty thicknessProperty;


    public DrawingPaneController() {
        this.drawingInfos = new DrawingInfos();
        this.colorProperty = new ColorProperty();


        this.thicknessProperty = new ThicknessProperty();
        this.initialize();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        graphicsContext = canvas.getGraphicsContext2D();
        thicknessProperty.addListener((v,o,n)->{
            graphicsContext.setLineWidth(n);
        });
        colorProperty.addListener((v, o, n)->{
            graphicsContext.setStroke(n);
        });

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {

            if(updatable){
                graphicsContext.beginPath();
                graphicsContext.moveTo(e.getX(), e.getY());
                this.currentLine = new Line(this.getThickness(), this.getColor());
                this.currentLine.addVertice(e.getX(), e.getY());
                this.drawingInfos.add(currentLine);
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent e) -> {

            if (updatable){
                graphicsContext.lineTo(e.getX(), e.getY());
                graphicsContext.stroke();
                this.currentLine.addVertice(e.getX(), e.getY());
            }


        });




    }

    /**
     * Set an initial value to all attributes.
     */
    @Override
    public void initialize() {
        this.updatable = true;
        this.setColor(Color.BLACK);
        this.setThickness(1);
    }


    /**
     * Clear the drawing pane.
     */
    @Override
    public void clearPane() {
        //Delete everything on the canvas
        this.graphicsContext.clearRect(0,0,canvas.getWidth(), canvas.getHeight());
        //Reset the drawing infos
        this.drawingInfos = new DrawingInfos();
        this.updatable = true;
    }

    /**
     * Return data needed to draw. These datas can be a set of points with color
     * and thickness.
     *
     * @return data needed to draw
     */
    @Override
    public DrawingInfos getDrawingInfos() {
        return this.drawingInfos;
    }

    /**
     * Set all the data needed to draw a picture a component.
     *
     * @param dInfos data needed to draw a picture a component (can be a set of
     * points with color and thickness)
     */
    @Override
    public void setDrawingInfos(DrawingInfos dInfos) {
        //First clear the canvas then draw the infos
        this.clearPane();
        this.drawingInfos = dInfos;
        this.draw();
        this.updatable = false;
    }


    /**
     * Draw the drawingInfos on the canvas
     *
     * Draw each lines by
     *  - Setting the color and the width
     *  - Drawing lines between vertices of the line
     *
     */
    private void draw(){

        for (Line line: drawingInfos.getLines()) {

            graphicsContext.setStroke(line.getColor());
            graphicsContext.setLineWidth(line.getThickness());
            graphicsContext.beginPath();
            graphicsContext.moveTo(line.getFirstVertice().getX(), line.getFirstVertice().getY());
            for(Vertice vertice: line.getAllButFirstVertice()){
                graphicsContext.lineTo(vertice.getX(), vertice.getY());
                graphicsContext.stroke();
            }
        }
    }


    /**
     * Return the colorProperty of the drawing line.
     *
     * @return the colorProperty of the drawing line
     */
    @Override
    public ObjectProperty<Color> colorProperty() {
        return this.colorProperty;
    }
    /**
     * Set the color value of the drawing line.
     *
     * @param color color value of the drawing line
     */
    @Override
    public void setColor(Color color) {
        this.colorProperty.setValue(color);
    }

    /**
     * Return the color value of the drawing line.
     *
     * @return the color value of the drawing line
     */
    @Override
    public Color getColor() {
        return this.colorProperty.getValue();
    }
    /**
     * Return the thicknessProperty of the drawing line.
     *
     * @return the thicknessProperty of the drawing line
     */
    @Override
    public ObjectProperty<Integer> thicknessProperty() {
        return this.thicknessProperty;
    }

    /**
     * Set the thickness value of the drawing line.
     *
     * @param thickness thickness value of the drawing line
     */
    @Override
    public void setThickness(int thickness) {
        this.thicknessProperty.setValue(thickness);
    }

    /**
     * Return the thickness value of the drawing line.
     *
     * @return the thickness value of the drawing line
     */
    @Override
    public int getThickness() {
        return this.thicknessProperty.getValue();
    }
}

