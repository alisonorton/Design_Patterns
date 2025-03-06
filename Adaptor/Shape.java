package Adaptor;

public abstract class Shape {
  // For simplicity, store location as x, y
  protected int x;
  protected int y;
  protected String color;

  public abstract void setLocation(int x, int y);
  public abstract String getLocation();
  public abstract void display();
  public abstract void fill();
  public abstract void setColor(String color);
  public abstract void undisplay();
}

