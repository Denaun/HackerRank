package artificialintelligence.botbuilding;

public enum Direction {
    LEFT,
    RIGHT,
    UP,
    DOWN;

    public Direction inverse() {
        switch (this) {
        case LEFT:
            return RIGHT;
        case RIGHT:
            return LEFT;
        case UP:
            return DOWN;
        case DOWN:
            return UP;
        }
        throw new AssertionError();
    }
}
