package dvt.labyrinth;

import dvt.devint.Fenetre;
import dvt.score.Score;

import javax.swing.*;
import java.awt.event.KeyEvent;

import static dvt.devint.ConstantesDevint.SYNTHESE_MAXIMALE;

/**
 * Created by user on 26/02/2016.
 */
public class Pawn extends Fenetre{
    String name;
    String image;
    // composants graphiques
    private JPanel tray;
    private JLabel player;
    int x = player.getX();
    int y = player.getY();


    public Pawn(String name, String image){
        this.name = name;
        this.image = image;

    }


    /**
     * m�thode appel�e � la cr�ation de l'objet
     * initialisation des composants graphiques et des variables de jeu
     * association des actions aux touches via les m�thodes addControl
     */
    public void init(Pawn pawn){
        tray = new JPanel();
        tray.setBackground(getForeground());
        tray.setBackground(getBackground());
        //this.add(tray);
        player = new JLabel();
        player.setIcon(new ImageIcon("../ressources/images/" + image +".JPG"));
        player.setLocation(0, 0);
        tray.add(player);

    };

    /**
     * m�thode appel�e avant la boucle
     * utilis�e pour la r�initialisation
     */
    public void reset(Pawn pawn){
        player.setLocation(0, 0);
    };

}