package dvt.labyrinth;

import dvt.devint.Jeu;

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

        for (int y = 0; y < 17; y++) {
            for (int x = 0; x < 17; x++) {
                JButton gui = new JButton("l");

                if (x%2 == 1 || y%2 == 1)
                    gui.setBackground(Color.black);
                else
                    gui.setBackground(Color.red);

                gui.setOpaque(true);

                gui.setBorder(new LineBorder(Color.black, 1));

                if (x%2 == 1)
                    gui.setPreferredSize(new Dimension(10,((y%2 == 1) ? 10 : 50)));
                else
                    gui.setPreferredSize(new Dimension(100,((y%2 == 1) ? 10 : 50)));

                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.CENTER;
                c.gridx = x;
                c.gridy = y;

                if (y%2 == 1) // Ligne de murs
                    c.gridwidth = (x%2 == 0) ? 2 : 1; // Enlever la petite case
                else
                    c.gridwidth = 1;

                c.gridheight = 1;

                System.out.println("["+y+","+x+"] : "+c.gridheight);

                c.weightx = 1.0;
                c.weighty = 1.0;
                c.fill = GridBagConstraints.BOTH;

                world.add(gui, c);

//                world.add(gui);
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
