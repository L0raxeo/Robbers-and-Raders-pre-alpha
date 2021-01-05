package dev.l0raxeo.tilegame.entities.creatures;

import dev.l0raxeo.tilegame.Handler;
import dev.l0raxeo.tilegame.gfx.Animation;
import dev.l0raxeo.tilegame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Laser extends Creature {

    //Animations
    private final Animation anim;
    //Target Coordinates
    private int targetX;
    private int targetY;

    public Laser(Handler handler, float x, float y, int width, int height, int targetX, int targetY) {
        super(handler, x, y, width, height);

        this.x = x;
        this.y = y;

        bounds.x = 103;
        bounds.y = 0;
        bounds.width = 3;
        bounds.height = 68;

        anim = new Animation(30, Assets.laser);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(),
                    (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()),
                    width, height, null);
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

    private BufferedImage getCurrentAnimationFrame() {
        return anim.getCurrentFrame();
    }

}
