package dvt.labyrinth;

import dvt.devint.Jeu;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Arnaud on 26/02/2016.
 */
public class Labyrinth extends Jeu{
    private JPanel world;

    @Override
    public void init() {
        world = new JPanel();
        world.setBackground(getBackground());
        Tray tray = new Tray();
        Tile tile[][] = tray.getTray();

        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                JLabel graphic = new JLabel();
                world.add(graphic);
                graphic.setLocation(tile[i][j].getX(), tile[i][j].getY());
                
                if (i % 2 == 0 || j % 2 == 0) {
                    graphic.setPreferredSize(new Dimension(70,70));
                    graphic.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));

                } else {
                    graphic.setPreferredSize(new Dimension(70,70));
                    graphic.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.green));

                }

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
