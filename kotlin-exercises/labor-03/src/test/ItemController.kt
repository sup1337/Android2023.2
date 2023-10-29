package test

class ItemController(val itemService: ItemService) {

    fun quiz(counter: Int){
        var correctAnswers = 0
        val items = itemService.selectRandomItems(counter)
        readLine()
        items.forEach {
            println(it.question + "\n")
            it.answers.forEach {
                println(it)
            }
            println()

            val answer = readLine()!!

            if(answer.toInt()-1 == it.correct)
                correctAnswers++
        }

        println("result: $correctAnswers / $counter")
    }
}