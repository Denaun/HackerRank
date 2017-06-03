package artificialintelligence.botbuilding;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private int x;
    private int y;

    public Coordinates() {
    }

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(Coordinates other) {
        this.x = other.x;
        this.y = other.y;
    }

    public Coordinates move(Direction direction) {
        switch (direction) {
        case LEFT:
            x -= 1;
            break;
        case RIGHT:
            x += 1;
            break;
        case UP:
            y -= 1;
            break;
        case DOWN:
            y += 1;
            break;
        }
        return this;
    }

    int getL1DistanceFrom(Coordinates other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
               "x=" + x +
               ", y=" + y +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
