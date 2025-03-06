package Factory;

public class HighResDisplayDriver implements DisplayDriver {
  private static HighResDisplayDriver instance = new HighResDisplayDriver();
  
  private HighResDisplayDriver() { }
  
  public static HighResDisplayDriver getInstance() {
      return instance;
  }
  
  @Override
  public String getDescription() {
      return "HighResDisplayDriver";
  }
}
