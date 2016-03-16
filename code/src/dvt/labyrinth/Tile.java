package dvt.labyrinth;

import dvt.labyrinth.actions.PutWall;
import dvt.labyrinth.game.TwoPlayers;
import dvt.labyrinth.model.Arrow;
import dvt.labyrinth.model.DefaultItem;
import dvt.labyrinth.model.Item;
import dvt.labyrinth.model.Wall;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * A tile.
 *
 * @author Arnaud
 */
public class Tile {
    public Color borderColor;

    // The position
    private Position pos;
    // Is the tile occupied ?
    private boolean occupied;
    // The item on the tile
    private Item item;
    // Is the tile highlighted ?
    private Color highlightedColor;
    // The component related to the tile
    private JButton component;

    /**
     * A constructor where we don't
     * need to set an item on the tile
     *
     * @param pos
     *          The position of the tile
     */
    public Tile(Position pos) {
        this(null, pos);
    }

    /**
     * The constructor
     *
     * @param item
     *          The item on the tile
     * @param pos
     *          The position of the tile
     */
    public Tile(Item item, Position pos) {
        this.item = item;
        this.pos = pos;

        occupied = false;
        highlightedColor = null;

        borderColor = Color.BLACK;

        createComponent();
    }

    /**
     * Is the tile occupied ?
     * @return true / false
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * Create the component (JButton)
     * of the tile.
     */
    public void createComponent() {
        if (item == null || item.getRes().getPath() == null)
            component = new JButton();
        else
            component = new JButton(new ImageIcon(item.getRes().getPath()));

        editComponent();
    }

    /**
     * Get the component
     * @return The component - JButton
     */
    public JButton getComponent() {
        return component;
    }

    /**
     * Set a new item on the tile
     * @param item
     *          The new item to set
     */
    public void setItem(Item item) {
        highlightedColor = null;

        component.setContentAreaFilled(true);

        if (item == null) {
            this.item = new DefaultItem();
            occupied = false;
        } else
            this.item = item;

        if (this.item != null || this.item.getRes().getPath() != null) {
            component.add(new JLabel(new ImageIcon(this.item.getRes().getPath())));
        }
        else
            component.setIcon(null);

        editComponent();
    }

    /**
     * Set a particular item (a pawn)
     * on the tile. We need to say that
     * the tile is occupied.
     *
     * @param item
     *          The pawn item
     */
    public void setPawn(Item item) {
        occupied = true;
        setItem(item);
    }

    /**
     * Highlight the tile without
     * setting a particular color
     */
    public void setHighlighted(Item item) {
        setHighlighted(item, null);
    }

    /**
     * Hightlight the tile and set an
     * item (maybe an arrow ?)
     *
     * @param itm
     *          The item to set
     * @param c
     *          The color
     */
    public void setHighlighted(Item itm, Color c) {
        if (itm != null)
            setItem(itm);

        highlightedColor = (c == null) ? Color.YELLOW : c;

        editComponent();
    }

    /**
     * "Un-highlight" the tile
     * and replace the item with
     * a Default Item.
     */
    public void unHighlight() {
        highlightedColor = null;
        occupied = false;
        setItem(new DefaultItem());

        editComponent();
    }

    /**
     * Refresh the component
     */
    public void editComponent() {
        component.setBackground(highlightedColor);
        component.setOpaque(true);
        component.setFocusable(false);

        if (isAWall() && this.item.getResPath() == null) // On a POSSIBLE wall
            component.setBorder(null);
        else if (pos.getY() % 2 == 1) // On a line of walls
            component.setBorder(new LineBorder(Color.BLACK, 1));
        else // On a wall or on an other thing
            component.setBorder(new LineBorder(Color.BLACK, 1));
    }

    /**
     * Is the tile highlighted ?
     * @return true / false
     */
    public boolean isHighlighted() {
        return (highlightedColor != null);
    }

    /**
     * Getter on the item
     * @return The item
     */
    public Item getItem() {
        return item;
    }

    /**
     * Getter on the position
     * @return The position
     */
    public Position getPosition() {
        return pos;
    }

    /**
     * Check if the tile is a wall
     * based on its Position
     *
     * @return true / false
     */
    public boolean isAWall() {
        return (pos.getX() % 2 == 1 || pos.getY() % 2 == 1);
    }

    /**
     * Set a wall on the tile.
     * A wall is a particular item.
     */
    public void putWall() {
        occupied = true;
        setHighlighted(new Wall());
    }

    /**
     * Set occupied
     */
    public void setOccupied() {
        this.occupied = true;
    }

    /**
     * Set a listener (click) on the wall.
     * We need to keep in memory the Labyrinth.
     *
     * @param lab
     *          The labyrinth
     */
    public void setListenerWall(TwoPlayers lab) {
        if (isAWall())
            component.addActionListener(new PutWall(lab, this));
    }
}

