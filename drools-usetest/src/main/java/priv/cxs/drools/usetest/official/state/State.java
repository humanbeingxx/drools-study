package priv.cxs.drools.usetest.official.state;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Copyright © 2018 QUNAR.COM. All rights reserved.
 *
 * @author xiaoshuang.cui
 * @date 2018/6/4 下午4:10
 **/

public class State {

    public final static int START = 0;
    public final static int STOP = 1;

    private final PropertyChangeSupport changes =
            new PropertyChangeSupport(this);

    private String name;

    private int state;

    public State() {
    }

    public State(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        int oldState = this.state;
        int newState = state;
        this.state = state;
        this.changes.firePropertyChange("state",
                oldState,
                newState);
    }

    public void addPropertyChangeListener(final PropertyChangeListener l) {
        this.changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(final PropertyChangeListener l) {
        this.changes.removePropertyChangeListener(l);
    }
}
