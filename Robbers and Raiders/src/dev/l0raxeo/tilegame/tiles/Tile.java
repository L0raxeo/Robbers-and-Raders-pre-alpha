package dev.l0raxeo.tilegame.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    //STATIC STUFF HERE

    public static Tile[] tiles = new Tile[256];
    public static Tile VOID_TILE = new VoidTile(2);
    public static Tile floorTile = new FloorTile(0);
    public static Tile wallTile = new WallTile(1);

    //CLASS

    public static final int TILE_WIDTH = 70, TILE_HEIGHT = 70;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick() {

    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    public boolean isSolid() {
        return false;
    }

    public int getId() {
        return id;
    }

}
