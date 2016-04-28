package dvt.labyrinth.model.essential;

import dvt.labyrinth.tools.ConstantesLabyrinth.RESOURCES;

import javax.swing.*;

/**
 * A default "template" for an item
 *
 * @author Etienne, Thomas
 */
public abstract class Item {
    // An item has a ressource
    protected RESOURCES res;

    /**
     * Constructor of the item
     *
     * @param res
     *          The resource related to the item
     */
    public Item(RESOURCES res) {
        this.res = res;
    }

    /**
     * Getter on the resource
     * @return The resource
     */
    public RESOURCES getRes() {
        return res;
    }

    /**
     * Get the path of its resource
     * @return The path
     */
    public String getResPath() {
        return res.getPath();
    }

    /**
     * Set a resource to the item
     * @param res
     *          The new resource
     */
    public void setRes(RESOURCES res) {
        this.res = res;
    }

    public ImageIcon getIcon() {
        return new ImageIcon(res.getPath());
    }
}
