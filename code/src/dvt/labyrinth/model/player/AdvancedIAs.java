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
    public AdvancedIAs(Pawn pawn, Position pos, Tray tray,Game  game) {
        super(pawn, pos, tray, ConstantesLabyrinth.WALL_NUMBER,game);
        setPos(pos);
    }

    @Override
    public abstract boolean move(ConstantesLabyrinth.DIRECTIONS directions);

    public abstract boolean moveAndWall(ConstantesLabyrinth.DIRECTIONS directions, Position position);

    /**
     * The full move method
     *
     * @param directions here will be null
     * @return if we moved or not
     */
    @Override
    public abstract boolean completeMove(ConstantesLabyrinth.DIRECTIONS directions);

}
