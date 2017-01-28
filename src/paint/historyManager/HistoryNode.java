
package paint.historyManager;

import paint.DTO.HorizontalAndVerticalScales;
import paint.DTO.SceneCoordinates;
import paint.models.ShapePicaso;

/**
 * CS 221 : Object Oriented Programming.
 * Assignment 2 : Picaso Paint Application (PPA)
 * @author Bishoy Nader & Marc Magdi
 * November 2016
 */
public class HistoryNode {
	/**.
	 * A reference to the shape that have the operation done on it
	 */
	private ShapePicaso shape;

	/**.
	 * The operation done on the shape
	 */
	private int operation;

	/**.
	 * (Move) old scene
	 */
	private SceneCoordinates oldScene;

	/**.
	 * (Move) new scene
	 */
	private SceneCoordinates newScene;

	/**
	 * (Resize) old scales.
	 */
	private HorizontalAndVerticalScales oldScales;

	/**
	 * (Resize) new scales.
	 */
	private HorizontalAndVerticalScales newScales;


	/**.
	 * A private constructor to save the shape and the operation
	 * @param shapeParam a reference to the shape that has the operation
	 * @param operationParam the operation indication
	 */
	public HistoryNode(final ShapePicaso shapeParam,
	        final int operationParam) {
		this.shape = shapeParam;
		this.operation = operationParam;
	}

	/**.
	 * A constructor used with the movement of a shape
	 * @param shapeParam a reference to the shape that has the operation
	 * @param operationParam the operation indication
	 * @param newSceneParam the old center
	 * @param oldSceneParam the new center
	 */
	public HistoryNode(final ShapePicaso shapeParam,
	        final int operationParam, final SceneCoordinates newSceneParam,
	        final SceneCoordinates oldSceneParam) {
		this(shapeParam, operationParam);
		this.oldScene = oldSceneParam;
		this.newScene = newSceneParam;
	}

	/**.
	 * A constructor used with the movement of a shape
	 * @param shapeParam a reference to the shape that has the operation
	 * @param operationParam the operation indication
	 * @param oldScalesParam the old scales
	 * @param newScalesParam the new scales
	 */
	public HistoryNode(final ShapePicaso shapeParam,
	        final int operationParam,
			final HorizontalAndVerticalScales newScalesParam,
			final HorizontalAndVerticalScales oldScalesParam) {
		this(shapeParam, operationParam);
		this.setOldScales(oldScalesParam);
		this.newScales = newScalesParam;
	}

	/**
	 * @return the operation
	 */
	public int getOperation() {
		return operation;
	}

	public HorizontalAndVerticalScales getNewScales() {
		return newScales;
	}

	public void setNewScales(HorizontalAndVerticalScales newScales) {
		this.newScales = newScales;
	}

	public void setNewScene(SceneCoordinates newScene) {
		this.newScene = newScene;
	}

	public HorizontalAndVerticalScales getOldScales() {
		return oldScales;
	}

	public void setOldScales(HorizontalAndVerticalScales oldScales) {
		this.oldScales = oldScales;
	}

	/**
     * @return the shape
     */
    public final ShapePicaso getShape() {
        return shape;
    }

    /**
     * @return the oldScene
     */
    public final SceneCoordinates getOldScene() {
        return oldScene;
    }

    /**
     * @return the newScene
     */
    public final SceneCoordinates getNewScene() {
        return newScene;
    }
}
