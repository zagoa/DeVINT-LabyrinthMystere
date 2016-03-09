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

        Tile tile[][] = tray.getTray();

        for (int y1 = 0; y1 < tile.length; y1++) {
            for (int x1 = 0; x1 < tile[y1].length; x1++) {
                tile[y1][x1].unHighlight();
            }
        }
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
