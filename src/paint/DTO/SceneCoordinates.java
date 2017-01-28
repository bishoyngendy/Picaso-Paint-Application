
package paint.DTO;

/**
 * CS 221 : Object Oriented Programming.
 * Assignment 2 : Picaso Paint Application (PPA)
 * @author Bishoy Nader & Marc Magdi
 * November 2016
 */
public class SceneCoordinates {

    /**
     * horizontal coordinate of the shape.
     */
    private double sceneX;

	/**
	 * vertical coordinate of the shape.
	 */
	private double sceneY;

    /**
     * horizontal translate of the shape.
     */
    private double translateX;

	/**
	 * vertical coordinate of the shape.
	 */
	private double translateY;

	/**
	 * Default constructor.
	 */
	public SceneCoordinates() {

	}

	/**
	 * rich constructor.
	 * @param sceneXParam horizontal translate of the shape.
	 * @param sceneYParam vertical translate of the shape.
	 */
	public SceneCoordinates(final double sceneXParam,
	        final double sceneYParam) {
		super();
		this.sceneX = sceneXParam;
		this.sceneY = sceneYParam;
	}

	public double getSceneX() {
		return sceneX;
	}

	public void setSceneX(final double sceneXParam) {
		this.sceneX = sceneXParam;
	}

	public double getSceneY() {
		return sceneY;
	}

	public void setSceneY(final double sceneYParam) {
		this.sceneY = sceneYParam;
	}

	public double getTranslateX() {
		return translateX;
	}

	public void setTranslateX(final double translateXParam) {
		this.translateX = translateXParam;
	}

	public double getTranslateY() {
		return translateY;
	}

	public void setTranslateY(final double translateYParam) {
		this.translateY = translateYParam;
	}

	/**.
	 * Get a copy from this object
	 */
	@Override
    public final SceneCoordinates clone() {
		SceneCoordinates sc = new
		        SceneCoordinates(this.sceneX, this.sceneY);
		sc.setTranslateX(this.translateX);
		sc.setTranslateY(this.translateY);

		return sc;
	}

}
