package refactoring;

public class Obstacle {
    private SimpleViewPoint.Position position;

    public Obstacle(SimpleViewPoint.Position position) {
        this.position = position;
    }

    public SimpleViewPoint.Position getPosition() {
        return position;
    }
}
