package model;

public class Stock {

    // Instance Variables
    private String stockSymbol;
    private String stockName;
    private double stockPrice;
    private int availableQuantity;

    // Default Constructor
    public Stock() {

    }

    // Parameterized Constructor
    public Stock(String stockSymbol, String stockName, double stockPrice, int availableQuantity) {
        this.stockSymbol = stockSymbol;
        this.stockName = stockName;
        this.stockPrice = stockPrice;
        this.availableQuantity = availableQuantity;
    }

    // Getters
    public String getStockSymbol() {
        return stockSymbol;
    }

    public String getStockName() {
        return stockName;
    }

    public double getStockPrice() {
        return stockPrice;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    // Setters
    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public void setStockPrice(double stockPrice) {
        if (stockPrice > 0) {
            this.stockPrice = stockPrice;
        }
    }

    public void setAvailableQuantity(int availableQuantity) {
        if (availableQuantity >= 0) {
            this.availableQuantity = availableQuantity;
        }
    }

    @Override
    public String toString() {
        return String.format(
                "%-12s %-30s ₹%-10.2f Qty: %d",
                stockSymbol,
                stockName,
                stockPrice,
                availableQuantity);
    }
    }
