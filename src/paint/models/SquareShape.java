
package paint.models;

import java.awt.Point;

/**
 * CS 221 : Object Oriented Programming.
 * Assignment 2 : Picaso Paint Application (PPA)
 * @author Bishoy Nader & Marc Magdi
 * November 2016
 */
public class SquareShape extends RectangleShape {

    /**
     * Empty Constructor.
     */
    public SquareShape() {

    }

    @Override
    public final void updateDrawableShape(final Point point, final int index) {
        this.updateShapeVertices(point, index);
    }

    /**
     * update vertices symmetrically while drawing the square.
     * @param point new point.
     * @param index index of the point in drawing
     */
    private void updateShapeVertices(final Point point, final int index) {

        double prevX = this.polygon.getPoints().get(index + 2);
        double prevY = this.polygon.getPoints().get(index + 3);
        double deltaX = point.getX() - prevX;
        double deltaY = point.getY() - prevY;
        double delta = Math.min(deltaX, deltaY);

        // index refers to the x of the second point
        this.polygon.getPoints().set(index,
                this.polygon.getPoints().get(index) + delta);

        // index refers to the x of the third point
        this.polygon.getPoints().set(index + 2,
                this.polygon.getPoints().get(index + 2) + delta);

        // index refers to the y of the third point
        this.polygon.getPoints().set(index + 3,
                this.polygon.getPoints().get(index + 3) + delta);

        // index refers to the y of the forth point
        this.polygon.getPoints().set(index + 5,
                this.polygon.getPoints().get(index + 5) + delta);
    }

    @Override
    public final void resizeDragging(final double x, final double y) {
        double scaleX = resizeRectangle.getScaleX();
        double scaleY = resizeRectangle.getScaleY();
        double newScale = 0;
        if (getxLSetted()) {
            double deltaX = x - getPrevX();
            newScale =
                (scaleX * ((resizeRectangle.getLayoutBounds().getWidth() / 2)
                        - deltaX));
            newScale /= (resizeRectangle.getLayoutBounds().getWidth() / 2);
        } else if (getxRSetted()) {
            double deltaX = x - getPrevX();
            newScale =
                (scaleX * ((resizeRectangle.getLayoutBounds().getWidth() / 2)
                        + deltaX));
            newScale /= (resizeRectangle.getLayoutBounds().getWidth() / 2);
        } else if (getyBSetted()) {
            double deltaY = y - getPrevY();
            newScale =
                (scaleY * ((resizeRectangle.getLayoutBounds().getHeight() / 2)
                    - deltaY));
            newScale /= (resizeRectangle.getLayoutBounds().getHeight() / 2);
        } else if (getyTSetted()) {
            double deltaY = y - getPrevY();
            newScale =
                (scaleY * ((resizeRectangle.getLayoutBounds().getHeight() / 2)
                        + deltaY));
            newScale /= (resizeRectangle.getLayoutBounds().getHeight() / 2);
        }
        shape.setScaleX(newScale);
        shape.setScaleY(newScale);
        resizeRectangle.setScaleX(newScale);
        resizeRectangle.setScaleY(newScale);
    }
}
