package Adaptor;

import java.util.ArrayList;
import java.util.List;

public class ShapeTest {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();

        shapes.add(new Point(1, 2));
        shapes.add(new Line(0, 0, 5, 5));
        shapes.add(new Rectangle(10, 10, 20, 30));
        shapes.add(new Circle(100, 100));

        for (Shape s : shapes) {
            s.setColor("red");
            s.display();
            s.fill();
            System.out.println("Location is " + s.getLocation());
            s.undisplay();
            System.out.println("---------------");
        }
    }
}

