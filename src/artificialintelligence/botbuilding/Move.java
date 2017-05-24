package artificialintelligence.botbuilding;

public class Move implements Action {
    private Direction direction;

    public Move(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return direction.toString();
    }
}
