package Adaptor;

public class Circle extends Shape {
  // Adaptee
  private XXCircle xxcircle;

  // We can store additional circle-specific data if we want (e.g. radius),
  // but for demonstration, let's keep it simple.
  public Circle(int x, int y) {
      xxcircle = new XXCircle();
      xxcircle.setLocation(x, y);
  }

  @Override
  public void setLocation(int x, int y) {
      xxcircle.setLocation(x, y);
  }

  @Override
  public String getLocation() {
      return xxcircle.getLocation();
  }

  @Override
  public void display() {
      xxcircle.displayIt();
  }

  @Override
  public void fill() {
      xxcircle.fillIt();
  }

  @Override
  public void setColor(String color) {
      xxcircle.setItsColor(color);
  }

  @Override
  public void undisplay() {
      xxcircle.undisplayIt();
  }
}

