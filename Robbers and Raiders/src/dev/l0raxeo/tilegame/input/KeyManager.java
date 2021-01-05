package dev.l0raxeo.tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private final boolean[] keys;
    public boolean up, down, left, right;
    public boolean uUp, uDown, uLeft, uRight;
    public boolean increment, decrement;

    public boolean esc;
    public boolean delay;

    public KeyManager() {
        keys = new boolean[256];

        delay = false;
    }

    public void tick() {
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];

        uUp = keys[KeyEvent.VK_E];
        uDown = keys[KeyEvent.VK_Q];
        uLeft = keys[KeyEvent.VK_R];
        uRight = keys[KeyEvent.VK_T];

        increment = keys[KeyEvent.VK_E];
        decrement = keys[KeyEvent.VK_Q];

        esc = keys[KeyEvent.VK_ESCAPE];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // the keycode number is set to true
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        setDelay(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public boolean isDelay() {
        return delay;
    }

    public void setDelay(boolean delay) {
        this.delay = delay;
    }
}
