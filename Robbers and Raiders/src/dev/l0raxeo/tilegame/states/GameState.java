package dev.l0raxeo.tilegame.states;

import dev.l0raxeo.tilegame.Handler;
import dev.l0raxeo.tilegame.worlds.World;

import java.awt.*;

public class GameState extends State {

    private final World world;

    public GameState(Handler handler) {
        super(handler);
        world = new World(handler, "/worlds/world1.txt"); // I initialize World which invokes the initialization to thief causing the nullpointer exception
        handler.setWorld(world); // I set the world here which only happens after I use it in the thief class
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }

}
