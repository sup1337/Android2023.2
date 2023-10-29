import java.util.*

abstract class Account(var balance: Double, var accountNumber: String) {
    init {
        // Generate account number if not provided
        if (accountNumber.isBlank()) {
            accountNumber = generateAccountNumber()
        }
    }

    fun deposit(amount: Double) {
        balance += amount
    }

    open fun withdraw(amount: Double) {
        if (amount <= balance) {
            balance -= amount
        } else {
            println("Insufficient balance")
        }
    }


    fun getBalance(): Double {
        return balance
    }

    open fun displayInfo() {
        println("Account Number: $accountNumber")
        println("Balance: $balance")
    }

    private fun generateAccountNumber(): String {
        val random = Random()
        return "ACC-${random.nextInt(10000)}"
    }

    companion object {
        fun createAccount(balance: Double, accountNumber: String): Account {
            return object : Account(balance, accountNumber) {}
        }
    }
}

class CheckingAccount(balance: Double, accountNumber: String, val overdraftLimit: Double) : Account(balance, accountNumber) {
    override fun withdraw(amount: Double) {
        if (amount <= balance + overdraftLimit) {
            balance -= amount
        } else {
            println("Insufficient funds")
        }
    }

    override fun displayInfo() {
        super.displayInfo()
        println("Overdraft Limit: $overdraftLimit")
    }
}