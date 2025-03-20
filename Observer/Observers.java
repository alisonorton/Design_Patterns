import java.io.FileWriter;
import java.io.IOException;


// Abscract class
interface Observer {
  public void update();    
};

class Average implements Observer {
  public void update() {
    try {
      FileWriter averageFile = new FileWriter("average.dat");
        for (Snapshot snap : Main.snapshotArray) {
          double sum = 0;
          int count = 0;
          for (StockData data : snap.getDataArray()) {
              sum += data.getPrice();
              count++;
          }
          double average = (count > 0) ? (sum / count) : 0;
          averageFile.write("Snapshot Date: " + snap.getDate() + " | Average Stock Price: " + average + "\n");
        }
      averageFile.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

      
  }
}

class HighLow implements Observer {
  public void update() {
    try {

      FileWriter highLowFile = new FileWriter("highlow.dat");

      for (Snapshot snap : Main.snapshotArray) {
        // System.out.println("Last updated " + snap.getDate());
        highLowFile.write("Last updated " + snap.getDate() + "\n");
      
        for (StockData data : snap.getDataArray()) {
            double highOnePercent = data.getHigh() * 0.01;
            double lowOnePercent = data.getLow() * 0.01;

            if ((data.getHigh() - data.getPrice()) <= highOnePercent || (data.getPrice() - data.getLow()) <= lowOnePercent) {
            // System.out.printf("%s: %.2f, %.2f, %.2f%n", data.getTicker(), data.getPrice(), data.getHigh(), data.getLow());
            highLowFile.write(String.format("%s: %.2f, %.2f, %.2f%n", 
                data.getTicker(), data.getPrice(), data.getHigh(), data.getLow()));

          }
        }
      }

      highLowFile.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

      
  }
}


class Selectors implements Observer {
  public void update(){
    try {
      FileWriter selectorsFile = new FileWriter("selectors.dat");

      for(Snapshot snap : Main.snapshotArray) {
        // System.out.println("Last updated " + snap.getDate());
        selectorsFile.write("Last updated " + snap.getDate() + "\n");
        for(StockData data : snap.getDataArray()) {
          if(data.getTicker().equals("ALL") ||
            data.getTicker().equals("BA") ||
            data.getTicker().equals("BC") ||
            data.getTicker().equals("GBEL") ||
            data.getTicker().equals("KFT") ||
            data.getTicker().equals("MCD") ||
            data.getTicker().equals("TR") ||
            data.getTicker().equals("WAG")
          ) {
            // System.out.println(data.toString());
            selectorsFile.write(data.toString() + "\n");
          }
        }
      }

      selectorsFile.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

    
  } 
    
}