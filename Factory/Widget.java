package Factory;

public class Widget {
  private DisplayDriver displayDriver;
  
  public Widget(DisplayDriver displayDriver) {
      this.displayDriver = displayDriver;
  }
  
  public void draw() {
      System.out.println("Drawing a Widget using a " + displayDriver.getDescription());
  }
}
