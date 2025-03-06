package Factory;

public interface DriverFactory {
  DisplayDriver getDisplayDriver();
  PrintDriver getPrintDriver();
}
