package dvt.labyrinth;

import dvt.devint.Jeu;
import dvt.labyrinth.model.Tray.Tray;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Arnaud on 26/02/2016.
 */
public class Labyrinth extends Jeu{
    private JPanel world;

    @Override
    public void init() {
        world = new JPanel();
        world.setBackground(getBackground());

        this.add(world);

        world.setLayout(new GridBagLayout());

        Tray tray = new Tray();
        Tile tile[][] = tray.getTray();

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                JPanel gui = new JPanel();
                JLabel l = new JLabel(""+x);
                gui.add(l);
                if (x%2 == 1 || y%2 == 1)
                    gui.setBackground(Color.black);
                else
                    gui.setBackground(Color.red);
                gui.setPreferredSize(new Dimension(300,50));
                gui.setBorder(new LineBorder(Color.white, 1));

                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.CENTER;
                c.gridx = x;
                c.gridy = y;

                if (x == 8)
                    c.gridwidth = GridBagConstraints.REMAINDER;
                else
                    c.gridwidth = (x%2 == 1) ? 1 : 2;

                if (y == 8)
                    c.gridheight = GridBagConstraints.REMAINDER;
                else
                    c.gridheight = (y%2 == 1) ? 1 : 2;

                c.weightx = 1.0;
                c.weighty = 1.0;
                c.fill = GridBagConstraints.BOTH;

                world.add(gui, c);
            }


        }
//        this.add(world);

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        world.setBackground(getBackground());


    }

    @Override
    public void reset() {

    }
}
