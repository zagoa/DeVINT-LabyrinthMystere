package dvt.jeuchronometre;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Permet de gerer l'action lors de l'appuie sur une touche
 * @author Justal Kevin
 */
public class Action extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient JeuChrono jeu2;
    private boolean value;

    /**
     * L'objet qui sera cree lors de l'appuie sur une touche
     * @param jeu2 La fenetre ou se trouve le lien entre la touche et l'action
     * @param value La valeur que l'on souhaite modifie
     */
    public Action(JeuChrono jeu2, boolean value) {
        this.jeu2 = jeu2;
        this.value = value;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.jeu2.action(value);
    }

}
