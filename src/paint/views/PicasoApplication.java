
package paint.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * CS 221 : Object Oriented Programming.
 * Assignment 2 : Picaso Paint Application (PPA)
 * @author Bishoy Nader & Marc Magdi
 * November 2016
 */
public class PicasoApplication extends Application {
    /**
     * The main start function of the application.
     * @param args the arguments to start with
     */
    public static void main(final String[] args) {
        launch(args);
    }

    /**
     * . Connect the application with FXML file and sets its title
     */
    @Override
    public final void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("Picaso Dawing Application");
        Parent root = FXMLLoader.load(getClass().getResource("Ui.fxml"));

        this.fullScreenApp(primaryStage);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * . Start the application as full screen
     * @param primaryStage the Application stage to apply the new dimensions
     */
    private void fullScreenApp(final Stage primaryStage) {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
    }
}
