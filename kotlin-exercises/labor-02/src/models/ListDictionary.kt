import interfaces.IDictionary
import java.io.File

object ListDictionary : IDictionary {
    private var words = mutableListOf<String>()

    init {
        File(IDictionary.DICTIONARY_FILE).forEachLine { add(it) }
    }

    override fun add(word: String): Boolean {
        if (!find(word)) {
            words.add(word)
            return true
        }
        return false
    }

    override fun find(word: String): Boolean {
        return words.contains(word)
    }

    override fun size(): Int {
        return words.size
    }
}

