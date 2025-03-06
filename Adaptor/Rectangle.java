package Adaptor;

public class Rectangle extends Shape {
  private int width;
  private int height;

  public Rectangle(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
  }

  @Override
  public void setLocation(int x, int y) {
      this.x = x;
      this.y = y;
      System.out.println("Rectangle setLocation to (" + x + ", " + y + ")");
  }

  @Override
  public String getLocation() {
      return "(" + x + ", " + y + ")";
  }

  @Override
  public void display() {
      System.out.println("Displaying rectangle at " + getLocation() + 
          " with width=" + width + " and height=" + height);
  }

  @Override
  public void fill() {
      System.out.println("Filling rectangle at " + getLocation());
  }

  @Override
  public void setColor(String color) {
      this.color = color;
      System.out.println("Rectangle color set to " + color);
  }

  @Override
  public void undisplay() {
      System.out.println("Removing rectangle from display");
  }
}

