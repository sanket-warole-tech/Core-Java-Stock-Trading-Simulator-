package model;

import java.time.LocalDateTime;

public class Transaction {
	
	private int transactionId;
	private String stockSymbol;
	private String transactionType;
	private int quantity;
	private double price;
	private LocalDateTime transactionDate;
	
	public Transaction() {
		
	}
	
	public Transaction(int transactionId, String stockSymbol,
            String transactionType, int quantity,
            double price, LocalDateTime transactionDate) {
		this.transactionId = transactionId;
        this.stockSymbol = stockSymbol;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.price = price;
        this.transactionDate = transactionDate;
	}
	
	//Getter 
	public int getTransactionId() {
		return transactionId;
	}
	public String getStockSymbol() {
        return stockSymbol;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }
    
    //setters
    public void setTransactionId(int transactionId) {
    	this.transactionId=transactionId;
    }
    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        }
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        }
    }
    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
    
    @Override
    public String toString() {
        return String.format(
                "%-4d %-6s %-12s %-8d ₹%-10.2f %s",
                transactionId,
                transactionType,
                stockSymbol,
                quantity,
                price,
                transactionDate);
    }
}

