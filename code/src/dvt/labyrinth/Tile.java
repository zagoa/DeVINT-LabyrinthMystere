package dvt.labyrinth;

import dvt.labyrinth.model.Item;
import javafx.geometry.Pos;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by Arnaud on 26/02/2016.
 */
public class Tile {
    private Position pos;
    private boolean occupied;
    private Item item;
    private boolean highlighted;
    private JButton component;

    public Tile(Position pos) {
        this(null, pos);
    }

    public Tile(Item item, Position pos){
        this.item = item;
        this.pos = pos;

        occupied = highlighted = false;

        createComponent();
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

        if (pos.getY()%2 == 1 || pos.getX()%2 == 1)
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

    public void changePosition(Position pos) {
        this.pos = pos;
    }

    public Position getPosition() {
        return pos;
    }
}

