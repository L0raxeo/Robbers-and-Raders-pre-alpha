package dev.l0raxeo.tilegame.entities.statics;

import dev.l0raxeo.tilegame.Handler;
import dev.l0raxeo.tilegame.entities.creatures.Laser;
import dev.l0raxeo.tilegame.gfx.Animation;
import dev.l0raxeo.tilegame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Hemlet extends HemletStation {

    private final Animation anim;
    private final int hemletID;

    public Hemlet(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);

        this.x = x;
        this.y = y;

        hemletID = super.assignHemletNumber();

        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 35;
        bounds.height = 35;

        anim = new Animation(500, Assets.hemlet);
    }

    @Override
    public void tick() {
        if (handler.getEntityFocuser().getFocusedEntity() != null && handler.getEntityFocuser().getFocusedEntity().equals(this))
            handler.getGameCamera().centerOnEntity(this);

        anim.tick();
        if (super.isInUse())
            setFocusedHemlet();

        if (handler.getEntityFocuser().getFocusedEntity() != null && handler.getEntityFocuser().getFocusedEntity().equals(this))
            getInput();
    }

    @Override
    public void render(Graphics g) {
        //Renders Hemlet Object
        g.drawImage(getCurrentHemletFrame(), (int) (x - handler.getGameCamera().getxOffset()),
                                             (int) (y - handler.getGameCamera().getyOffset()),
                                             width, height,null);

        //Renders Reticle
        if (handler.getEntityFocuser().getFocusedEntity() != null && handler.getEntityFocuser().getFocusedEntity().equals(this))
            handler.getMouseManager().setMouseCursorImg(handler.getGame().display.getFrame(), Assets.reticle);
        //so that it is aligned we will just set the target to the mouse position +/- the difference between the mouse point and the image center
    }

    @Override
    public void die() {
        active = false;
    }

    @Override
    public void use() {
        handler.getWorld().getEntityManager().addEntity(new Laser(handler, this.x, this.y, 34, 34, handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()));
    }

    public void esc() {
        handler.getEntityFocuser().focusOnEntity(null);
        super.setInUse(false);
    }

    public void getInput() {
        //Scrolling

        int min = 1;
        int max = hemletNumber;
        if (handler.getKeyManager().increment && focusedHemlet < max && !handler.getKeyManager().isDelay()) {
            focusedHemlet++;
            handler.getKeyManager().setDelay(true); // LIMITS SO THAT IT CAN ONLY HAPPEN ONCE PER CLICK
        }
        else if (handler.getKeyManager().decrement && focusedHemlet > min && !handler.getKeyManager().isDelay()) {
            focusedHemlet--;
            handler.getKeyManager().setDelay(true); // LIMITS SO THAT IT CAN ONLY HAPPEN ONCE PER CLICK
        }

        //Using
        if (handler.getMouseManager().isLeftPressed()) use();

        //Escape

        if (handler.getKeyManager().esc && !handler.getKeyManager().isDelay())
            esc();
    }

    private BufferedImage getCurrentHemletFrame() {
        return anim.getCurrentFrame();
    }

    public void setFocusedHemlet() {
        if (focusedHemlet == hemletID)
            handler.getEntityFocuser().focusOnEntity(this);
    }
}
