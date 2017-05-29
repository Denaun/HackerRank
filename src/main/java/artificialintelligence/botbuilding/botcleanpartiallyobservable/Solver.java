package artificialintelligence.botbuilding.botcleanpartiallyobservable;

import artificialintelligence.botbuilding.*;

import java.io.Serializable;

class Solver {
    private Coordinates start;
    private Map map;
    private Action nextAction;
    private Direction verticalDirection;

    Solver(Coordinates start, Map map) {
        this.start = start;
        this.map = map;
        this.nextAction = null;
        this.verticalDirection = null;
    }

    boolean solve() {
        if (map.isDirty(start.getX(), start.getY())) {
            nextAction = new Clean();
            return true;
        }

        if (verticalDirection == null) {
            if (start.getX() > 0) {
                nextAction = new Move(Direction.LEFT);
                return true;
            }
            if (start.getY() > 0) {
                nextAction = new Move(Direction.UP);
                return true;
            }
            verticalDirection = Direction.DOWN;
        }

        if (start.getY() <= 1 && map.isColumnClean(start.getX())) {
            verticalDirection = Direction.DOWN;
            nextAction = new Move(Direction.RIGHT);
            return true;
        }
        if (start.getY() >= map.size() - 2 && map.isColumnClean(start.getX())) {
            verticalDirection = Direction.UP;
            nextAction = new Move(Direction.RIGHT);
            return true;
        }

        // Check the previous position in case we switched column early and there is dirt on the
        // border.
        Coordinates prevPos = new Coordinates(start).move(verticalDirection.inverse());
        if (prevPos.getY() >= 0 && prevPos.getY() <= map.size() - 1
            && map.isDirty(prevPos.getX(), prevPos.getY())) {
            nextAction = new Move(verticalDirection.inverse());
            return true;
        }
        Coordinates nextPos = new Coordinates(start).move(verticalDirection);
        if (nextPos.getY() >= 0 && nextPos.getY() <= map.size() - 1) {
            nextAction = new Move(verticalDirection);
            return true;
        }
        nextAction = null;
        return false;
    }

    Action getNextMove() {
        return nextAction;
    }

    Serializable getSerializableState() {
        return verticalDirection;
    }

    void setSerializableState(Object state) {
        verticalDirection = (Direction) state;
    }
}
