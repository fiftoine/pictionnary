package ATLG4.g43389.pictionnary.model;


import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * One line on a drawing
 * Consist of a color, a thickness and a list of vertices
 *
 * Drawing ease methods are present (first vertice, all but first vertices)
 *
 * As Color is not serializable, writeObject and readObject have been implemented
 * Color is serialized splitting it (red, green, blue, opacity)
 */
public class Line implements Serializable {

    /**
     * Thickness of the line
     */
    private int thickness;

    /**
     * Color of the line
     */
    private transient  Color color;

    /**
     * List of vertices of the line
     */
    private List<Vertice> vertices;


    /**
     * Creates an instance of a Line.
     * @param thickness the thickness of the line.
     * @param color the color of the line.
     */
    public Line(int thickness, Color color) {

        this.thickness = thickness;
        this.color = color;
        this.vertices = new ArrayList<>();

    }

    /**
     * Returns the thickness of the line.
     * @return the thickness of the line.
     */
    public int getThickness() {
        return thickness;
    }



    /**
     * Returns the color of the line.
     * @return the color of the line.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the list of vertices of the line
     * @return the list of vertices of the line
     */
    public List<Vertice> getVertices() {
        return vertices;
    }

    /**
     * Add a vertice to the line
     * @param vi vertice to add to the line
     */
    public void addVertice(Vertice vi){
        this.vertices.add(vi);
    }

    public void addVertice(double x, double y){
        this.addVertice(new Vertice(x, y));
    }

    /**
     * Returns the first vertice of the line
     * @return the first vertice of the line
     */
    public Vertice getFirstVertice(){
        return this.getVertices().get(0);
    }

    /**
     * Returns all vertices but the first one
     * @return all vertices but the first one
     */
    public List<Vertice> getAllButFirstVertice(){
        return this.getVertices().stream().skip(1).collect(Collectors.toList());
    }


    @Override
    public String toString() {
        return "LineInfo{" +
                "thickness=" + thickness +
                ", color=" + color +
                ", vertices=" + vertices +
                '}';
    }


    private void readObject(final ObjectInputStream ois) throws IOException,
            ClassNotFoundException {

        this.thickness = ois.readInt();
        double red = ois.readDouble();
        double green = ois.readDouble();
        double blue = ois.readDouble();
        double opacity = ois.readDouble();
        this.color = new Color(red, green, blue, opacity);
        this.vertices = (ArrayList) ois.readObject();

    }

    private void writeObject(final ObjectOutputStream oos) throws IOException {

        oos.writeInt(this.thickness);
        oos.writeDouble(this.color.getRed());
        oos.writeDouble(this.color.getGreen());
        oos.writeDouble(this.color.getBlue());
        oos.writeDouble(this.color.getOpacity());
        oos.writeObject(this.vertices);
    }


}
