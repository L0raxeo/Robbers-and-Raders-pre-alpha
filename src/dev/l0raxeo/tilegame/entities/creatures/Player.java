package dev.l0raxeo.tilegame.entities.creatures;

import dev.l0raxeo.tilegame.Handler;
import dev.l0raxeo.tilegame.entities.Entity;
import dev.l0raxeo.tilegame.gfx.Animation;
import dev.l0raxeo.tilegame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    //Animations
    private final Animation animRight;
    private final Animation animLeft;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 0;
        bounds.y = 22;
        bounds.width = 35;
        bounds.height = 12;

        //Animations
        animRight = new Animation(1000, Assets.player_right);
        animLeft = new Animation(1000, Assets.player_left);
    }

    @Override
    public void tick() {
        // Position
        super.move();
        if (handler.getEntityFocuser().getFocusedEntity() == null){
            handler.getEntityFocuser().focusOnEntity(this);
        }
        else if (handler.getEntityFocuser().getFocusedEntity().equals(this))
            getInput();
        //Animations
        animRight.tick();
        animLeft.tick();
        if (handler.getEntityFocuser().getFocusedEntity() == null){
            handler.getEntityFocuser().focusOnEntity(this);
        }
        else if (handler.getEntityFocuser().getFocusedEntity().equals(this))
            handler.getGameCamera().centerOnEntity(this);
        checkUse();
    }

    private void checkUse() {
        //collision bounds
        Rectangle cb = getCollisionBounds(0, 0);
        //use rectangle
        Rectangle ur = new Rectangle();
        int urSize = 15;
        ur.width = urSize;
        ur.height = urSize;

        if (handler.getKeyManager().uUp) { // up
            ur.x = cb.x + cb.width / 2 - urSize / 2;
            ur.y = cb.y - urSize;
        }
        else if (handler.getKeyManager().uDown) { // down
            ur.x = cb.x + cb.width / 2 - urSize / 2;
            ur.y = cb.y + cb.height;
        }
        else if (handler.getKeyManager().uLeft) { // left
            ur.x = cb.x - urSize;
            ur.y = cb.y + cb.height / 2 - urSize / 2;
        }
        else if (handler.getKeyManager().uRight) { // right
            ur.x = cb.x + cb.width;
            ur.y = cb.y + cb.height / 2 - urSize / 2;
        } else return;

        for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this))
                continue;
            if (e.getCollisionBounds(0,0).intersects(ur)) {
                e.use();
                return;
            }
        }

    }

    @Override
    public float getxMove() {
        return xMove;
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if (handler.getKeyManager().up) yMove = -speed;
        if (handler.getKeyManager().down) yMove = speed;
        if (handler.getKeyManager().left) xMove = -speed;
        if (handler.getKeyManager().right) xMove = speed;

        if (handler.getKeyManager().esc && !handler.getKeyManager().isDelay()) esc();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
                                                (int) (y - handler.getGameCamera().getyOffset()),
                                                width, height, null);
    }

    @Override
    public void die() {

    }

    @Override
    public void use() {

    }

    public void esc() {
        handler.getKeyManager().setDelay(true);
        System.out.println("escape");
    }

    private BufferedImage getCurrentAnimationFrame() {
        if (xMove < 0) {
            return animLeft.getCurrentFrame();
        } else {
            return animRight.getCurrentFrame();
        }
    }

}
