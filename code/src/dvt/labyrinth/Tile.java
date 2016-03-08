package dvt.labyrinth;

import dvt.labyrinth.model.Item;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by Arnaud on 26/02/2016.
 */
public class Tile {
    private boolean occupied;
    private Item item;
    private boolean highlighted;

    public Tile() {
        this(null);
    }

    public Tile(Item item){
        occupied = false;
        this.item = item;
        highlighted = false;
    }

    public boolean isOccupied(){
        return occupied;
    }

    public JButton getItem() {
        JButton button;

        if (item == null || item.getRes().getPath() == null)
            button = new JButton();
        else
            button = new JButton(new ImageIcon(item.getRes().getPath()));

        button.setBackground((highlighted) ? Color.YELLOW : null);
        button.setOpaque(true);

        if (item.getY()%2 == 1 || item.getX()%2 == 1)
            button.setBorder(null);
        else
            button.setBorder(new LineBorder(Color.black, 1));

        return button;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setHighlighted() {
        highlighted = true;
    }

    public boolean isHighlighted() {
        return highlighted;
    }
}

