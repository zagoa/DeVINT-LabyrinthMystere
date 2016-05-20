package dvt.labyrinth.menu;

import dvt.labyrinth.menu.Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Permet de gerer l'action lors de l'appuie sur certaines touches
 * @author Justal Kevin
 */
public class ActionMenu extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient MenuGeneric menu;
    private int choice;

    /**
     * L'objet qui sera cree lors de l'appuie sur une touche
     * @param menu La fenetre ou se trouve le lien entre la touche et l'action
     * @param choice La valeur que l'on souhaite modifie
     */
    public ActionMenu(MenuGeneric menu, int choice) {
        this.menu = menu;
        this.choice = choice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        menu.chooseChoice(choice);
    }


}
