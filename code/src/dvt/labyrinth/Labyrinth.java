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

        GridLayout layout = new GridLayout(9,9);
        world.setLayout(new GridLayout(9,9));


        Tray tray = new Tray();
        Tile tile[][] = tray.getTray();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JPanel gui = new JPanel();
                gui.setBackground(Color.green);
                gui.setPreferredSize(new Dimension(300,50));
                gui.setBorder(new LineBorder(Color.blue, 10));

                final int wJ = j;
                final int hI = i;

                gui.addMouseListener( new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent me) {
                        int w = gui.getWidth();
                        int h = gui.getHeight();
                        int x = me.getPoint().x-wJ*w;
                        int y = me.getPoint().y-hI*h;

                        boolean inBorder =
                                (x < 10 || y < 10
                                || x > w-10 || y > h-10);

                        if (inBorder) {
                            System.out.println(me.getPoint());
                        } else {
                            System.out.println("Ignore!");
                        }
                    }
                });

                world.add(gui);
            }


        }
        this.add(world);

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
