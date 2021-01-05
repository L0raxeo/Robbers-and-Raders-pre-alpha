package dev.l0raxeo.tilegame.entities.statics;

import dev.l0raxeo.tilegame.Handler;
import dev.l0raxeo.tilegame.entities.Entity;

public abstract class StaticEntity extends Entity {

    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }

}
