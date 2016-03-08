package dvt.labyrinth;

import dvt.devint.Jeu;
import dvt.labyrinth.model.Item;
import dvt.labyrinth.model.Pawn;

import javax.swing.*;
import java.awt.*;
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

    @Override
    public void init() {
        world = new JPanel();
        world.setBackground(getBackground());

        this.add(world);

        world.setLayout(new GridBagLayout());

        tray = new Tray();

        items = new ArrayList<>();
        items.add(new Pawn("Bernard", RESSOURCES.CLOUD, 8, 16));

        addPlayers();

        initTray();
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        world.setBackground(getBackground());

        initTray();
    }

    @Override
    public void reset() {

    }

    public void initTray() {
        Tile tile[][] = tray.getTray();

        for (int y = 0; y < tile.length; y++) {
            for (int x = 0; x < tile[y].length; x++) {
                GridBagConstraints c = getGridBagConstraints(x,y);

                JButton button = tile[y][x].getItem();

                if (x % 2 == 1)
                    button.setPreferredSize(new Dimension(10, ((y % 2 == 1) ? 10 : 50)));
                else
                    button.setPreferredSize(new Dimension(100, ((y % 2 == 1) ? 10 : 50)));

                world.add(button, c);
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

    private void addPlayers() {
        for (Item item : items) {
            System.out.println(item.getResPath()+" - "+item.getX()+" - "+item.getY());
            tray.getTray()[item.getY()][item.getX()].setItem(item);
        }
    }
}
