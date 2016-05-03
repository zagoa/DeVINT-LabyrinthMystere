package dvt.labyrinth.model.player;

import dvt.labyrinth.game.Game;
import dvt.labyrinth.model.essential.Arrow;
import dvt.labyrinth.model.essential.Pawn;
import dvt.labyrinth.model.essential.Tile;
import dvt.labyrinth.model.essential.Tray;
import dvt.labyrinth.tools.ConstantesLabyrinth;
import dvt.labyrinth.tools.Position;

import java.awt.*;
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
    // The tray
    protected Tray tray;
    // The game
    protected Game game;


    // Is a real player ?
    private boolean isABot;

    //Number of wall
    protected int nbWall;

    public Player(String name, Pawn pawn, Position pos, Tray tray, int nbWall, boolean bot,Game game) {
        this.name = name;
        this.pawn = pawn;
        this.originalPos = pos;
        this.nbWall = nbWall;
        this.isABot = bot;
        this.tray = tray;
        this.game = game;

        setPos(pos);
    }

    public abstract boolean move(DIRECTIONS directions);

    /**
     * We want to know if we can move towards a direction from our actual position
     *
     * @param direction where we want to go
     * @return whether or not we can move to the wanted position ( we can't go to a position
     * if the tile(wanted position) is occupied (Wall,Player)
     */
    public boolean canMove(DIRECTIONS direction) {
        int x = pos.getX();
        int y = pos.getY();

        switch (direction) {
            case FRONT:
                return (y - CASE_LENGTH >= 0
                        && !tray.getTile(x, y - WALL_LENGTH).isOccupied()
                        && !tray.getTile(x, y - CASE_LENGTH).isOccupied()); // In map && wall not present && tile not occupied

            case BACK:
                return (y + CASE_LENGTH <= config.get(CONFIG.LENGTH) - 1
                        && !tray.getTile(x, y + WALL_LENGTH).isOccupied()
                        && !tray.getTile(x, y + CASE_LENGTH).isOccupied()); // In map && wall not present && tile not occupied

            case RIGHT:
                return (x + CASE_LENGTH <= config.get(CONFIG.LENGTH) - 1
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
     * @return if the player can access to the last tray's line which means he can win and he isn't blocked
     */
    public boolean isBlocked() {
        can.clear();
        hasAccessTo();
        //It represent if the pan is on the last line, so he can finish the game
        for (int i = 0; i < config.get(CONFIG.LENGTH); i += 2) {
            for (int j = 0; j < config.get(CONFIG.LENGTH); j += 2) {
                if (can.contains(tray.getTile(i, 0)) && can.contains(tray.getTile(j, config.get(CONFIG.LENGTH) - 1))) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Add in a list all the tile where the pawn can move to
     *
     */
    public void hasAccessTo() {
        can.add(tray.getTile(getPosition().getX(), getPosition().getY()));

        int i;
        for (i = 0; can.size() > i; i++) {
            int x = can.get(i).getPosition().getX();
            int y = can.get(i).getPosition().getY();
            Position pos = new Position(x, y);

            if (checkMoveFromPosition(DIRECTIONS.FRONT, pos) && !can.contains(tray.getTile(new Position(x, y - 2)))) {
                can.add(tray.getTile(x, y - 2));
            }
            if (checkMoveFromPosition(DIRECTIONS.BACK, pos) && !can.contains(tray.getTile(new Position(x, y + 2)))) {
                can.add(tray.getTile(x, y + 2));
            }
            if (checkMoveFromPosition(DIRECTIONS.RIGHT, pos) && !can.contains(tray.getTile(new Position(x + 2, y)))) {
                can.add(tray.getTile(x + 2, y));
            }
            if (checkMoveFromPosition(DIRECTIONS.LEFT, pos) && !can.contains(tray.getTile(new Position(x - 2, y)))) {
                can.add(tray.getTile(x - 2, y));
            }


        }
    }

    /**
     * We check if from the wanted position we can move towards the wanted direction
     *
     * @param pos       a position on the tray we want to "study"
     * @param direction the direction we want to go to
     * @return Check whether we can move a position towards a direction in advance. It will of use when we predict the IA
     * movements in advance
     */
    public boolean checkMoveFromPosition(ConstantesLabyrinth.DIRECTIONS direction, Position pos) {
        int x = pos.getX();
        int y = pos.getY();

        switch (direction) {
            case FRONT:
                return (y - CASE_LENGTH >= 0
                        && !tray.getTile(x, y - WALL_LENGTH).isOccupied()
                        && !tray.getTile(x, y - CASE_LENGTH).isOccupied()); // In map && wall not present && tile not occupied

            case BACK:
                return (y + CASE_LENGTH <= config.get(CONFIG.LENGTH) - 1
                        && !tray.getTile(x, y + WALL_LENGTH).isOccupied()
                        && !tray.getTile(x, y + CASE_LENGTH).isOccupied()); // In map && wall not present && tile not occupied

            case RIGHT:
                return (x + CASE_LENGTH <= config.get(CONFIG.LENGTH) - 1
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
     * @param newP the position we want to go to
     */
    public void updatePlayerPos(Position newP) {
        tray.getTile(newP).setPawn(pawn);
        tray.getTile(pos).setItem(null);
        if(this.isABot()) { //old position of the bot
            tray.getTile(pos).setHighlighted(new Arrow(RESOURCES.ARROW_CARDINAL));

        }

        pos = newP;
    }


    public void setPos(Position pos) {
        this.pos = pos;

        tray.getTile(pos).setPawn(pawn);
    }

    /**
     * @return whether or not the player has won
     */
    public boolean hasWon() {
        if (originalPos.getY() == 0)
            return (pos.getY() == config.get(CONFIG.LENGTH) - 1);
        else if (originalPos.getY() == (config.get(CONFIG.LENGTH) - 1))
            return (pos.getY() == 0);
        return false;
    }


    public int getWonY() {
        if (originalPos.getY() == 0)
            return config.get(CONFIG.LENGTH) - 1;

        return 0;
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

    public boolean checkCanMove(Player player) {
        return (player.canMove(DIRECTIONS.RIGHT)
                || player.canMove(DIRECTIONS.LEFT)
                || player.canMove(DIRECTIONS.FRONT)
                || player.canMove(DIRECTIONS.BACK));

    }




}
