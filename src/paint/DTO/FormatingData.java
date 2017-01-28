
package paint.DTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
import javafx.scene.paint.Color;

/**
 * CS 221 : Object Oriented Programming.
 * Assignment 2 : Picaso Paint Application (PPA)
 * @author Bishoy Nader & Marc Magdi
 * November 2016
 */
@XmlRootElement
public class FormatingData {

    /**
     * Default constructor.
     */
    public FormatingData() {

    }

    /**.
     * The size of the stroke around the shape
     */
    public int strokeSize;

    /**.
     * The color of the stroke around the shape
     */
    public Color strokeColor;

    /**.
     * The color of the shape.
     */
    public Color fillColor;

    public int getStrokeSize() {
        return strokeSize;
    }

    @XmlElement
    public void setStrokeSize(final int strokeSizeParam) {
        this.strokeSize = strokeSizeParam;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    @XmlElement
    public void setStrokeColor(final Color strokeColorParam) {
        this.strokeColor = strokeColorParam;
    }

    public Color getFillColor() {
        return fillColor;
    }

    @XmlElement
    public void setFillColor(final Color fillColorParam) {
        this.fillColor = fillColorParam;
    }
}