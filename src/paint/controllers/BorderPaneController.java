
package paint.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import paint.DTO.FormatingData;
import paint.models.ShapePicaso;

/**
 * CS 221 : Object Oriented Programming.
 * Assignment 2 : Picaso Paint Application (PPA)
 * @author Bishoy Nader & Marc Magdi
 * November 2016
 */
public class BorderPaneController implements Initializable {

    /**
     * menuController reference.
     */
    @FXML
    MenuController menuController;

    /**
     * menuController reference.
     */
    @FXML
    ShapesPanelController shapesPanelController;

    /**
     * menuController reference.
     */
    @FXML
    DrawingAreaController drawingAreaController;

    /**
     * menuController reference.
     */
    @FXML
    ControllersController controllersController;

    /**
     * menuController reference.
     */
    @FXML
    FormattingPanelController formatingPanelController;

    /**
     * initialization.
     */
    @Override
    public final void initialize(final URL location,
            final ResourceBundle resources) {
        shapesPanelController.init(this);
        drawingAreaController.init(this);
        controllersController.init(this);
        formatingPanelController.init(this);
        menuController.init(this);
    }

    /**
     * set the active class for drawing.
     * @param currentlyBeingDrawed the class of the shape
     * @throws Exception unable to add class.
     */
    public final void setActiveClass(
            final Class currentlyBeingDrawed) throws Exception {
        drawingAreaController.setCurrentlyDrawnShape(currentlyBeingDrawed);
    }

    /**
     * pass operation to be performed.
     * @param currentState new operation.
     */
    public final void passCurrentState(final int currentState) {
        drawingAreaController.setCurrentState(currentState);
    }

    /**
     * get formating data for drawing.
     * @return formating data for drawing.
     */
    public final FormatingData getFormatingDataFromFormatingPanel() {
        return formatingPanelController.getFormatingData();
    }

    /**
     * undo.
     */
    public final void undo() {
        drawingAreaController.undo();
    }

    /**
     * redo.
     */
    public final void redo() {
        drawingAreaController.redo();
    }

    /**
     * add shapes to shapes panel.
     * @param newShapeClasses list of classes.
     */
    public final void addNewClasses(final List<Class<?>> newShapeClasses) {
        this.shapesPanelController.addShapes(newShapeClasses);
    }

    /**
     * Get the all shapes for saving them.
     * @return list of all current shapes
     */
    public final List<ShapePicaso> getAllShapes() {
        return this.drawingAreaController.getAllShapes();
    }

    /**
     * load shapes to drawing area.
     * @param shapes list of shapes to be drawn
     */
    public final void loadShapes(final List<ShapePicaso> shapes) {
        drawingAreaController.loadShapesFromFile(shapes);
    }
}
