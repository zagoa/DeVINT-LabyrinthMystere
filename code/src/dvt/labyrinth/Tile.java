package dvt.labyrinth;

import dvt.labyrinth.model.Item;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by Arnaud on 26/02/2016.
 */
public class Tile {
    private int x;
    private int y;
    private boolean occupied;
    private Item item;
    private boolean highlighted;
    private JButton component;

    public Tile(int x, int y) {
        this(null, x, y);
    }

    public Tile(Item item, int x, int y){
        this.item = item;
        this.x = x;
        this.y = y;

        occupied = highlighted = false;

        createComponent();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOccupied(){
        return occupied;
    }

    public void createComponent() {
        if (item == null || item.getRes().getPath() == null)
            component = new JButton();
        else
            component = new JButton(new ImageIcon(item.getRes().getPath()));

        editComponent();
    }

    public JButton getComponent() {
        return component;
    }

    public void setItem(Item item) {
        highlighted = false;
        this.item = item;

        if (item != null || item.getRes().getPath() == null)
            component.setIcon(new ImageIcon(item.getRes().getPath()));
        else
            component.setIcon(null);

        editComponent();
    }

    public void setHighlighted() {
        highlighted = true;

        editComponent();
    }

    public void unHighlight() {
        highlighted = false;

        editComponent();
    }

    public void editComponent() {
        component.setBackground((highlighted) ? Color.YELLOW : null);
        component.setOpaque(true);
        component.setFocusable(true);

        if (y%2 == 1 || x%2 == 1)
            component.setBorder(null);
        else
            component.setBorder(new LineBorder(Color.black, 1));
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public Item getItem() {
        return item;
    }

    public void changePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

