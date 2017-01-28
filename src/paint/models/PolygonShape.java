
package paint.models;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import paint.DTO.FormatingData;
import paint.DTO.SceneCoordinates;

/**
 * CS 221 : Object Oriented Programming.
 * Assignment 2 : Picaso Paint Application (PPA)
 * @author Bishoy Nader & Marc Magdi
 * November 2016
 */
public abstract class PolygonShape extends ShapePicaso {

    /**
     * The polygon object.
     */
    protected Polygon polygon;

    /**
     * constructor.
     */
    public PolygonShape() {
        polygon = new Polygon();
        shape = polygon;
    }

    @Override
    public final Node getDrawableShape(final Point point,
            final FormatingData formats) {
        this.addPointsToShape(point);
        this.setFormats(formats);
        polygon.setStroke(formats.getStrokeColor());
        polygon.setStrokeWidth(formats.getStrokeSize());
        polygon.setStrokeLineCap(StrokeLineCap.ROUND);
        polygon.setFill(formats.getFillColor());

        return this.polygon;
    }

    /**
     * add new point to polygon.
     * @param point new point
     */
    private void addPointsToShape(final Point point) {
        for (int i = 0; i < this.minimumNumberOfPointsToDraw; i++) {
            this.polygon.getPoints().addAll(point.getX(), point.getY());
        }
    }

    @Override
    public void updateDrawableShape(final Point point, final int index) {
        this.polygon.getPoints().set(index, point.getX());
        this.polygon.getPoints().set(index + 1, point.getY());
    }

    @Override
    public final ObservableList<Double> getCorners() {
        ObservableList<Double> points = polygon.getPoints();

        ObservableList<Double> corners = FXCollections.observableArrayList();

        double minX = points.get(0), minY = points.get(1);
        double maxX = points.get(0), maxY = points.get(1);

        for (int i = 2; i < points.size(); i += 2) {
            minX = Math.min(minX, points.get(i));
            maxX = Math.max(maxX, points.get(i));
            minY = Math.min(minY, points.get(i + 1));
            maxY = Math.max(maxY, points.get(i + 1));
        }
        corners.addAll(minX, minY, maxX, minY, maxX, maxY, minX, maxY);
        return corners;
    }

    @Override
    public List<Double> getPoints() {
        return polygon.getPoints();
    }

    @Override
    public void resizeDragging(final double x, final double y) {
        double scaleX = resizeRectangle.getScaleX();
        double scaleY = resizeRectangle.getScaleY();
        if (getxLSetted()) {
            double deltaX = x - getPrevX();
            double newScale =
                    (scaleX * ((resizeRectangle
                            .getLayoutBounds().getWidth() / 2) - deltaX));
            newScale /= (resizeRectangle.getLayoutBounds().getWidth() / 2);
            shape.setScaleX(newScale);
            resizeRectangle.setScaleX(newScale);
        }
        if (getxRSetted()) {
            double deltaX = x - getPrevX();
            double newScale =
                    (scaleX * ((resizeRectangle
                            .getLayoutBounds().getWidth() / 2) + deltaX));
            newScale /= (resizeRectangle.getLayoutBounds().getWidth() / 2);
            shape.setScaleX(newScale);
            resizeRectangle.setScaleX(newScale);
        }
        if (getyBSetted()) {
            double deltaY = y - getPrevY();
            double newScale =
                    (scaleY * ((resizeRectangle
                            .getLayoutBounds().getHeight() / 2) - deltaY));
            newScale /= (resizeRectangle.getLayoutBounds().getHeight() / 2);
            shape.setScaleY(newScale);
            resizeRectangle.setScaleY(newScale);
        }
        if (getyTSetted()) {
            double deltaY = y - getPrevY();
            double newScale =
                    (scaleY * ((resizeRectangle
                            .getLayoutBounds().getHeight() / 2) + deltaY));
            newScale /= (resizeRectangle.getLayoutBounds().getHeight() / 2);
            shape.setScaleY(newScale);
            resizeRectangle.setScaleY(newScale);
        }
    }
}
