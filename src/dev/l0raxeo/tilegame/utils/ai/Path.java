package dev.l0raxeo.tilegame.utils.ai;

import dev.l0raxeo.tilegame.entities.Entity;
import dev.l0raxeo.tilegame.entities.EntityManager;

public class Path extends AI {

    private final Entity target;

    private int length;

    public Path(EntityManager entityManager, Entity target) {
        super(entityManager, ai);

        this.target = target;
    }

    public void createPathTowards(Entity target) {
        int totalLengthOfPath = 0;
        float targetX = target.getX();
        float targetY = target.getY();

        if (ai.getX() < targetX)
            totalLengthOfPath += targetX - ai.getX();
        else if (ai.getX() > targetX)
            totalLengthOfPath += ai.getX() - targetX;

        if (ai.getY() < targetY)
            totalLengthOfPath += targetY - ai.getY();
        else if (ai.getY() > targetY)
            totalLengthOfPath += ai.getY() - targetY;

        setLength(totalLengthOfPath);
    }

    public int getLength() {
        createPathTowards(target);
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

}
