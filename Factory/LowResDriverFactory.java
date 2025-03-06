package Factory;

public class LowResDriverFactory implements DriverFactory {
  @Override
  public DisplayDriver getDisplayDriver() {
      return LowResDisplayDriver.getInstance();
  }
  
  @Override
  public PrintDriver getPrintDriver() {
      return LowResPrintDriver.getInstance();
  }
}
