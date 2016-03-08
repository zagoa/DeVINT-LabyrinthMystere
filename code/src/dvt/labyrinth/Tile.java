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

    public Tile(int x, int y) {
        this(x,y, null);
    }

    public Tile(int x, int y, Item item){
        this.x = x;
        this.y = y;
        occupied = false;
        this.item = item;
    }

    public boolean isOccupied(){
        return occupied;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){ return this.y; }

    public void setX(int x){ this.x= x; }

    public void setY(int y){ this.y= y; }

    public JButton getItem() {
        JButton button = new JButton();

        button.setOpaque(true);
        button.setBorder(new LineBorder(Color.black, 1));

        if (item == null || item.getRes().getPath() == null) // Transparent
            button.setBackground(null);
        else // Something
            button.setIcon(new ImageIcon(item.getRes().getPath()));

        return button;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}

