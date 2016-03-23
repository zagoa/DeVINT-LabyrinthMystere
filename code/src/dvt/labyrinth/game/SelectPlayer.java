package dvt.labyrinth.game;

import dvt.devint.Jeu;
import dvt.labyrinth.StretchIcon;
import dvt.labyrinth.actions.MorePawns;
import dvt.labyrinth.actions.SelectPawnPlayer;
import dvt.labyrinth.actions.ValidSelectPlayer;
import dvt.labyrinth.menu.Menu;

import static dvt.labyrinth.ConstantesLabyrinth.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A class to select a player
 */
public class SelectPlayer extends Jeu {
    // Number of pawns shown
    private static final int NUMBER_PAWNS = 2;
    // Default border style
    private static final LineBorder DEFAULT_BORDER = new LineBorder(Color.black, 2, true);

    // The menu
    private Menu menu;
    // n-players
    private int numberOfPlayers;

    // The list of players
    private HashMap<String, RESOURCES> players;

    private JPanel world;
    private JLabel infoPseudo;
    private JLabel infoIcon;
    private JTextField name;
    private JButton valid;

    // Get more icons
    private JButton getMore;

    // The n item to show
    private int showItem;

    // The pawns
    private Map<RESOURCES, JButton> allPawns;

    // Select pawn
    private RESOURCES selectedPawn;

    /**
     * The default constructor
     *
     * @param menu
     *          The menu which gets the players
     * @param n
     *          The number of players
     */
    public SelectPlayer(Menu menu, int n) {
        super();

        this.menu = menu;
        this.numberOfPlayers = n;

        players = new HashMap<>();
    }

    @Override
    public void init() {
        getAllPawns();

        world = new JPanel();
        world.setLayout(new GridBagLayout());

        this.add(world);

        setView();
    }

    /**
     * Just create the view
     */
    public void setView() {
        /* ******** */
        infoPseudo = new JLabel("<html><div style='border-bottom: 2px dashed black;'>Pseudo Joueur 1</div></html>");
        infoPseudo.setFont(getFont());

        /* ******** */
        infoIcon = new JLabel("<html><br /><div style='border-bottom: 2px dashed black;'>Icone Joueur 1</div></html>");
        infoIcon.setFont(getFont());

        /* ******** */
        name = new JTextField("", 5);
        name.setFont(getFont());

        /* ******** */
        JLabel sep = new JLabel(" ");

        /* ******** */
        valid = new JButton("VALIDER");
        valid.setOpaque(true);
        valid.setBorder(new LineBorder(Color.black, 2, true));
        valid.setBackground(new Color(28,178,58));
        valid.setForeground(Color.WHITE);
        valid.setFont(getFont());
        valid.addActionListener(new ValidSelectPlayer(this));
        valid.setFocusable(false);

        /* ******** */
        getMore = new JButton("Autres icones");
        getMore.setFont(new Font("Arial", Font.PLAIN, 30));
        getMore.addActionListener(new MorePawns(this));
        getMore.setFocusable(false);

        /* ******** */
        int gridWidth = NUMBER_PAWNS;

        world.add(infoPseudo    , getConstraint(1, 0, gridWidth));
        world.add(name          , getConstraint(1, 1, gridWidth));
        world.add(infoIcon      , getConstraint(1, 2, gridWidth));

        showItem = 0;
        for (int k = 0; k < NUMBER_PAWNS; k++)
            world.add(getNextIcon(), getConstraint((k+1), 3, 1));

        world.add(sep           , getConstraint(1, 5, gridWidth));
        world.add(valid         , getConstraint(1, 6, gridWidth));
        world.add(getMore       , getConstraint(1, 4, gridWidth));
    }

    /**
     * Get the pawns from the RESSOURCES
     */
    public void getAllPawns() {
        showItem = 0;
        allPawns = new LinkedHashMap<>();

        for (RESOURCES r : RESOURCES.values()) {
            if (r.isAPawn() && !r.isABot()) allPawns.put(r, getButton(r));
        }
    }

    /**
     * Create a button with a given RESSOURCES
     *
     * @param r
     *          The RESSOURCES
     * @return the created button
     */
    public JButton getButton(RESOURCES r) {
        JButton j = new JButton(new StretchIcon(r.getPath()));
        j.setPreferredSize(new Dimension(200,200));
        j.addActionListener(new SelectPawnPlayer(this, r));
        j.setOpaque(true);
        j.setBackground(Color.WHITE);
        j.setBorder(DEFAULT_BORDER);
        j.setFocusable(false);

        return j;
    }

    /**
     * Get a constraint (grid bag)
     *
     * @param gridx
     *          The x
     * @param gridy
     *          The y
     * @param gridwidth
     *          The width
     * @return  the constraint
     */
    public GridBagConstraints getConstraint(int gridx, int gridy, int gridwidth) {
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = gridx;       // x col
        c.gridy = gridy;       // x Row
        c.gridwidth = gridwidth;   // x columns wide
        c.insets = new Insets(3,3,3,3); // margin

        return c;
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        world.setBackground(getBackground());
        valid.setFont(getFont());
        getMore.setFont(new Font(getFont().getFontName(), Font.PLAIN, getFont().getSize()));
        infoIcon.setFont(getFont());
        infoPseudo.setFont(getFont());
    }

    @Override
    public void reset() {
        infoIcon.setText(infoIcon.getText().replaceAll(""+players.size(), ""+(players.size()+1)));
        infoPseudo.setText(infoPseudo.getText().replaceAll(""+players.size(), ""+(players.size()+1)));
        name.setText("");

        showItem = 0;

        // Players can't have the same icon
        if (selectedPawn != null) {
            // So we need to remove it from our list
            for(Iterator<Map.Entry<RESOURCES,JButton>> it = allPawns.entrySet().iterator(); it.hasNext();){
                Map.Entry<RESOURCES, JButton> entry = it.next();

                // If this is the selected icon
                if (entry.getKey().equals(selectedPawn)) {
                    // We forgot it
                    it.remove();
                    // We remove it
                    world.remove(entry.getValue());
                    // Job done
                    break;
                }
            }

            // No more selected pawn
            selectedPawn = null;
        }

        setNextIcons();
    }

    /**
     * Get the next icons
     */
    public void setNextIcons() {
        // If we have selected an item, we forgot it
        if (selectedPawn != null)
            allPawns.get(selectedPawn).setBorder(DEFAULT_BORDER); // Set default border

        selectedPawn = null;

        // Keep the index in memory
        int oldShowItem = showItem;

        for (int k = NUMBER_PAWNS; k > 0; k--) {
            int n = (oldShowItem-k < 0) ? allPawns.size()-(k-oldShowItem) : oldShowItem-k;

            // Remove current button
            world.remove(getButton(n));
            // Add the new button
            world.add(getNextIcon(), getConstraint(k, 3, 1));
        }

        revalidate();
        repaint();
    }

    /**
     * Get the next JButton
     *
     * @return the JButton
     */
    public JButton getNextIcon() {
        if (showItem >= allPawns.size()) // No more pawns
            showItem = 0;

        return getButton(showItem++);
    }

    /**
     * Get a given n-button
     *
     * @param n
     *          The n index of the button
     * @return the button
     */
    public JButton getButton(int n) {
        return (JButton)allPawns.values().toArray()[n];
    }

    /**
     * Select a given pawn
     *
     * @param r
     *          The RESSOURCES
     * @param b
     *          The button
     */
    public void selectPawn(RESOURCES r, JButton b) {
        if (selectedPawn != null) // previously selected one
            allPawns.get(selectedPawn).setBorder(DEFAULT_BORDER);

        selectedPawn = r;
        b.setBorder(new LineBorder(new Color(28,178,58), 12, true));
    }

    /**
     * If we have filled all the field
     */
    public void validSelection() {
        if (name.getText().length() >= 1
                && selectedPawn != null
                && players.get(name.getText()) == null)
            getNextPlayer();
        else if (name.getText().length() < 1)
            getSIVOX().playText(parse(VOCAL.PSEUDO_LENGTH, (players.size()+1)));
        else if (selectedPawn == null)
            getSIVOX().playText(parse(VOCAL.SELECT_PAWN, (players.size()+1)));
        else
            getSIVOX().playText(parse(VOCAL.SAME_PSEUDO, players.size()));
    }

    /**
     * Check if we have more players to worry
     */
    public void getNextPlayer() {
        players.put(name.getText(), selectedPawn);

        if (players.size() != numberOfPlayers)
            reset();
        else {
            menu.setPlayers(players);
            dispose();
        }
    }
}
