package Factory;

public class HighResPrintDriver implements PrintDriver {
  private static HighResPrintDriver instance = new HighResPrintDriver();
  
  private HighResPrintDriver() { }
  
  public static HighResPrintDriver getInstance() {
      return instance;
  }
  
  @Override
  public String getDescription() {
      return "HighResPrintDriver";
  }
}
