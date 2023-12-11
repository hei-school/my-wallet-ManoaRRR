class InsufficientFundsError(Exception):
    def __init__(self, message="Insufficient funds"):
        super().__init__(message)

class BalanceLimitExceededError(Exception):
    def __init__(self, message="Balance limit exceeded"):
        super().__init__(message)

class Wallet:
    def __init__(self, balance=0, balance_limit=float('inf')):
        self.balance = balance
        self.balance_limit = balance_limit

    def deposit(self, amount):
        self.balance += amount

    def withdraw(self, amount):
        if amount > self.balance:
            raise InsufficientFundsError()
        self.balance -= amount

    def transfer(self, destination_wallet, amount):
        if amount > self.balance:
            raise InsufficientFundsError()

        self.withdraw(amount)
        destination_wallet.deposit(amount)

    def is_empty(self):
        return self.balance == 0

    def get_balance(self):
        return self.balance

    def set_balance_limit(self, limit):
        self.balance_limit = limit

    def check_balance_limit(self, amount):
        if self.balance + amount > self.balance_limit:
            raise BalanceLimitExceededError()

    def display_wallet_details(self):
        print(f"Balance: {self.balance}, Balance Limit: {self.balance_limit}")


# Example Usage
wallet = Wallet(100)
wallet.deposit(50)
print(wallet.get_balance())  # 150

wallet.withdraw(25)
print(wallet.get_balance())  # 125

another_wallet = Wallet(50)
wallet.transfer(another_wallet, 20)
print(wallet.get_balance())          # 105
print(another_wallet.get_balance())   # 70

print(wallet.is_empty())  # False

wallet.set_balance_limit(200)
wallet.check_balance_limit(80)  # No exception
wallet.deposit(130)           # Raises BalanceLimitExceededError

wallet.display_wallet_details()  # Balance: 105, Balance Limit: 200