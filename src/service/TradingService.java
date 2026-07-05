package service;

import java.util.ArrayList;
import java.util.Random;
import java.time.LocalDateTime;

import model.Portfolio;
import model.Stock;
import model.Transaction;
import model.User;

public class TradingService {
	
	//Available stocks
	private ArrayList<Stock> stockList;
	
	//user portfolio
	private ArrayList<Portfolio> portfolioList;
	
	//Transaction history
	private ArrayList<Transaction> transactionList;
	
	//current logged in user
	private User user;
	
	private Random random = new Random();
	//constructor
	public TradingService(User user) {
		this.user=user;
		
		stockList = new ArrayList<>();
		portfolioList = new ArrayList<>();
		transactionList = new ArrayList<>();
		
		loadDefaultStocks();
		
	}
	
	//Load initial Stocks
	private void loadDefaultStocks() {
		stockList.add(new Stock("TCS","Tata Consultancy services",3500.00,500));
		stockList.add(new Stock("INFY","Infosys",1600.00,700));

        stockList.add(new Stock("RELIANCE","Reliance Industries",2900.00,400));

        stockList.add(new Stock("AAPL","Apple", 180.00,300));

        stockList.add(new Stock("TSLA","Tesla",250.00,250));
        stockList.add(new Stock("HDFC","HDFC Bank",1750.00,600));
        stockList.add(new Stock("ITC","ITC Limited",430.00,800));
	}
	//Display availabe Stocks
	public void viewStocks() {

	    System.out.println("\n================ AVAILABLE STOCKS ================");

	    System.out.printf("%-12s %-35s %-12s %-10s%n",
	            "SYMBOL", "COMPANY", "PRICE", "AVAILABLE");

	    System.out.println("---------------------------------------------------------------");

	    for (Stock stock : stockList) {
	        System.out.println(stock);
	    }
	}
	
	public void buyStock(String stockSymbol, int quantity) {

	    // Find the stock
	    Stock selectedStock = null;

	    for (Stock stock : stockList) {

	        if (stock.getStockSymbol().equalsIgnoreCase(stockSymbol)) {
	            selectedStock = stock;
	            break;
	        }
	    }

	    // Stock not found
	    if (selectedStock == null) {
	        System.out.println("Stock not found.");
	        return;
	    }

	    // Check available quantity
	    if (selectedStock.getAvailableQuantity() < quantity) {
	        System.out.println("Not enough stock available.");
	        return;
	    }

	    // Calculate total amount
	    double totalAmount = selectedStock.getStockPrice() * quantity;

	    // Check user balance
	    if (!user.withdraw(totalAmount)) {
	        System.out.println("Insufficient balance.");
	        return;
	    }

	    // Reduce available quantity
	    selectedStock.setAvailableQuantity(
	            selectedStock.getAvailableQuantity() - quantity);

	    // Update Portfolio
	    Portfolio existingPortfolio = null;

	    for (Portfolio portfolio : portfolioList) {

	        if (portfolio.getStockSymbol()
	                .equalsIgnoreCase(stockSymbol)) {

	            existingPortfolio = portfolio;
	            break;
	        }
	    }

	    if (existingPortfolio == null) {

	        Portfolio newPortfolio = new Portfolio(
	                selectedStock.getStockSymbol(),
	                selectedStock.getStockName(),
	                quantity,
	                selectedStock.getStockPrice());

	        portfolioList.add(newPortfolio);

	    } else {

	        existingPortfolio.buyShares(
	                quantity,
	                selectedStock.getStockPrice());
	    }

	    // Create Transaction
	    Transaction transaction = new Transaction(

	            transactionList.size() + 1,

	            selectedStock.getStockSymbol(),

	            "BUY",

	            quantity,

	            selectedStock.getStockPrice(),

	            LocalDateTime.now());

	    transactionList.add(transaction);

	    System.out.println("Stock purchased successfully.");
	}
	
	//view portfolio
	public void viewPortfolio() {

	    if (portfolioList.isEmpty()) {
	        System.out.println("\nYour portfolio is empty.");
	        return;
	    }

	    System.out.println("\n========== MY PORTFOLIO ==========");

	    for (Portfolio portfolio : portfolioList) {
	        System.out.println(portfolio);
	    }
	}
	
	public void sellStock(String stockSymbol, int quantity) {

	    // Find stock in portfolio
	    Portfolio selectedPortfolio = null;

	    for (Portfolio portfolio : portfolioList) {

	        if (portfolio.getStockSymbol().equalsIgnoreCase(stockSymbol)) {
	            selectedPortfolio = portfolio;
	            break;
	        }
	    }

	    // Check if stock exists in portfolio
	    if (selectedPortfolio == null) {
	        System.out.println("You do not own this stock.");
	        return;
	    }

	    // Check quantity
	    if (selectedPortfolio.getQuantity() < quantity) {
	        System.out.println("Not enough shares to sell.");
	        return;
	    }

	    // Find stock in market
	    Stock selectedStock = null;

	    for (Stock stock : stockList) {

	        if (stock.getStockSymbol().equalsIgnoreCase(stockSymbol)) {
	            selectedStock = stock;
	            break;
	        }
	    }

	    if (selectedStock == null) {
	        System.out.println("Stock not found.");
	        return;
	    }

	    // Calculate selling amount
	    double totalAmount = selectedStock.getStockPrice() * quantity;

	    // Add money to user account
	    user.deposit(totalAmount);

	    // Reduce shares from portfolio
	    selectedPortfolio.sellShares(quantity);

	    // Increase market quantity
	    selectedStock.setAvailableQuantity(
	            selectedStock.getAvailableQuantity() + quantity);

	    // Remove stock if quantity becomes zero
	    if (selectedPortfolio.getQuantity() == 0) {
	        portfolioList.remove(selectedPortfolio);
	    }

	    // Create transaction
	    Transaction transaction = new Transaction(
	            transactionList.size() + 1,
	            selectedStock.getStockSymbol(),
	            "SELL",
	            quantity,
	            selectedStock.getStockPrice(),
	            java.time.LocalDateTime.now()
	    );

	    transactionList.add(transaction);

	    System.out.println("Stock sold successfully.");
	}
	public void searchStock(String stockSymbol) {

	    for (Stock stock : stockList) {

	        if (stock.getStockSymbol().equalsIgnoreCase(stockSymbol)) {

	            System.out.println("\n========== STOCK FOUND ==========");
	            System.out.println(stock);
	            return;
	        }
	    }

	    System.out.println("Stock not found.");
	}
	
	public void viewTransactionHistory() {

	    if (transactionList.isEmpty()) {

	        System.out.println("\nNo transactions found.");
	        return;
	    }

	    System.out.println("\n================ TRANSACTION HISTORY ================");

	    System.out.printf("%-4s %-6s %-12s %-8s %-12s %-25s%n",
	            "ID", "TYPE", "SYMBOL", "QTY", "PRICE", "DATE & TIME");

	    System.out.println("--------------------------------------------------------------------------");

	    for (Transaction transaction : transactionList) {

	        System.out.println(transaction);
	    }
	}
	
	public void refreshMarket() {

	    System.out.println("\nRefreshing Market Prices...\n");

	    for (Stock stock : stockList) {

	        double oldPrice = stock.getStockPrice();

	        // Random change between -50 and +50
	        double change = random.nextInt(101) - 50;

	        double newPrice = oldPrice + change;

	        // Prevent negative price
	        if (newPrice < 1) {
	            newPrice = 1;
	        }

	        stock.setStockPrice(newPrice);

	        System.out.printf("%-12s ₹%-10.2f → ₹%.2f%n",
	                stock.getStockSymbol(),
	                oldPrice,
	                newPrice);
	    }

	    System.out.println("\nMarket Updated Successfully.");
	}
	
	public ArrayList<Transaction> getTransactionList() {

	    return transactionList;
	}
	
	public void showDashboard() {

	    System.out.println("\n======================================");
	    System.out.println("            USER DASHBOARD");
	    System.out.println("======================================");

	    System.out.println("Username           : " + user.getUsername());
	    System.out.printf("Current Balance    : ₹%.2f%n", user.getBalance());

	    System.out.println("Stocks Owned       : " + portfolioList.size());

	    System.out.println("Total Transactions : " + transactionList.size());

	    System.out.println("======================================");
	}
	
	}