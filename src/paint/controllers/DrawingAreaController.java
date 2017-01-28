
package paint.controllers;

import java.awt.Point;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import paint.DTO.FormatingData;
import paint.DTO.SceneCoordinates;
import paint.historyManager.HistoryFactory;
import paint.historyManager.Operations;
import paint.models.ShapePicaso;
import paint.utilities.Constants;

/**
 * CS 221 : Object Oriented Programming.
 * Assignment 2 : Picaso Paint Application (PPA)
 * @author Bishoy Nader & Marc Magdi
 * November 2016
 */
public class DrawingAreaController implements Initializable {

	/**.
	 * List of the current shapes
	 */
	private List<ShapePicaso> shapes;

	/**
	 * The main area object that we draw in.
	 */
	@FXML
    private Pane mainArea;

	/**
	 * The class of the shape passed from the shapes panel.
	 */
    private Class<?> currentlyShapeClass;

    /**
     * List of vertices of the shape.
     */
    private List<Point> points;

    /**
     * Reference to the parent of the drawing area.
     * (i.e) border pane controller
     */
    private BorderPaneController borderPaneController;

    /**
     * The current instance that is being drawing.
     */
    private ShapePicaso currentShape;

    /**.
     * boolean variable to indicate if I am drawing or not
     * it's true when I start to draw, by drawing the first point
     * and return to false after drawing the shape
     */
    private boolean isDrawing;

    /**.
     * counter for drawn points of the shape representing x and y
     * if we have one point drawn then the value will be 2 not 1
     */
    private int drawnVerticesCounter;

    /**.
     * The current state for the pane: drawing, resizing, moving
     */
    private int state;

    /**.
     * History Factor
     */
    private HistoryFactory historyFactory;

    /**.
     * History old Object
     */
    private Object historyOld;

    /**.
     * History new Object
     */
    private Object historyNew;

    /**
     * Initializes the reference to the reference to the border pane.
     * @param borderPaneParam reference to the the parent.
     */
    public final void init(final BorderPaneController borderPaneParam) {
        this.borderPaneController = borderPaneParam;
    }

    /**
     * setting the shape to be drawn from the shapes panel.
     * @param currentlyShapeClassParam the class object.
     */
    public final void setCurrentlyDrawnShape(
				final Class<?> currentlyShapeClassParam) {
        this.currentlyShapeClass = currentlyShapeClassParam;
        this.resetShape();
        mainArea.setUserData(currentlyShapeClassParam);
    }

    /**
     * set the current operation either drawing.
     * or resizing or moving or deleting
     * @param currentState the current state from controllers panel
     */
    public final void setCurrentState(final int currentState) {
        this.state = currentState;
        disableResize();
    }

    /**
     * disable resize rectangles for all shapes.
     */
    public final void disableResize() {
        for (int i = 0; i < shapes.size(); i++) {
        	shapes.get(i).disableResize();
        }
    }

    @Override
	public final void initialize(final URL location,
			final ResourceBundle resources) {
    	// default state
    	this.state = Constants.STATE_DRAWING;
    	this.historyFactory = new HistoryFactory();
    	shapes = new ArrayList<>();
    	mainArea.setStyle("-fx-background-color: #ffffff");
    	points = new ArrayList<Point>();
    	this.resetShape();
    	this.addDrawingHandlersToMainArea();
    }

    /**.
     * Set event handlers for the drawing area
     */
    private void addDrawingHandlersToMainArea() {
    	mainArea.addEventFilter(MouseEvent.MOUSE_PRESSED,
        		new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent mouseEvent) {
                if (state == Constants.STATE_DRAWING) {
                	drawShape(mouseEvent);
                }
            }
        });

        mainArea.addEventFilter(MouseEvent.MOUSE_MOVED,
        		new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent mouseEvent) {
            	if (isDrawing) {
            		Point point = new Point((int) mouseEvent.getX(),
            				(int) mouseEvent.getY());
            		currentShape.updateDrawableShape(point,
            				drawnVerticesCounter);
            	}
            }
        });
    }

    /**.
     * Draw step by step the current selected shape
     * @param mouseEvent containing the mouse event
     */
    private void drawShape(final MouseEvent mouseEvent) {

    	if (this.currentlyShapeClass == null) {
            return;
        }

    	if (this.currentShape == null) {
    		initiliazeShape();
    	}

    	Point point = new Point((int) mouseEvent.getX(),
				(int) mouseEvent.getY());

    	if (!isDrawing) { // the first point of the shape
    		this.isDrawing = true;
    		Node shape = this.currentShape.getDrawableShape(point,
    				borderPaneController.
    				getFormatingDataFromFormatingPanel());
    		shape.setUserData(this.currentShape);

    		this.mainArea.getChildren().add(shape);
    		shapes.add(currentShape);
    		this.addHandlersToShape(shape);
    		this.drawnVerticesCounter += 2;
    	} else {
    		this.currentShape.updateDrawableShape(point,
    				this.drawnVerticesCounter);

    		this.drawnVerticesCounter += 2;
    		this.historyFactory.addHistoryNode(currentShape,
    				Operations.DRAW);
    		// last point needed to draw the shape
    		if (this.drawnVerticesCounter == 2 * this.currentShape
    				.getMinimumNumberOfVericesToDraw()) {
    			currentShape.setUpResizeRectangle();
    			Polygon rectangle = currentShape.getResizeRectangle();
    			addHandlersToResizeRectangle(rectangle, currentShape);
    			mainArea.getChildren().add(rectangle);
    			currentShape.disableResize();
    			this.resetShape();
    		}
    	}
    }

    /**
     * Initialize currentShape object with a new.
     * instance of the currentClass object
     */
    @SuppressWarnings("unchecked")
	private void initiliazeShape() {
    	Constructor<? extends ShapePicaso>[] constructors =
    			(Constructor<? extends ShapePicaso>[])
				currentlyShapeClass.getConstructors();
        Constructor<? extends ShapePicaso> constructor = constructors[0];
        try {
            currentShape = constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reset the current shape by clearing the points.
     * and setting the shape to null
     */
    private void resetShape() {
    	this.points.clear();
    	this.currentShape = null;
    	this.drawnVerticesCounter = 0;
    	this.isDrawing = false;
    }

    /**
     * add handlers for showing and hiding resize rectangle.
     * @param rectangle reference to the rectangle
     * @param currentShapeObject reference to the shape itself
     */
    private void addHandlersToResizeRectangle(final Polygon rectangle,
    		final ShapePicaso currentShapeObject) {
    	rectangle.addEventHandler(MouseEvent.MOUSE_PRESSED,
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(final MouseEvent e) {
					if (state == Constants.STATE_RESIZING) {
						currentShapeObject
						.rectanglePressed(e.getX(),
						        e.getY());
					}
				}
			});
    	rectangle.addEventHandler(MouseEvent.MOUSE_DRAGGED,
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(final MouseEvent e) {
					if (state == Constants.STATE_RESIZING) {
					    currentShapeObject.resizeDragging(
						        e.getX(), e.getY());
					}
				}
			});
    	rectangle.addEventHandler(MouseEvent.MOUSE_RELEASED,
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(final MouseEvent e) {
					if (state == Constants.STATE_RESIZING) {
					    finishResizing(currentShapeObject);
					}
				}

                /**
                 * @param currentShapeObject reference to shape object.
                 */
                private void finishResizing(
                        final ShapePicaso currentShapeObject) {
                    currentShapeObject.endResizing();
                    historyFactory.addHistoryNode(currentShapeObject,
                    Operations.RESIZE, currentShapeObject.getNewScales(),
                    currentShapeObject.getOldScales());
                }
			});
    }

    /**
     * Add event handlers to the shape for its movement.
     * @param shape node of the shape
     */
    private void addHandlersToShape(final Node shape) {
    	if (shape.getUserData() instanceof ShapePicaso) {
    		ShapePicaso shapeObject = (ShapePicaso) shape.getUserData();

	    	this.shapeMousePressHandler(shape, shapeObject);

	    	this.shapeMouseDragHandler(shape, shapeObject);

	    	this.shapeMoveReleaseHandler(shape, shapeObject);
    	}
	}

    /**
     * add event handlers to the shape on pressing on it.
     * @param shape node of the shape
     * @param shapeObject instance of the shape
     */
    private void shapeMousePressHandler(final Node shape,
                                        final ShapePicaso shapeObject) {
    	shape.addEventHandler(MouseEvent.MOUSE_PRESSED,
    			new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent e) {
            	if (state == Constants.STATE_MOVING) {
            		shapeMoveHandlerAndHistoryInit(shapeObject, e);
            	// move the delete to another function
            	} else if (state == Constants.STATE_DELETING) {
            		historyFactory.addHistoryNode(shapeObject,
            				Operations.DELETE);
            		shapeObject.delete();
            	} else if (state == Constants.STATE_RESIZING) {
            		shapeObject.enableResize();
            		shapeObject.rectanglePressed(e.getX(), e.getY());
            	}
            }
        });
    }

    /**
     * add dragging event handlers to the shape.
     * @param shape reference to the shape node.
     * @param shapeObject reference to the shapePicaso object.
     */
    private void shapeMouseDragHandler(final Node shape,
    		final ShapePicaso shapeObject) {
    	shape.addEventHandler(MouseEvent.MOUSE_DRAGGED,
    			new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent e) {
            	if (state == Constants.STATE_MOVING) {
            		shapeMoveHandlerDragging(shapeObject, e);
            	} else if (state == Constants.STATE_RESIZING) {
            		shapeObject.resizeDragging(e.getX(), e.getY());
            	}
            }
        });
    }

    /**
     * add release event handlers to the shape.
     * @param shape reference to the shape node.
     * @param shapeObject reference to the shapePicaso object.
     */
    private void shapeMoveReleaseHandler(final Node shape,
    		final ShapePicaso shapeObject) {
    	shape.addEventHandler(MouseEvent.MOUSE_RELEASED,
    			new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent e) {
            	if (state == Constants.STATE_MOVING) {
	            	if (shape.getUserData() instanceof ShapePicaso) {
	            		ShapePicaso shapeObject = (ShapePicaso)
	            				shape.getUserData();
	            		SceneCoordinates coordinates = new
	                			SceneCoordinates(e.getSceneX(),
	                					e.getSceneY());
	            		shapeObject.setSceneCoordinates(coordinates);

	            		historyNew = shapeObject
	            				.getSceneCoordinates().clone();
	            		historyFactory.addHistoryNode(shapeObject,
	            				Operations.MOVE,
	            				historyNew, historyOld);
	            	}
            	}
            }
        });
    }

    /**
     * pass start moving event to object and set it in history.
     * @param shapeObject reference to the ShapePicaso object
     * @param e reference to the mouse event
     */
    private void shapeMoveHandlerAndHistoryInit(final ShapePicaso shapeObject,
    		final MouseEvent e) {
    	SceneCoordinates coordinates = new
				SceneCoordinates(e.getSceneX(),	e.getSceneY());

		shapeObject.setSceneCoordinates(coordinates);
		historyOld = shapeObject.getSceneCoordinates().clone();
    }

    /**
     * pass during moving event to object and set it in history.
     * @param shapeObject reference to the ShapePicaso object
     * @param e reference to the mouse event
     */
    private void shapeMoveHandlerDragging(final ShapePicaso shapeObject,
    		final MouseEvent e) {
    	SceneCoordinates coordinates = new
    			SceneCoordinates(e.getSceneX(),	e.getSceneY());

		shapeObject.move(coordinates);
    }

    /**
     * Undo to the last move made.
     */
    public final void undo() {
    	this.historyFactory.undo();
    	disableResize();
    }

    /**.
     * Redo to the last move made
     */
    public final void redo() {
    	this.historyFactory.redo();
    }

    /**.
     * return the list of the shapes
     * @return list of all shapes in the drawing area
     */
    public final List<ShapePicaso> getAllShapes() {
    	return this.shapes;
    }

    /**
     * add loaded shapes to the main area after reseting it.
     * @param shapesLoaded list of loaded shapes
     */
	public final void loadShapesFromFile(
	            final List<ShapePicaso> shapesLoaded) {
		mainArea.getChildren().clear();

		for (int i = 0; i < shapesLoaded.size(); i++) {
			Boolean startDraw = false;
			drawnVerticesCounter = 0;
			int sz = shapesLoaded.get(i).getSavedPoints().size();
			for (int j = 0; j < sz; j += 2) {
		    	if (!startDraw) { // the first point of the shape
		    		startDraw = true;
		    		int xCoord = shapesLoaded.get(i)
	    				.getSavedPoints().get(j).intValue();
		    		int yCoord = shapesLoaded.get(i)
	    				.getSavedPoints().get(j + 1).intValue();
		    		Point first = new Point(xCoord, yCoord);
		    		FormatingData formating = shapesLoaded
    							.get(i).getFormats();
		    		Node shape = shapesLoaded.get(i)
	    				.getDrawableShape(first, formating);
		    		shape.setUserData(shapesLoaded.get(i));
		    		this.mainArea.getChildren().add(shape);
		    		shapes.add(shapesLoaded.get(i));
		    		this.addHandlersToShape(shape);
		    		this.drawnVerticesCounter += 2;
		    	} else {
		    		int xCoord = shapesLoaded.get(i)
	    				.getSavedPoints().get(j).intValue();
		    		int yCoord = shapesLoaded.get(i)
	    				.getSavedPoints().get(j + 1).intValue();
		    		Point second = new Point(xCoord, yCoord);
		    		shapesLoaded.get(i).updateDrawableShape(second,
		    				this.drawnVerticesCounter);
		    		this.drawnVerticesCounter += 2;
		    		this.historyFactory.addHistoryNode(
    				shapesLoaded.get(i), Operations.DRAW);
		    		// last point needed to draw the shape
		    		if (this.drawnVerticesCounter == 2
		    		        * shapesLoaded.get(i)
		    		        .getMinimumNumberOfVericesToDraw()) {
		    			shapesLoaded.get(i)
		    			        .setUpResizeRectangle();
		    			Polygon rectangle = shapesLoaded.get(i)
	    			            .getResizeRectangle();
		    			addHandlersToResizeRectangle(rectangle,
		    			        shapesLoaded.get(i));
		    			mainArea.getChildren().add(rectangle);
		    			shapesLoaded.get(i).disableResize();
		    			this.resetShape();
		    		}
		    	}
			}
		}
	}
}
