package homeworks.homework5.task1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

internal class TrieTest {
    private val path = "./src/test/resources/homeworks/homework5/task1"

    @Test
    fun getSize_EmptyTrie_MustWork() {
        val trie = Trie()
        assertEquals(0, trie.wordCount)
    }

    @Test
    fun getSize_NonEmptyTrie_MustWork() {
        val trie = Trie()
        trie.add("qwerty")
        trie.add("sfkn")
        trie.add("sdkngnewinieingwnrjinwekgnjiewnginwb")
        trie.remove("1")
        trie.remove("sfkn")
        trie.add("dsbg98bhgji g39u4g1g")
        trie.add("")
        assertEquals(4, trie.wordCount)
    }

    @Test
    fun getSize_AddSeveralEqualElements_MustWork() {
        val trie = Trie()
        trie.add("qwerty")
        trie.add("qwerty")
        trie.add("qwerty")
        trie.add("qwerty")
        trie.add("qwerty")
        assertEquals(1, trie.wordCount)
    }

    @Test
    fun contains_EmptyTrie_MustWork() {
        val trie = Trie()
        assertFalse(trie.contains("23256346"))
    }

    @Test
    fun contains_ElementInTrie_MustWork() {
        val trie = Trie()
        trie.add("qwerty")
        trie.add("")
        trie.add("ensdnuewu")
        trie.add("23256346")
        trie.add("viouirbg")
        assert(trie.contains("23256346"))
    }

    @Test
    fun contains_ElementNotInTrie_MustWork() {
        val trie = Trie()
        trie.add("qwerty")
        trie.add("")
        trie.add("ensdnuewu")
        trie.add("23256346")
        trie.add("viouirbg")
        assertFalse(trie.contains("abc"))
    }

    @Test
    fun howManyStartWithPrefix_EmptyTrie_MustWork() {
        val trie = Trie()
        assertEquals(0, trie.howManyStartWithPrefix("1235"))
    }

    @Test
    fun howManyStartWithPrefix_PrefixInTrie_MustWork() {
        val trie = Trie()
        trie.add("")
        trie.add("qwerty")
        trie.add("qwe")
        trie.add("qwertyqwerty")
        trie.add("qwabcd")
        trie.add("qwert")
        assertEquals(4, trie.howManyStartWithPrefix("qwe"))
    }

    @Test
    fun howManyStartWithPrefix_PrefixNotInTrie_MustWork() {
        val trie = Trie()
        trie.add("")
        trie.add("qwerty")
        trie.add("qwe")
        trie.add("qwertyqwerty")
        trie.add("qwabcd")
        trie.add("qwert")
        assertEquals(0, trie.howManyStartWithPrefix("abcd"))
    }

    @Test
    fun addAndRemove_EmptyTrie_MustWork() {
        val expected = Trie()
        val actual = Trie()
        actual.add("qwerty")
        actual.remove("qwerty")
        assertEquals(expected, actual)
    }

    @Test
    fun addAndRemove_NonEmptyTrie_MustWork() {
        val expected = Trie()
        expected.add("qwerty")
        expected.add("qwe")
        expected.add("123456")

        val actual = Trie()
        actual.add("qwerty")
        actual.add("qwertyqwerty")
        actual.add("qwe")
        actual.add("123456")
        actual.add("1234")
        actual.remove("qwertyqwerty")
        actual.remove("1234")

        assertEquals(expected, actual)
    }

    @Test
    fun serialize_EmptyTrie_MustWork() {
        val trieForSerialize = Trie()
        val out = FileOutputStream("$path/actualEmptyTrie.txt")
        trieForSerialize.writeObject(out)

        val expected = File("$path/emptyTrie.txt").readText()
        val actual = File("$path/actualEmptyTrie.txt").readText()
        assertEquals(expected, actual)
    }

    @Test
    fun serialize_NonEmptyTrie_MustWork() {
        val trieForSerialize = Trie()
        trieForSerialize.add("")
        trieForSerialize.add("ndugiesgnjib")
        trieForSerialize.add("fgvgrvbgtbty")
        trieForSerialize.add("tedjfcycouykiuyljfiuitlufy")
        trieForSerialize.add("yriydliudlikudmjuyfloifjuynytdmliuffooi7o97")
        trieForSerialize.add("qwerty")
        val out = FileOutputStream("$path/actualNonEmptyTrie.txt")
        trieForSerialize.writeObject(out)

        val expected = File("$path/nonEmptyTrie.txt").readText()
        val actual = File("$path/actualNonEmptyTrie.txt").readText()
        assertEquals(expected, actual)
    }

    @Test
    fun deserialize_EmptyTree_MustWork() {
        val expected = Trie()
        val actual = Trie()
        actual.add("qwerty")
        val input = FileInputStream("$path/emptyTrie.txt")
        actual.readObject(input)
        assertEquals(expected, actual)
    }

    @Test
    fun deserialize_NonEmptyTree_MustWork() {
        val expected = Trie()
        expected.add("")
        expected.add("ndugiesgnjib")
        expected.add("fgvgrvbgtbty")
        expected.add("tedjfcycouykiuyljfiuitlufy")
        expected.add("yriydliudlikudmjuyfloifjuynytdmliuffooi7o97")
        expected.add("qwerty")

        val actual = Trie()
        actual.add("123")
        val input = FileInputStream("$path/nonEmptyTrie.txt")
        actual.readObject(input)
        assertEquals(expected, actual)
    }

    @Test
    fun deserialize_InvalidInputStream_ExceptionThrown() {
        val trie = Trie()
        val input = FileInputStream("$path/invalidInput.txt")
        assertThrows(IOException::class.java) {
            trie.readObject(input)
        }
    }
}
