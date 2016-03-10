package dvt.labyrinth.model;

import dvt.labyrinth.ConstantesLabyrinth;
import dvt.labyrinth.Position;
import dvt.labyrinth.Tile;
import dvt.labyrinth.Tray;


import java.util.ArrayList;
import java.util.Map;

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
    ArrayList<Tile> can = new ArrayList<>();
    private Map<Item, Position> items;


    public Player(String name, Pawn pawn,int time, boolean timeToPlay){
        this.name = name;
        this.pawn = pawn;
        this.time = time;
        this.timeToPlay = timeToPlay;
    }

    public void move(ConstantesLabyrinth.DIRECTIONS d) {
        int x = getPositionPlayer().getX();
        int y = getPositionPlayer().getY();
        switch (d) {
            case FRONT:
                if(canMove(ConstantesLabyrinth.DIRECTIONS.FRONT)) {
                    updatePlayerPos(new Position(x,y -= CASE_LENGTH) );
                    break;
                }
            case BACK:
                if(canMove(ConstantesLabyrinth.DIRECTIONS.BACK)) {
                    updatePlayerPos(new Position(x,y += CASE_LENGTH) );
                    break;
                }
            case RIGHT:
                if(canMove(ConstantesLabyrinth.DIRECTIONS.RIGHT)) {
                    updatePlayerPos(new Position(x+CASE_LENGTH, y) );
                    break;
                }
            case LEFT:
                if(canMove(ConstantesLabyrinth.DIRECTIONS.LEFT)) {
                    updatePlayerPos(new Position(x-CASE_LENGTH, y) );
                    break;
                }
        }
    }

    public boolean canMove(ConstantesLabyrinth.DIRECTIONS direction){
        int x = getPositionPlayer().getX();
        int y = getPositionPlayer().getY();
        switch (direction) {
            case FRONT:
                if (y == 0) return false;
                if (tray.getTile(x,y-1).isOccupied()){ return false; }
                if (tray.getTile(x,y-2).isOccupied()){ return false; }
                return true;

            case BACK:
                if (y == NBRE_CASES-1) return false;
                if (tray.getTile(x, y+1).isOccupied()){ return false; }
                if (tray.getTile(x, y+2).isOccupied()){ return false; }
                return true;

            case RIGHT:
                if (x ==NBRE_CASES-1) return false;
                if (tray.getTile(x+1, y).isOccupied()){ return false; }
                if (tray.getTile(x+2, y).isOccupied()){ return false; }
                return true;

            case LEFT:
                if (x ==0) return false;
                if (tray.getTile(x-1, y).isOccupied()){ return false; }
                if (tray.getTile(x-2, y).isOccupied()){ return false; }
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
        can.add(this.tray.getTile(getPositionPlayer().getX() , getPositionPlayer().getY()));
        int i=0;
        for(can.get(i); i == can.size(); i++){
            int x = can.get(i).getPosition().getX();
            int y = can.get(i).getPosition().getY();
            if (canMove(ConstantesLabyrinth.DIRECTIONS.FRONT)&& !can.contains(tray.getTile(x-2,y))) {
                can.add(tray.getTile(x+2,y));
            }
            if (canMove(ConstantesLabyrinth.DIRECTIONS.BACK) && !can.contains(tray.getTile(x+2,y))) {
                can.add(tray.getTile(x-2,y));
            }
            if (canMove(ConstantesLabyrinth.DIRECTIONS.RIGHT) && !can.contains(tray.getTile(x,y+2))) {
                can.add(tray.getTile(x,y+2));
            }
            if (canMove(ConstantesLabyrinth.DIRECTIONS.LEFT) && !can.contains(tray.getTile(x,y-2))) {
                can.add(tray.getTile(x,y-2));
            }
        }
    }

    public Position getPositionPlayer() { return items.get(pawn); }

    private void updatePlayerPos(Position newP) {
        tray.getTile(newP).setItem(pawn);
        tray.getTile(items.get(pawn)).setItem(null);
        items.put(pawn, newP);
    }


    public boolean isTimeToPlay(){
        return timeToPlay;
    }

    public void setTimeToPlay(){
        if(this.time%2==0) { this.timeToPlay = true; }
        else {  this.timeToPlay = false; }
    }

    public void turnFinished(){ this.time+=1; }

}
