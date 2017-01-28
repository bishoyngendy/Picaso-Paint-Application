
package paint.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import paint.utilities.Constants;

/**
 * CS 221 : Object Oriented Programming.
 * Assignment 2 : Picaso Paint Application (PPA)
 * @author Bishoy Nader & Marc Magdi
 * November 2016
 */
public class ControllersController implements Initializable {

    /**
     * topmenu reference.
     */
    @FXML
    private VBox topMenu;

    /**
     * draw button.
     */
    @FXML
    private Button drawButton;

    /**
     * move button.
     */
    @FXML
    private Button moveButton;

    /**
     * resize button.
     */
    @FXML
    private Button resizeButton;

    /**
     * delete button.
     */
    @FXML
    private Button deleteButton;

    /**
     * undo button.
     */
    @FXML
    private Button undoButton;

    /**
     * redo button.
     */
    @FXML
    private Button redoButton;

    /**
     * . Reference to the parent controller, the Border Pane Controller
     */
    private BorderPaneController borderPaneController;

    /**
     * . set the controller to the main controller
     * @param borderPaneParam the main border controller
     */
    public final void init(final BorderPaneController borderPaneParam) {
        this.borderPaneController = borderPaneParam;
    }

    /**
     * panel initialization.
     */
    @Override
    public final void initialize(final URL location,
            final ResourceBundle resources) {
        drawButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                borderPaneController.passCurrentState(Constants.STATE_DRAWING);
            }
        });
        moveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                borderPaneController.passCurrentState(Constants.STATE_MOVING);
            }
        });
        resizeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                borderPaneController.passCurrentState(Constants.STATE_RESIZING);
            }
        });
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                borderPaneController.passCurrentState(Constants.STATE_DELETING);
            }
        });
        undoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                borderPaneController.undo();
            }
        });
        redoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                borderPaneController.redo();
            }
        });
    }
}
