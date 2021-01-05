package dev.l0raxeo.tilegame.entities;

import dev.l0raxeo.tilegame.Handler;
import dev.l0raxeo.tilegame.entities.creatures.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {

    private Handler handler;
    private final Player player;
    private ArrayList<Entity> entities;
    private final Comparator<Entity> renderSorter = Comparator.comparingInt(a -> (int) a.getY() + a.getHeight());

    public EntityManager(Handler handler, Player player) {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<>();
        addEntity(player);
    }

    public void tick() {
        Iterator<Entity> it = entities.iterator();

        while (it.hasNext()) {
            Entity e = it.next();
            e.tick();

            if (!e.isActive())
                it.remove();
        }
        entities.sort(renderSorter);
    }

    public void render(Graphics g) {
        for (Entity e : entities) {
            e.render(g);
        }
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    public Handler getHandler() {
        return handler;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

}
