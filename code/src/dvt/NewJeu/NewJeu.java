package dvt.NewJeu;

import dvt.devint.Jeu;
import dvt.score.Score;

import javax.swing.*;

import java.awt.*;

import static dvt.devint.ConstantesDevint.SYNTHESE_MAXIMALE;

public class NewJeu extends Jeu {

    private JPanel world;
    private int y = 0;
    private JLabel lea ;
    private JLabel theo;
    private int height;
    private JLabel info;
    private boolean over;


    public void down(){
        if(!(over)) {
            theo.setLocation(theo.getX(), theo.getY() + 10);
        }

    }

    @Override
    public void init() {
        world = new JPanel();
        info = new JLabel();
        info.setVisible(true);
        world.setBackground(getBackground());
        theo = new JLabel(new ImageIcon("../ressources/images/theo.JPG"));
        lea =  new JLabel(new ImageIcon("../ressources/images/lea.JPG"));
        world.add(lea);
        world.add(theo);
        world.add(info);
        this.add(world);
        addControlDown(32,new TutoAction(this)); //code ASCII de l'espace
        addControlDown(10,new Restart(this));

    }

    @Override
    public void update() {
        y += 1 ;
        height = this.getContentPane().getHeight();
        info.setFont(getFont());
        info.setForeground(getForeground());

        if(lea.getY()>= height){
            this.getSIVOX().playText("Tu as perdu",SYNTHESE_MAXIMALE);
            info.setText("Perdu");
            over = true;



        }
        if(theo.getY()>=height){
            this.getSIVOX().playText("Tu as gagné",SYNTHESE_MAXIMALE);
            info.setText("Gagné ! Score : "+(this.getContentPane().getHeight()-lea.getY()));

            over = true;
            Score.writeXML("NewJeu", this.getContentPane().getHeight()-lea.getY())  ;

        }

    }

    @Override
    public void render() {
        world.setBackground(getBackground());
        if(!(over)) {
            lea.setLocation(lea.getX(), y );
        }


    }

    @Override
    public void reset() {
        y = 0;
        lea.setLocation(lea.getX(),0);
        theo.setLocation(theo.getX(),0);
        over = false;
        info.setText("");



    }
}
