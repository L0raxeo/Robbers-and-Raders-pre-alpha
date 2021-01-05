package dev.l0raxeo.tilegame.tiles;

import dev.l0raxeo.tilegame.gfx.Assets;

public class FloorTile extends Tile {

    public FloorTile(int id) {
        super(Assets.floor, id);
    }

    public int getId() {
        return id;
    }

}
