package artificialintelligence.botbuilding.botcleanpartiallyobservable;

import java.io.Serializable;

class Map implements Serializable {
    Map(int size) {
    }

    void notifyClean(int x, int y) {
    }

    boolean isClean(int x, int y) {
        return false;
    }

    void notifyDirty(int x, int y) {
    }

    boolean isDirty(int x, int y) {
        return false;
    }

    int size() {
        return 0;
    }
}
