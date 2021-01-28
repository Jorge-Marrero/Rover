package refactoring;

import java.util.HashMap;
import java.util.Map;

public class SimpleViewPoint implements ViewPoint{
    private Heading heading;
    private Position position;
    private static final Map<Position, Obstacle> obstacles = new HashMap<>();

    public SimpleViewPoint(String facing, int x, int y) {
        this(Heading.of(facing), new Position(x, y));
    }

    public SimpleViewPoint(Heading heading, int x, int y) {
        this(heading, new Position(x, y));
    }

    public SimpleViewPoint(Heading heading, Position position) {
        this.heading = heading;
        this.position = position;
    }

    public void obstacleAdd(Obstacle obs){
        obstacles.put(obs.getPosition(), obs);
    }

    public Heading getHeading() {
        return heading;
    }

    public Position getPosition() {
        return position;
    }

    public static Map<Position, Obstacle> getObstacles() {
        return obstacles;
    }

    @Override
    public ViewPoint forward() {
        position = position.forward(heading);
        return this;
    }

    @Override
    public ViewPoint backward() {
        position = position.backward(heading);
        return this;
    }

    @Override
    public ViewPoint turnLeft() {
        heading = heading.turnLeft();
        return this;
    }

    @Override
    public ViewPoint turnRight() {
        heading = heading.turnRight();
        return this;
    }

    public enum Heading {
        North, East, South, West;

        public static Heading of(String label) {
            return of(label.charAt(0));
        }

        public static Heading of(char label) {
            if (label == 'N') return North;
            if (label == 'S') return South;
            if (label == 'W') return West;
            if (label == 'E') return East;
            return null;
        }

        public Heading turnRight() {
            return values()[add(+1)];
        }

        public Heading turnLeft() {
            return values()[add(-1)];
        }

        private int add(int offset) {
            return (this.ordinal() + offset + values().length) % values().length;
        }

    }

    public static class Position {
        private final int x;
        private final int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position forward(Heading heading) {
            Position next = forwardPos(heading);
            return obstacleExist(next) ? this : next;
        }

        public Position backward(Heading heading) {
            Position next = backwardPos(heading);
            return obstacleExist(next) ? this : next;
        }

        private boolean obstacleExist(Position next) {
            return obstacles.containsKey(next);
        }

        private Position backwardPos(Heading heading) {
            return new Position(x - dx(heading), y - dy(heading));
        }

        public Position forwardPos(Heading heading){
            return new Position(x + dx(heading), y + dy(heading));
        }

        private int dy(Heading heading) {
            return (heading == Heading.North) ? 1 :
                    (heading == Heading.South) ? -1 : 0;
        }

        private int dx(Heading heading) {
            return (heading == Heading.East) ? 1 :
                    (heading == Heading.West) ? -1 : 0;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        @Override
        public boolean equals(Object object) {
            return isSameClass(object) && equals((Position) object);
        }

        private boolean equals(Position position) {
            return position == this || (x == position.x && y == position.y);
        }

        private boolean isSameClass(Object object) {
            return object != null && object.getClass() == Position.class;
        }

    }
}
