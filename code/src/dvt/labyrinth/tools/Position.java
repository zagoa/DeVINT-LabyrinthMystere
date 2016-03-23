package dvt.labyrinth.tools;

/**
 * This class is used to keep x & y coordinates.
 * We use them with Players
 *
 * @author Adrian & Thomas
 */
public class Position {
    private int x;
    private int y;

    /**
     * Constructor
     *
     * @param x
     *          The x coordinate
     * @param y
     *          The y coordinate
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter on x
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Getter on y
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Setter on x
     * @param x
     *          The new x value
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setter on y
     * @param y
     *          The new y value
     */public void setY(int y) {
        this.y = y;
    }
}
