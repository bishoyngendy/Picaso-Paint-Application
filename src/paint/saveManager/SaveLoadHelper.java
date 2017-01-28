
package paint.saveManager;

import java.io.File;
import java.util.List;

import paint.models.ShapePicaso;

/**
 * CS 221 : Object Oriented Programming.
 * Assignment 2 : Picaso Paint Application (PPA)
 * @author Bishoy Nader & Marc Magdi
 * November 2016
 */
public class SaveLoadHelper {
	/**.
	 * Helper to serialize the data and to save them
	 */
	private SaveHelper saveHelper;

	/**.
	 * XML Save and Load Helper
	 */
	private XmlSaveLoad xmlHelper;

	/**.
     * Json Save and Load Helper
     */
	private JsonSaveLoad jsonHelper;

	/**.
	 * Default constructor to initialize data
	 */
	public SaveLoadHelper() {
		this.saveHelper = new SaveHelper();
		this.xmlHelper = new XmlSaveLoad();
		this.jsonHelper = new JsonSaveLoad();
	}

	/**.
	 * Save current context to a JSON or XML file
	 * @param file the file to save to
	 * @param shapes list of shapes to save
	 */
	public final void save(final File file,
			final List<ShapePicaso> shapes) {
		String fileExtension = this.getExtension(file);
		SavableShapes savableShapes = new SavableShapes();
		savableShapes.setShapesList(
		        saveHelper.getSerializedList(shapes));

		switch (fileExtension) {
			case "json":
				this.jsonHelper.saveToFile(file, savableShapes);
				break;
			case "xml":
				this.xmlHelper.saveToFile(file, savableShapes);
				break;
    		default:
    			break;
		}
	}

	/**.
	 * Load the data from the selected file
	 * @param file the file to load from
	 * @return list of shapes loaded
	 */
	public final List<ShapePicaso> load(final File file) {
		String fileExtension = this.getExtension(file);
		List<ShapePicaso> shapes = null;
		switch (fileExtension) {
			case "xml":
				shapes = this.xmlHelper.loadFromFile(file);
				break;
			case "json":
				shapes = this.jsonHelper.loadFromFile(file);
				break;
		default:
			break;
		}

		return shapes;
	}

	/**.
	 * Get extension
	 * @param file the file to get extension
	 * @return return the extension of the give file name
	 */
	private String getExtension(final File file) {
		String name = file.getName();
		String[] nameWithExtension = name.split("\\.");
		return nameWithExtension[nameWithExtension.length - 1];
	}

}
