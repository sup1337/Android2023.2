package test


import java.io.File
import java.util.*

class ItemRepository {

    val items: MutableList<Item>

    init{
        items = mutableListOf<Item>()

        val file = File("input.txt")
        val scanner = Scanner(file)

        while(scanner.hasNextLine()){
            val question = scanner.nextLine()
            val answer1 = scanner.nextLine()
            val answer2 = scanner.nextLine()
            val answer3 = scanner.nextLine()
            val answer4 = scanner.nextLine()
            val correctPosition = scanner.nextLine().toInt()

            items.add(Item(question, listOf(answer1,answer2,answer3,answer4),correctPosition))
        }
    }

    fun randomItem() : Item {
        val position = (0..items.size-1).random()
        return items[position]
    }

    fun save(item: Item){
        items.add(item)
    }

    fun size() : Int{
        return items.size
    }
}