package dvt.labyrinth;

import dvt.labyrinth.model.Item;
import dvt.labyrinth.model.Pawn;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Arnaud on 26/02/2016.
 */
public class Tile {
    private JPanel tile;
    private int x;
    private int y;
    private Item item;
    private boolean i;

    public Tile(int x, int y, Item item, boolean i){
        this.x = x;
        this.y = y;
        this.item = item;
        this.tile = new JPanel();
        tile.setSize(new Dimension(50, 50));
        tile.setLocation(x,y);
        this.i = i;

    }
    public JPanel getTile() {
        return tile;
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
