package paint.models;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import paint.DTO.SymmetricCoordinates;
import paint.helpers.SymmetricalShapesHelper;

/**
 * CS 221 : Object Oriented Programming.
 * Assignment 2 : Picaso Paint Application (PPA)
 * @author Bishoy Nader & Marc Magdi
 * November 2016
 */
public class CircleShape extends ElipseShape {

    /**
     * Default constructor.
     */
    public CircleShape() {

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

        double minRadius = Math.min(radiusX, radiusY);
        this.updateEllipseRadiusCenter(minRadius, minRadius, ellipseCenter);
    }

    /**
     * update ellipse center while drawing.
     * @param radiusXParam horizontal radius
     * @param radiusYParam vertical radius
     * @param centerParam reference to the center
     */
    private void updateEllipseRadiusCenter(
            final double radiusXParam, final double radiusYParam,
            final Point centerParam) {
        ellipse.setRadiusX(radiusXParam);
        ellipse.setRadiusY(radiusYParam);
        ellipse.setCenterX(centerParam.getX());
        ellipse.setCenterY(centerParam.getY());
    }

    @Override
    public void resizeDragging(final double x, final double y) {
        double scaleX = resizeRectangle.getScaleX();
        double scaleY = resizeRectangle.getScaleY();
        double newScale = 0;
        if (getxLSetted()) {
            double deltaX = x - getPrevX();
            newScale = (scaleX * ((resizeRectangle.getLayoutBounds()
                    .getWidth() / 2) - deltaX));
            newScale /= (resizeRectangle.getLayoutBounds().getWidth() / 2);
        } else if (getxRSetted()) {
            double deltaX = x - getPrevX();
            newScale = (scaleX * ((resizeRectangle.getLayoutBounds()
                    .getWidth() / 2) + deltaX));
            newScale /= (resizeRectangle.getLayoutBounds().getWidth() / 2);
        } else if (getyBSetted()) {
            double deltaY = y - getPrevY();
            newScale = (scaleY * ((resizeRectangle.getLayoutBounds()
                    .getHeight() / 2) - deltaY));
            newScale /= (resizeRectangle.getLayoutBounds().getHeight() / 2);
        } else if (getyTSetted()) {
            double deltaY = y - getPrevY();
            newScale = (scaleY * ((resizeRectangle.getLayoutBounds()
                    .getHeight() / 2) + deltaY));
            newScale /= (resizeRectangle.getLayoutBounds().getHeight() / 2);
        }
        shape.setScaleX(newScale);
        shape.setScaleY(newScale);
        resizeRectangle.setScaleX(newScale);
        resizeRectangle.setScaleY(newScale);
    }
}
