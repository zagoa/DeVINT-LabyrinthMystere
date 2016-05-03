package dvt.labyrinth.model.player;

import dvt.labyrinth.game.Game;
import dvt.labyrinth.tools.ConstantesLabyrinth;
import dvt.labyrinth.tools.Position;
import dvt.labyrinth.model.essential.Tray;
import dvt.labyrinth.model.essential.Pawn;

import static dvt.labyrinth.tools.ConstantesLabyrinth.*;


public class IAHard extends AdvancedIAs{

    public IAHard(Pawn pawn, Position pos, Tray tray,Game game){
        super(pawn,pos,tray,game);
    }



    @Override
    public DIFFICULTY getType() {
        return DIFFICULTY.DIFFICILE;
    }
}
