package paint.saveManager;

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
public class SavableShapes {

    /**
     * list of savabale shapes.
     */
    private List<SavableShape> shapes;

    /**
     * Default constructor.
     */
    public SavableShapes() {
    }

    /**
     * Overloaded constructor.
     * @param shapesParam list of savabale shapes.
     */
    public SavableShapes(final List<SavableShape> shapesParam) {
        this.setShapesList(shapesParam);
    }

    @XmlElement(name = "ShapePica")
    public void setShapesList(List<SavableShape> shapesList) {
        this.shapes = shapesList;
    }

    public List<SavableShape> getShapesList() {
        return this.shapes;
    }
}
