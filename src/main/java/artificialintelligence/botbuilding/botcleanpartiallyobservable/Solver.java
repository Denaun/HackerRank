package artificialintelligence.botbuilding.botcleanpartiallyobservable;

import artificialintelligence.botbuilding.*;

class Solver {
    private Coordinates start;
    private Map map;
    private Action nextAction;

    Solver(Coordinates start, Map map) {
        this.start = start;
        this.map = map;
        this.nextAction = null;
    }

    boolean solve() {
        if (map.isDirty(start.getX(), start.getY())) {
            nextAction = new Clean();
            return true;
        }
        Direction verticalDirection;
        if (start.getX() % 2 == 0) {
            verticalDirection = Direction.DOWN;
        } else {
            verticalDirection = Direction.UP;
        }
        Coordinates nextPos = new Coordinates(start).move(verticalDirection);
        if (nextPos.getY() > 0 && nextPos.getY() < map.size() - 1) {
            nextAction = new Move(verticalDirection);
            return true;
        }
        if (nextPos.getY() == 0 || nextPos.getY() == map.size() - 1) {
            if (map.isDirty(nextPos.getX(), nextPos.getY())) {
                nextAction = new Move(verticalDirection);
                return true;
            }
            if (nextPos.getX() < map.size() - 1 &&
                map.isDirty(nextPos.getX() + 1, nextPos.getY())) {
                nextAction = new Move(verticalDirection);
                return true;
            }
        }
        if (nextPos.getX() < map.size()) {
            nextAction = new Move(Direction.RIGHT);
            return true;
        }
        nextAction = null;
        return false;
    }

    Action getNextMove() {
        return nextAction;
    }
}
