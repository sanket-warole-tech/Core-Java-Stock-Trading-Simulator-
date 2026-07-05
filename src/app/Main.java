package app;

import java.util.InputMismatchException;
import java.util.Scanner;
import util.FileManager;

import model.User;
import service.TradingService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Create Default User
        User user = new User(1, "Sanket", "1234", 100000);

        // Create Trading Service
        TradingService tradingService = new TradingService(user);

        int choice = 0;

        do {
        	
        	tradingService.showDashboard();

            System.out.println("\n====================================");
            System.out.println("     STOCK TRADING SIMULATOR");
            System.out.println("====================================");
            System.out.println("1. View Stocks");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Transaction History");
            System.out.println("6. Search Stock");
            System.out.println("7. View Balance");
            
            System.out.println("8. Refresh Market");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            
            try {
            	choice =sc.nextInt();
            	sc.nextLine();
            }
            catch(InputMismatchException e) {
            	System.out.println("Please enter the numbers only.");
            	sc.nextLine();
            	continue;
            }
           
            switch (choice) {

            case 1:
                tradingService.viewStocks();
                break;

            case 2:

                System.out.print("Enter Stock Symbol: ");
                String buySymbol = sc.nextLine().trim();
                
                if(buySymbol.isEmpty()) {
                	System.out.println("Stock Symbol cannot be empty.");
                	break;
                }
                

                System.out.print("Enter Quantity: ");
                int buyQty = sc.nextInt();
                
                if(buyQty <=0) {
                	System.out.println("Quantity must be greater than 0.");
                	break;
                }

                tradingService.buyStock(buySymbol, buyQty);
                break;

            case 3:

                System.out.print("Enter Stock Symbol: ");
                String sellSymbol = sc.nextLine();

                System.out.print("Enter Quantity: ");
                int sellQty = sc.nextInt();
                
                if(sellQty <=0) {
                	System.out.println("Quantity must be greater than 0.");
                	break;
                }

                tradingService.sellStock(sellSymbol, sellQty);
                break;

            case 4:

                tradingService.viewPortfolio();
                break;

            case 5:

                tradingService.viewTransactionHistory();
                break;

            case 6:

                System.out.print("Enter Stock Symbol: ");
                String search = sc.nextLine();

                tradingService.searchStock(search);
                break;

            case 7:

                System.out.println("\nCurrent Balance : ₹" + user.getBalance());
                break;

            case 8:

                tradingService.refreshMarket();
                break;

            case 9:

                System.out.println("Thank You for using Stock Trading Simulator.");
                break;

            default:

                System.out.println("Invalid Choice.");
            }

        } while (choice != 9);
        
        FileManager.saveTransactions(tradingService.getTransactionList());

        

        sc.close();
    }
}