package refactoring;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

public class Rover {
    private ViewPoint viewPoint;

    public Rover(ViewPoint viewPoint) {
        this.viewPoint = viewPoint;
    }

    public ViewPoint getViewPoint() {
        return viewPoint;
    }

    public void setViewPoint(ViewPoint viewPoint) {
        if(viewPoint != null){
            this.viewPoint = viewPoint;
        }

    }

    public void go(String instructions){
        setViewPoint(go(stream(instructions.split("")).map(Order::of)));
    }

    public void go(Order... orders){
        setViewPoint(go(stream(orders)));
    }


    private ViewPoint go (Stream<Order> orders){
        return orders.filter(Objects::nonNull).reduce(viewPoint, this::execute, (v1, v2)->null);
    }

    private ViewPoint execute(ViewPoint viewPoint, Order order){
        return viewPoint != null ? actions.get(order).execute(viewPoint) : null;
    }

    Map<Order,Action> actions = new HashMap<>();
    {
        actions.put(Order.Left, ViewPoint::turnLeft);
        actions.put(Order.Right, ViewPoint::turnRight);
        actions.put(Order.Forward, ViewPoint::forward);
        actions.put(Order.Backward, ViewPoint::backward);
    }

    public interface Action{
        ViewPoint execute(ViewPoint viewPoint);
    }

    public enum Order{
        Forward, Backward, Left, Right;

        public static Order of(String label){
            if(label.equals("F")) return Forward;
            if(label.equals("B")) return Backward;
            if(label.equals("R")) return Right;
            if(label.equals("L")) return Left;
            return null;
        }
    }
}

