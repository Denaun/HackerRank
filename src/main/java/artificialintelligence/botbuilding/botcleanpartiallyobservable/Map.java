package artificialintelligence.botbuilding.botcleanpartiallyobservable;

import java.io.Serializable;
import java.util.Arrays;

class Map implements Serializable {
    private enum State {
        UNKNOWN,
        CLEAN,
        DIRTY
    }

    private State[][] states;

    Map(int size) {
        states = new State[size][size];
        for (State[] anArray : states) {
            Arrays.fill(anArray, State.UNKNOWN);
        }
    }

    void notifyClean(int x, int y) {
        states[y][x] = State.CLEAN;
    }

    boolean isClean(int x, int y) {
        return states[y][x] == State.CLEAN;
    }

    void notifyDirty(int x, int y) {
        states[y][x] = State.DIRTY;
    }

    boolean isDirty(int x, int y) {
        return states[y][x] == State.DIRTY;
    }

    int size() {
        return states.length;
    }
}
