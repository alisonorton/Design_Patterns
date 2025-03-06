package Adaptor;

public class Line extends Shape {
  private int x2;
  private int y2;

  public Line(int x1, int y1, int x2, int y2) {
      this.x = x1;
      this.y = y1;
      this.x2 = x2;
      this.y2 = y2;
  }

  @Override
  public void setLocation(int x, int y) {
      // Just shift the line so that its "start" is at (x, y)
      int dx = x - this.x;
      int dy = y - this.y;
      this.x = x;
      this.y = y;
      this.x2 += dx;
      this.y2 += dy;
      System.out.println("Line moved to start at (" + this.x + ", " + this.y + ")");
  }

  @Override
  public String getLocation() {
      return "start: (" + x + ", " + y + "), end: (" + x2 + ", " + y2 + ")";
  }

  @Override
  public void display() {
      System.out.println("Displaying a line from " + getLocation());
  }

  @Override
  public void fill() {
      System.out.println("Filling a line is nonsensical, but let's do it anyway!");
  }

  @Override
  public void setColor(String color) {
      this.color = color;
      System.out.println("Line color set to " + color);
  }

  @Override
  public void undisplay() {
      System.out.println("Removing line from display");
  }
}

