package main

import Account


class Client(val name: String, val address: String) {
    private val accounts: MutableList<Account> = mutableListOf()

    fun addAccount(account: Account) {
        accounts.add(account)
    }

    fun displayAccounts() {
        for (account in accounts) {
            account.displayInfo()
        }
    }

    fun printAllSavingsAccount() {
        for (account in accounts) {
            if (account is SavingsAccount) {
                account.displayInfo()
            }
        }
    }

    fun removeAccount(accountId: String): Boolean {
        val account = accounts.find { it.accountNumber == accountId }
        return if (account != null) {
            accounts.remove(account)
            true
        } else {
            false
        }
    }

    fun calculateTotalAmountOfMoney(): Double {
        var totalAmount = 0.0
        for (account in accounts) {
            totalAmount += account.getBalance()
        }
        return totalAmount
    }

    fun sortAccounts(criteria: Order) {
        when (criteria) {
            Order.ASCENDING -> accounts.sortBy { it.getBalance() }
            Order.DESCENDING -> accounts.sortByDescending { it.getBalance() }
        }
    }
}

enum class Order {
    ASCENDING,
    DESCENDING
}