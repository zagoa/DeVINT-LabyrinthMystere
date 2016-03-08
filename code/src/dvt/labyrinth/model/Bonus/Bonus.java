package dvt.labyrinth.model.bonus;

import dvt.labyrinth.Tile;
import dvt.labyrinth.model.Pawn;

/**
 * Created by etienne on 26/02/16.
 */
public abstract class Bonus{
    Tile tile;
    Pawn pawn;


    public Bonus(Tile tile, Pawn pawn) {
        this.tile=tile;
        this.pawn=pawn;
    }



}
