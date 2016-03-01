package dvt.labyrinth;

import dvt.devint.Jeu;

import javax.swing.*;

/**
 * Created by Arnaud on 26/02/2016.
 */
public class Labyrinth extends Jeu{
    private JPanel world;
    private JLabel envi;




    @Override
    public void init() {
        world = new JPanel();
        world.setBackground(getBackground());
        Tray tray = new Tray();
        Tile tile[][] = tray.getPlateau();

        for(int i = 0;i<17; i++){
            for(int j = 0;i<17; j++){
            world.add(tile[i][j].getTile());
            }

        }

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

    }

    @Override
    public void reset() {

    }
}
