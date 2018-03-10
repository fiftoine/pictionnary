package com.fiftoine.ui.components;

import com.fiftoine.model.DrawingInfos;
import com.fiftoine.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller of the DrawingPaneControl component
 */
public class DrawingPaneControlController implements Initializable {

    /**
     * Name of the file to save to and load from DrawingInfos
     */
    private static final String DRAWINGINFOS_FILENAME = "drawinginfos.serial";

    /**
     * Controller of the sub component Drawing Pane
     */
    @FXML
    private DrawingPaneController drawingPaneController;

    /**
     * Textfield for the thickness of the line to draw
     */
    @FXML
    private TextField thicknessInput;

    /**
     * ColorPicker to choose the color of the line to draw
     */
    @FXML
    private ColorPicker colorPicker;

    /**
     * Button to clear the drawings
     */
    @FXML
    private Button clearButton;

    /**
     * Button to save the current drawing to file
     */
    @FXML
    private Button saveButton;

    /**
     * Button to load the drawing from the file and draw it on the canvas
     */
    @FXML
    private Button loadButton;


    /**
     * JavaFx initialization
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colorPicker.setValue(Color.BLACK);
        thicknessInput.setText("1");
        drawingPaneController.colorProperty().bind(colorPicker.valueProperty());
        thicknessInput.textProperty().addListener((v, o, n)-> {
            drawingPaneController.setThickness(Integer.valueOf(n));
        });
        clearButton.setOnAction(event -> {
            drawingPaneController.clearPane();
        });

        saveButton.setOnAction(event -> {
            this.saveDrawingInfos();
        });

        loadButton.setOnAction(event -> {
            this.loadDrawingInfos();
        });

        //Initial thickness set to 1

    }

    /**
     * Save the current drawing on the Drawing Pane component to a file
     */
    private void saveDrawingInfos(){
        FileUtils.saveDrawingInfosToFile(this.drawingPaneController.getDrawingInfos(), DRAWINGINFOS_FILENAME);
    }

    /**
     * Load drawing from a file and communication information to the Drawing Pane component
     */
    private void loadDrawingInfos(){
        DrawingInfos drawingInfos = FileUtils.loadDrawingInfosFromFile(DRAWINGINFOS_FILENAME);
        this.drawingPaneController.setDrawingInfos(drawingInfos);
    }
}
