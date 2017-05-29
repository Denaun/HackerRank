package artificialintelligence.botbuilding.botcleanpartiallyobservable;

import java.io.Serializable;
import java.util.Arrays;

class Map implements Serializable {
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

    boolean isColumnClean(int x) {
        for (State[] state : states) {
            if (state[x] != State.CLEAN) return false;
        }
        return true;
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

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (State[] state : states) {
            for (State aState : state) {
                switch (aState) {
                case UNKNOWN:
                    buffer.append('o');
                    break;
                case CLEAN:
                    buffer.append('-');
                    break;
                case DIRTY:
                    buffer.append('d');
                    break;
                }
            }
            buffer.append('\n');
        }
        return buffer.toString();
    }

    private enum State {
        UNKNOWN,
        CLEAN,
        DIRTY
    }
}
