package shapes;

/**
 * A shapes.Point class.
 */
public class Point {

	//declaring the class fields
	private double x;
	private double y;

	/**
	 * Construct a point given x and y coordinates.
	 *
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x coordinate
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * @return the y coordinate
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * @param other a point to measure the distance to
	 * @return the distance to the other point
	 */
	public double distanceTo(Point other) {
		double dx = this.x - other.getX();
		double dy = this.y - other.getY();
		double dis = Math.sqrt((dx * dx) + (dy * dy));
		return dis;
	}

}
