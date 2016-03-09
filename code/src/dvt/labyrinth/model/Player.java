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



}
