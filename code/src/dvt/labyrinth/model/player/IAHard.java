package dvt.labyrinth.model.player;

import dvt.labyrinth.game.Game;
import dvt.labyrinth.tools.ConstantesLabyrinth;
import dvt.labyrinth.tools.Position;
import dvt.labyrinth.model.essential.Tray;
import dvt.labyrinth.model.essential.Pawn;

import static dvt.labyrinth.tools.ConstantesLabyrinth.*;


public class IAHard extends AdvancedIAs{
    private static int counter =0;

    public IAHard(Pawn pawn, Position pos, Tray tray,Game game){
        super(pawn,pos,tray,game);
    }


    /**
     * The IA moves and positions walls in front of the other player
     *
     * @param directions the direction we want to move to
     * @param position the position of the other player
     * @return if the IA has performed the action successfully or not
     */
    @Override
    public boolean moveAndWall(DIRECTIONS directions, Position position){
        counter++;
        if(position.getX() < config.get(CONFIG.LENGTH)-CASE_LENGTH && counter%3==0
                && tray.canSetAWall(DIRECTIONS.RIGHT,new Position(position.getX(),position.getY()-1))
                && !tray.getTile(new Position(position.getX()+2,position.getY()-1)).isOccupied()){

            // Get tile where to set a wall
            tray.getTile(new Position(position.getX(), position.getY() - 1)).positionWall();

            // If we have walls more than 1 unit
            if (config.get(CONFIG.WALL) > 0) {
                // Get the next tile where to set the wall
                tray.getTile(new Position(position.getX() + CASE_LENGTH, position.getY() - 1)).positionWall();

                // Fill the gap
                game.fillGap(DIRECTIONS.RIGHT, new Position(position.getX() + 2, position.getY() - 1));
            }

            // Speak & pause...
            putWall(game);

            return true;
        }
        else if (counter%3==0
                && tray.canSetAWall(DIRECTIONS.LEFT,new Position(position.getX(),position.getY()-1))
                && !tray.getTile(new Position(position.getX(),position.getY()-1)).isOccupied()){
            // Get tile where to set a wall
            tray.getTile(new Position(position.getX(),position.getY() - 1)).positionWall();

            // If we have walls more than 1 unit
            if (config.get(CONFIG.WALL) > 0) {
                // Get the next tile where to set the wall
                tray.getTile(new Position(position.getX() - 2, position.getY() - 1)).positionWall();

                // Fill the gap
                game.fillGap(DIRECTIONS.LEFT, new Position(position.getX() - 2, position.getY() - 1));
            }

            // Speak & pause...
            putWall(game);

            return true;
        }

        return completeMove(directions);
    }

    /**
     * Read the direction from the queue in order to move the IA pawn
     * @return whether the bot moved or not
     */
    @Override
    public boolean move(DIRECTIONS directions){
        decision.add(ConstantesLabyrinth.DIRECTIONS.BACK);
        if ((!decision.isEmpty())) {
            if (canMove(previous = decision.poll())){
                updatePlayerPos(convertDirectionToPosition(previous));
                return true;
            }

            else {
                decision.clear();
                strategyIA();
                move(null);
                return true;
            }
        }
        else {
            strategyIA();
            move(null);
            return true;
        }
    }


    /**
     * The full move method
     *
     * @param directions here will be null
     * @return if we moved or not
     */
    public boolean completeMove(DIRECTIONS directions){
        boolean  i = move(null);
        hasMoved(previous);
        return i;
    }

    @Override
    public DIFFICULTY getType() {
        return DIFFICULTY.DIFFICILE;
    }
}
