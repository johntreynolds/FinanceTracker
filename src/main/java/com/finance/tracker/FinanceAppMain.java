package com.finance.tracker;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane; // Required for the popup window

public class FinanceAppMain {
    public static void main(String[] args) {
        System.out.println("--- Booting Portable Bank Parser ---");
        
        ReadBankCSV bankReader = new ReadBankCSV("data");

        try {
            ArrayList<Transaction> masterList = bankReader.collectTransactions();
            StringBuilder clipboardString = new StringBuilder();
            
            for (Transaction t : masterList) {
                clipboardString.append(t.getDate()).append("\t")
                               .append(t.getInfo()).append("\t")
                               .append(t.getAccountType()).append("\t")
                               .append(t.getAmount()).append("\n");
            }
            
            StringSelection selection = new StringSelection(clipboardString.toString());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
            
            System.out.println("\n[SUCCESS] " + masterList.size() + " rows copied to clipboard!");
            
            // NEW: Pops up a small Windows alert box over your screen
            JOptionPane.showMessageDialog(
                null, 
                masterList.size() + " rows successfully copied to clipboard!\nGo to Google Sheets and press Ctrl + V.", 
                "Parser Success", 
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IOException e) {
            // Error Popup if something goes wrong (like a missing data folder)
            JOptionPane.showMessageDialog(
                null, 
                "Failed to process files. Make sure your 'data' folder exists right next to the app.", 
                "Parser Error", 
                JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }
}