package com.codemanship.marsrover;

import org.junit.Before;
import org.junit.Test;
import refactoring.Camera;
import refactoring.CameraViewPoint;
import refactoring.ImageProcessor;
import refactoring.ViewPoint;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CameraViewPoint_Mock {
    private Camera camera;
    private ViewPoint initialViewPoint;

    @Before
    public void setUp(){
        camera = mock(Camera.class);
        ImageProcessor imageProcessor = mock(ImageProcessor.class);
        initialViewPoint = new CameraViewPoint(camera, imageProcessor);
    }

    @Test
    public void when_turning_left_should_return_a_new_viewPoint(){
        ViewPoint viewPoint = initialViewPoint.turnLeft();
        assertThat(viewPoint).isNotNull().isNotEqualTo(initialViewPoint);
        verify(camera).turnLeft(90);
    }

    @Test
    public void should_be_able_to_turn_right(){
        Camera camera = mock(Camera.class);
        when(camera.turnRight(90)).thenReturn(camera);
        ImageProcessor imageProcessor = mock(ImageProcessor.class);
        CameraViewPoint cameraViewPoint = new CameraViewPoint(camera, imageProcessor);
        cameraViewPoint.turnRight();
        verify(camera).turnRight(90);
    }
}
