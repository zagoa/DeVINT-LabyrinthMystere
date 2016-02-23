package dvt.jeuchronometre;

import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import static dvt.devint.ConstantesDevint.*;
import static dvt.jeuchronometre.ConstantesJeu.*;
import dvt.jeuchronometre.Action;
import dvt.score.Score;

/**
 * Permet de gerer le jeu et la fenetre qui contient le jeu
 * @author Justal Kevin
 */
public class JeuChrono extends dvt.devint.Jeu {
    private static final long serialVersionUID = 1L;
    // composants graphiques
    private JPanel world;
    private JLabel info;
    
    // éléments du modèle
    private transient Chronometer ch;
    private int count;
    private boolean pressed;
    private int record;
    private boolean once;
    private String HTMLtext;

    /**
     * L'initalisation du jeu
     */
    @Override
    public void init() {
        world = new JPanel();
        world.setBackground(getForeground());
        world.setLayout(null);
        info = new JLabel(CONSIGNE, JLabel.CENTER);
        info.setFont(getFont());
        info.setVisible(true);
        world.add(info);

        this.add(world);
        
        // on gère à la fois le Down et le Up pour qu'on ne puisse pas tricher
        // ça ne compte pas si on laisse la touche espace appuyée
        // si on appuie sur espace, value est true (cf classe Action)
        addControlDown(KeyEvent.VK_SPACE, new Action(this, true));
        // si on relache sur espace, value est false (cf classe Action)
        addControlUp(KeyEvent.VK_SPACE, new Action(this, false));
        // si on appuie sur enter, on relance le jeu 
        addControlDown(KeyEvent.VK_ENTER, new Restart(this));

    }

    /**
     * Permet de reset le jeu afin de recommencer une partie
     */
    @Override
    public void reset() {
        count = 0;
        ch = new Chronometer();
        this.pressed = true;
        this.once = false;
        info.setText(CONSIGNE);
        this.getSIVOX().stop();
        this.getSIVOX().playText(CONSIGNE_WITHOUT_HTML,SYNTHESE_MAXIMALE);
    }

    /**
     * Permet de faire l'update du jeu
     */
    @Override
    public void update() {
        if (count > 0) {
            ch.stop();
            int seconds = ch.getSeconds();
            if (seconds < 10) {
                HTMLtext = "<html><center>" + ch.getChrono() + "<br /><br />"
                        + count + "</center></html>";
                record = count;
            } else {
            	HTMLtext ="<html><center>BRAVO<br />Vous avez appuye "
                        + record
                        + " fois sur espace<br /><br /><br />Pour recommencer, appuyez sur 'Entree'</center></html>";
                if(!once) {
                    this.getSIVOX().stop();
                    Score.writeXML("NAME IF YOU WANT", record);
                    this.getSIVOX().playText("Vous avez appuye "+record+" fois sur espace ! Pour rejouer, appuyez sur Entree",SYNTHESE_MAXIMALE); 
                    this.once = !once;
                }
            }
        }
    }

    /**
     * Permet de faire le rendue du jeu suivant les modifications faites dans l'update
     */
    @Override
    public void render() {
        info.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.info.setFont(getFont());
        this.info.setForeground(getForeground());
        this.info.setText(HTMLtext);
        world.setBackground(getBackground());
    }    
    
    /**
     * Permet de gerer les resultats des actions
     * @param value La valeur que l'action nous renvoie
     */
    public void action(boolean value) {
        if (pressed && !once) {
            if (count == 0) {
                ch.start();
                this.getSIVOX().stop();
                this.getSIVOX().playText("La partie est lancee !",SYNTHESE_MAXIMALE);
            }
            count++;
            if(count % 10 == 0) {
                this.getSIVOX().stop();
                this.getSIVOX().playText(String.valueOf(count),SYNTHESE_MOYENNE); 
            }
            pressed = false;
        }
        if (!value)
            pressed = true;
    }

    /**
     * Permet de gerer l'action pour redemarrer le jeu
     */
    public void restart() {
        reset();
    }
    
    /**
     * ###################################################################################################"
     */
    
    public int getCount() {
        return count;
    }
    
    public boolean getPressed() {
        return pressed;
    }
}
