package dev.l0raxeo.tilegame;

import dev.l0raxeo.tilegame.display.Display;
import dev.l0raxeo.tilegame.entities.EntityFocus;
import dev.l0raxeo.tilegame.gfx.Assets;
import dev.l0raxeo.tilegame.gfx.GameCamera;
import dev.l0raxeo.tilegame.input.KeyManager;
import dev.l0raxeo.tilegame.input.MouseManager;
import dev.l0raxeo.tilegame.states.GameState;
import dev.l0raxeo.tilegame.states.MenuState;
import dev.l0raxeo.tilegame.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    public Display display;
    private final int width;
    private final int height;
    public String title;

    private boolean running = false;
    private Thread thread;

    //States
    public State gameState;
    public State menuState;

    //Input
    private final KeyManager keyManager;
    private final MouseManager mouseManager;

    //Camera
    private GameCamera gameCamera;

    //Focus
    private final EntityFocus entityFocuser;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        entityFocuser = new EntityFocus();
    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        //Handler
        Handler handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setState(menuState);// if I were to add a menu then we would use State.setState(menuState); or something like that
    }

    private void tick() {
        keyManager.tick();

        if (State.getState() != null) {
            State.getState().tick();
        }
    }

    private void render() {
        BufferStrategy bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);
        //Draw Here

        if (State.getState() != null)
            State.getState().render(g);

        //End Drawing
        bs.show();
        g.dispose();
    }

    public void run() {

        init();

        int fps = 60;
        double timePerTick = 1000000000f / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000) {
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public EntityFocus getEntityFocuser() {
        return entityFocuser;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
