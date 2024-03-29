package dvt.labyrinth.model.essential;

import dvt.labyrinth.actions.PutWall;
import dvt.labyrinth.game.Game;
import dvt.labyrinth.tools.ConstantesLabyrinth;
import dvt.labyrinth.tools.Position;

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
    public int borderSize;

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
        borderSize = 2;

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

        if (item == null) {
            this.item = new DefaultItem();
            occupied = false;
        } else {
            this.item = item;
        }

        if (this.item != null && this.item.getRes().getPath() != null)
            component.setIcon(item.getIcon());
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
        component.setBackground((item.getRes().isAPawn()) ? Color.WHITE : highlightedColor);
        component.setOpaque(true);
        component.setFocusable(false);

        if (isAWall()) // On a POSSIBLE wall
            component.setBorder(null);
        else // On a wall or on an other thing
            component.setBorder(new LineBorder(borderColor, borderSize));
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

    public void clearTile(){
        this.setItem(null);
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

    public void positionWall() {
        // (Are we on a line of walls) ? Horizontal : Vertical
        positionWall((pos.getY()%2 == 1) ? ConstantesLabyrinth.DIRECTIONS.LEFT : ConstantesLabyrinth.DIRECTIONS.BACK);
    }

    /**
     * Set a wall on the tile.
     * A wall is a particular item.
     */
    public void positionWall(ConstantesLabyrinth.DIRECTIONS d) {
        occupied = true;

        switch (d) {
            case FRONT:
            case BACK:
                occupied = true;
                setItem(new Wall(true)); // Vertical
                break;

            case LEFT:
            case RIGHT:
                occupied = true;
                setItem(new Wall(false)); // Horizontal
                break;

            default:
                break;
        }
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
    public void setListenerWall(Game lab) {
        if (isAWall())
            component.addActionListener(new PutWall(lab, this));
    }

    public void setBorderColor(Color color) {
        borderColor = color;
        editComponent();
    }

    public void setBorderSize(int size) {
        borderSize = size;
        editComponent();
    }
}

