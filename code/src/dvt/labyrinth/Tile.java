package dvt.labyrinth;

import dvt.labyrinth.model.Item;
import dvt.labyrinth.model.Pawn;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Arnaud on 26/02/2016.
 */
public class Tile {
    private int x;
    private int y;
    private Item item;
    private boolean i;

    public Tile(int x, int y, Item item, boolean i){
        this.x = x;
        this.y = y;
        this.item = item;



    }



    public int getX() {
        return x;
    }

    public boolean isOccupied(){
        return this.i;
    }

    public void setOccupied(){
        this.i = true;
    }

    public void unsetOccupied(){
        this.i = false;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setX(int x){ this.x= x; }

    public void setY(int y){ this.y= y; }
}

    public void setX(int x) {
        this.x = x;
    }
