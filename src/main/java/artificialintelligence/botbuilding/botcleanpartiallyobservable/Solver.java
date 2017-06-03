package artificialintelligence.botbuilding.botcleanpartiallyobservable;

import artificialintelligence.botbuilding.*;

import java.io.Serializable;

class Solver {
    private final Coordinates start;
    private final Map map;
    private Action nextAction;
    private SolverState state;

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
            state = new SolverState();
            state.center = new Coordinates(start);
            state.next = new Coordinates(start);
        }

        if (start.equals(state.next)) {
            Coordinates destination = state.next;
            do {
                destination = nextCirclePoint(destination);
            } while (map.outOfBounds(destination));
            state.next = destination;
        }
        nextAction = new Move(directionTowards(state.next));
        return true;
    }

    Action getNextMove() {
        return nextAction;
    }

    Serializable getSerializableState() {
        return state;
    }

    void setSerializableState(Serializable state) {
        this.state = (SolverState) state;
    }

    /**
     * "Circular" motion: start from going right, then down, then left, then up.
     * When we reach the top right corner, increment the radius.
     */
    private Coordinates nextCirclePoint(Coordinates current) {
        final int xDistance = current.getX() - state.center.getX();
        final int yDistance = current.getY() - state.center.getY();
        Coordinates result = new Coordinates(current);
        if (Math.abs(xDistance) == Math.abs(yDistance)) {
            // Corner.
            if (yDistance <= 0) {
                // North: rotate if left, expand if right. In the end it's the same.
                return result.move(Direction.RIGHT);
            } else if (xDistance > 0) {
                // South east: rotate.
                return result.move(Direction.LEFT);
            } else {
                // South west: rotate.
                assert xDistance != 0 : "This means y=0.";
                return result.move(Direction.UP);
            }
        } else {
            // Edge: keep going.
            if (Math.abs(xDistance) < Math.abs(yDistance)) {
                // Moving along X.
                assert yDistance != 0 : "abs(x) would have to be < 0.";
                if (yDistance < 0) {
                    // North.
                    return result.move(Direction.RIGHT);
                } else {
                    // South.
                    return result.move(Direction.LEFT);
                }
            } else {
                assert xDistance != 0 : "abs(y) would have to be < 0.";
                if (xDistance > 0) {
                    // East.
                    return result.move(Direction.DOWN);
                } else {
                    // West.
                    return result.move(Direction.UP);
                }
            }
        }
    }

    private Direction directionTowards(Coordinates destination) {
        if (start.getX() > destination.getX()) {
            return Direction.LEFT;
        }
        if (start.getX() < destination.getX()) {
            return Direction.RIGHT;
        }
        if (start.getY() > destination.getY()) {
            return Direction.UP;
        }
        if (start.getY() < destination.getY()) {
            return Direction.DOWN;
        }
        assert false;
        return null;
    }
}

class SolverState implements Serializable {
    Coordinates center;
    Coordinates next;
}

