package paint.DTO;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * CS 221 : Object Oriented Programming.
 * Assignment 2 : Picaso Paint Application (PPA)
 * @author Bishoy Nader & Marc Magdi
 * November 2016
 */
@XmlRootElement
public class SavableShape {

    /**
     * class name of shape.
     */
    public String className;

    /**
     * shape formating data.
     */
    public FormatingData formats;

    /**
     * drawing point of the shape.
     */
    public List<Double> points;

    /**
     * rich constructor.
     * @param className class name of shape.
     * @param formats shape formating data.
     */
    public SavableShape(final String className, final FormatingData formats) {
        super();
        this.className = className;
        this.formats = formats;
    }

    /**
     * Default constructor.
     */
    public SavableShape() {
    }

    @XmlElement
    public String getClassName() {
        return className;
    }

    public void setClassName(final String classNameParam) {
        this.className = classNameParam;
    }

    public FormatingData getFormats() {
        return formats;
    }

    @XmlElement
    public void setFormats(final FormatingData formatsParam) {
        this.formats = formatsParam;
    }

    @XmlElement
    public List<Double> getPoints() {
        return points;
    }

    public void setPoints(final List<Double> pointsParam) {
        this.points = pointsParam;
    }
}