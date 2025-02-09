import java.util.ArrayList;

interface Publisher {
  public
    void subscribe(Observer observer);
    void unsubscribe(Observer target);
    void notifySubs();
};


class LocalStocks implements Publisher {
  private ArrayList<Observer> subscribers = new ArrayList<>();

    public ArrayList<Observer> getSubscribers() {
      return subscribers;
    }
  
    @Override
    public void subscribe(Observer observer) {
      subscribers.add(observer);
    }

    @Override
    public void unsubscribe(Observer target) {
      for(Observer o : subscribers) {
        if(o.equals(target)) {
          subscribers.remove(o);
        }
      }
    }

    public void notifySubs() {
      // loop through subs and notify
      for(Observer item : subscribers) {
        item.update();
      }

    }
  };