package dvt.labyrinth.model.essential;

import dvt.labyrinth.tools.ConstantesLabyrinth;
import dvt.labyrinth.tools.StretchIcon;

/**
 * An arrow item
 *
 * @author Adrian
 */
public class Arrow extends Item {
    public Arrow(ConstantesLabyrinth.RESOURCES res) {
        super(res);
    }

    @Override
    public StretchIcon getIcon() {
        return new StretchIcon(res.getPath());
    }
}
