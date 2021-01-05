package dev.l0raxeo.tilegame.utils.ai;

import dev.l0raxeo.tilegame.entities.Entity;
import dev.l0raxeo.tilegame.entities.EntityManager;

public class AI {

    private final EntityManager entityManager;

    protected static Entity ai;

    public AI(EntityManager entityManager, Entity ai) {
        this.entityManager = entityManager;

        AI.ai = ai;
    }

    public Entity getSmallestIndex(Entity[] entity, int[] pathLengths, int total) { //total = iteration
        Entity tempEntity;
        int tempInt;
        for (int i = 0; i < total; i++) {
            for (int j = i + 1; j < total; j++) {
                if (pathLengths[i] > pathLengths[j]) {
                    tempEntity = entity[i];
                    tempInt = pathLengths[i];
                    entity[i] = entity[j];
                    pathLengths[i] = pathLengths[j];
                    entity[j] = tempEntity;
                    pathLengths[j] = tempInt;
                }
            }
        }

        return entity[0];
    }

    public void findNearestEntity() {
        int[] pathLengths = new int[100];
        Entity[] target = new Entity[100];
        int iteration = 0;

        for (Entity checkEntity : entityManager.getEntities()) {
            if (checkEntity.equals(ai))
                continue;
            pathLengths[iteration] = findPathToTarget(checkEntity).getLength();
            target[iteration] = checkEntity;
            iteration++;
        }

        Entity currentTarget = getSmallestIndex(target, pathLengths, iteration);
        executePathToTarget(currentTarget);
    }

    private Path findPathToTarget(Entity target) {
        return new Path(entityManager, target);
    }

    public void executePathToTarget(Entity target) {
        float targetX = target.getX();
        float targetY = target.getY();

        if (ai.getX() < targetX - 35)
            ai.setxMove(2.25f);
        else if (ai.getX() > targetX + 35)
            ai.setxMove(-2.25f);

        if (ai.getY() + 35 < targetY)
            ai.setyMove(2.25f);
        else if (ai.getY() + 35 > targetY)
            ai.setyMove(-2.25f);
    }
}
