package main

import kotlin.random.Random


fun printSum(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a + b}")
}

fun isPrime(number: Int): Boolean {
    if (number <= 1) {
        return false
    }
    for (i in 2 until number) {
        if (number % i == 0) {
            return false
        }
    }
    return true
}

// Caesar titkosítás és visszafejtés kulcsával
val caesarKey = 3

// Függvény az üzenet kódolására Caesar titkosítással
fun encode(msg: String): String {
    val result = StringBuilder()
    for (char in msg) {
        if (char.isLetter()) {
            val shiftedChar = if (char.isUpperCase()) {
                ((char.toInt() - 'A'.toInt() + caesarKey) % 26 + 'A'.toInt()).toChar()
            } else {
                ((char.toInt() - 'a'.toInt() + caesarKey) % 26 + 'a'.toInt()).toChar()
            }
            result.append(shiftedChar)
        } else {
            result.append(char)
        }
    }
    return result.toString()
}

// Függvény az üzenet visszafejtésére Caesar titkosítással
fun decode(encodedMsg: String): String {
    val result = StringBuilder()
    for (char in encodedMsg) {
        if (char.isLetter()) {
            val shiftedChar = if (char.isUpperCase()) {
                ((char.toInt() - 'A'.toInt() - caesarKey + 26) % 26 + 'A'.toInt()).toChar()
            } else {
                ((char.toInt() - 'a'.toInt() - caesarKey + 26) % 26 + 'a'.toInt()).toChar()
            }
            result.append(shiftedChar)
        } else {
            result.append(char)
        }
    }
    return result.toString()
}

// Magasabb rendű függvény az üzenet kódolására vagy visszafejtésére
fun messageCoding(msg: String, func: (String) -> String): String {
    return func(msg)
}

fun printEvenNumbers(numbers: List<Int>) = numbers.filter { it % 2 == 0 }.forEach { println(it) }

fun main(args: Array<String>) {
    //1 es feladat
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
    println("\n")
    printSum(2, 3)
    //2 es feladat
    // Létrehozunk egy listát, amely tartalmazza a napokat
    val daysOfWeek = mutableListOf("Hétfő", "Kedd", "Szerda", "Csütörtök", "Péntek", "Szombat", "Vasárnap")

    // Az összes nap kiíratása
    println("Az összes nap:")
    for (day in daysOfWeek) {
        println(day)
    }

    // Napok kiíratása, amelyek 'T'-vel kezdődnek
    println("\nNapok, amelyek 'T'-vel kezdődnek:")
    val daysStartingWithT = daysOfWeek.filter { it.startsWith("T") }
    for (day in daysStartingWithT) {
        println(day)
    }

    // Napok kiíratása, amelyek tartalmazzák az 'e' betűt
    println("\nNapok, amelyek tartalmazzák az 'e' betűt:")
    val daysContainingE = daysOfWeek.filter { it.contains("e") }
    for (day in daysContainingE) {
        println(day)
    }

    // Napok kiíratása, amelyek 6 karakter hosszúak
    println("\nNapok, amelyek 6 karakter hosszúak:")
    val daysWithLength6 = daysOfWeek.filter { it.length == 6 }
    for (day in daysWithLength6) {
        println(day)
    }
    println("\n")
    //3 as feladat
    val list = listOf(1, 2, 3, 4, 5)

    println("A lista tartalma:")
    println(list)

    println("Prímszámok a listában:")
    val primeNumbers = list.filter { isPrime(it) }
    println(primeNumbers)

    val numberToCheck = 3
    if (numberToCheck in list) {
        println("$numberToCheck a listában van")
    } else {
        println("$numberToCheck nincs a listában")
    }
    // 4 es feladat
    val message = "Hello, World!" // Az eredeti üzenet

    // Üzenet kódolása és dekódolása a Caesar titkosítással
    val encodedMessage = messageCoding(message, ::encode)
    val decodedMessage = messageCoding(encodedMessage, ::decode)

    println("Eredeti üzenet: $message")
    println("Kódolt üzenet: $encodedMessage")
    println("Visszafejtett üzenet: $decodedMessage")
    println("\n")

    //5. feladat

    println("Páros számok a listában:")
    printEvenNumbers(list)

    //6. feladat

    // 1. Kétszerezze meg a számokat és nyomtassa ki
    val doubledNumbers = list.map { it * 2 }
    println("Kétszeres számok: $doubledNumbers")

    // 2. Nyomtassa ki a napokat kisbetűvel
    val capitalizedDays = daysOfWeek.map { it.toUpperCase() }
    println("Napok nagybetűvel: $capitalizedDays")

    // 3. Nyomtassa ki a napok első karakterét nagybetűvel
    val firstCharCapitalized = daysOfWeek.map { it.first().toUpperCase() }
    println("Napok első karaktere nagybetűvel: $firstCharCapitalized")

    // 4. Nyomtassa ki a napok hosszát
    val dayLengths = daysOfWeek.map { it.length }
    println("Napok hossza: $dayLengths")

    // 5. Számítsa ki a napok átlagos hosszát
    val averageLength = dayLengths.average()
    println("Napok átlagos hossza: $averageLength")

    //7. feladat

    // Azokat a napokat távolítsuk el, amelyek tartalmazzák a 'n' betűt
    daysOfWeek.removeIf { it.contains('n') }

    // Nyomtassuk ki a listát indexszel együtt
    val indexedDaysOfWeek = daysOfWeek.withIndex()
    for ((index, day) in indexedDaysOfWeek) {
        println("Item at $index is $day")
    }

    // Rendezzük az eredmény listát ábécé sorrendben
    daysOfWeek.sort()
    println("Rendezett lista: $daysOfWeek")

    //8. feladat
    val randomNumbers = Array(10) { Random.nextInt(101) }

    println("Tömb elemei:")
    randomNumbers.forEach { println(it) }

    randomNumbers.sort()
    println("Tömb rendezve növekvő sorrendben:")
    randomNumbers.forEach { println(it) }

    val containsEven = randomNumbers.any { it % 2 == 0 }
    if (containsEven) {
        println("A tömb tartalmaz páros számot.")
    } else {
        println("A tömb nem tartalmaz páros számot.")
    }

    val allEven = randomNumbers.all { it % 2 == 0 }
    if (allEven) {
        println("Az összes szám páros.")
    } else {
        println("Nem minden szám páros.")
    }

    val sum = randomNumbers.sum()
    val average = sum.toDouble() / randomNumbers.size
    println("A generált számok átlaga: $average")

}