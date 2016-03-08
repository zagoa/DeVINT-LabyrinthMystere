package dvt.labyrinth;

import dvt.labyrinth.model.Item;
import dvt.labyrinth.model.Pawn;


/**
 * Created by Arnaud on 26/02/2016.
 */
public class Tile {
    private int x;
    private int y;
    private Item item;
    private Pawn pawn;

    public Tile(int x, int y, Item item, Pawn pawn){
        this.x = x;
        this.y = y;
        this.item = item;
        this.pawn = pawn;

    }

    public boolean isOccupied(){
        if(this.pawn == null){ return false; }
        return true;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){ return this.y; }

    public void setX(int x){ this.x= x; }

    public void setY(int y){ this.y= y; }

    public Pawn getPawn(){
        return this.pawn;
    }
}

