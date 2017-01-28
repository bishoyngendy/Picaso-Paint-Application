
package paint.saveManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import paint.models.ShapePicaso;

/**
 * CS 221 : Object Oriented Programming.
 * Assignment 2 : Picaso Paint Application (PPA)
 * @author Bishoy Nader & Marc Magdi
 * November 2016
 */
public class JsonSaveLoad {

    /**
     * Save the current shapes to an Json file.
     * @param file the file to save to
     * @param shapes the shapes to save
     */
    public final void saveToFile(final File file, final SavableShapes shapes) {
        Gson gson = new Gson();
        String json = gson.toJson(shapes);

        try {
            PrintWriter writer = new PrintWriter(file);
            writer.println(json);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * load shapes from file.
     * @param file the file
     * @return the list of shapes.
     */
    public final List<ShapePicaso> loadFromFile(final File file) {
        SavableShapes shapes;

        FileReader fr;
        BufferedReader br;
        StringBuilder builder = new StringBuilder();
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                builder.append(line);
                line = br.readLine();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        Gson gson = new Gson();
        shapes = gson.fromJson(builder.toString(), SavableShapes.class);
        List<ShapePicaso> ret = new ArrayList<>();

        for (int i = 0; i < shapes.getShapesList().size(); i++) {
            ret.add(ShapesDecoder
                    .getShapeFromSavable(shapes.getShapesList().get(i)));
        }

        return ret;
    }
}
