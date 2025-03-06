package Factory;

public class LowResDisplayDriver implements DisplayDriver {
  private static LowResDisplayDriver instance = new LowResDisplayDriver();
  
  private LowResDisplayDriver() { }
  
  public static LowResDisplayDriver getInstance() {
      return instance;
  }
  
  @Override
  public String getDescription() {
      return "LowResDisplayDriver";
  }
}
