package dvt.labyrinth.model.Bonus;

import dvt.labyrinth.Tile;
import dvt.labyrinth.model.Pawn;

/**
 * Created by user on 01/03/2016.
 */
public class TimeBonus extends Bonus {
    int time;


    public TimeBonus (int time,Tile tile,Pawn pawn){
        super(tile,pawn);
        this.time = time;
    }
}
