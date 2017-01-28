package paint.DTO;

/**
 * CS 221 : Object Oriented Programming.
 * Assignment 2 : Picaso Paint Application (PPA)
 * @author Bishoy Nader & Marc Magdi
 * November 2016
 */
public class SymmetricCoordinates {

    /**
     * X Coordinate of the fixed point.
     */
    private int minX;

    /**
     * Y Coordinate of the fixed point.
     */
    private int minY;

    /**
     * X Coordinate of the relative point.
     */
    private int maxX;

    /**
     * Y Coordinate of the relative point.
     */
    private int maxY;

    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMinX(final int minXParam) {
        this.minX = minXParam;
    }

    public void setMinY(final int minYParam) {
        this.minY = minYParam;
    }

    public void setMaxX(final int maxXParam) {
        this.maxX = maxXParam;
    }

    public void setMaxY(final int maxYParam) {
        this.maxY = maxYParam;
    }

    /**
     * Overloaded Constructor.
     * @param minXParam X Coordinate of the fixed point.
     * @param minYParam Y Coordinate of the fixed point.
     * @param maxXParam X Coordinate of the relative point.
     * @param maxYParam Y Coordinate of the relative point.
     */
    public SymmetricCoordinates(final int minXParam, final int minYParam,
            final int maxXParam, final int maxYParam) {
        this.minX = minXParam;
        this.minY = minYParam;
        this.maxX = maxXParam;
        this.maxY = maxYParam;
    }

    /**
     * Default Constructor.
     */
    public SymmetricCoordinates() {
    }
}
