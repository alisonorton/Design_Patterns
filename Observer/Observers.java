
import java.io.*;
import java.util.*;

// Abscract class
interface Observer {
  public void update();    
};

class Average implements Observer {
  public void update(){
    double sum = 0;
    for(Snapshot snap : Main.snapshotArray) {
      for(StockData data : snap.getDataArray()) {
        sum += data.getPrice();
        System.out.println(sum);
      }
    }
  } 
    
};

class HighLow implements Observer {
  public void update(){

    } 
    
};


class Selectors implements Observer {
  public void update(){

    } 
    
};