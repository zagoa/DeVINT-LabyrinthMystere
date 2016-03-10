package dvt.labyrinth.model;

import dvt.labyrinth.Position;
import dvt.labyrinth.Tile;
import dvt.labyrinth.Tray;


import java.util.ArrayList;
import java.util.Map;

import static dvt.labyrinth.ConstantesLabyrinth.*;

/**
 * Created by user on 02/03/2016.
 */

public class Player {
    // His name
    private String name;
    // Item
    private Pawn pawn;

    private int time;
    private boolean timeToPlay;

    // Check if can set a wall
    private ArrayList<Tile> can = new ArrayList<>();

    // His position
    private Position pos;

    public Player(String name, Pawn pawn, int time, boolean timeToPlay){
        this.name = name;
        this.pawn = pawn;
        this.time = time;
        this.timeToPlay = timeToPlay;
    }

    public boolean move(Tray tray, DIRECTIONS d) {
        int x = pos.getX();
        int y = pos.getY();

        switch (d) {
            case FRONT:
                if(canMove(tray, DIRECTIONS.FRONT))
                    updatePlayerPos(tray, new Position(x, y - CASE_LENGTH));
                break;

            case BACK:
                if(canMove(tray, DIRECTIONS.BACK))
                    updatePlayerPos(tray, new Position(x, y + CASE_LENGTH));
                break;

            case RIGHT:
                if(canMove(tray, DIRECTIONS.RIGHT))
                    updatePlayerPos(tray, new Position(x + CASE_LENGTH, y));
                break;

            case LEFT:
                if(canMove(tray, DIRECTIONS.LEFT))
                    updatePlayerPos(tray, new Position(x - CASE_LENGTH, y));
                break;

            default:
                break;
        }

        return true;
    }

    public boolean canMove(Tray tray, DIRECTIONS direction){
        int x = pos.getX();
        int y = pos.getY();

        switch (direction) {
            case FRONT:
                return (y-CASE_LENGTH >= 0
                        && !tray.getTile(x, y-WALL_LENGTH).isOccupied()
                        && !tray.getTile(x, y-CASE_LENGTH).isOccupied()); // In map && wall not present && tile not occupied

            case BACK:
                return (y+CASE_LENGTH <= NBRE_CASES-1
                        && !tray.getTile(x, y+WALL_LENGTH).isOccupied()
                        && !tray.getTile(x, y+CASE_LENGTH).isOccupied()); // In map && wall not present && tile not occupied

            case RIGHT:
                return (x+CASE_LENGTH <= NBRE_CASES-1
                        && !tray.getTile(x+WALL_LENGTH, y).isOccupied()
                        && !tray.getTile(x+CASE_LENGTH, y).isOccupied()); // In map && wall not present && tile not occupied

            case LEFT:
                return (x-CASE_LENGTH >= 0
                        && !tray.getTile(x-WALL_LENGTH, y).isOccupied()
                        && !tray.getTile(x-CASE_LENGTH, y).isOccupied()); // In map && wall not present && tile not occupied

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
        can.add(tray.getTile(getPosition().getX() , getPosition().getY()));
        int i=0;
        for(can.get(i); i == can.size(); i++){
            int x = can.get(i).getPosition().getX();
            int y = can.get(i).getPosition().getY();
            if (canMove(tray, DIRECTIONS.FRONT)&& !can.contains(tray.getTile(x-2,y))) {
                can.add(tray.getTile(x+2,y));
            }
            if (canMove(tray, DIRECTIONS.BACK) && !can.contains(tray.getTile(x+2,y))) {
                can.add(tray.getTile(x-2,y));
            }
            if (canMove(tray, DIRECTIONS.RIGHT) && !can.contains(tray.getTile(x,y+2))) {
                can.add(tray.getTile(x,y+2));
            }
            if (canMove(tray, DIRECTIONS.LEFT) && !can.contains(tray.getTile(x,y-2))) {
                can.add(tray.getTile(x,y-2));
            }
        }
    }

    public Position getPosition() { return pos; }

    private void updatePlayerPos(Tray tray, Position newP) {
        tray.getTile(newP).setPawn(pawn);
        tray.getTile(pos).setItem(null);

        pos = newP;
    }

    public boolean isTimeToPlay(){
        return timeToPlay;
    }

    public void setTimeToPlay(){
        if(this.time%2==0) { this.timeToPlay = true; }
        else {  this.timeToPlay = false; }
    }

    public void turnFinished(){ this.time+=1; }

    public void setPos(Position pos, Tray tray) {
        this.pos = pos;

        tray.getTile(pos).setPawn(pawn);
    }
}
