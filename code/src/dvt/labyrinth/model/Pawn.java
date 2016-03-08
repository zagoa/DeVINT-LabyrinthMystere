package dvt.labyrinth.model;

import javax.swing.*;
import static dvt.labyrinth.ConstantesLabyrinth.*;

import dvt.labyrinth.Tile;
import dvt.labyrinth.model.Tray.Tray;


public class Pawn extends Item {
    private Tile tile;
    boolean block;

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
        while(this.tile.getX()<tray.getNbCase() || this.tile.getY()<tray.getNbCase()
                || this.tile.getX()>= 0 || this.tile.getY()>= 0 || !block) {
            if(block) return block;

            if (canMove(tray, Directions.FRONT)) {
                moveFront(tray);
                block = false;
                return isBlocked(tray);
            }

            if (canMove(tray, Directions.RIGHT)) {
                moveRight(tray);
                block = false;
                return isBlocked(tray);
            }

            if (canMove(tray, Directions.BACK)) {
                moveBack(tray);
                block = false;
                return changeStrategy(tray);
            }

            if (canMove(tray, Directions.LEFT)) {
                moveLeft(tray);
                block = false;
                return changeStrategy(tray);
            }

            else block = true;

            return block;
        }
        return false;

    }

    public boolean changeStrategy(Tray tray){
        while(this.tile.getX()<tray.getNbCase() || this.tile.getY()<tray.getNbCase()
                || this.tile.getX()> 1 || this.tile.getY()> 1 || !block) {
            if(block) return block;

            if (canMove(tray, Directions.FRONT)) {
                moveFront(tray);
                block = false;
                return changeStrategy(tray);
            }

            if (canMove(tray, Directions.LEFT)) {
                moveLeft(tray);
                block = false;
                return changeStrategy(tray);
            }

            if (canMove(tray, Directions.BACK)) {
                moveBack(tray);
                block = false;
                return isBlocked(tray);
            }

            if (canMove(tray, Directions.RIGHT)) {
                isBlocked(tray);
                block = false;
                return changeStrategy(tray);
            }

            else block = true;

            return block;
        }
        return false;

    }

}

