public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException() {
        super("Insufficient funds");
    }
}public class Wallet {

    private class Identification {
        String number;
        boolean status = false; // Lost: true, Not lost: false
    }

    private Identification CIN = new Identification();
    private double balance = 0;
    private boolean haveDrivingLicense = false;
    private String businessCardContent = "";
    private String idPhoto = null;
    private String creditCardNumber = null;

    public void addCIN(String number) {
        CIN.number = number;
        CIN.status = false;
        System.out.println("CIN added successfully.");
    }

    public void removeCIN() {
        if (!CIN.status) {
            CIN.number = null;
            System.out.println("CIN removed successfully.");
        } else {
            System.out.println("Cannot remove lost CIN.");
        }
    }

    public void addMoney(double amount) {
        balance += amount;
        System.out.println("Money added successfully.");
    }

    public void removeMoney(double amount) {
        if (balance != 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Money removed successfully.");
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    public void checkBalance() {
        System.out.println("Current balance: " + balance);
    }

    public void addDrivingLicense() {
        haveDrivingLicense = true;
        System.out.println("Driving license added successfully.");
    }

    public void removeDrivingLicense() {
        if (haveDrivingLicense) {
            haveDrivingLicense = false;
            System.out.println("Driving license removed successfully.");
        } else {
            System.out.println("Driving license not found.");
        }
    }

    public void showBusinessCard() {
        System.out.println("Business card content: " + businessCardContent);
    }

    public void exportBusinessCardToPDF() {
        // Logic to export business card to PDF (not implemented here)
        System.out.println("Business card exported to PDF.");
    }

    public void showIDPhoto() {
        System.out.println("ID photo: " + idPhoto);
    }

    public void updateIDPhoto(String photo) {
        idPhoto = photo;
        System.out.println("ID photo updated successfully.");
    }

    public void showCreditCardNumber() {
        System.out.println("Credit card number: " + creditCardNumber);
    }

    public void makePayment() {
        // Logic to process payment (not implemented here)
        System.out.println("Payment processed successfully.");
    }

    public static void main(String[] args) {
        // Example Usage:
        Wallet myWallet = new Wallet();
        myWallet.addCIN("123456789");
        myWallet.addMoney(100);
        myWallet.checkBalance();
        myWallet.removeMoney(50);
        myWallet.checkBalance();
        myWallet.addDrivingLicense();
        myWallet.showIDPhoto();
        myWallet.updateIDPhoto("new_photo.jpg");
        myWallet.showCreditCardNumber();
        myWallet.makePayment();
    }
}


public class BalanceLimitExceededException extends RuntimeException {
    public BalanceLimitExceededException() {
        super("Balance limit exceeded");
    }
}

public class Wallet {
    private int balance;

    public Wallet(int balance) {
        this.balance = balance;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    public void withdraw(int amount) {
        if (amount > this.balance) {
            throw new InsufficientFundsException();
        }
        this.balance -= amount;
    }

    public void transfer(Wallet destinationWallet, int amount) {
        if (amount > this.balance) {
            throw new InsufficientFundsException();
        }

        this.withdraw(amount);
        destinationWallet.deposit(amount);
    }

    public boolean isEmpty() {
        return this.balance == 0;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalanceLimit(int limit) {
        // In Java, you might want to handle this differently, perhaps by using a checked exception.
        throw new UnsupportedOperationException("Setting balance limit is not supported in this version.");
    }

    public void checkBalanceLimit(int amount) {
        // In Java, you might want to handle this differently, perhaps by using a checked exception.
        throw new UnsupportedOperationException("Checking balance limit is not supported in this version.");
    }

    public void displayWalletDetails() {
        System.out.println("Balance: " + this.balance);
    }

    public static void main(String[] args) {
        // Example Usage
        Wallet wallet = new Wallet(100);
        wallet.deposit(50);
        System.out.println(wallet.getBalance()); // 150

        wallet.withdraw(25);
        System.out.println(wallet.getBalance()); // 125

        Wallet anotherWallet = new Wallet(50);
        wallet.transfer(anotherWallet, 20);
        System.out.println(wallet.getBalance());           // 105
        System.out.println(anotherWallet.getBalance());    // 70

        System.out.println(wallet.isEmpty());  // false

        try {
            wallet.setBalanceLimit(200);
            wallet.checkBalanceLimit(80);  // No exception
            wallet.deposit(130);           // Throws BalanceLimitExceededException
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }

        wallet.displayWalletDetails();  // Balance: 105
    }
}