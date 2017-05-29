package artificialintelligence.botbuilding.botcleanpartiallyobservable;

import java.io.Serializable;
import java.util.Arrays;

public class Map implements Serializable {
    private State[][] states;

    public Map(int size) {
        states = new State[size][size];
        for (State[] anArray : states) {
            Arrays.fill(anArray, State.UNKNOWN);
        }
    }

    public void notifyClean(int x, int y) {
        states[y][x] = State.CLEAN;
    }

    public boolean isClean(int x, int y) {
        return states[y][x] == State.CLEAN;
    }

    boolean isColumnClean(int x) {
        for (State[] state : states) {
            if (state[x] != State.CLEAN) return false;
        }
        return true;
    }

    public void notifyDirty(int x, int y) {
        states[y][x] = State.DIRTY;
    }

    public boolean isDirty(int x, int y) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Map map = (Map) o;

        return Arrays.deepEquals(states, map.states);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(states);
    }

    private enum State {
        UNKNOWN,
        CLEAN,
        DIRTY
    }
}
