package interfaces

interface IDictionary {
    companion object{
        const val DICTIONARY_FILE = "dictionary.txt"
    }
    fun add(word: String) : Boolean
    fun find(word: String) : Boolean
    fun size() : Int
}