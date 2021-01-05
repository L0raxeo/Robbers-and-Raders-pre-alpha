package dev.l0raxeo.tilegame.entities;

public class EntityFocus {

    private Entity focusedEntity;

    public void focusOnEntity(Entity e) {
        this.focusedEntity = e;
    }

    public Entity getFocusedEntity() {
        return this.focusedEntity;
    }

}
