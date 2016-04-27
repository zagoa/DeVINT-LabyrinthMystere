package dvt.labyrinth.model.player;

import dvt.labyrinth.game.Game;
import dvt.labyrinth.model.essential.Pawn;
import dvt.labyrinth.model.essential.Tray;
import dvt.labyrinth.tools.ConstantesLabyrinth;
import dvt.labyrinth.tools.Position;

/**
 * Created by user on 02/04/2016.
 */
public abstract class AdvancedIAs extends IA {

    /**
     * We create an IA with a predefined name (and soon enough a predefined pawn)
     *
     * @param pawn
     * @param pos  the initial position of the IA
     * @param tray
     */
    public AdvancedIAs(Pawn pawn, Position pos, Tray tray) {
        super(pawn, pos, tray, ConstantesLabyrinth.WALL_NUMBER);
        setPos(pos, tray);
    }

    @Override
    public abstract boolean move(Tray tray, ConstantesLabyrinth.DIRECTIONS directions);

    public abstract boolean moveAndWall(Tray tray, ConstantesLabyrinth.DIRECTIONS directions, Position position,Game game);

    /**
     * The full move method
     * @param tray the actual tray with both of the players
     * @param game the  game
     * @param directions here will be null
     * @return if we moved or not
     */
    @Override
    public abstract boolean completeMove(Tray tray, Game game, ConstantesLabyrinth.DIRECTIONS directions);

}
