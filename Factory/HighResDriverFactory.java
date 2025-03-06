package Factory;

public class HighResDriverFactory implements DriverFactory {
  @Override
  public DisplayDriver getDisplayDriver() {
      return HighResDisplayDriver.getInstance();
  }
  
  @Override
  public PrintDriver getPrintDriver() {
      return HighResPrintDriver.getInstance();
  }
}
