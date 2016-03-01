package dvt.labyrinth;

import dvt.labyrinth.model.Item;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Arnaud on 26/02/2016.
 */
public class Tile {
    private int x;
    private int y;
    private Item item;

    public Tile(int x, int y, Item item){
        this.x = x;
        this.y = y;
        this.item = item;



    }



    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}


