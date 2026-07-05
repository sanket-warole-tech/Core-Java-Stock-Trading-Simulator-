package util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Transaction;

public class FileManager {

    public static void saveTransactions(ArrayList<Transaction> transactionList) {

        try {

            FileWriter writer = new FileWriter("transactions.txt");

            for (Transaction transaction : transactionList) {

                writer.write(transaction.toString());
                writer.write("\n");
            }

            writer.close();

            System.out.println("Transaction history saved successfully.");

        } catch (IOException e) {

            System.out.println("Error while saving file.");
        }
    }
}