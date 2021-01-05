package dev.l0raxeo.tilegame.entities.statics;

import dev.l0raxeo.tilegame.Handler;
import dev.l0raxeo.tilegame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ChargeStation extends StaticEntity {

    private int state = 0;

    public ChargeStation(Handler handler, float x, float y) {
        super(handler, x, y, 50, 50);

        bounds.x = 10;
        bounds.y = (int) (height / 1.5f);
        bounds.width = width - 20;
        bounds.height = (int) (height - height / 1.5f);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getChargeStationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die() {

    }

    @Override
    public void use() {

    }

    @Override
    public float getxMove() {
        return 0;
    }

    @Override
    public float getyMove() {
        return 0;
    }

    @Override
    public void setxMove(float xMove) {

    }

    @Override
    public void setyMove(float yMove) {

    }

    public void esc() {
        handler.getEntityFocuser().focusOnEntity(null);
    }

    private BufferedImage getChargeStationFrame() {
        if (state == 4) {
            return Assets.charge_station[4];
        } else if (state == 3) {
            return Assets.charge_station[3];
        } else if (state == 2) {
            return Assets.charge_station[2];
        } else if (state == 1) {
            return Assets.charge_station[1];
        } else {
            return Assets.charge_station[0];
        }
    }

}
