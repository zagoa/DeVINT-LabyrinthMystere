package dvt.labyrinth.model;

import dvt.labyrinth.ConstantesLabyrinth.RESSOURCES;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * A default "template" for an item
 *
 * @author Etienne, Thomas
 */
public abstract class Item {
    // An item has a ressource
    protected RESSOURCES res;

    /**
     * Constructor of the item
     *
     * @param res
     *          The ressource related to the item
     */
    public Item(RESSOURCES res) {
        this.res = res;
    }

    /**
     * Getter on the ressource
     * @return The ressource
     */
    public RESSOURCES getRes() {
        return res;
    }

    /**
     * Get the path of its ressource
     * @return The path
     */
    public String getResPath() {
        return res.getPath();
    }

    /**
     * Set a ressource to the item
     * @param res
     *          The new ressource
     */
    public void setRes(RESSOURCES res) {
        this.res = res;
    }
}
