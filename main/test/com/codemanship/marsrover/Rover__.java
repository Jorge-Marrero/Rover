package com.codemanship.marsrover;

import org.junit.Test;
import refactoring.Obstacle;
import refactoring.SimpleViewPoint;
import refactoring.Rover;

import static org.assertj.core.api.Assertions.assertThat;
import static refactoring.Rover.Order.*;
import static refactoring.SimpleViewPoint.Heading.*;
import static refactoring.SimpleViewPoint.Position;

public class Rover__ {

    @Test
    public void could_turn_right(){
        Rover rover = new Rover (new SimpleViewPoint(East, new Position(5, 1)));
        rover.go(Right);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getHeading()).isEqualTo(South);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getPosition()).isEqualTo(new Position(5,1));
    }

    @Test
    public void could_turn_left(){
        Rover rover = new Rover (new SimpleViewPoint(North, new Position(3, 3)));
        rover.go(Left);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getHeading()).isEqualTo(West);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getPosition()).isEqualTo(new Position(3,3));
    }

    @Test
    public void could_go_forward(){
        Rover rover = new Rover (new SimpleViewPoint(South, new Position(-1, 1)));
        rover.go(Forward);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getHeading()).isEqualTo(South);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getPosition()).isEqualTo(new Position(-1,0));
    }

    @Test
    public void could_go_backward(){
        Rover rover = new Rover (new SimpleViewPoint(West, new SimpleViewPoint.Position(-4, 4)));
        rover.go(Backward);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getHeading()).isEqualTo(West);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getPosition()).isEqualTo(new SimpleViewPoint.Position(-3,4));
    }

    @Test
    public void could_execute_many_orders(){
        Rover rover = new Rover (new SimpleViewPoint(West, new Position(3, 1)));
        rover.go(Backward, Left, Forward, Right, Forward);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getHeading()).isEqualTo(West);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getPosition()).isEqualTo(new Position(3,0));
    }

    @Test
    public void could_ignore_legacy_instructions(){
        Rover rover = new Rover (new SimpleViewPoint(West, new Position(3, 1)));
        rover.go("BLF*RF");
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getHeading()).isEqualTo(West);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getPosition()).isEqualTo(new Position(3,0));
    }

    @Test
    public void could_not_move_forward_if_there_is_an_obstacle(){
        SimpleViewPoint simpleViewPoint = new SimpleViewPoint(North, new Position(1,1));
        Rover rover = new Rover (simpleViewPoint);
        Obstacle obstacle = new Obstacle(new Position(1, 2));
        simpleViewPoint.obstacleAdd(obstacle);
        rover.go(Forward);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getHeading()).isEqualTo(North);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getPosition()).isEqualTo(new Position(1,1));
    }

    @Test
    public void could_not_move_backward_if_there_is_an_obstacle(){
        SimpleViewPoint simpleViewPoint = new SimpleViewPoint(South, new Position(-1,3));
        Rover rover = new Rover (simpleViewPoint);
        Obstacle obstacle = new Obstacle(new Position(0, 3));
        simpleViewPoint.obstacleAdd(obstacle);
        rover.go(Right, Backward);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getHeading()).isEqualTo(West);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getPosition()).isEqualTo(new Position(-1,3));
    }
}