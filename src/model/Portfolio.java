package model;

public class Portfolio {

    // Instance Variables
    private String stockSymbol;
    private String stockName;
    private int quantity;
    private double averagePrice;

    // Default Constructor
    public Portfolio() {

    }

    // Parameterized Constructor
    public Portfolio(String stockSymbol, String stockName, int quantity, double averagePrice) {
        this.stockSymbol = stockSymbol;
        this.stockName = stockName;
        this.quantity = quantity;
        this.averagePrice = averagePrice;
    }

    // Getters
    public String getStockSymbol() {
        return stockSymbol;
    }

    public String getStockName() {
        return stockName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    // Setters
    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        }
    }

    public void setAveragePrice(double averagePrice) {
        if (averagePrice > 0) {
            this.averagePrice = averagePrice;
        }
    }

    // Buy More Shares
    public void buyShares(int qty, double price) {

        double totalInvestment = (averagePrice * quantity) + (price * qty);

        quantity += qty;

        averagePrice = totalInvestment / quantity;
    }

    // Sell Shares
    public boolean sellShares(int qty) {

        if (qty <= quantity) {
            quantity -= qty;
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format(
            "%-12s %-30s Qty: %-5d Avg Price: ₹%.2f",
            stockSymbol,
            stockName,
            quantity,
            averagePrice
        );
    }
}