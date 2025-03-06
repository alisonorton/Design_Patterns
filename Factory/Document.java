package Factory;

public class Document {
  private PrintDriver printDriver;
  
  public Document(PrintDriver printDriver) {
      this.printDriver = printDriver;
  }
  
  public void print() {
      System.out.println("Printing a Document using a " + printDriver.getDescription());
  }
}
