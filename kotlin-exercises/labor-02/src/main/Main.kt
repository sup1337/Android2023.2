package main

import interfaces.IDictionary
import ListDictionary

fun main(args: Array<String>) {
//    val dict: IDictionary = ListDictionary
//    println("Number of words: ${dict.size()}")
//    var word: String?
//    while(true){
//        print("What to find? ")
//        word = readLine()
//        if( word.equals("quit")){
//            break
//        }
//        println("Result: ${word?.let { dict.find(it) }}")
//    }

    val name = "Laszlo Hunor"
    println(name.monogram())

    val words= listOf("apple","pear","plum","strawberry")
    println(words.joinBySeparator("#"))
    println(words.longestString())
}
fun List<String>.longestString()=this.maxByOrNull { it.length }
fun String.monogram()=split(" ").map { it.first() }.joinToString("")
fun List<String>.joinBySeparator(separator: String) = this.joinToString(separator)