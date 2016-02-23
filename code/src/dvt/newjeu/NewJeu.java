package dvt.newjeu;

import dvt.devint.ConstantesDevint;
import dvt.devint.Jeu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import dvt.devint.ConstantesDevint.*;
import dvt.score.Score;

/**
 * Created by Adrian on 03/02/2016.
 */
public class NewJeu extends Jeu {
    private static final String IMAGEPATH = "../ressources/images/";

    private JPanel game;
    private JPanel bandeau;

    private JLabel lea;
    private JLabel theo;
    private JLabel info;

    private int yPos;

    private boolean isOver;
    private boolean win;

    @Override
    public void init() {
        game = new JPanel();
        game.setBackground(getBackground());

        yPos = 0;

        isOver = false;
        win = false;

        bandeau = new JPanel();
        bandeau.setBackground(getBackground());

        lea = new JLabel(new ImageIcon(IMAGEPATH+"lea.JPG"));
        theo = new JLabel(new ImageIcon(IMAGEPATH+"theo.JPG"));

        info = new JLabel();

        this.addControlDown(' ', new TutoAction(this));
        this.addControlDown(10, new RestartAction(this));

        game.add(lea);
        game.add(theo);
        game.add(info);

        this.add(game);
    }

    public void moveTheo() {
        if (!isOver)
            theo.setLocation(theo.getX(), theo.getY()+26);
    }

    @Override
    public void update() {
        if (!isOver) {
            yPos = yPos + 1;
            int size = this.getContentPane().getHeight();
            int score = size-(lea.getY()+lea.getHeight());

            if (theo.getY() + theo.getHeight() >= size) {
                info.setText("Gagne. Score = "+score);
                isOver = true;
                win = true;
            }
            else if (lea.getY() + lea.getHeight() >= size) {
                info.setText("Perdu :-(");
                isOver = true;
            }

            if (isOver) { // On vient de terminer
                this.getSIVOX().playText(info.getText(), ConstantesDevint.SYNTHESE_MAXIMALE);

                if (win)
                    Score.writeXML("theo", score);
            }
        }

    }

    @Override
    public void render() {
        lea.setLocation(lea.getX(), yPos);
        game.setBackground(getBackground());

        info.setForeground(getForeground());
        info.setFont(getFont());
    }

    @Override
    public void reset() {
        theo.setLocation(theo.getX(), 0);
        lea.setLocation(lea.getX(), 0);
        info.setText("");
        isOver = false;
        win = false;
        yPos = 0;
    }
}
