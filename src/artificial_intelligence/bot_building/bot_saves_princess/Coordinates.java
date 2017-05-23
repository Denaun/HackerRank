package artificial_intelligence.bot_building.bot_saves_princess;

class Coordinates {
    private int x;
    private int y;

    Coordinates() {
    }

    Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Coordinates(Coordinates other) {
        this.x = other.x;
        this.y = other.y;
    }

    Coordinates move(Direction direction) {
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
