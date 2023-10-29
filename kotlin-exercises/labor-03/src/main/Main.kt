package main

import test.ItemController
import test.ItemRepository
import test.ItemService

fun main() {
    val client = Client("John Doe", "123 Main St")

    val account1 = SavingsAccount(1000.0, "SAV-12345", 0.05)
//    val account2 = CheckingAccount(500.0, "CHK-67890", 200.0)

    client.addAccount(account1)
//    client.addAccount(account2)

    client.displayAccounts()

    println("Total Amount of Money: ${client.calculateTotalAmountOfMoney()}")

    account1.applyInterest()
//    account2.withdraw(300.0)

    client.displayAccounts()

    client.sortAccounts(Order.DESCENDING)
    client.displayAccounts()

    client.removeAccount("CHK-67890")

    client.displayAccounts()
}