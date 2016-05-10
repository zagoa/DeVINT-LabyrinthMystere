package dvt.labyrinth.model.player;

import dvt.labyrinth.game.Game;
import dvt.labyrinth.tools.ConstantesLabyrinth;
import dvt.labyrinth.tools.Position;
import dvt.labyrinth.model.essential.Tray;
import dvt.labyrinth.model.essential.Pawn;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by user on 21/03/2016.
 */
public class IAEasy extends IA {

    public IAEasy(Pawn pawn, Position pos, Tray tray,Game game){
        super(pawn,pos,tray,0,game);
    }



    @Override
    public ConstantesLabyrinth.DIFFICULTY getType() {
        return ConstantesLabyrinth.DIFFICULTY.FACILE;
    }

}
