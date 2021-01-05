package dev.l0raxeo.tilegame.entities.statics;

import dev.l0raxeo.tilegame.Handler;
import dev.l0raxeo.tilegame.gfx.Assets;

import java.awt.*;

public class HemletStation extends StaticEntity {

    protected static boolean inUse = false;
    protected static int hemletNumber;
    protected static int focusedHemlet = 1;

    public HemletStation(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);

        this.x = x;
        this.y = y;

        bounds.x = 0;
        bounds.y = height / 3;
        bounds.width = width;
        bounds.height = height - height / 3;
    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.hemlet_station, (int) (x - handler.getGameCamera().getxOffset()),
                                           (int) (y - handler.getGameCamera().getyOffset()),
                                           width, height,null);
    }

    @Override
    public void die() {}

    @Override
    public void use() {
        setInUse(true);
    }

    @Override
    public float getxMove() {return 0;}

    @Override
    public float getyMove() {return 0;}

    @Override
    public void setxMove(float xMove) {}

    @Override
    public void setyMove(float yMove) {}

    public int assignHemletNumber() {
        hemletNumber++;
        return hemletNumber;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        HemletStation.inUse = inUse;
    }

}
