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
        //Initial thickness set to 1
        thicknessInput.setText("1");


        //Watch changes on the thickness input
        thicknessInput.textProperty().addListener((v, oldValue, newValue)->{
            //Remove all but numbers from the field
            if (!newValue.matches("[0-9]+")) {
                thicknessInput.setText(newValue.replaceAll("[^0-9]", ""));
            }
            //Propagate the input value to the Drawing Pane component
            if(!newValue.equals("")){
                drawingPaneController.setThickness(Integer.valueOf(thicknessInput.getText()));
            }
        });

        //Initial color to Black
        colorPicker.setValue(Color.BLACK);
        //Bind color picker value to the Drawing Pane component
        drawingPaneController.colorProperty().bind(colorPicker.valueProperty());

        //Handle clear button click
        clearButton.setOnAction(e->{
            drawingPaneController.clearPane();
        });

        //Handle Save button click
        saveButton.setOnAction(e->{
            this.saveDrawingInfos();
        });

        //Handle Load button click
        loadButton.setOnAction(e->{
            this.loadDrawingInfos();
        });
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
