package dvt.labyrinth.model.bonus;

import dvt.labyrinth.model.essential.Tile;
import dvt.labyrinth.model.essential.Pawn;

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
