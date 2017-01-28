
package paint.historyManager;

import java.util.Stack;

import paint.models.ShapePicaso;

/**
 * CS 221 : Object Oriented Programming.
 * Assignment 2 : Picaso Paint Application (PPA)
 * @author Bishoy Nader & Marc Magdi
 * November 2016
 */
public class HistoryManager {

	/**.
	 * The undo stack
	 */
	private Stack<HistoryNode> undoStack;

	/**.
	 * The redo stack
	 */
	private Stack<HistoryNode> redoStack;

	/**.
	 * Constructor to initialize the used stacks
	 */
	public HistoryManager() {
		this.undoStack = new Stack<HistoryNode>();
		this.redoStack = new Stack<HistoryNode>();
	}

	/**
	 * undo.
	 */
	public final void undo() {
		if (this.undoStack.size() > 0) {
			HistoryNode node = this.undoStack.pop();
			this.redoStack.push(node);

			switch (node.getOperation()) {
				case Operations.MOVE:
					this.undoMove(node);
					break;
				case Operations.DELETE:
					this.undoDelete(node);
					break;
				case Operations.DRAW:
					this.redoDelete(node);
					break;
				case Operations.RESIZE:
					this.undoResize(node);
					break;
            default:
                break;
			}
		}
	}

	/**
	 * redo.
	 */
	public final void redo() {
		if (this.redoStack.size() > 0) {
			HistoryNode node = this.redoStack.pop();
			this.undoStack.push(node);

			switch (node.getOperation()) {
				case Operations.MOVE:
					this.redoMove(node);
					break;
				case Operations.DELETE:
					this.redoDelete(node);
					break;
				case Operations.DRAW:
					this.undoDelete(node);
					break;
				case Operations.RESIZE:
					this.redoResize(node);
					break;
            default:
                break;
			}
		}
	}

	/**
     * undo resize.
     * @param node history node.
     */
	private void undoResize(final HistoryNode node) {
		ShapePicaso shape = node.getShape();
		shape.rescale(node.getOldScales().getScaleX(),
				node.getOldScales().getScaleY());
	}

	/**
     * redo resize.
     * @param node history node.
     */
	private void redoResize(final HistoryNode node) {
		ShapePicaso shape = node.getShape();
		shape.rescale(node.getNewScales().getScaleX(),
				node.getNewScales().getScaleY());
	}

	/**
     * undo delete.
     * @param node history node.
     */
	private void undoDelete(final HistoryNode node) {
		node.getShape().unDelete();
	}

	/**
     * redo delete.
     * @param node history node.
     */
	private void redoDelete(final HistoryNode node) {
		node.getShape().delete();
	}

	/**
     * undo move.
     * @param node history node.
     */
	private void undoMove(final HistoryNode node) {
		ShapePicaso shape = node.getShape();
		shape.setSceneCoordinates(node.getNewScene());
		shape.move(node.getOldScene());
	}

	/**
	 * redo move.
	 * @param node history node.
	 */
	private void redoMove(final HistoryNode node) {
		ShapePicaso shape = node.getShape();
		shape.setSceneCoordinates(node.getOldScene());
		shape.move(node.getNewScene());
	}

	/**
	 * add history node.
	 * @param node the new node.
	 */
	public final void addNewRecord(final HistoryNode node) {
		this.undoStack.push(node);
		this.redoStack.clear();
	}
}
