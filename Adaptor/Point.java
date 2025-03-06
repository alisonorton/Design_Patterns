package Adaptor;

public class Point extends Shape {

  public Point(int x, int y) {
      this.x = x;
      this.y = y;
  }

  @Override
  public void setLocation(int x, int y) {
      this.x = x;
      this.y = y;
      System.out.println("Point setLocation to (" + x + ", " + y + ")");
  }

  @Override
  public String getLocation() {
      return "(" + x + ", " + y + ")";
  }

  @Override
  public void display() {
      System.out.println("Displaying a point at " + getLocation());
  }

  @Override
  public void fill() {
      System.out.println("Filling a point at " + getLocation());
  }

  @Override
  public void setColor(String color) {
      this.color = color;
      System.out.println("Point color set to " + color);
  }

  @Override
  public void undisplay() {
      System.out.println("Removing point from display");
  }
}

