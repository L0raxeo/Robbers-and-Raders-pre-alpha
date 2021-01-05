package dev.l0raxeo.tilegame.states;

import dev.l0raxeo.tilegame.Handler;

import java.awt.*;

public abstract class State {

    private static State currentState = null;

    //GAME STATE MANAGER

    public static void setState(State state) {
        currentState = state;
    }

    public static State getState() {
        return currentState;
    }

    //CLASS

    protected Handler handler;

    public State(Handler handler) {
        this.handler = handler;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

}
