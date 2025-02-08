import java.io.*;
import java.util.*;

class StockData {
    private String ticker;
    private String companyName;
    private double[] values;

    public void setData(String companyName, String ticker, double... values) {
        this.companyName = companyName;
        this.ticker = ticker;
        this.values = values;
    }

    public double getPrice() {
      return values[0];
    }

    @Override
    public String toString() {
        return "Ticker: " + ticker + ", Company: " + companyName + ", Values: " + Arrays.toString(values);
    }
}

class Snapshot {
    private String date;
    private List<StockData> stockDataList = new ArrayList<>();

    public void setDate(String date) {
        this.date = date;
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
                    stockData.setData(companyNameBuilder.toString().trim(), ticker, numericValues.stream().mapToDouble(d -> d).toArray());
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
        
        // Print the snapshotArray values
        for (Snapshot snapshot : snapshotArray) {
            System.out.println(snapshot);
        }

        Average average = new Average();
        // LocalStocks.subscribe(average);
        average.update();
    }
}


