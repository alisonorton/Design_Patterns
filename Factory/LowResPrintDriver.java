package Factory;

public class LowResPrintDriver implements PrintDriver {
  private static LowResPrintDriver instance = new LowResPrintDriver();
  
  private LowResPrintDriver() { }
  
  public static LowResPrintDriver getInstance() {
      return instance;
  }
  
  @Override
  public String getDescription() {
      return "LowResPrintDriver";
  }
}
