package dvt.labyrinth.model.essential;

import dvt.labyrinth.tools.StretchIcon;

import static dvt.labyrinth.tools.ConstantesLabyrinth.RESOURCES;

/**
 * A pawn item
 *
 * @author Arnaud
 */
public class Pawn extends Item {
    public Pawn(RESOURCES res) {
        super(res);
    }

    @Override
    public StretchIcon getIcon() {
        return new StretchIcon(res.getPath());
    }
}