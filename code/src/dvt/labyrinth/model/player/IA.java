package dvt.labyrinth.model.player;

import dvt.labyrinth.tools.ConstantesLabyrinth;
import dvt.labyrinth.tools.Position;
import dvt.labyrinth.model.essential.Tray;
import dvt.labyrinth.model.essential.Pawn;

import java.util.Queue;


public abstract class IA extends Player{
    //A queue for the next decisions taken by the computer
    Queue<ConstantesLabyrinth.DIRECTIONS> decision;


    /**
     * We create an IA with a predefined name (and soon enough a predefined pawn)
     * @param pawn
     * @param pos the initial position of the IA
     * @param tray
     */
    public IA(Pawn pawn, Position pos, Tray tray, int nbWall){
        super("Computer",pawn,pos,tray,nbWall);
        setPos(pos, tray);
    }

    @Override
    public abstract boolean move(Tray tray, ConstantesLabyrinth.DIRECTIONS directions);

    /**
     * A strategy where we choose to move to the front if we can, if not to the right,
     * and then depending of the possible movements we apply other strategies
     * @param tray
     */
    public void strategyIA(Tray tray){
        if (canMove(tray, ConstantesLabyrinth.DIRECTIONS.FRONT)) decision.add(ConstantesLabyrinth.DIRECTIONS.FRONT);
        else if (!canMove(tray, ConstantesLabyrinth.DIRECTIONS.RIGHT) && !canMove(tray, ConstantesLabyrinth.DIRECTIONS.LEFT)) strategyIABack(tray);
        else if (!canMove(tray, ConstantesLabyrinth.DIRECTIONS.RIGHT) && canMove(tray, ConstantesLabyrinth.DIRECTIONS.LEFT)) strategyIALeft(tray);
        else decision.add(ConstantesLabyrinth.DIRECTIONS.RIGHT);
    }

    /**
     *  A strategy where we choose to move to the front if we can, if not to the left,
     *  and then depending of the possible movements we apply other strategies
     * @param tray
     */
    public void strategyIALeft(Tray tray){
        decision.add(ConstantesLabyrinth.DIRECTIONS.LEFT);
        if(checkMoveFromPosition(tray,convertDirectionToPosition(ConstantesLabyrinth.DIRECTIONS.LEFT), ConstantesLabyrinth.DIRECTIONS.LEFT))
            decision.add(ConstantesLabyrinth.DIRECTIONS.LEFT);
    }

    /**
     * A strategy only used when moving back is the only possible option
     * @param tray
     */
    public void strategyIABack(Tray tray){
        decision.add(ConstantesLabyrinth.DIRECTIONS.BACK);
        if(checkMoveFromPosition(tray,convertDirectionToPosition(ConstantesLabyrinth.DIRECTIONS.RIGHT), ConstantesLabyrinth.DIRECTIONS.RIGHT))
            decision.add(ConstantesLabyrinth.DIRECTIONS.RIGHT);
        else if (checkMoveFromPosition(tray,convertDirectionToPosition(ConstantesLabyrinth.DIRECTIONS.RIGHT), ConstantesLabyrinth.DIRECTIONS.RIGHT)){
            decision.add(ConstantesLabyrinth.DIRECTIONS.LEFT);
            if (checkMoveFromPosition(tray,convertDirectionToPosition(ConstantesLabyrinth.DIRECTIONS.RIGHT), ConstantesLabyrinth.DIRECTIONS.RIGHT))
                decision.add(ConstantesLabyrinth.DIRECTIONS.LEFT);
        }
    }

    /**
     * @param direction the direction we want to go to
     * @return the position corresponding to the direction we want to go from the pawn position
     */
    public Position convertDirectionToPosition(ConstantesLabyrinth.DIRECTIONS direction){
        switch (direction) {
            case FRONT:
                this.pos.setX(this.pos.getX()-1);
                break;
            case BACK:
                this.pos.setX(this.pos.getX()+1);
                break;
            case RIGHT:
                this.pos.setY(this.pos.getY()+1);
                break;
            case LEFT:
                this.pos.setY(this.pos.getY()-1);
                break;
            default:
                break;
        }
        return pos;
    }

    /**
     *
     * @param tray
     * @param position a position on the tray we want to "study"
     * @param direction the direction we want to go to
     * @return Check whether we can move a position towards a direction in advance. It will of use when we predict the IA
     * movements in advance
     */
    public boolean checkMoveFromPosition(Tray tray, Position position, ConstantesLabyrinth.DIRECTIONS direction){
        Position savePos = this.pos;
        setPos(position,tray);
        boolean i = canMove(tray,direction);
        setPos(savePos,tray);
        return i;
    }

}
