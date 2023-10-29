package main

import Account

class SavingsAccount(balance: Double, accountNumber: String, val interestRate: Double) : Account(balance, accountNumber) {
    fun applyInterest() {
        val interest = balance * interestRate
        balance += interest
    }

//     fun displayInfo() {
//        super.displayInfo()
//        println("Interest Rate: $interestRate")
//    }
}