package dvt.labyrinth.model;

import javax.swing.*;
import static dvt.labyrinth.ConstantesLabyrinth.*;

import dvt.labyrinth.Tile;
import dvt.labyrinth.model.Tray.Tray;

import java.util.ArrayList;


public class Pawn extends Item {
    private Tile tile;
    ArrayList<Tile> can = new ArrayList<Tile>();


    public Pawn(Tile tile) {
        item = new JLabel(new ImageIcon(IMAGEPATH + IMAGEEXT));
        this.tile=tile;
    }


    public boolean canMove(Tray tray,Directions direction){
        switch (direction) {
            case FRONT:
                if (this.tile.getX() >= 17) return false;
                if (tray.getXYTile(this.tile.getX(), this.tile.getY()+1).isOccupied()){ return false; }
                if (tray.getXYTile(this.tile.getX(), this.tile.getY()+2).isOccupied()){ return false; }
                return true;

            case BACK:
                if (this.tile.getX() <= 0) return false;
                if (tray.getXYTile(this.tile.getX(), this.tile.getY()-1).isOccupied()){ return false; }
                if (tray.getXYTile(this.tile.getX(), this.tile.getY()-2).isOccupied()){ return false; }
                return true;

            case RIGHT:
                if (this.tile.getY() >=17) return false;
                if (tray.getXYTile(this.tile.getX()+1, this.tile.getY()).isOccupied()){ return false; }
                if (tray.getXYTile(this.tile.getX()+2, this.tile.getY()).isOccupied()){ return false; }
                return true;

            case LEFT:
                if (this.tile.getY() <=0) return false;
                if (tray.getXYTile(this.tile.getX()-1, this.tile.getY()).isOccupied()){ return false; }
                if (tray.getXYTile(this.tile.getX()-2, this.tile.getY()).isOccupied()){ return false; }
                return true;

            default:
                return false;
        }
    }

    public void moveFront(Tray tray){ if(this.canMove(tray,Directions.FRONT)) this.tile.setY(this.tile.getY()+2); }

    public void moveBack(Tray tray){ if(this.canMove(tray,Directions.BACK)) this.tile.setY(this.tile.getY()-2); }

    public void moveRight(Tray tray){ if(this.canMove(tray,Directions.RIGHT)) this.tile.setX(this.tile.getX()+2); }

    public void moveLeft(Tray tray){ if(this.canMove(tray,Directions.LEFT)) this.tile.setX(this.tile.getX()-2); }

    public boolean isBlocked(Tray tray){
        hasAccessTo(tray);
        //It represent if the pan is on the last line, so he can finish the game
        for(int i=0;i<tray.getNbCase();i+=2) {
            if (can.contains(tray.getXYTile(i, 0))) return true;
        }
        return false;
    }


    public void hasAccessTo(Tray tray) {
        can.add(this.tile);
        int i=0;
        for(can.get(i); i == can.size(); i++){
            if (canMove(tray, Directions.FRONT)&& this.tile.getX()<0) {
                can.add(tray.getXYTile(this.tile.getX()+2,this.tile.getY()));
            }
            if (canMove(tray, Directions.BACK) && this.tile.getX()<tray.getNbCase()) {
                can.add(tray.getXYTile(this.tile.getX()-2,this.tile.getY()));
            }
            if (canMove(tray, Directions.RIGHT)&& this.tile.getX()<tray.getNbCase()) {
                can.add(tray.getXYTile(this.tile.getX(),this.tile.getY()+2));
            }
            if (canMove(tray, Directions.LEFT)&& this.tile.getX()>0) {
                can.add(tray.getXYTile(this.tile.getX(),this.tile.getY()-2));
            }
        }
    }

}

