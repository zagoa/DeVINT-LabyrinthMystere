package dvt.labyrinth.model.Bonus;

import dvt.labyrinth.Tile;
import dvt.labyrinth.model.Item;
import dvt.labyrinth.model.Pawn;

/**
 * Created by etienne on 26/02/16.
 */
public abstract class Bonus extends Item {
    Tile tile;
    Pawn pawn;


    public Bonus(Tile tile, Pawn pawn) {
        this.tile=tile;
        this.pawn=pawn;
    }



}
