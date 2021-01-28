package refactoring;

public class Camera {
    private CameraView frontView;
    private CameraView rearView;
    private int angle;

    public Camera(int angle){
        this.angle = angle;
    }

    public Camera turnLeft(Integer degrees){
        return new Camera(angle-degrees);
    }

    public Camera turnRight(Integer degrees){
        return new Camera(angle+degrees);
    }
}
