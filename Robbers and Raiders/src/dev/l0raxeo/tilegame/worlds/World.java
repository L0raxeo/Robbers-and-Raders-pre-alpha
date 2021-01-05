package dev.l0raxeo.tilegame.worlds;

import dev.l0raxeo.tilegame.Handler;
import dev.l0raxeo.tilegame.entities.EntityManager;
import dev.l0raxeo.tilegame.entities.creatures.Player;
import dev.l0raxeo.tilegame.entities.creatures.Thief;
import dev.l0raxeo.tilegame.entities.statics.ChargeStation;
import dev.l0raxeo.tilegame.entities.statics.Hemlet;
import dev.l0raxeo.tilegame.entities.statics.HemletStation;
import dev.l0raxeo.tilegame.tiles.Tile;
import dev.l0raxeo.tilegame.utils.Utils;

import java.awt.*;

public class World {

    private final Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;
    //Entities
    private final EntityManager entityManager;

    public World(Handler handler, String path) {
        this.handler = handler;
        // temp
        entityManager = new EntityManager(handler, new Player(handler, 100, 100));
        entityManager.addEntity(new Thief(handler, 156, 78, 42, 78));
        entityManager.addEntity(new ChargeStation(handler, 1000, 250));
        entityManager.addEntity(new Hemlet(handler, 700, 650, 35, 35));
        entityManager.addEntity(new Hemlet(handler, 1200, 950, 35, 35));
        entityManager.addEntity(new Hemlet(handler, 700, 850, 35, 35));
        entityManager.addEntity(new Hemlet(handler, 800, 850, 35, 35));
        entityManager.addEntity(new HemletStation(handler, 1000, 650, 68, 68));

        loadWorld(path);

        entityManager.getPlayer().setX((spawnX * 70) + 17.5f);
        entityManager.getPlayer().setY((spawnY * 70) + 17.5f);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void tick() {
        entityManager.tick();
    }

    public void render(Graphics g) {
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
                                        (int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
        //Entities
        entityManager.render(g);
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height)
            return Tile.floorTile;

        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null)
            return Tile.floorTile;
        return t;
    }

    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
