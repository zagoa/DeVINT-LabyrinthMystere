package dvt.labyrinth.game;

import dvt.devint.Jeu;
import dvt.labyrinth.model.player.Player;

import javax.swing.*;

import static dvt.devint.ConstantesDevint.SYNTHESE_MAXIMALE;

/**
 * The Win view for the game
 *
 * @author Adrian
 */
public class WinGame extends Jeu {
    private Player winner;

    private JPanel world;
    private JLabel info;
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
        world.setLayout(null);

        info = new JLabel("", JLabel.CENTER);
        info.setFont(getFont());
        info.setVisible(true);
        world.add(info);
        this.add(world);
    }

    public void setWinner() {
        score = "<html><center>PARTIE TERMIN&Eacute;E";

        score += "<br />____________________<br /><br />";
        score += winner.getName()+" a gagn&eacute; !<br />";
        score +="</center></html>";

        info.setIcon(new ImageIcon(winner.getPawn().getResPath()));
        info.setText(score);

        String s = "Félicitations "+winner.getName()+" !"
                + "Tu as remporté la partie."
                + "Appuie sur échap pour revenir au menu.";

        this.getSIVOX().playText(s, SYNTHESE_MAXIMALE);
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
    }

    @Override
    public void reset() {

    }
}
