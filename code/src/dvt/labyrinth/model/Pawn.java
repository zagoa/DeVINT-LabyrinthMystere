package dvt.labyrinth.model;

import javax.swing.*;
import static dvt.labyrinth.ConstantesLabyrinth.*;

import dvt.labyrinth.ConstantesLabyrinth;
import dvt.labyrinth.Tile;
import dvt.labyrinth.Tray;

public class Pawn extends Item {
    String name;
    private Tile tile;
    ArrayList<Tile> can = new ArrayList<Tile>();


    public Pawn(String name, RESSOURCES res, int x, int y) {
        super(res, x, y);

        this.name = name;
    }

    public void move(DIRECTIONS d) {
        switch (d) {
            case FRONT:
                y += CASE_LENGTH;
                break;
        }
    }

    /*public boolean canMove(Tray tray,Directions direction){
        switch (direction) {
            case FRONT:
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

    public void moveFront(Tray tray,Directions FRONT){ if(this.canMove(tray,FRONT)) this.tile.setY(this.tile.getY()+2); }

    public void moveBack(Tray tray,Directions BACK){ if(this.canMove(tray,BACK)) this.tile.setY(this.tile.getY()-2); }

    public void moveRight(Tray tray,Directions RIGHT){ if(this.canMove(tray,RIGHT)) this.tile.setX(this.tile.getX()+2); }

    public void moveLeft(Tray tray,Directions LEFT){ if(this.canMove(tray,LEFT)) this.tile.setX(this.tile.getX()-2); }*/

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

