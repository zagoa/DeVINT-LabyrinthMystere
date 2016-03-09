package dvt.labyrinth.model;

import dvt.labyrinth.ConstantesLabyrinth;
import dvt.labyrinth.Tile;
import dvt.labyrinth.Tray;

import java.util.ArrayList;

import static dvt.labyrinth.ConstantesLabyrinth.CASE_LENGTH;
import static dvt.labyrinth.ConstantesLabyrinth.NBRE_CASES;

/**
 * Created by user on 02/03/2016.
 */

public class Player {
    private String name;
    private Pawn pawn;
    private Tray tray;
    private int time;
    private boolean timeToPlay;
    ArrayList<Tile> can = new ArrayList<Tile>();


    public Player(String name, Pawn pawn,int time, boolean timeToPlay){
        this.name = name;
        this.pawn = pawn;
        this.time = time;
        this.timeToPlay = timeToPlay;
    }

    public boolean isTimeToPlay(){
        return timeToPlay;
    }

    public void setTimeToPlay(){
        if(this.time%2==0) { this.timeToPlay = true; }
        else {  this.timeToPlay = false; }
    }

    public void turnFinished(){ this.time+=1; }

    public void move(ConstantesLabyrinth.DIRECTIONS d) {
        switch (d) {
            case FRONT:
                this.pawn.y -= CASE_LENGTH;
                break;
            case BACK:
                this.pawn.y += CASE_LENGTH;
                break;
            case RIGHT:
                this.pawn.x += CASE_LENGTH;
                break;
            case LEFT:
                this.pawn.x -= CASE_LENGTH;
                break;
        }
    }

    public boolean canMove(ConstantesLabyrinth.DIRECTIONS direction){
        switch (direction) {
            case FRONT:
                if (this.pawn.x <= 0) return false;
                if (tray.getTile(this.pawn.x,this.pawn.y-1).isOccupied()){ return false; }
                if (tray.getTile(this.pawn.x,this.pawn.y-2).isOccupied()){ return false; }
                return true;

            case BACK:
                if (this.pawn.x <= NBRE_CASES) return false;
                if (tray.getTile(this.pawn.x, this.pawn.y+1).isOccupied()){ return false; }
                if (tray.getTile(this.pawn.x, this.pawn.y+2).isOccupied()){ return false; }
                return true;

            case RIGHT:
                if (this.pawn.y >=NBRE_CASES) return false;
                if (tray.getTile(this.pawn.x+1, this.pawn.y).isOccupied()){ return false; }
                if (tray.getTile(this.pawn.x+2, this.pawn.y).isOccupied()){ return false; }
                return true;

            case LEFT:
                if (this.pawn.y <=0) return false;
                if (tray.getTile(this.pawn.x-1, this.pawn.y).isOccupied()){ return false; }
                if (tray.getTile(this.pawn.x-2, this.pawn.y).isOccupied()){ return false; }
                return true;

            default:
                return false;
        }
    }

    public boolean isBlocked(Tray tray){
        hasAccessTo(tray);
        //It represent if the pan is on the last line, so he can finish the game
        for(int i=0;i<NBRE_CASES;i+=2) {
            if (can.contains(tray.getTile(i, 0))) return true;
        }
        return false;
    }


    public void hasAccessTo(Tray tray) {
        can.add(this.tray.getTile(this.pawn.x , this.pawn.y));
        int i=0;
        for(can.get(i); i == can.size(); i++){
            if (canMove(ConstantesLabyrinth.DIRECTIONS.FRONT)&& this.pawn.x<0) {
                can.add(tray.getTile(this.pawn.x+2,this.pawn.y));
            }
            if (canMove(ConstantesLabyrinth.DIRECTIONS.BACK) && this.pawn.x <NBRE_CASES) {
                can.add(tray.getTile(this.pawn.x-2,this.pawn.y));
            }
            if (canMove(ConstantesLabyrinth.DIRECTIONS.RIGHT)&& this.pawn.x <NBRE_CASES) {
                can.add(tray.getTile(this.pawn.x,this.pawn.y+2));
            }
            if (canMove(ConstantesLabyrinth.DIRECTIONS.LEFT)&& this.pawn.x>0) {
                can.add(tray.getTile(this.pawn.x,this.pawn.y-2));
            }
        }
    }


}
