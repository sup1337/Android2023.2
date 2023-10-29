package providers

import ListDictionary
import models.DictinaryType

class DictinaryProviders {
    companion object{
        fun createDictinary(type: DictinaryType) =
            when (type) {
                DictinaryType.ARRAY_LIST -> ListDictionary
                DictinaryType.HASH_SET -> ListDictionary
                DictinaryType.TREE_SET -> ListDictionary
            }
    }
}