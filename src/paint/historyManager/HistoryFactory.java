
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
public class HistoryFactory {

	/**.
	 * History manager
	 */
	private HistoryManager historyManager;

	/**.
	 * Main constructor
	 */
	public HistoryFactory() {
		this.historyManager = new HistoryManager();
	}

	/**.
	 * Create a new History node object
	 * @param shape the reference to shape object
	 * @param operation the operation running on the shape
	 * @param newHist new Value
	 * @param oldHist old Value
	 */
	public final void addHistoryNode(final ShapePicaso shape,
			final int operation, final Object newHist,
			final Object oldHist) {
		if (newHist instanceof SceneCoordinates
				&& operation == Operations.MOVE) {
			SceneCoordinates newVal = (SceneCoordinates) newHist;
			SceneCoordinates oldVal = (SceneCoordinates) oldHist;
			HistoryNode node = new HistoryNode(shape,
					operation, newVal, oldVal);
			this.historyManager.addNewRecord(node);
		} else if (newHist instanceof HorizontalAndVerticalScales
				&& operation == Operations.RESIZE) {
			HorizontalAndVerticalScales newVal =
			        (HorizontalAndVerticalScales) newHist;
			HorizontalAndVerticalScales oldVal =
			        (HorizontalAndVerticalScales) oldHist;
			HistoryNode node = new HistoryNode(shape,
					operation, newVal, oldVal);
			this.historyManager.addNewRecord(node);
		}
	}

	/**.
	 * Add a node without previous or new object, for delete, for draw
	 * @param shape the reference to shape object
	 * @param operation the operation running on the shape
	 */
	public final void addHistoryNode(final ShapePicaso shape,
			final int operation) {
		if (operation == Operations.DELETE
				|| operation == Operations.DRAW) {
			HistoryNode node = new HistoryNode(shape, operation);
			this.historyManager.addNewRecord(node);
		}
	}

	/**.
	 * Undo the context to the previews state
	 */
	public final void undo() {
		this.historyManager.undo();
	}

	/**.
	 * Redo the context to the next state
	 */
	public final void redo() {
		this.historyManager.redo();
	}
}
