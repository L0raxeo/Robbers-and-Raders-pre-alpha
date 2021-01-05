package dev.l0raxeo.tilegame;

import dev.l0raxeo.tilegame.entities.EntityFocus;
import dev.l0raxeo.tilegame.gfx.GameCamera;
import dev.l0raxeo.tilegame.input.KeyManager;
import dev.l0raxeo.tilegame.input.MouseManager;
import dev.l0raxeo.tilegame.worlds.World;

public class Handler {

    private final Game game;
    private World world;

    public Handler(Game game) {
        this.game = game;
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public GameCamera getGameCamera() {
        return game.getGameCamera();
    }

    public EntityFocus getEntityFocuser() {
        return game.getEntityFocuser();
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }

    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }

}
