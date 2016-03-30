package dvt.labyrinth.menu;

import dvt.devint.Fenetre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static dvt.devint.ConstantesDevint.*;
import static dvt.labyrinth.tools.ConstantesLabyrinth.*;

/**
 * Menu inspiré du menu principal.
 * @author Justal Kevin
 */
public class MenuGeneric extends Fenetre {
    private static final long serialVersionUID = 1L;

    protected GridBagConstraints c = new GridBagConstraints();
    protected JPanel menuPrincipal = new JPanel();
    protected int countMenu, menuSelected;

    protected List<JButton> listeBoutton;
    protected JLabel titleJeu;
    protected int gameChoice;

    /**
     * Le constructeur du menu
     * Permet de construire un Menu avec tout les composants
     */
    public MenuGeneric() {
        listeBoutton = new ArrayList<JButton>();

        GridBagLayout layoutMenu = new GridBagLayout();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(MARGE_TOP_BOT, MARGE_LEFT_RIGHT, MARGE_TOP_BOT,
                MARGE_LEFT_RIGHT);
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 100;
        c.gridwidth = 3;
        menuPrincipal.setLayout(layoutMenu);

        this.add(menuPrincipal);
        this.setVisible(true);
    }

    /**
     * Permet de gerer le rendu du menu
     */
    public void render() {
        menuPrincipal.setBackground(getBackground());
        this.titleJeu.setFont(getFont());
        this.titleJeu.setForeground(getForeground());

        for (int i = 0; i < listeBoutton.size(); i++) {
            if (i == menuSelected % listeBoutton.size()) {
                selectedButton(listeBoutton.get(i));
            } else {
                unselectedButton(listeBoutton.get(i));
            }
        }
    }

    /**
     * Permet d'ajouter du texte dans le menu
     * @param title Le titre du jeu
     */
    public void addLabel(String title) {
        this.titleJeu = new JLabel(title.toUpperCase(), JLabel.CENTER);
        c.weightx = c.weighty = 1.0;
        c.gridy = countMenu++;
        menuPrincipal.add(this.titleJeu, c);
    }

    /**
     * Permet d'ajouter un bouton
     * @param title Le texte sur le bouton
     * @param action L'action que l'on lie au bouton
     */
    public void addMenu(String title, ActionListener action) {
        JButton button = new JButton(title.toUpperCase());
        button.addActionListener(action);
        unselectedButton(button);
        c.weightx = c.weighty = 1.0;
        c.gridy = countMenu++;
        menuPrincipal.add(button, c);
        listeBoutton.add(button);
    }

    /**
     * Permet de gere l'action lorsque 'lon appuie sur bas
     */
    public void down() {
        unselectedButton(listeBoutton.get(menuSelected++ % listeBoutton.size()));
        selectedButton(listeBoutton.get(menuSelected % listeBoutton.size()));
        this.getSIVOX().stop();
        this.getSIVOX().playText(listeBoutton.get(menuSelected % listeBoutton.size()).getText(),SYNTHESE_MAXIMALE);
        this.getSIVOX().playText("CETTE OPTION EST "+listeBoutton.get(menuSelected % listeBoutton.size()).getText(),SYNTHESE_MINIMALE);
    }

    /**
     * Permet de gerer l'action lorsque l'on appuie sur haut
     */
    public void up() {
        unselectedButton(listeBoutton.get(menuSelected % listeBoutton.size()));
        menuSelected = menuSelected == 0 ? (listeBoutton.size() - 1)
                : menuSelected - 1;
        selectedButton(listeBoutton.get(menuSelected % listeBoutton.size()));
        this.getSIVOX().stop();
        this.getSIVOX().playText(listeBoutton.get(menuSelected % listeBoutton.size()).getText(),SYNTHESE_MAXIMALE);
        this.getSIVOX().playText("CETTE OPTION EST "+listeBoutton.get(menuSelected % listeBoutton.size()).getText(),SYNTHESE_MINIMALE);
    }

    /**
     * Permet de gerer l'action lorsque l'on appuie sur haut
     */
    public void actionChoice(int choice) {
        menuSelected = choice;
        unselectedButton(listeBoutton.get(menuSelected % listeBoutton.size()));
        menuSelected = menuSelected == 0 ? (listeBoutton.size() - 1)
                : menuSelected - 1;
        selectedButton(listeBoutton.get(menuSelected % listeBoutton.size()));
        this.getSIVOX().playText(listeBoutton.get(menuSelected % listeBoutton.size()).getText(),SYNTHESE_MOYENNE);
        this.getSIVOX().playText("CETTE OPTION EST "+listeBoutton.get(menuSelected % listeBoutton.size()).getText(),SYNTHESE_MINIMALE);

        for (int i = 0; i < listeBoutton.size(); i++) {
            listeBoutton.get(i).setFocusable(false);
        }
    }

    /**
     * Permet de gerer les action lie au choix dans le menu
     * @param choice Le choix que l'on a effectue dans le menu
     */
    public void chooseChoice(int choice) {
        actionChoice(choice);
        this.gameChoice = choice;
    }

    /**
     * ###################################################################################################"
     */

    public GridBagConstraints getC() {
        return c;
    }
}
