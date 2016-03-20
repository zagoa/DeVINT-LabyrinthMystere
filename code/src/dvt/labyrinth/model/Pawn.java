package dvt.labyrinth.model;

import dvt.labyrinth.StretchIcon;

import static dvt.labyrinth.ConstantesLabyrinth.RESSOURCES;

/**
 * A pawn item
 *
 * @author Arnaud
 */
public class Pawn extends Item {
    public Pawn(RESSOURCES res) {
        super(res);
    }

    @Override
    public StretchIcon getIcon() {
        return new StretchIcon(res.getPath());
    }
}