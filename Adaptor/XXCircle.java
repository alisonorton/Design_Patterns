package Adaptor;

public class XXCircle {
  private int x;
  private int y;
  private String color;

  public void setLocation(int x, int y) {
      this.x = x;
      this.y = y;
      System.out.println("XXCircle setLocation to (" + x + ", " + y + ")");
  }

  public String getLocation() {
      return "(" + x + ", " + y + ")";
  }

  public void displayIt() {
      System.out.println("Displaying XXCircle at " + getLocation());
  }

  public void fillIt() {
      System.out.println("Filling XXCircle at " + getLocation());
  }

  public void setItsColor(String color) {
      this.color = color;
      System.out.println("XXCircle color set to " + color);
  }

  public void undisplayIt() {
      System.out.println("Removing XXCircle from display");
  }
}

