package dvt.labyrinth.game;

import dvt.devint.EchapAction;
import dvt.devint.Jeu;
import static dvt.labyrinth.tools.ConstantesLabyrinth.*;
import dvt.labyrinth.model.player.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;

import static dvt.devint.ConstantesDevint.SYNTHESE_MAXIMALE;

/**
 * The Win view for the game
 *
 * @author Adrian
 */
public class WinGame extends Jeu {
    private Player winner;

    private JPanel world;
    private JPanel bottom;

    private JLabel info;
    private JButton reset;
    private String score;

    public WinGame(Player player) {
        super();

        winner = player;
        setWinner();
    }

    @Override
    public void init() {
        world = new JPanel();
        world.setBackground(getBackground());
        world.setLayout(new GridLayout(2,1));

        info = new JLabel("", JLabel.CENTER);
        info.setVerticalAlignment(JLabel.TOP);
        info.setFont(getFont());
        info.setVisible(true);
        world.add(info);
        //TODO afficher le bouton

        reset = new JButton("Revenir au Menu");
        reset.setBackground(Color.RED);
        reset.setForeground(Color.WHITE);
        reset.setBorder(new LineBorder(Color.black, 2, true));
        reset.addActionListener(new EchapAction(this));
        reset.setFont(getFont());
        reset.setFocusable(false);
        reset.setOpaque(true);

        bottom = new JPanel();
        bottom.setLayout(new GridBagLayout());
        bottom.add(reset);
        bottom.setBackground(getBackground());

        world.add(bottom);


//        world.add(new JButton("bite"));

        this.add(world);

    }

    public void setWinner() {
        score = "<html><center>PARTIE TERMIN&Eacute;E";

        score += "<br />____________________<br /><br />";
        score += winner.getName()+" a gagn&eacute; !<br />";
        score +="</center></html>";

        info.setIcon(new ImageIcon(winner.getPawn().getResPath()));
        info.setText(score);

        this.getSIVOX().playText(parse(VOCAL.WIN, winner.getName()), SYNTHESE_MAXIMALE);
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        info.setBounds(0, 0, this.getWidth(), this.getHeight());
        info.setBackground(getBackground());
        info.setForeground(getForeground());
        info.setFont(getFont());
        world.setBackground(getBackground());
        bottom.setBackground(getBackground());
    }

    @Override
    public void reset() {

    }
}
