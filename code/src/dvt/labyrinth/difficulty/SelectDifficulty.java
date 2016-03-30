package dvt.labyrinth.difficulty;

import dvt.devint.Fenetre;
import dvt.labyrinth.menu.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static dvt.devint.ConstantesDevint.*;
import static dvt.labyrinth.tools.ConstantesLabyrinth.*;

/**
 * Created by etienne on 23/03/16.
 */
public class SelectDifficulty extends Fenetre {

    private JPanel menuPrincipal = new JPanel();
    private int countMenu, menuSelected;
    private GridBagConstraints c = new GridBagConstraints();
    private List<JButton> listeBoutton;
    private JLabel titleJeu;
    private int gameChoice;
    private Menu menu;

    public SelectDifficulty(Menu menu){
        c = new GridBagConstraints();
        listeBoutton = new ArrayList<JButton>();
        GridBagLayout layoutMenu = new GridBagLayout();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(MARGE_TOP_BOT, MARGE_LEFT_RIGHT, MARGE_TOP_BOT, MARGE_LEFT_RIGHT);
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 100;
        c.gridwidth = 3;
        menuPrincipal.setLayout(layoutMenu);
        addLabel(SELECTDIFFICULTY);
        this.menu = menu;
        addMenu(DIFFICULTY.EASY.toString(),new ActionDifficulty(this,1));
        addMenu(DIFFICULTY.MEDIUM.toString(), new ActionDifficulty(this,2));
        addMenu(DIFFICULTY.HARD.toString(), new ActionDifficulty(this, 3));
        addControl("DOWN", new DownDifficulty(this));
        addControl("UP", new UpDifficulty(this));
        this.add(menuPrincipal);
        this.setVisible(true);
    }


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

    public void addLabel(String title) {
        this.titleJeu = new JLabel(title.toUpperCase(), JLabel.CENTER);
        c.weightx = c.weighty = 1.0;
        c.gridy = countMenu++;
        menuPrincipal.add(this.titleJeu, c);
    }

    public void addMenu(String title, ActionListener action) {
        JButton button = new JButton(title.toUpperCase());
        button.addActionListener(action);
        unselectedButton(button);
        c.weightx = c.weighty = 1.0;
        c.gridy = countMenu++;
        menuPrincipal.add(button, c);
        listeBoutton.add(button);
    }

    public void chooseChoice(int choice) {
        actionChoice(choice);
        this.gameChoice = choice;
    }

    private void actionChoice(int choice) {
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

    public void down() {
        unselectedButton(listeBoutton.get(menuSelected++ % listeBoutton.size()));
        selectedButton(listeBoutton.get(menuSelected % listeBoutton.size()));
        this.getSIVOX().stop();
        this.getSIVOX().playText(listeBoutton.get(menuSelected % listeBoutton.size()).getText(),SYNTHESE_MAXIMALE);
        this.getSIVOX().playText("CETTE OPTION EST "+listeBoutton.get(menuSelected % listeBoutton.size()).getText(),SYNTHESE_MINIMALE);
    }

    public void up() {
        unselectedButton(listeBoutton.get(menuSelected % listeBoutton.size()));
        menuSelected = menuSelected == 0 ? (listeBoutton.size() - 1)
                : menuSelected - 1;
        selectedButton(listeBoutton.get(menuSelected % listeBoutton.size()));
        this.getSIVOX().stop();
        this.getSIVOX().playText(listeBoutton.get(menuSelected % listeBoutton.size()).getText(),SYNTHESE_MAXIMALE);
        this.getSIVOX().playText("CETTE OPTION EST "+listeBoutton.get(menuSelected % listeBoutton.size()).getText(),SYNTHESE_MINIMALE);
    }

    public void loop() {
        long lastLoopTime,timeLoop;

        while (this.isDisplayable()) {
            long now = System.nanoTime();
            lastLoopTime = now;
            render();
            if (gameChoice != 0) {
                this.setVisible(false);
                this.getSIVOX().stop();
                switch (gameChoice) {
                    case 1:
                        menu.setBotDifficulty(DIFFICULTY.EASY);
                        break;

                    case 2:
                        menu.setBotDifficulty(DIFFICULTY.MEDIUM);
                        break;

                    case 3:
                        menu.setBotDifficulty(DIFFICULTY.HARD);
                    default:
                        break;
                }
                this.getSIVOX().stop();
                this.setVisible(true);
            }

            gameChoice = 0;

            try {
                timeLoop = (lastLoopTime - System.nanoTime() + 1000000000L/60) / 1000000;
                if(timeLoop>0) {
                    Thread.sleep(timeLoop);
                }
            } catch (InterruptedException e) {
                throw new IllegalArgumentException("");
            }
        }
    }

}
