package dev.l0raxeo.tilegame.entities.creatures;

import dev.l0raxeo.tilegame.Handler;
import dev.l0raxeo.tilegame.entities.Entity;
import dev.l0raxeo.tilegame.tiles.Tile;

public abstract class Creature extends Entity {

    public static final float DEFAULT_SPEED = 3.25f;
    public static final int DEFAULT_CREATURE_WIDTH = 35,
                            DEFAULT_CREATURE_HEIGHT = 35;

    protected float speed;
    protected float xMove, yMove;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move() {
        if (checkEntityCollisions(xMove, 0f)) moveX();
        if (checkEntityCollisions(0f, yMove)) moveY();
    }

    public void moveX() {
        if (xMove > 0) {
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;

            if (collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                    collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
                x += xMove;
            } else {
                x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
            }
        } else if (xMove < 0) {
            int tx = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;

            if (collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                    collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
                x += xMove;
            } else {
                x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
            }
        }
    }

    public void moveY() {
        if (yMove < 0) {
            int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;

            if (collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
                y += yMove;
            } else {
                y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            }
        } else if (yMove > 0) {
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;

            if (collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
                y += yMove;
            } else {
                y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
            }
        }
    }

    protected boolean collisionWithTile(int x, int y) {
        return !handler.getWorld().getTile(x, y).isSolid();
    }

    public abstract float getxMove();

    public float getyMove() {
        return yMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

}
