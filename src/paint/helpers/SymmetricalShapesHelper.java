
package paint.helpers;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import paint.DTO.SymmetricCoordinates;

/**
 * CS 221 : Object Oriented Programming.
 * Assignment 2 : Picaso Paint Application (PPA)
 * @author Bishoy Nader & Marc Magdi
 * November 2016
 */
public abstract class SymmetricalShapesHelper {

    /**
     * Get the coordinates for fixed and relative points in first quadrant.
     * @param diffX the horizontal difference between the two points
     * @param diffY the vertical difference between the two points
     * @param fixedPoint main point in drawing the shape.
     * @param relativePoint secondary point in drawing the shape.
     * @return SymmetricCoordinates object carrying the modified coordinates.
     */
    public static SymmetricCoordinates getFirstQuadrandCoordinates(
            final int diffX, final int diffY,
            final Point fixedPoint, final Point relativePoint) {
        SymmetricCoordinates symCoordinates = new SymmetricCoordinates();
        symCoordinates.setMinX(fixedPoint.x);
        symCoordinates.setMinY(fixedPoint.y);
        symCoordinates.setMaxX(fixedPoint.x + Math.min(diffX, diffY));
        symCoordinates.setMaxY(fixedPoint.y + Math.min(diffX, diffY));
        return symCoordinates;
    }

    /**
     * Get the coordinates for fixed and relative points in second quadrant.
     * @param diffX the horizontal difference between the two points
     * @param diffY the vertical difference between the two points
     * @param fixedPoint main point in drawing the shape.
     * @param relativePoint secondary point in drawing the shape.
     * @return SymmetricCoordinates object carrying the modified coordinates.
     */
    public static SymmetricCoordinates getSecondQuadrandCoordinates(
            final int diffX, final int diffY,
            final Point fixedPoint, final Point relativePoint) {
        SymmetricCoordinates symCoordinates = new SymmetricCoordinates();
        int diff = Math.min(Math.abs(diffX), diffY);
        symCoordinates.setMinX(fixedPoint.x - diff);
        symCoordinates.setMinY(fixedPoint.y);
        symCoordinates.setMaxX(fixedPoint.x);
        symCoordinates.setMaxY(fixedPoint.y + diff);
        return symCoordinates;
    }

    /**
     * Get the coordinates for fixed and relative points in third quadrant.
     * @param diffX the horizontal difference between the two points
     * @param diffY the vertical difference between the two points
     * @param fixedPoint main point in drawing the shape.
     * @param relativePoint secondary point in drawing the shape.
     * @return SymmetricCoordinates object carrying the modified coordinates.
     */
    public static SymmetricCoordinates getThirdQuadrandCoordinates(
            final int diffX, final int diffY,
            final Point fixedPoint, final Point relativePoint) {
        SymmetricCoordinates symCoordinates = new SymmetricCoordinates();
        symCoordinates.setMinX(fixedPoint.x + Math.max(diffX, diffY));
        symCoordinates.setMinY(fixedPoint.y + Math.max(diffX, diffY));
        symCoordinates.setMaxX(fixedPoint.x);
        symCoordinates.setMaxY(fixedPoint.y);
        return symCoordinates;
    }

    /**
     * Get the coordinates for fixed and relative points in forth quadrant.
     * @param diffX the horizontal difference between the two points
     * @param diffY the vertical difference between the two points
     * @param fixedPoint main point in drawing the shape.
     * @param relativePoint secondary point in drawing the shape.
     * @return SymmetricCoordinates object carrying the modified coordinates.
     */
    public static SymmetricCoordinates getForthQuadrandCoordinates(
            final int diffX, final int diffY,
            final Point fixedPoint, final Point relativePoint) {
        SymmetricCoordinates symCoordinates = new SymmetricCoordinates();
        int diff = Math.min(Math.abs(diffY), diffX);
        symCoordinates.setMinX(fixedPoint.x);
        symCoordinates.setMinY(fixedPoint.y - diff);
        symCoordinates.setMaxX(fixedPoint.x + diff);
        symCoordinates.setMaxY(fixedPoint.y);
        return symCoordinates;
    }
}
