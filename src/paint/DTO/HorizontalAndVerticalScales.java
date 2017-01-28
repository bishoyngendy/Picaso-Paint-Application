
package paint.DTO;

/**
 * CS 221 : Object Oriented Programming.
 * Assignment 2 : Picaso Paint Application (PPA)
 * @author Bishoy Nader & Marc Magdi
 * November 2016
 */
public class HorizontalAndVerticalScales {

    /**
     * horizontal scale.
     */
    private double scaleX;

    /**
     * vertical scale.
     */
    private double scaleY;

    /**
     * default constructor.
     */
    public HorizontalAndVerticalScales() {

    }

    /**
     * rich constructor.
     * @param scaleXParam horizontal scale.
     * @param scaleYParam vertical scale.
     */
    public HorizontalAndVerticalScales(final double scaleXParam,
                                final double scaleYParam) {
        super();
        this.scaleX = scaleXParam;
        this.scaleY = scaleYParam;
    }

    public double getScaleX() {
        return scaleX;
    }

    public void setScaleX(final double scaleXParam) {
        this.scaleX = scaleXParam;
    }

    public double getScaleY() {
        return scaleY;
    }

    public void setScaleY(final double scaleYParam) {
        this.scaleY = scaleYParam;
    }
}
