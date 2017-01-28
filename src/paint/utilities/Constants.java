
package paint.utilities;

/**
 * CS 221 : Object Oriented Programming.
 * Assignment 2 : Picaso Paint Application (PPA)
 * @author Bishoy Nader & Marc Magdi
 * November 2016
 */
public abstract class Constants {

	/**
	 * Models Path for dynamic loading (Bishoy's machine)
	 */
	public static final String MODELS_PATH_BISHOY =
			"C:/Users/Bishoy Nader/git/bmi-solutions"
			+ "/VectorDrawer/bin/paint/models/";

	/**
	 * Paint Path for dynamic loading (Bishoy's machine)
	 */
	public static final String PAINT_PATH_BISHOY =
			"C:/Users/Bishoy Nader/git/bmi-solutions"
			+ "/VectorDrawer/bin/paint/";

	/**
	 * Models Path for dynamic loading (Marc's machine)
	 */
	public static final String MODELS_PATH_MARC =
			"/home/mav/git/bmi-solutions/"
			+ "VectorDrawer/bin/paint/models";

	/**
	 * Paint Path for dynamic loading (Marc's machine)
	 */
	public static final String PAINT_PATH_MARC =
			"/home/mav/git/bmi-solutions/VectorDrawer/bin/paint/";

	/**
	 * Drawing area state: drawing
	 */
	public static final int STATE_DRAWING = 1;

	/**
	 * Drawing area state: moving
	 */
	public static final int STATE_MOVING = 2;

	/**.
	 * Drawing area state: resizing
	 */
	public static final int STATE_RESIZING = 3;

	/**
	 * Drawing area state: deleting
	 */
	public static final int STATE_DELETING = 4;
}
