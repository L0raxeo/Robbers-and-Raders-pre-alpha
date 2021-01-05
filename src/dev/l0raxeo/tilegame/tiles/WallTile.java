package dev.l0raxeo.tilegame.tiles;

import dev.l0raxeo.tilegame.gfx.Assets;

public class WallTile extends Tile {

    public WallTile(int id) {
        super(Assets.wall, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }

}
