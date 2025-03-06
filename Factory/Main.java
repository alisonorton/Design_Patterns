package Factory;

public class Main {
  public static void main(String[] args) {
      // Default resolution setting (could also be read from a config file)
      String resolution = "low";
      
      // For demonstration, try to read resolution from a file named "config.txt"
      try (java.util.Scanner scanner = new java.util.Scanner(new java.io.File("config.txt"))) {
          if (scanner.hasNextLine()) {
              resolution = scanner.nextLine().trim().toLowerCase();
          }
      } catch (java.io.FileNotFoundException e) {
          System.out.println("config.txt not found. Using default resolution: low");
      }
      
      DriverFactory factory;
      if (resolution.equals("high")) {
          factory = new HighResDriverFactory();
      } else {
          factory = new LowResDriverFactory();
      }
      
      Widget widget = new Widget(factory.getDisplayDriver());
      Document document = new Document(factory.getPrintDriver());
      
      widget.draw();
      document.print();
  }
}
