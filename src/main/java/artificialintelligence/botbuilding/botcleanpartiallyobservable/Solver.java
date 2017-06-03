package artificialintelligence.botbuilding.botcleanpartiallyobservable;

import artificialintelligence.botbuilding.*;

import java.io.Serializable;
import java.util.function.Predicate;

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
        }

        Coordinates destination = nearestDirt();
        if (destination == null) {
            destination = nearestUnexplored();
        }
        assert destination != null;
        state.next = destination;
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

    private Coordinates nearestDirt() {
        return nearestWith(c -> map.isDirty(c.getX(), c.getY()));
    }

    private Coordinates nearestUnexplored() {
        return nearestWith(
                c -> !map.isDirty(c.getX(), c.getY()) && !map.isClean(c.getX(), c.getY()));
    }

    private Coordinates nearestWith(Predicate<Coordinates> condition) {
        Coordinates destination = null;
        int minDistance = Integer.MAX_VALUE;
        for (int x = 0; x < map.size(); x++) {
            for (int y = 0; y < map.size(); y++) {
                Coordinates coordinates = new Coordinates(x, y);
                if (condition.test(coordinates)) {
                    int distance = start.getL1DistanceFrom(coordinates);
                    if (distance < minDistance) {
                        destination = coordinates;
                    }
                }
            }
        }
        return destination;
    }
}

class SolverState implements Serializable {
    Coordinates next;
}

