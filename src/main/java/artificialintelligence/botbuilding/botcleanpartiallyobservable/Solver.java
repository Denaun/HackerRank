package artificialintelligence.botbuilding.botcleanpartiallyobservable;

import artificialintelligence.botbuilding.Action;
import artificialintelligence.botbuilding.Coordinates;

class Solver {
    private Coordinates start;
    private Map map;

    Solver(Coordinates start, Map map) {
        this.start = start;
        this.map = map;
    }

    boolean solve() {
        return false;
    }

    Action getNextMove() {
        return new Action() {
        };
    }
}
