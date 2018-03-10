package ATLG4.g43389.pictionnary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Pictionnary extends Application {

    private static final String MAIN_VIEW_NAME = "ATLG4/g43389/pictionnary/views/main.fxml";

    private static final String APP_TITLE = "Pictionnary";

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(MAIN_VIEW_NAME));
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setScene(new Scene(root, 800, 650));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
