package artificialintelligence.botbuilding.botcleanpartiallyobservable;

import artificialintelligence.botbuilding.*;

import java.io.Serializable;

class Solver {
    private Coordinates start;
    private Map map;
    private Action nextAction;
    private State state;
    Solver(Coordinates start, Map map) {
        this.start = start;
        this.map = map;
        this.nextAction = null;
        this.state = null;
    }

    boolean solve() {
        if (map.isDirty(start.getX(), start.getY())) {
            nextAction = new Clean();
            return true;
        }

        if (state == null) {
            state = new State();
        }
        if (state.verticalDirection == null || state.horizontalDirection == null) {
            if (start.getX() < map.size() / 2) {
                if (start.getX() > 0) {
                    nextAction = new Move(Direction.LEFT);
                    return true;
                }
                state.horizontalDirection = Direction.RIGHT;
            } else {
                if (start.getX() < map.size() - 1) {
                    nextAction = new Move(Direction.RIGHT);
                    return true;
                }
                state.horizontalDirection = Direction.LEFT;
            }
            if (start.getY() < map.size() / 2) {
                if (start.getY() > 0) {
                    nextAction = new Move(Direction.UP);
                    return true;
                }
                state.verticalDirection = Direction.DOWN;
            } else {
                if (start.getY() < map.size() - 1) {
                    nextAction = new Move(Direction.DOWN);
                    return true;
                }
                state.verticalDirection = Direction.UP;
            }
        }

        if (start.getY() <= 1 && map.isColumnClean(start.getX())) {
            state.verticalDirection = Direction.DOWN;
            nextAction = new Move(state.horizontalDirection);
            return true;
        }
        if (start.getY() >= map.size() - 2 && map.isColumnClean(start.getX())) {
            state.verticalDirection = Direction.UP;
            nextAction = new Move(state.horizontalDirection);
            return true;
        }

        // Check the previous position in case we switched column early and there is dirt on the
        // border.
        Coordinates prevPos = new Coordinates(start).move(state.verticalDirection.inverse());
        if (prevPos.getY() >= 0 && prevPos.getY() <= map.size() - 1
            && map.isDirty(prevPos.getX(), prevPos.getY())) {
            nextAction = new Move(state.verticalDirection.inverse());
            return true;
        }
        Coordinates nextPos = new Coordinates(start).move(state.verticalDirection);
        if (nextPos.getY() >= 0 && nextPos.getY() <= map.size() - 1) {
            nextAction = new Move(state.verticalDirection);
            return true;
        }
        nextAction = null;
        return false;
    }

    Action getNextMove() {
        return nextAction;
    }

    Serializable getSerializableState() {
        return state;
    }

    void setSerializableState(Object state) {
        this.state = (State) state;
    }

    private class State implements Serializable {
        Direction verticalDirection;
        Direction horizontalDirection;
    }
}
