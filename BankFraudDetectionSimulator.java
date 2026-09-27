import java.util.*;

class Transaction {

    String customerName;
    double amount;
    String location;
    int failedAttempts;
    int riskScore;
    String status;

    Transaction(String customerName, double amount,
                String location, int failedAttempts) {

        this.customerName = customerName;
        this.amount = amount;
        this.location = location;
        this.failedAttempts = failedAttempts;
    }
}

public class BankFraudDetectionSimulator {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n======================================");
            System.out.println("     BANK FRAUD DETECTION SIMULATOR");
            System.out.println("======================================");
            System.out.println("1. Add Transaction");
            System.out.println("2. Detect Fraud");
            System.out.println("3. View All Transactions");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addTransaction();
                    break;

                case 2:
                    detectFraud();
                    break;

                case 3:
                    viewTransactions();
                    break;

                case 4:
                    System.out.println("Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Add transaction
    static void addTransaction() {

        System.out.println("\n--- Enter Transaction Details ---");

        System.out.print("Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Transaction Amount: ₹");
        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.print("Transaction Location: ");
        String location = sc.nextLine();

        System.out.print("Number of Failed Attempts: ");
        int failedAttempts = sc.nextInt();

        Transaction t =
            new Transaction(name, amount, location, failedAttempts);

        transactions.add(t);

        System.out.println("\nTransaction added successfully!");
    }

    // Fraud detection
    static void detectFraud() {

        if (transactions.isEmpty()) {

            System.out.println("\nNo transactions available.");
            return;
        }

        System.out.println("\n======================================");
        System.out.println("         FRAUD DETECTION RESULTS");
        System.out.println("======================================");

        for (Transaction t : transactions) {

            int score = 0;

            // Rule 1: High transaction amount
            if (t.amount > 50000) {
                score += 30;
            }

            // Rule 2: Very high transaction amount
            if (t.amount > 100000) {
                score += 20;
            }

            // Rule 3: Failed login/transaction attempts
            if (t.failedAttempts >= 3) {
                score += 25;
            }

            // Rule 4: Unknown location
            if (t.location.equalsIgnoreCase("Unknown")) {
                score += 20;
            }

            // Rule 5: Suspicious locations
            if (t.location.equalsIgnoreCase("International")
                    || t.location.equalsIgnoreCase("Foreign")) {
                score += 20;
            }

            t.riskScore = score;

            // Classification
            if (score >= 60) {
                t.status = "HIGH RISK";
            }
            else if (score >= 30) {
                t.status = "SUSPICIOUS";
            }
            else {
                t.status = "SAFE";
            }

            System.out.println("\nCustomer      : " + t.customerName);
            System.out.println("Amount        : ₹" + t.amount);
            System.out.println("Location      : " + t.location);
            System.out.println("Failed Attempts: " + t.failedAttempts);
            System.out.println("Risk Score    : " + t.riskScore);
            System.out.println("Status        : " + t.status);

            if (t.status.equals("HIGH RISK")) {
                System.out.println("Action        : TRANSACTION FLAGGED");
            }
            else if (t.status.equals("SUSPICIOUS")) {
                System.out.println("Action        : REQUIRES VERIFICATION");
            }
            else {
                System.out.println("Action        : TRANSACTION APPROVED");
            }

            System.out.println("--------------------------------------");
        }
    }

    // View transactions
    static void viewTransactions() {

        if (transactions.isEmpty()) {

            System.out.println("\nNo transactions available.");
            return;
        }

        System.out.println("\n======================================");
        System.out.println("        ALL TRANSACTIONS");
        System.out.println("======================================");

        int count = 1;

        for (Transaction t : transactions) {

            System.out.println("\nTransaction " + count++);

            System.out.println("Customer       : " + t.customerName);
            System.out.println("Amount         : ₹" + t.amount);
            System.out.println("Location       : " + t.location);
            System.out.println("Failed Attempts: " + t.failedAttempts);

            if (t.status != null) {
                System.out.println("Risk Score     : " + t.riskScore);
                System.out.println("Status         : " + t.status);
            }
            else {
                System.out.println("Status         : Not Analyzed");
            }
        }
    }
}

