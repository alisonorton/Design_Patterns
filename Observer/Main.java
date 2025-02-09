import java.io.*;
import java.util.*;

class StockData {
    private String ticker;
    private String companyName;
    private double currentPrice;
    private double priceChange;
    private double percentChange;
    private double ytdChange;
    private double weekHigh;
    private double weekLow;

    public void setData(String companyName, String ticker, double currentPrice, double priceChange, double percentChange, double ytdChange, double weekHigh, double weekLow) {
        this.companyName = companyName;
        this.ticker = ticker;
        this.currentPrice = currentPrice;
        this.priceChange = priceChange;
        this.percentChange = percentChange;
        this.ytdChange = ytdChange;
        this.weekHigh = weekHigh;
        this.weekLow = weekLow;
    }

    public String getTicker() {
      return ticker;
    }

    public double getPrice() {
      return currentPrice;
    }

    public double getChangePercent() {
      return percentChange;
    }

    public double getHigh() {
      return weekHigh;
    }

    public double getLow() {
      return weekLow;
    }

    @Override
    public String toString() {
        return companyName + " " + ticker + " " + currentPrice + ", " + priceChange + ", " + percentChange + ", " + ytdChange + ", " + weekHigh + ", " + weekLow;
    }
}

class Snapshot {
    private String date;
    private List<StockData> stockDataList = new ArrayList<>();

    public void setDate(String date) {
        this.date = date.replace("Last updated ", "");
    }

    public String getDate() {
      return date;
    }
    
    public void addStockData(StockData stockData) {
        stockDataList.add(stockData);
    }
    
    public List<StockData> getDataArray() {
        return stockDataList;
    }
    
    @Override
    public String toString() {
        return "Date: " + date + "\n" + stockDataList;
    }
}

/******************************************
 * Main
 ******************************************/

public class Main {
    static List<Snapshot> snapshotArray = new ArrayList<>();
    public static void main(String[] args) {

        // Read file and parse data
        try (Scanner scanner = new Scanner(new File("Observer/Ticker.dat"))) {
            int count = 0;
            Snapshot snapshot = new Snapshot();
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                
                if (line.isEmpty() && scanner.hasNextLine()) {
                    count = 0;
                    snapshotArray.add(snapshot);
                    snapshot = new Snapshot();
                    continue;
                }
                
                if (count == 0) {
                    snapshot.setDate(line);
                } else {
                    String[] tokens = line.split("\\s+"); // Split using regex to handle multiple spaces
                    List<Double> numericValues = new ArrayList<>();
                    StringBuilder companyNameBuilder = new StringBuilder();
                    String ticker = "";
                    
                    
                    for (int i = tokens.length - 1; i >= 0; i--) {
                      if(tokens[i].trim().equals("-")) {
                        continue;
                      }
                        if (!tokens[i].isEmpty()) { // Ensure token is not empty
                            try {
                                numericValues.add(0, Double.parseDouble(tokens[i]));
                            } catch (NumberFormatException e) {
                              
                                ticker = tokens[i];
                                companyNameBuilder.setLength(0);
                                for (int j = 0; j < i; j++) {
                                    companyNameBuilder.append(tokens[j]).append(" ");
                                }
                                break;
                            }
                        }
                    }

                    StockData stockData = new StockData();
                    stockData.setData(companyNameBuilder.toString().trim(), ticker, numericValues.get(0), numericValues.get(1), numericValues.get(2), numericValues.get(3), numericValues.get(4), numericValues.get(5));
                    snapshot.addStockData(stockData);
                }
                count++;
            }
            // Ensure the last snapshot is only added if it has a date or stock data
            if (snapshot.getDate() != null && !snapshot.getDataArray().isEmpty()) {
              snapshotArray.add(snapshot);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        //
        // Implementing Observer pattern
        //

        LocalStocks localStocks = new LocalStocks();

        Average average = new Average();
        localStocks.subscribe(average);

        HighLow highLow = new HighLow();
        localStocks.subscribe(highLow);

        Selectors selectors = new Selectors();
        localStocks.subscribe(selectors);

        localStocks.notifySubs();
    }
}


