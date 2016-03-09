package dvt.labyrinth;

import dvt.devint.Jeu;
import dvt.labyrinth.actions.MovePlayerAction;
import dvt.labyrinth.model.Arrow;
import dvt.labyrinth.model.Item;
import dvt.labyrinth.model.Pawn;
import javafx.geometry.Pos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dvt.labyrinth.ConstantesLabyrinth.*;

/**
 * Created by Arnaud on 26/02/2016.
 */
public class Labyrinth extends Jeu{
    private JPanel world;
    private Tray tray;
    private Map<Item, Position> items;
    private Pawn currentPlayer;
    ArrayList<Tile> can = new ArrayList<>();

    @Override
    public void init() {
        world = new JPanel();
        world.setBackground(getBackground());

        this.add(world);

        world.setLayout(new GridBagLayout());

        tray = new Tray();

        items = new HashMap<>();

        addPlayers();

        showTray();
    }

    @Override
    public void update() {
        checkMovePositions();
    }

    public void checkMovePositions() {
        int x = getPositionPlayer().getX();
        int y = getPositionPlayer().getY();

        Tile tile[][] = tray.getTray();
        if (x-CASE_LENGTH >= 0)
            tile[y][x - CASE_LENGTH].setHighlighted(new Arrow(RESSOURCES.ARROW_LEFT)); // Left
        if (x+CASE_LENGTH < NBRE_CASES)
            tile[y][x+CASE_LENGTH].setHighlighted(new Arrow(RESSOURCES.ARROW_RIGHT)); // Right
        if (y-CASE_LENGTH >= 0)
            tile[y-CASE_LENGTH][x].setHighlighted(new Arrow(RESSOURCES.ARROW_UP)); // Down
        if (y+CASE_LENGTH < NBRE_CASES)
            tile[y + CASE_LENGTH][x].setHighlighted(new Arrow(RESSOURCES.ARROW_DOWN)); // Up

        addControl("DOWN", new MovePlayerAction(this, DIRECTIONS.BACK));
        addControl("UP", new MovePlayerAction(this, DIRECTIONS.FRONT));
        addControl("LEFT", new MovePlayerAction(this, DIRECTIONS.LEFT));
        addControl("RIGHT", new MovePlayerAction(this, DIRECTIONS.RIGHT));
    }

    public void move(ConstantesLabyrinth.DIRECTIONS d) {
        int x = getPositionPlayer().getX();
        int y = getPositionPlayer().getY();
        switch (d) {
            case FRONT:
                if(canMove(ConstantesLabyrinth.DIRECTIONS.FRONT)) {
                    updatePlayerPos(new Position(x,y -= CASE_LENGTH) );
                    break;
                }
            case BACK:
                if(canMove(ConstantesLabyrinth.DIRECTIONS.BACK)) {
                    updatePlayerPos(new Position(x,y += CASE_LENGTH) );
                    break;
                }
            case RIGHT:
                if(canMove(ConstantesLabyrinth.DIRECTIONS.RIGHT)) {
                    updatePlayerPos(new Position(x+CASE_LENGTH, y) );
                    break;
                }
            case LEFT:
                if(canMove(ConstantesLabyrinth.DIRECTIONS.LEFT)) {
                    updatePlayerPos(new Position(x-CASE_LENGTH, y) );
                    break;
                }
        }
    }

    public boolean canMove(ConstantesLabyrinth.DIRECTIONS direction){
        int x = getPositionPlayer().getX();
        int y = getPositionPlayer().getY();
        switch (direction) {
            case FRONT:
                if (y == 0) return false;
                if (tray.getTile(x,y-1).isOccupied()){ return false; }
                if (tray.getTile(x,y-2).isOccupied()){ return false; }
                return true;

            case BACK:
                if (y == NBRE_CASES-1) return false;
                if (tray.getTile(x, y+1).isOccupied()){ return false; }
                if (tray.getTile(x, y+2).isOccupied()){ return false; }
                return true;

            case RIGHT:
                if (x ==NBRE_CASES-1) return false;
                if (tray.getTile(x+1, y).isOccupied()){ return false; }
                if (tray.getTile(x+2, y).isOccupied()){ return false; }
                return true;

            case LEFT:
                if (x ==0) return false;
                if (tray.getTile(x-1, y).isOccupied()){ return false; }
                if (tray.getTile(x-2, y).isOccupied()){ return false; }
                return true;

            default:
                return false;
        }
    }

    public boolean isBlocked(Tray tray){
        hasAccessTo(tray);
        //It represent if the pan is on the last line, so he can finish the game
        for(int i=0;i<NBRE_CASES;i+=2) {
            if (can.contains(tray.getTile(i, 0))) return true;
        }
        return false;
    }


    public void hasAccessTo(Tray tray) {
        can.add(this.tray.getTile(getPositionPlayer().getX() , getPositionPlayer().getY()));
        int i=0;
        for(can.get(i); i == can.size(); i++){
            int x = can.get(i).getPosition().getX();
            int y = can.get(i).getPosition().getY();
            if (canMove(ConstantesLabyrinth.DIRECTIONS.FRONT)&& !can.contains(tray.getTile(x-2,y))) {
                can.add(tray.getTile(x+2,y));
            }
            if (canMove(ConstantesLabyrinth.DIRECTIONS.BACK) && !can.contains(tray.getTile(x+2,y))) {
                can.add(tray.getTile(x-2,y));
            }
            if (canMove(ConstantesLabyrinth.DIRECTIONS.RIGHT) && !can.contains(tray.getTile(x,y+2))) {
                can.add(tray.getTile(x,y+2));
            }
            if (canMove(ConstantesLabyrinth.DIRECTIONS.LEFT) && !can.contains(tray.getTile(x,y-2))) {
                can.add(tray.getTile(x,y-2));
            }
        }
    }

    @Override
    public void render() {
        world.setBackground(getBackground());
//        showTray();
    }

    @Override
    public void reset() {

    }

    public void showTray() {
        Tile tile[][] = tray.getTray();

        for (int y = 0; y < tile.length; y++) {
            for (int x = 0; x < tile[y].length; x++) {
                if (x % 2 == 1)
                    tile[y][x].getComponent().setPreferredSize(new Dimension(10, ((y % 2 == 1) ? 10 : 50)));
                else
                    tile[y][x].getComponent().setPreferredSize(new Dimension(100, ((y % 2 == 1) ? 10 : 50)));

                world.add(tile[y][x].getComponent(), getGridBagConstraints(x,y));
            }
        }
    }

    public GridBagConstraints getGridBagConstraints(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.CENTER;
        c.gridx = x;
        c.gridy = y;

        if (y % 2 == 1) // Ligne de murs
            c.gridwidth = (x % 2 == 0) ? 2 : 1; // Enlever la petite case
        else
            c.gridwidth = 1;

        c.gridheight = 1;

        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;

        return c;
    }

    public void addPlayers() {
        Pawn player = new Pawn("Bernard", RESSOURCES.THEO);
        Position pos = new Position(8, 10);
        tray.getTile(pos).setItem(player);

        items.put(player, pos);

        currentPlayer = player;
    }

    public void unHighlightAll() {
        int x = getPositionPlayer().getX();
        int y = getPositionPlayer().getY();

        if (x-CASE_LENGTH >= 0)
            tray.getTile(x-CASE_LENGTH, y).unHighlight();

        if (x+CASE_LENGTH < NBRE_CASES)
            tray.getTile(x+CASE_LENGTH, y).unHighlight();

        if (y-CASE_LENGTH >= 0)
            tray.getTile(x, y-CASE_LENGTH).unHighlight();

        if (y+CASE_LENGTH < NBRE_CASES)
            tray.getTile(x, y+CASE_LENGTH).unHighlight();

        Tile tile[][] = tray.getTray();

        for (int y1 = 0; y1 < tile.length; y1++) {
            for (int x1 = 0; x1 < tile[y1].length; x1++) {
                tile[y1][x1].unHighlight();
            }
        }

        showTray();
    }

    public void movePlayer(DIRECTIONS d) {
        int xAfter, yAfter;

        switch (d) {
            case FRONT:
                xAfter = getPositionPlayer().getX();
                yAfter = getPositionPlayer().getY()-CASE_LENGTH;
                break;

            case BACK:
                xAfter = getPositionPlayer().getX();
                yAfter = getPositionPlayer().getY()+CASE_LENGTH;
                break;

            case LEFT:
                xAfter = getPositionPlayer().getX()-CASE_LENGTH;
                yAfter = getPositionPlayer().getY();
                break;

            case RIGHT:
                xAfter = getPositionPlayer().getX()+CASE_LENGTH;
                yAfter = getPositionPlayer().getY();
                break;

            default:
                return;
        }

        if (xAfter < 0 || xAfter >= NBRE_CASES
                || yAfter < 0 || yAfter >= NBRE_CASES)
            return;

        Position tmp = new Position(xAfter, yAfter);

        unHighlightAll();

        updatePlayerPos(tmp);

        showTray();
    }

    private void updatePlayerPos(Position newP) {
        tray.getTile(newP).setItem(currentPlayer);
        tray.getTile(items.get(currentPlayer)).setItem(null);
        items.put(currentPlayer, newP);
    }

    public Position getPositionPlayer() {
        return items.get(currentPlayer);
    }
}
