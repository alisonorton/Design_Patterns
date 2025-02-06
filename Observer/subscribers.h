#ifndef SUBSCRIBERS_H
#define SUBSCRIBERS_H

#include <iostream>
using namespace std;

// Abscract class
class SubscriberInterface {
  public:
    // Take in all the data so each concrete subscriber can calculate and display as needed
    virtual void update(string comopany, string ticker, double currentPrice, double priceChange, double percentChange, 
                        double ytdChange, double weekHigh, double weekLow, double peRatio) = 0;
};

class LocalStocks : public SubscriberInterface {
  private:
    string company;
    string ticker;
    double currentPrice;
    double priceChange;
    double percentChange; 
    double ytdChange; 
    double weekHigh; 
    double weekLow; 
    double peRatio;
    
    // These numbers will be calculated in main when reading the file??
    int numStocks;
    double stockSum;

  public:
    void update(string comopany, string ticker, double currentPrice, double priceChange, double percentChange, 
                double ytdChange, double weekHigh, double weekLow, double peRatio){
                  this->company = company;
                  this->ticker = ticker;
                  this->currentPrice = currentPrice;
                  this->priceChange = priceChange;
                  this->percentChange = percentChange;
                  this->ytdChange = ytdChange;
                  this->weekHigh = weekHigh;
                  this->weekLow = weekLow;
                  this->peRatio = peRatio;
                } 

    // Helper methods
    int getNumStocks() {
      return numStocks;
    }

    void setNumStocks(int numStocks) {
      this->numStocks = numStocks;
    }

    int getStockSum() {
      return stockSum;
    }

    void setStockSum(int stockSum) {
      this->stockSum = stockSum;
    }

    double average() {
      return stockSum / numStocks;
    }  

    double weekHighLow() {

    } 

    // Display methods
    void displayAverage() {

    }

    void displayWeekHighLow() {

    }

    void displayCompanies() {

    }
    
};

#endif