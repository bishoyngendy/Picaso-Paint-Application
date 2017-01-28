
package paint.models;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.StrokeLineCap;
import paint.DTO.FormatingData;

/**
 * CS 221 : Object Oriented Programming.
 * Assignment 2 : Picaso Paint Application (PPA)
 * @author Bishoy Nader & Marc Magdi
 * November 2016
 */
public class ElipseShape extends ShapePicaso {

    /**
     * The ellipse object.
     */
    protected Ellipse ellipse;

    /**
     * Center of the ellipse.
     */
    private Point center;

    /**
     * horizontal radius of the ellipse.
     */
    private double radiusX;

    /**
     * vertical radius of the ellipse.
     */
    private double radiusY;

    /**
     * Default Constructor.
     */
    public ElipseShape() {
        this.minimumNumberOfVericesToDraw = 2;
        ellipse = new Ellipse();
        shape = ellipse;
        center = new Point();

        ellipse.setStroke(Color.FORESTGREEN);
        ellipse.setStrokeWidth(4);
        ellipse.setStrokeLineCap(StrokeLineCap.ROUND);
        ellipse.setFill(Color.BLUE.deriveColor(0, 1.2, 1, 0.6));
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getRadiusX() {
        return radiusX;
    }

    public void setRadiusX(double radiusX) {
        this.radiusX = radiusX;
    }

    public double getRadiusY() {
        return radiusY;
    }

    public void setRadiusY(double radiusY) {
        this.radiusY = radiusY;
    }

    @Override
    public Node getDrawableShape(
            final Point point, final FormatingData formats) {

        Point center = new Point();

        center.x = (int) point.getX();
        center.y = (int) point.getY();
        this.setFormats(formats);
        ellipse.setStroke(formats.getStrokeColor());
        ellipse.setStrokeWidth(formats.getStrokeSize());
        ellipse.setStrokeLineCap(StrokeLineCap.ROUND);
        ellipse.setFill(formats.getFillColor());

        this.updateEllipseRadiusCenter(0, 0, center);
        return this.ellipse;
    }

    @Override
    public void updateDrawableShape(final Point point, final int index) {
        int x = (int) (2 * ellipse.getCenterX() - point.getX());
        int y = (int) (2 * ellipse.getCenterY() - point.getY());

        Point fixedPoint = new Point(x, y);

        Point relativePoint = new Point((int) point.getX(), (int) point.getY());
        Point ellipseCenter = new Point();

        double radiusX = (Math.abs(fixedPoint.getX()
                - relativePoint.getX()) / 2);
        double radiusY = (Math.abs(fixedPoint.getY()
                - relativePoint.getY()) / 2);

        ellipseCenter.x = (int) (Math.min(fixedPoint.getX(),
                relativePoint.getX()) + radiusX);
        ellipseCenter.y = (int) (Math.min(fixedPoint.getY(),
                relativePoint.getY()) + radiusY);

        this.updateEllipseRadiusCenter(radiusX, radiusY, ellipseCenter);
    }

    /**
     * update ellipse center while drawing.
     * @param radiusXParam horizontal radius
     * @param radiusYParam vertical radius
     * @param centerParam reference to the center
     */
    private void updateEllipseRadiusCenter(final double radiusXParam, final double radiusYParam,
            final Point centerParam) {
        ellipse.setRadiusX(radiusXParam);
        ellipse.setRadiusY(radiusYParam);
        ellipse.setCenterX(centerParam.getX());
        ellipse.setCenterY(centerParam.getY());
    }

    @Override
    public ObservableList<Double> getCorners() {
        ObservableList<Double> points = getObservableDoubleList();
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

    /**
     * convert ellipse corners to observable double list.
     * @return ellipse corners to observable double list.
     */
    private ObservableList<Double> getObservableDoubleList() {
        ObservableList<Double> corners = FXCollections.observableArrayList();

        double minX = ellipse.getCenterX() - ellipse.getRadiusX();
        double minY = ellipse.getCenterY() - ellipse.getRadiusY();
        double maxX = ellipse.getCenterX() + ellipse.getRadiusX();
        double maxY = ellipse.getCenterY() + ellipse.getRadiusY();

        corners.addAll(minX, minY, maxX, minY, maxX, maxY, minX, maxY);

        return corners;
    }

    @Override
    public List<Double> getPoints() {
        List<Double> points = new ArrayList<>();
        points.add(ellipse.getCenterX());
        points.add(ellipse.getCenterY());
        points.add(ellipse.getCenterX() + ellipse.getRadiusX());
        points.add(ellipse.getCenterY() + ellipse.getRadiusY());
        return points;
    }

    @Override
    public void resizeDragging(final double x, final double y) {
        double scaleX = resizeRectangle.getScaleX();
        double scaleY = resizeRectangle.getScaleY();
        if (getxLSetted()) {
            double deltaX = x - getPrevX();
            double newScale = (scaleX
                    * ((resizeRectangle.getLayoutBounds().getWidth() / 2)
                            - deltaX));
            newScale /= (resizeRectangle.getLayoutBounds().getWidth() / 2);
            shape.setScaleX(newScale);
            resizeRectangle.setScaleX(newScale);
        }
        if (getxRSetted()) {
            double deltaX = x - getPrevX();
            double newScale = (scaleX
                    * ((resizeRectangle.getLayoutBounds().getWidth() / 2)
                            + deltaX));
            newScale /= (resizeRectangle.getLayoutBounds().getWidth() / 2);
            shape.setScaleX(newScale);
            resizeRectangle.setScaleX(newScale);
        }
        if (getyBSetted()) {
            double deltaY = y - getPrevY();
            double newScale = (scaleY
                    * ((resizeRectangle.getLayoutBounds().getHeight() / 2)
                            - deltaY));
            newScale /= (resizeRectangle.getLayoutBounds().getHeight() / 2);
            shape.setScaleY(newScale);
            resizeRectangle.setScaleY(newScale);
        }
        if (getyTSetted()) {
            double deltaY = y - getPrevY();
            double newScale = (scaleY
                    * ((resizeRectangle.getLayoutBounds().getHeight() / 2)
                            + deltaY));
            newScale /= (resizeRectangle.getLayoutBounds().getHeight() / 2);
            shape.setScaleY(newScale);
            resizeRectangle.setScaleY(newScale);
        }
    }
}