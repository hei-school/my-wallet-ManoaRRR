class InsufficientFundsException extends Error {
    constructor() {
        super("Insufficient funds");
        this.name = "InsufficientFundsException";
    }
}

class Wallet {
    constructor(balance) {
        this.balance = balance;
    }

    deposit(amount) {
        this.balance += amount;
        console.log("Money added successfully.");
    }

    withdraw(amount) {
        if (amount > this.balance) {
            throw new InsufficientFundsException();
        }
        this.balance -= amount;
        console.log("Money removed successfully.");
    }

    transfer(destinationWallet, amount) {
        if (amount > this.balance) {
            throw new InsufficientFundsException();
        }

        this.withdraw(amount);
        destinationWallet.deposit(amount);
        console.log("Money transferred successfully.");
    }

    isEmpty() {
        return this.balance === 0;
    }

    getBalance() {
        return this.balance;
    }

    setBalanceLimit(limit) {
        throw new Error("Setting balance limit is not supported in this version.");
    }

    checkBalanceLimit(amount) {
        throw new Error("Checking balance limit is not supported in this version.");
    }

    displayWalletDetails() {
        console.log("Current balance: " + this.balance);
    }
}

// Example Usage
let wallet = new Wallet(100);
wallet.deposit(50);
console.log(wallet.getBalance()); // 150

wallet.withdraw(25);
console.log(wallet.getBalance()); // 125

let anotherWallet = new Wallet(50);
wallet.transfer(anotherWallet, 20);
console.log(wallet.getBalance());           // 105
console.log(anotherWallet.getBalance());    // 70

console.log(wallet.isEmpty());  // false

try {
    wallet.setBalanceLimit(200);
    wallet.checkBalanceLimit(80);  // No exception
    wallet.deposit(130);           // Throws InsufficientFundsException
} catch (e) {
    if (e instanceof InsufficientFundsException) {
        console.error(e.message);
    } else {
        throw e;
    }
}

wallet.displayWalletDetails();  // Current balance: 105
