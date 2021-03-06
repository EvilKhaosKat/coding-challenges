import Common.Companion.assertEquals

/*
Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

    WordDictionary() Initializes the object.
    void addWord(word) Adds word to the data structure, it can be matched later.
    bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.

Example:
```
Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True
```

Constraints:

    1 <= word.length <= 500
    word in addWord consists lower-case English letters.
    word in search consist of  '.' or lower-case English letters.
    At most 50000 calls will be made to addWord and search.

 */
//alternatively: use array instead of map, don't keep letter in Trie node since not used here, iterate instead recursion
class WordDictionary {
    private var root: TrieNode = TrieNode(Char.MAX_SURROGATE)

    class TrieNode(val letter: Char) {
        var isWord: Boolean = false
        var children: MutableMap<Char, TrieNode> = mutableMapOf()
    }

    fun addWord(word: String) {
        var node: TrieNode = root

        for (c in word) {
            val child = node.children[c]
            if (child == null) {
                val newNode = TrieNode(c)
                node.children[c] = newNode
                node = newNode
            } else {
                node = child
            }
        }

        node.isWord = true
    }

    fun search(word: String): Boolean {
        return search(word, root, 0)
    }

    private fun search(word: String, node: TrieNode, index: Int): Boolean {
        if (word.length == index) {
            return node.isWord
        }

        val letter = word[index]

        if (letter == '.') {
            for (child in node.children.values) {
                if (search(word, child, index + 1)) {
                    return true
                }
            }

            return false
        }

        val child = node.children[letter]
        if (child != null) {
            return search(word, child, index + 1)
        }

        return false
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * var obj = WordDictionary()
 * obj.addWord(word)
 * var param_2 = obj.search(word)
 */
fun main() {
    val dict = WordDictionary()
    dict.addWord("bad")
    dict.addWord("dad")
    dict.addWord("mad")
    assertEquals(dict.search("pad"), false)
    assertEquals(dict.search("bad"), true)
    assertEquals(dict.search(".ad"), true)
    assertEquals(dict.search("b.."), true)
}