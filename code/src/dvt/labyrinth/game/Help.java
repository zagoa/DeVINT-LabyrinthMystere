package dvt.labyrinth.game;

import dvt.devint.Jeu;
import static dvt.labyrinth.tools.ConstantesLabyrinth.*;

import dvt.labyrinth.tools.StretchIcon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Win view for the game
 *
 * @author Adrian
 */
public class Help extends Jeu {
    private JPanel world;
    private JPanel right;
    private JPanel buttons;

    private JLabel img;
    private JLabel imgSound;

    private HELP_STRINGS help = HELP_STRINGS.HOME;

    private int numberHelp = HELP_STRINGS.values().length;
    private int i = 0;

    @Override
    public void init() {
        world = new JPanel();
        right = new JPanel();

        world.setBackground(getBackground());
        world.setLayout(new GridLayout(1,2));

        right.setBackground(getBackground());
        right.setLayout(new GridLayout(2,1));

        img = new JLabel("", JLabel.CENTER);
        img.setVisible(true);
        img.setVerticalAlignment(JLabel.CENTER);

        imgSound = new JLabel("", JLabel.CENTER);
        imgSound.setVisible(true);
        imgSound.setVerticalAlignment(JLabel.CENTER);

        world.add(img);
        world.add(right);

        right.add(imgSound);

        this.add(world);

        setWinner("");

    }

    public void setWinner(String sentence) {
        img.setIcon(new StretchIcon(RESOURCES.HOME_HELP.getPath()));

        imgSound.setIcon(new StretchIcon(RESOURCES.SPEAKEROFF_HELP.getPath()));

        buttons = new JPanel();
        buttons.setBackground(getBackground());
        buttons.setBorder(new EmptyBorder(50, 0, 0, 0));

        JButton play = new JButton(" ECOUTER L'AIDE ");
        play.setBackground(Color.green);
        play.setBorder(new LineBorder(Color.black, 2));
        play.setFont(getFont());
        play.setFocusable(false);
        play.setOpaque(true);
        play.addActionListener(new PlayHelp());

        JButton next = new JButton(" AIDE SUIVANTE ");
        next.setMargin(new Insets(100, 0, 0, 0));
        next.setBackground(Color.orange);
        next.setBorder(new LineBorder(Color.black, 2));
        next.setFont(getFont());
        next.setFocusable(false);
        next.setOpaque(true);
        next.addActionListener(new NextHelp());

        JButton menu = new JButton(" REVENIR AU MENU ");
        menu.setMargin(new Insets(100, 10, 10, 10));
        menu.setBackground(Color.red);
        menu.setBorder(new LineBorder(Color.black, 2));
        menu.setFont(getFont());
        menu.setFocusable(false);
        menu.setOpaque(true);

        buttons.add(play);
        buttons.add(next);
        buttons.add(menu);

        right.add(buttons);
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        img.setBackground(getBackground());
        world.setBackground(getBackground());
        right.setBackground(getBackground());
        buttons.setBackground(getBackground());
    }

    @Override
    public void reset() {

    }

    private class PlayHelp implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            imgSound.setIcon(new StretchIcon(RESOURCES.SPEAKERON_HELP.getPath()));
            playText(getSIVOX(), help.toString());
        }
    }

    private class NextHelp implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            i++;

            if (i == numberHelp) {
                playText(getSIVOX(), "Tu es revenu au menu principal car il n'y a plus d'aide disponibles.");
                dispose();
            } else {
                help = help.next();
                changeHelp();
            }
        }
    }

    private void changeHelp() {
        img.setIcon(new StretchIcon(help.getRes().getPath()));
        imgSound.setIcon(new StretchIcon(RESOURCES.SPEAKEROFF_HELP.getPath()));
    }
}
