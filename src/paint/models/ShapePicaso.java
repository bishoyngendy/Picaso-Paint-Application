
package paint.models;

import java.awt.Point;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import paint.DTO.FormatingData;
import paint.DTO.HorizontalAndVerticalScales;
import paint.DTO.SceneCoordinates;
import paint.saveManager.SavableShape;

/**
 * CS 221 : Object Oriented Programming.
 * Assignment 2 : Picaso Paint Application (PPA)
 * @author Bishoy Nader & Marc Magdi
 * November 2016
 */
public abstract class ShapePicaso {

	/**
	 * The colors and stroke width of shape.
	 */
    private FormatingData formats;

    /**.
     * Minimum number of vertices to draw the shape
     */
    protected int minimumNumberOfVericesToDraw;

    /**.
     * Minimum number of vertices to draw the shape
     */
    protected int minimumNumberOfPointsToDraw;

    /**
     * old horizontal and vertical scales before resizing.
     */
    private HorizontalAndVerticalScales oldScales;

    /**
     * new horizontal and vertical scales after resizing.
     */
    private HorizontalAndVerticalScales newScales;

    /**
     * loaded point from files.
     */
    private List<Double> savedPoints;

    /**
     * previous x during resizing.
     */
    private double prevX;

    /**
     * previous y during resizing.
     */
    private double prevY;

    /**
     * indicate that shape is resized from right edge.
     */
    private Boolean xRightSetted = false;

    /**
     * indicate that shape is resized from top edge.
     */
    private Boolean yTopSetted = false;

    /**
     * indicate that shape is resized from left edge.
     */
    private Boolean xLeftSetted = false;

    /**
     * indicate that shape is resized from bottom edge.
     */
    private Boolean yBottomSetted = false;

    /**
     * reference to the shape.
     */
    protected Shape shape;

    /**
     * The resizing rectangle.
     */
    protected Polygon resizeRectangle;

    /**
     * The current position of the shape.
     */
    protected SceneCoordinates sceneCoordinates;

    /**
     * get the four corners of the shape.
     * @return the four corners of the shape as x then y
     */
    public abstract ObservableList<Double> getCorners();

    /**
     * the main points for drawing the shape.
     * @return main points for drawing the shape as x then y
     */
    public abstract List<Double> getPoints();

    /**
     * invoke when resizing the shape.
     * @param x current x coordinate
     * @param y current y coordinate
     */
    public abstract void resizeDragging(double x, double y);

    /**
     * get the shape as a node.
     * @param point first point in the shape
     * @param formatsParam fill and stroke colors and stroke width
     * @return node of the shape
     */
    public abstract Node getDrawableShape(final Point point,
            final FormatingData formatsParam);

    /**.
     * Update the drawable shape while it's drawn
     * @param point the new value of a point
     * @param index its index in the list
     */
    public abstract void updateDrawableShape(Point point, int index);

    public int getMinimumNumberOfVericesToDraw() {
        return this.minimumNumberOfVericesToDraw;
    }

    public int getMinimumNumberOfPointsToDraw() {
        return this.minimumNumberOfPointsToDraw;
    }

    public Node getShape() {
        return this.shape;
    }

    public SceneCoordinates getSceneCoordinates() {
        return sceneCoordinates;
    }

    public void setSceneCoordinates(
            final SceneCoordinates sceneCoordinatesParam) {
        this.sceneCoordinates = sceneCoordinatesParam;
        this.sceneCoordinates.setTranslateX(shape.getTranslateX());
        this.sceneCoordinates.setTranslateY(shape.getTranslateY());
    }


    public void move(SceneCoordinates newSceneCoordinates) {
        double offsetX = newSceneCoordinates.getSceneX()
                - getSceneCoordinates().getSceneX();
        double offsetY = newSceneCoordinates.getSceneY()
                - getSceneCoordinates().getSceneY();
        double newTranslateX = getSceneCoordinates().getTranslateX() + offsetX;
        double newTranslateY = getSceneCoordinates().getTranslateY() + offsetY;

        shape.setTranslateX(newTranslateX);
        shape.setTranslateY(newTranslateY);
        resizeRectangle.setTranslateX(newTranslateX);
        resizeRectangle.setTranslateY(newTranslateY);
    }

    /**
     * Initializes the rectangle using shape corners.
     */
    public final void setUpResizeRectangle() {
    	resizeRectangle = new Polygon();
    	resizeRectangle.setStyle("-fx-stroke-dash-array: 2 12 12 2;");
    	resizeRectangle.setFill(Color.TRANSPARENT);
    	resizeRectangle.setStroke(Color.BLACK);
		resizeRectangle.getPoints().setAll(getCorners());
		oldScales = new HorizontalAndVerticalScales(shape.getScaleX(),
				shape.getScaleY());
    }

    /**
     * reset variables after resizing.
     */
    public final void endResizing() {
    	xRightSetted = false;
    	yTopSetted = false;
    	xLeftSetted = false;
    	yBottomSetted = false;
    	newScales = new HorizontalAndVerticalScales(shape.getScaleX(),
				shape.getScaleY());
    }

    /**
     * Delete the shape.
     */
    public final void delete() {
        shape.setVisible(false);
        shape.setDisable(true);
    }

    /**
     * Undelete the shape.
     */
    public final void unDelete() {
        shape.setVisible(true);
        shape.setDisable(false);
    }

    /**
     * encodes the shapePicaso to a savable shape.
     * @return the savable object
     */
    public final SavableShape getSavableShape() {
        SavableShape savable = new SavableShape();
        savable.setClassName(this.getClass().getSimpleName());
        savable.setFillColor(getFormats().getFillColor().toString());
        savable.setStrokeColor(getFormats().getStrokeColor().toString());
        savable.setStrokeSize(getFormats().getStrokeSize());
        savable.setPoints(getPoints());
        return savable;
    }

    /**
     * invoked when dragging the the resize rectangle.
     * @param x new horizontal place
     * @param y new vertical place
     */
    public final void rectanglePressed(final double x, final double y) {
    	if (Math.abs(x - resizeRectangle.getLayoutBounds().getMinX()) < 10) {
    		xLeftSetted = true;
    		xRightSetted = false;
    		prevX = x;
    	} else if (Math.abs(x - resizeRectangle.getLayoutBounds().getMaxX())
    	        < 10) {
    		xRightSetted = true;
    		xLeftSetted = false;
    		prevX = x;
    	} else {
    		xRightSetted = false;
    		xLeftSetted = false;
    	}
        if (Math.abs(y - resizeRectangle.getLayoutBounds().getMinY())
                < 10) {
    		yBottomSetted = true;
    		yTopSetted = false;
    		prevY = y;
    	} else if (Math.abs(y - resizeRectangle.getLayoutBounds().getMaxY())
    	        < 10) {
    		yTopSetted = true;
    		yBottomSetted = false;
    		prevY = y;
    	} else {
    		yTopSetted = false;
    		yBottomSetted = false;
    	}
        oldScales = new HorizontalAndVerticalScales(shape.getScaleX(),
				shape.getScaleY());
    }

    /**
     * resizes shape and rectangle.
     * @param scaleX previous scale x
     * @param scaleY previous scale y
     */
    public final void rescale(final double scaleX, final double scaleY) {
    	shape.setScaleX(scaleX);
    	resizeRectangle.setScaleX(scaleX);
    	shape.setScaleY(scaleY);
    	resizeRectangle.setScaleY(scaleY);
    }

    public FormatingData getFormats() {
        return formats;
    }

    public void setFormats(FormatingData formats) {
        this.formats = formats;
    }

	public double getPrevX() {
		return prevX;
	}

	public void setPrevX(double prevX) {
		this.prevX = prevX;
	}

	public double getPrevY() {
		return prevY;
	}

	public void setPrevY(double prevY) {
		this.prevY = prevY;
	}

	public Boolean getxRSetted() {
		return xRightSetted;
	}

	public void setxRSetted(Boolean xRSetted) {
		this.xRightSetted = xRSetted;
	}

	public Boolean getyTSetted() {
		return yTopSetted;
	}

	public void setyTSetted(Boolean yTSetted) {
		this.yTopSetted = yTSetted;
	}

	public Boolean getxLSetted() {
		return xLeftSetted;
	}

	public void setxLSetted(Boolean xLSetted) {
		this.xLeftSetted = xLSetted;
	}

	public Boolean getyBSetted() {
		return yBottomSetted;
	}

	public void setyBSetted(Boolean yBSetted) {
		this.yBottomSetted = yBSetted;
	}

	public Polygon getResizeRectangle() {
		return resizeRectangle;
	}

	/**
	 * disable resizing rectangle.
	 */
	public void disableResize() {
		resizeRectangle.setVisible(false);
		resizeRectangle.setDisable(true);
	}

	/**
	 * enable resizing rectangle.
	 */
	public void enableResize() {
		resizeRectangle.setVisible(true);
		resizeRectangle.setDisable(false);
	}

	public List<Double> getSavedPoints() {
		return savedPoints;
	}

	public void setSavedPoints(List<Double> savedPoints) {
		this.savedPoints = savedPoints;
	}

    /**
     * Default Constructor.
     */
    public ShapePicaso() {

    }

	public HorizontalAndVerticalScales getOldScales() {
		return oldScales;
	}

	public void setOldScales(HorizontalAndVerticalScales oldScales) {
		this.oldScales = oldScales;
	}

	public HorizontalAndVerticalScales getNewScales() {
		return newScales;
	}

	public void setNewScales(HorizontalAndVerticalScales newScales) {
		this.newScales = newScales;
	}

}