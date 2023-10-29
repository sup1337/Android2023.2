package test

class ItemService(val itemRepository: ItemRepository) {
    fun selectRandomItems(counter: Int) : List<Item>{
        var currentList = mutableListOf<Item>()
        currentList.addAll(itemRepository.items)
        val result = mutableListOf<Item>()

        (1..counter).forEach {
            val position = (0..currentList.size - 1).random()
            result.add(currentList[position])
            currentList.remove(currentList[position])
        }

        return result.toList()

    }
}