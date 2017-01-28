package paint.saveManager;

import java.util.ArrayList;
import java.util.List;

import paint.models.ShapePicaso;

/**
 * CS 221 : Object Oriented Programming.
 * Assignment 2 : Picaso Paint Application (PPA)
 * @author Bishoy Nader & Marc Magdi
 * November 2016
 */
public class SaveHelper {

    /**
     * serializes the list of shapePicaso to a list of savable objects.
     * @param shapes ShapePicaso list.
     * @return list of savable objects.
     */
    public final List<SavableShape> getSerializedList(
            final List<ShapePicaso> shapes) {
        List<SavableShape> savableShapes = new ArrayList<SavableShape>();
        for (int i = 0; i < shapes.size(); i++) {
            savableShapes.add(shapes.get(i).getSavableShape());
        }

        return savableShapes;
    }
}
