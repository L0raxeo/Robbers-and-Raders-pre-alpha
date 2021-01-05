package dev.l0raxeo.tilegame.states;

import dev.l0raxeo.tilegame.Handler;
import dev.l0raxeo.tilegame.gfx.Assets;
import dev.l0raxeo.tilegame.ui.ClickListener;
import dev.l0raxeo.tilegame.ui.UIImageButton;
import dev.l0raxeo.tilegame.ui.UIManager;

import java.awt.*;

public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImageButton(200, 200, 128, 64, Assets.btn_start, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }
    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }
}
