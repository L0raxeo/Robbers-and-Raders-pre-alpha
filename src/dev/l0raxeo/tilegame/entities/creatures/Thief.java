package dev.l0raxeo.tilegame.entities.creatures;

import dev.l0raxeo.tilegame.Handler;
import dev.l0raxeo.tilegame.entities.Entity;
import dev.l0raxeo.tilegame.gfx.Animation;
import dev.l0raxeo.tilegame.gfx.Assets;
import dev.l0raxeo.tilegame.utils.ai.AI;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Thief extends Creature {

    //Animations
    private final Animation animRight;
    private final Animation animLeft;
    private final Animation animAttackRight;
    private final Animation animAttackLeft;
    //Attack timer
    private long lastAttackTimer;
    private final long attackCooldown = 800;
    private long attackTimer = attackCooldown;
    //Attack Manager
    private boolean attackRight;
    private boolean attackLeft;

    public Thief(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);

        this.x = x;
        this.y = y;

        bounds.x = 6;
        bounds.y = 32;
        bounds.width = 34;
        bounds.height = 50;

        animRight = new Animation(250, Assets.thief_right);
        animLeft = new Animation(250, Assets.thief_left);
        animAttackRight = new Animation(250, Assets.thief_attack_right);
        animAttackLeft = new Animation(250, Assets.thief_attack_left);

        attackRight = false;
        attackLeft = false;
    }

    @Override
    public void tick() {
        AI ai = new AI(handler.getWorld().getEntityManager(), this);
        // Bounds
        super.move();
        // Put AI moving here
        ai.findNearestEntity(); // this doesn't work because we can't init the object ai
        //Animations
        animRight.tick();
        animLeft.tick();

        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown)
            return;

        Rectangle cb = getCollisionBounds(0, 0);
        //use rectangle
        Rectangle ur = new Rectangle();
        int urSize = 15;
        ur.width = urSize;
        ur.height = urSize;

        if (getxMove() == -2.25) {
            attackLeft = true;
            ur.x = cb.x - urSize;
            ur.y = cb.y + cb.height / 2 - urSize / 2;
        } else if (getxMove() == 2.25) {
            attackRight = true;
            ur.x = cb.x + cb.width;
            ur.y = cb.y + cb.height / 2 - urSize / 2;
        }

        attackTimer = 0;

        for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this))
                continue;
            if (e.getCollisionBounds(0,0).intersects(ur)) {
                e.hurt(1);
                return;
            }
        }

        attackRight = false;
        attackLeft = false;
    }

    @Override
    public float getxMove() {
        return xMove;
    }

    @Override
    public float getyMove() {
        return yMove;
    }

    @Override
    public void setxMove(float xMove) {
        super.xMove = xMove;
    }

    @Override
    public void setyMove(float yMove) {
        super.yMove = yMove;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
                                                (int) (y - handler.getGameCamera().getyOffset()),
                                                width, height, null);
    }

    @Override
    public void die() {
        //drop items ig
        System.out.println("played dead");
    }

    @Override
    public void use() {

    }

    private BufferedImage getCurrentAnimationFrame() {
        if (attackRight) {
            return animAttackRight.getCurrentFrame();
        } else if (attackLeft) {
            return animAttackLeft.getCurrentFrame();
        } else if (xMove < 0) {
            return animLeft.getCurrentFrame();
        } else {
            return animRight.getCurrentFrame();
        }
    }

}
