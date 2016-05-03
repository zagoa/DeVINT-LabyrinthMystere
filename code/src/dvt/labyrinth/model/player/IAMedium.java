package dvt.labyrinth.model.player;

import static dvt.labyrinth.tools.ConstantesLabyrinth.*;

import dvt.labyrinth.game.Game;
import dvt.labyrinth.tools.ConstantesLabyrinth;
import dvt.labyrinth.tools.Position;
import dvt.labyrinth.model.essential.Tray;
import dvt.labyrinth.model.essential.Pawn;
import javafx.geometry.Pos;

import java.util.Queue;

/**
 * Created by user on 21/03/2016.
 */
public class IAMedium extends AdvancedIAs{

    public IAMedium(Pawn pawn, Position pos, Tray tray,Game game){
        super(pawn,pos,tray,game);
    }



    @Override
    public DIFFICULTY getType() {
        return DIFFICULTY.MOYEN;
    }


}
