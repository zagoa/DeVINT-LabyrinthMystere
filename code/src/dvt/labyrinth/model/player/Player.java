package dvt.labyrinth.model.player;

import dvt.labyrinth.model.essential.Pawn;
import dvt.labyrinth.model.essential.Tile;
import dvt.labyrinth.model.essential.Tray;
import dvt.labyrinth.tools.ConstantesLabyrinth;
import dvt.labyrinth.tools.Position;

import java.util.ArrayList;

import static dvt.labyrinth.tools.ConstantesLabyrinth.*;


public abstract class Player {
    // His name
    protected String name;
    // Item
    private Pawn pawn;

    // Check if can set a wall
    protected ArrayList<Tile> can = new ArrayList<>();

    // His original position
    protected Position originalPos;
    // His position
    protected Position pos;


    // Is a real player ?
    protected boolean isABot;

    //Number of wall
    protected int nbWall;

    public Player(String name, Pawn pawn, Position pos, Tray tray, int nbWall) {
        this.name = name;
        this.pawn = pawn;
        this.originalPos = pos;
        this.isABot = pawn.getRes().isABot();
        this.nbWall = nbWall;

        setPos(pos, tray);
    }

    public abstract boolean move(Tray tray, DIRECTIONS directions);

    /**
     * We want to know if we can move towards a direction from our actual position
     *
     * @param tray      the tray
     * @param direction where we want to go
     * @return whether or not we can move to the wanted position ( we can't go to a position
     * if the tile(wanted position) is occupied (Wall,Player)
     */
    public boolean canMove(Tray tray, DIRECTIONS direction) {
        int x = pos.getX();
        int y = pos.getY();

        switch (direction) {
            case FRONT:
                return (y - CASE_LENGTH >= 0
                        && !tray.getTile(x, y - WALL_LENGTH).isOccupied()
                        && !tray.getTile(x, y - CASE_LENGTH).isOccupied()); // In map && wall not present && tile not occupied

            case BACK:
                return (y + CASE_LENGTH <= NBRE_CASES - 1
                        && !tray.getTile(x, y + WALL_LENGTH).isOccupied()
                        && !tray.getTile(x, y + CASE_LENGTH).isOccupied()); // In map && wall not present && tile not occupied

            case RIGHT:
                return (x + CASE_LENGTH <= NBRE_CASES - 1
                        && !tray.getTile(x + WALL_LENGTH, y).isOccupied()
                        && !tray.getTile(x + CASE_LENGTH, y).isOccupied()); // In map && wall not present && tile not occupied

            case LEFT:
                return (x - CASE_LENGTH >= 0
                        && !tray.getTile(x - WALL_LENGTH, y).isOccupied()
                        && !tray.getTile(x - CASE_LENGTH, y).isOccupied()); // In map && wall not present && tile not occupied

            default:
                return false;
        }
    }

    /**
     * Check if the player could end the game in the actual tray disposition
     *
     * @param tray
     * @return if the player can access to the last tray's line which means he can win and he isn't blocked
     */
    public boolean isBlocked(Tray tray) {
        can.clear();
        hasAccessTo(tray);
        //It represent if the pan is on the last line, so he can finish the game
        for (int i = 0; i < NBRE_CASES; i += 2) {
            for (int j = 0; j < NBRE_CASES; j += 2) {
                if (can.contains(tray.getTile(i, 0)) && can.contains(tray.getTile(j, NBRE_CASES - 1))) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Add in a list all the tile where the pawn can move to
     *
     * @param tray
     */
    public void hasAccessTo(Tray tray) {
        can.add(tray.getTile(getPosition().getX(), getPosition().getY()));

        int i;
        for (i = 0; can.size() > i; i++) {
            int x = can.get(i).getPosition().getX();
            int y = can.get(i).getPosition().getY();
            Position pos = new Position(x, y);

            if (checkMoveFromPosition(tray, DIRECTIONS.FRONT, pos) && !can.contains(tray.getTile(new Position(x, y - 2)))) {
                can.add(tray.getTile(x, y - 2));
            }
            if (checkMoveFromPosition(tray, DIRECTIONS.BACK, pos) && !can.contains(tray.getTile(new Position(x, y + 2)))) {
                can.add(tray.getTile(x, y + 2));
            }
            if (checkMoveFromPosition(tray, DIRECTIONS.RIGHT, pos) && !can.contains(tray.getTile(new Position(x + 2, y)))) {
                can.add(tray.getTile(x + 2, y));
            }
            if (checkMoveFromPosition(tray, DIRECTIONS.LEFT, pos) && !can.contains(tray.getTile(new Position(x - 2, y)))) {
                can.add(tray.getTile(x - 2, y));
            }


        }
    }

    /**
     * @param tray
     * @param pos       a position on the tray we want to "study"
     * @param direction the direction we want to go to
     * @return Check whether we can move a position towards a direction in advance. It will of use when we predict the IA
     * movements in advance
     */
    public boolean checkMoveFromPosition(Tray tray, ConstantesLabyrinth.DIRECTIONS direction, Position pos) {
        int x = pos.getX();
        int y = pos.getY();

        switch (direction) {
            case FRONT:
                return (y - CASE_LENGTH >= 0
                        && !tray.getTile(x, y - WALL_LENGTH).isOccupied()
                        && !tray.getTile(x, y - CASE_LENGTH).isOccupied()); // In map && wall not present && tile not occupied

            case BACK:
                return (y + CASE_LENGTH <= NBRE_CASES - 1
                        && !tray.getTile(x, y + WALL_LENGTH).isOccupied()
                        && !tray.getTile(x, y + CASE_LENGTH).isOccupied()); // In map && wall not present && tile not occupied

            case RIGHT:
                return (x + CASE_LENGTH <= NBRE_CASES - 1
                        && !tray.getTile(x + WALL_LENGTH, y).isOccupied()
                        && !tray.getTile(x + CASE_LENGTH, y).isOccupied()); // In map && wall not present && tile not occupied

            case LEFT:
                return (x - CASE_LENGTH >= 0
                        && !tray.getTile(x - WALL_LENGTH, y).isOccupied()
                        && !tray.getTile(x - CASE_LENGTH, y).isOccupied()); // In map && wall not present && tile not occupied

            default:
                return false;
        }
    }


    public Position getPosition() {
        return pos;
    }

    /**
     * Update the pawn position to the wanted one
     *
     * @param tray
     * @param newP the position we want to go to
     */
    public void updatePlayerPos(Tray tray, Position newP) {
        tray.getTile(newP).setPawn(pawn);
        tray.getTile(pos).setItem(null);

        pos = newP;
    }


    public void setPos(Position pos, Tray tray) {
        this.pos = pos;

        tray.getTile(pos).setPawn(pawn);
    }

    /**
     * @return whether or not the player has won
     */
    public boolean hasWon() {
        switch (originalPos.getY()) {
            case 0: // Top of the tray
                return (pos.getY() == NBRE_CASES - 1);

            case NBRE_CASES - 1:
                return (pos.getY() == 0);

            default:
                return false;
        }
    }


    public int getWonY() {
        switch (originalPos.getY()) {
            case 0: // Top of the tray
                return NBRE_CASES - 1;

            case NBRE_CASES - 1:
                return 0;

            default:
                return 0;
        }
    }

    public String getName() {
        return name;
    }

    public Pawn getPawn() {
        return pawn;
    }

    public boolean isABot() {
        return isABot;
    }

    public int getNbWall() {
        return nbWall;
    }

    public void setNbWall(int nbWall) {
        this.nbWall = nbWall;
    }

    public ArrayList<Tile> getCan() {
        return can;
    }


}
