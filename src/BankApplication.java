import java.util.Scanner;

class Account {
    private int accountId;
    private String ownerName;
    private double balance;

    public Account(int accountId, String ownerName, double initialBalance) {
        this.accountId = accountId;
        this.ownerName = ownerName;
        this.balance = initialBalance;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class SavingsAccount extends Account {
    public SavingsAccount(int accountId, String ownerName, double initialBalance) {
        super(accountId, ownerName, initialBalance);
    }
}

class CheckingAccount extends Account {
    public CheckingAccount(int accountId, String ownerName, double initialBalance) {
        super(accountId, ownerName, initialBalance);
    }
}

public class BankApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Account[] accounts = new Account[100];
        int accountCount = 0;

        while (true) {
            System.out.println("Banking Application Menu\n" +
                    "1. Add Savings Account\n" +
                    "2. Add Checking Account\n" +
                    "3. Display All Accounts\n" +
                    "4. Deposit to Account\n" +
                    "5. Withdraw from Account\n" +
                    "6. Check Balance\n" +
                    "7. Exit\n" +
                    "Enter your choice:");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter account ID:");
                    int savingsAccountId = scanner.nextInt();
                    System.out.println("Enter owner name:");
                    String savingsOwnerName = scanner.next();
                    System.out.println("Enter initial balance:");
                    double savingsInitialBalance = scanner.nextDouble();
                    accounts[accountCount++] = new SavingsAccount(savingsAccountId, savingsOwnerName, savingsInitialBalance);
                    System.out.println("Savings Account added successfully");
                    break;
                case 2:
                    System.out.println("Enter account ID:");
                    int checkingAccountId = scanner.nextInt();
                    System.out.println("Enter owner name:");
                    String checkingOwnerName = scanner.next();
                    System.out.println("Enter initial balance:");
                    double checkingInitialBalance = scanner.nextDouble();
                    accounts[accountCount++] = new CheckingAccount(checkingAccountId, checkingOwnerName, checkingInitialBalance);
                    System.out.println("Checking Account added successfully");
                    break;
                case 3:
                    System.out.println("Display All Accounts\n" +
                            "1. Display All Accounts\n" +
                            "2. Display Savings Accounts\n" +
                            "3. Display Checking Accounts");
                    int displayChoice = scanner.nextInt();
                    switch (displayChoice) {
                        case 1:
                            for (int i = 0; i < accountCount; i++) {
                                System.out.println("Account ID: " + accounts[i].getAccountId() +
                                        ", Owner Name: " + accounts[i].getOwnerName() +
                                        ", Balance: " + accounts[i].getBalance());
                            }
                            break;
                        case 2:
                            for (int i = 0; i < accountCount; i++) {
                                if (accounts[i] instanceof SavingsAccount) {
                                    System.out.println("Account ID: " + accounts[i].getAccountId() +
                                            ", Owner Name: " + accounts[i].getOwnerName() +
                                            ", Balance: " + accounts[i].getBalance());
                                }
                            }
                            break;
                        case 3:
                            for (int i = 0; i < accountCount; i++) {
                                if (accounts[i] instanceof CheckingAccount) {
                                    System.out.println("Account ID: " + accounts[i].getAccountId() +
                                            ", Owner Name: " + accounts[i].getOwnerName() +
                                            ", Balance: " + accounts[i].getBalance());
                                }
                            }
                            break;
                    }
                    break;
                case 4:
                    System.out.println("Enter account ID for deposit:");
                    int depositAccountId = scanner.nextInt();
                    System.out.println("Enter amount to deposit:");
                    double depositAmount = scanner.nextDouble();
                    boolean depositSuccess = false;
                    for (int i = 0; i < accountCount; i++) {
                        if (accounts[i].getAccountId() == depositAccountId) {
                            accounts[i].deposit(depositAmount);
                            depositSuccess = true;
                            break;
                        }
                    }
                    if (depositSuccess) {
                        System.out.println("Deposit successful!");
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;
                case 5:
                    System.out.println("Enter account ID for withdrawal:");
                    int withdrawalAccountId = scanner.nextInt();
                    System.out.println("Enter amount to withdraw:");
                    double withdrawalAmount = scanner.nextDouble();
                    boolean withdrawalSuccess = false;
                    for (int i = 0; i < accountCount; i++) {
                        if (accounts[i].getAccountId() == withdrawalAccountId) {
                            withdrawalSuccess = accounts[i].withdraw(withdrawalAmount);
                            break;
                        }
                    }
                    if (withdrawalSuccess) {
                        System.out.println("Withdrawal successful!");
                    } else {
                        System.out.println("Account not found or insufficient balance!");
                    }
                    break;
                case 6:
                    System.out.println("Enter account ID to check balance:");
                    int checkBalanceAccountId = scanner.nextInt();
                    boolean balanceChecked = false;
                    for (int i = 0; i < accountCount; i++) {
                        if (accounts[i].getAccountId() == checkBalanceAccountId) {
                            System.out.println("Account ID: " + accounts[i].getAccountId() +
                                    ", Owner Name: " + accounts[i].getOwnerName() +
                                    ", Balance: " + accounts[i].getBalance());
                            balanceChecked = true;
                            break;
                        }
                    }
                    if (!balanceChecked) {
                        System.out.println("Account not found!");
                    }
                    break;
                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}