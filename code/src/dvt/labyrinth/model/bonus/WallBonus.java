package dvt.labyrinth.model.bonus;

import dvt.labyrinth.model.essential.Tile;
import dvt.labyrinth.model.essential.Pawn;
import dvt.labyrinth.model.essential.Wall;

/**
 * Created by user on 02/03/2016.
 */
public class WallBonus extends Bonus {
    Wall wall;

    public WallBonus(Tile tile, Pawn pawn,Wall wall){
        super(tile,pawn);
        this.wall=wall;
    }
}
