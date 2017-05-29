package artificialintelligence.botbuilding.botcleanpartiallyobservable;

import artificialintelligence.botbuilding.Action;
import artificialintelligence.botbuilding.Clean;
import artificialintelligence.botbuilding.Coordinates;
import artificialintelligence.botbuilding.Move;

import java.io.InvalidObjectException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

class Validator {
    private final int size;
    private Coordinates bot;
    private Set<Coordinates> dirt;

    public Validator(int size, Coordinates bot, Collection<Coordinates> dirt) {
        this.size = 5;
        this.bot = bot;
        this.dirt = new HashSet<>(dirt);
    }

    public void performAction(Action action) throws InvalidObjectException {
        if (action instanceof Move) {
            bot.move(((Move) action).getDirection());
            if (bot.getX() < 0 || bot.getY() < 0 || bot.getX() >= size || bot.getY() >= size) {
                throw new InvalidObjectException("Moving out of bounds.");
            }
            return;
        }
        if (action instanceof Clean) {
            if (!dirt.contains(bot)) {
                throw new InvalidObjectException("Cannot clean an already clean cell.");
            }
            dirt.remove(bot);
            return;
        }
        throw new InvalidObjectException("Unexpected action.");
    }

    public Coordinates getBot() {
        return bot;
    }

    public Map asMap(int visibility) {
        Map result = new Map(size);
        int minX = Math.max(bot.getX() - visibility, 0);
        int minY = Math.max(bot.getY() - visibility, 0);
        int maxX = Math.min(bot.getX() + visibility + 1, size);
        int maxY = Math.min(bot.getY() + visibility + 1, size);
        for (int y = minY; y < maxY; y++) {
            for (int x = minX; x < maxX; x++) {
                if (dirt.contains(new Coordinates(x, y))) {
                    result.notifyDirty(x, y);
                } else {
                    result.notifyClean(x, y);
                }
            }
        }
        return result;
    }

    public boolean isFinished() {
        return dirt.isEmpty();
    }
}
