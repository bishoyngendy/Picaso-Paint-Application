
package paint.models;

/**
 * CS 221 : Object Oriented Programming.
 * Assignment 2 : Picaso Paint Application (PPA)
 * @author Bishoy Nader & Marc Magdi
 * November 2016
 */
public class LineShape extends PolygonShape {

    /**
     * Default constructor.
     */
    public LineShape() {
        this.minimumNumberOfVericesToDraw = 2;
        this.minimumNumberOfPointsToDraw = 2;
    }

}
