package dvt.labyrinth.model.Tray;

import java.awt.event.KeyEvent;
import java.awt.*;
import java.awt.event.MouseEvent;

import dvt.devint.Fenetre;
import dvt.labyrinth.Tile;

/**
 * Created by Arnaud on 26/02/2016.
 */
public class Tray extends Fenetre {
    private final int nbCase = 17;//nombre de cases par lignes, prenant en compte les Tile Pawn+les Tile wall
    private Tile tray[][];


    public Tray() {
        tray = new Tile[17][17];
        for (int i = 0; i < nbCase; i++) {
            for (int j = 0; j < nbCase; j++) {
                tray[i][j] = new Tile(i, j,null,null);
            }
        }
    }

    public Tile[][] getTray() {
        return tray;
    }

    public Tile  getXYTile(int x, int y){
        return tray[x][y];
    }

    public void moveWall(){    }

    public void moveFront(){ getXYTile(getX(),getY()).getPawn().moveFront(this); }

    public void moveBack(){ getXYTile(getX(),getY()).getPawn().moveBack(this); }

    public void moveRight(){ getXYTile(getX(),getY()).getPawn().moveRight(this); }

    public void moveLeft(){ getXYTile(getX(),getY()).getPawn().moveLeft(this); }

    public int getNbCase(){ return this.nbCase;}

    /*
    public void init(){
        //Move a wall
        addControlDown(MouseEvent.BUTTON1, new LeftClickAction(this));
        addControlUp(MouseEvent.BUTTON1, new LeftClickAction(this));

        //Move to the front
        addControlDown(KeyEvent.VK_UP, new FrontAction(this));
        addControlUp(KeyEvent.VK_UP, new FrontAction(this));

        //Move backwards
        addControlDown(KeyEvent.VK_DOWN, new BackAction(this));
        addControlUp(KeyEvent.VK_DOWN, new BackAction(this));

        //Move to the right
        addControlDown(KeyEvent.VK_RIGHT, new FrontAction(this));
        addControlUp(KeyEvent.VK_RIGHT, new FrontAction(this));

        //Move to the  left
        addControlDown(KeyEvent.VK_LEFT, new LeftAction(this));
        addControlUp(KeyEvent.VK_LEFT, new LeftAction(this));
    }
    */
}