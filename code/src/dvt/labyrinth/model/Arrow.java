package dvt.labyrinth.model;

import dvt.labyrinth.ConstantesLabyrinth;
import dvt.labyrinth.StretchIcon;

/**
 * An arrow item
 *
 * @author Adrian
 */
public class Arrow extends Item {
    public Arrow(ConstantesLabyrinth.RESSOURCES res) {
        super(res);
    }

    @Override
    public StretchIcon getIcon() {
        return new StretchIcon(res.getPath());
    }
}
