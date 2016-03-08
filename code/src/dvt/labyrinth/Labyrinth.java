package dvt.labyrinth;

import dvt.devint.Jeu;
import dvt.labyrinth.actions.MovePlayerAction;
import dvt.labyrinth.model.Item;
import dvt.labyrinth.model.Pawn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import static dvt.labyrinth.ConstantesLabyrinth.*;

/**
 * Created by Arnaud on 26/02/2016.
 */
public class Labyrinth extends Jeu{
    private JPanel world;
    private Tray tray;
    private List<Item> items;
    private Pawn player;

    @Override
    public void init() {
        world = new JPanel();
        world.setBackground(getBackground());

        this.add(world);

        world.setLayout(new GridBagLayout());

        tray = new Tray();

        items = new ArrayList<>();
        player = new Pawn("Bernard", RESSOURCES.THEO, 8, 10);
        items.add(player);

        addPlayers();

        showTray();
    }

    @Override
    public void update() {
        checkMovePositions();
    }

    public void checkMovePositions() {
        int x = player.getX();
        int y = player.getY();

        Tile tile[][] = tray.getTray();
        if (x-CASE_LENGTH >= 0)
            tile[y][x - CASE_LENGTH].setHighlighted();
        if (x+CASE_LENGTH < NBRE_CASES)
            tile[y][x+CASE_LENGTH].setHighlighted();
        if (y-CASE_LENGTH >= 0)
            tile[y-CASE_LENGTH][x].setHighlighted();
        if (y+CASE_LENGTH < NBRE_CASES)
            tile[y + CASE_LENGTH][x].setHighlighted();

        addControl("DOWN", new MovePlayerAction(this, DIRECTIONS.BACK));
        addControl("UP", new MovePlayerAction(this, DIRECTIONS.FRONT));
        addControl("LEFT", new MovePlayerAction(this, DIRECTIONS.LEFT));
        addControl("RIGHT", new MovePlayerAction(this, DIRECTIONS.RIGHT));
    }

    @Override
    public void render() {
        world.setBackground(getBackground());
        showTray();
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
        for (Item item : items) {
            tray.getTray()[item.getY()][item.getX()].setItem(item);
        }
    }

    public void unHighlightAll() {
        int x = player.getX();
        int y = player.getY();

        if (x-CASE_LENGTH >= 0)
            tray.getTile(x-CASE_LENGTH, y).unHighlight();
        if (x+CASE_LENGTH < NBRE_CASES)
            tray.getTile(x+CASE_LENGTH, y).unHighlight();
        if (y-CASE_LENGTH >= 0)
            tray.getTile(x, y-CASE_LENGTH).unHighlight();
        if (y+CASE_LENGTH < NBRE_CASES)
            tray.getTile(x, y+CASE_LENGTH).unHighlight();
    }

    public void movePlayer(DIRECTIONS d) {
        int xAfter, yAfter;

        switch (d) {
            case FRONT:
                xAfter = player.getX();
                yAfter = player.getY()-CASE_LENGTH;
                break;

            case BACK:
                xAfter = player.getX();
                yAfter = player.getY()+CASE_LENGTH;
                break;

            case LEFT:
                xAfter = player.getX()-CASE_LENGTH;
                yAfter = player.getY();
                break;

            case RIGHT:
                xAfter = player.getX()+CASE_LENGTH;
                yAfter = player.getY();
                break;

            default:
                return;
        }

        if (xAfter < 0 || xAfter >= NBRE_CASES
                || yAfter < 0 || yAfter >= NBRE_CASES)
            return;

        unHighlightAll();

        showTray();

        tray.movePlayer(player.getX(), player.getY(), xAfter, yAfter);
        player.changePosition(xAfter, yAfter);
    }
}
