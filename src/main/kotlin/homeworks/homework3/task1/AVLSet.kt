package homeworks.homework3.task1

private const val BALANCE_FACTOR_NEEDED_FOR_LEFT_TURN = 2
private const val BALANCE_FACTOR_NEEDED_FOR_RIGHT_TURN = -2

class AVLSet<K, V> where K : Comparable<K> {
    private var root: Node<K, V>? = null
    private var size = 0

    fun printKeys() {
        root?.printKeys() ?: print("Null")
        println()
    }

    fun containsKey(key: K): Boolean {
        var current = root
        while (current != null) {
            current = when {
                current.key == key -> return true
                current.key > key -> current.leftChild
                else -> current.rightChild
            }
        }
        return false
    }

    fun add(key: K, value: V) {
        if (!containsKey(key)) {
            root = root?.add(key, value) ?: Node(key, value)
        }
        size++
    }

    fun removeKey(key: K) {
        if (containsKey(key)) {
            root = root?.removeKey(key)
        }
        size--
    }

    class Node<K, V>(val key: K, var value: V) where K : Comparable<K> {
        private var height = 0
        var leftChild: Node<K, V>? = null
        var rightChild: Node<K, V>? = null

        private fun updateHeight() {
            val leftChildHeight = leftChild?.height ?: -1
            val rightChildHeight = rightChild?.height ?: -1
            height = maxOf(leftChildHeight, rightChildHeight) + 1
        }

        fun getBalanceFactor(): Int {
            val leftChildHeight = leftChild?.height ?: -1
            val rightChildHeight = rightChild?.height ?: -1
            return rightChildHeight - leftChildHeight
        }

        fun printKeys() {
            print("( $key ")
            leftChild?.printKeys() ?: print(" Null ")
            rightChild?.printKeys() ?: print(" Null ")
            print(") ")
        }

        fun add(key: K, value: V): Node<K, V>? {
            if (this.key > key) {
                leftChild = leftChild?.add(key, value) ?: Node(key, value)
            } else if (this.key < key) {
                rightChild = rightChild?.add(key, value) ?: Node(key, value)
            }
            return balance()
        }

        fun rotateRight(): Node<K, V>? {
            val pivot = leftChild
            leftChild = pivot?.rightChild
            pivot?.rightChild = this
            updateHeight()
            pivot?.updateHeight()
            return pivot
        }

        fun rotateLeft(): Node<K, V>? {
            val pivot = rightChild
            rightChild = pivot?.leftChild
            pivot?.leftChild = this
            updateHeight()
            pivot?.updateHeight()
            return pivot
        }

        fun balance(): Node<K, V>? {
            updateHeight()
            val rightChildBalanceFactor = rightChild?.getBalanceFactor() ?: -1
            val leftChildBalanceFactor = leftChild?.getBalanceFactor() ?: -1
            val balanceFactor = getBalanceFactor()
            var result: Node<K, V>? = this

            if (balanceFactor == BALANCE_FACTOR_NEEDED_FOR_LEFT_TURN) {
                if (rightChildBalanceFactor < 0) {
                    rightChild = rightChild?.rotateRight()
                }
                result = rotateLeft()
            }

            if (balanceFactor == BALANCE_FACTOR_NEEDED_FOR_RIGHT_TURN) {
                if (leftChildBalanceFactor > 0) {
                    leftChild = leftChild?.rotateLeft()
                }
                result = rotateRight()
            }

            return result
        }

        fun getMinimumNodeFromRightSubtree(): Node<K, V> {
            return leftChild?.getMinimumNodeFromRightSubtree() ?: this
        }

        fun removeMinimumNodeFromRightSubtree(): Node<K, V>? {
            leftChild = leftChild?.removeMinimumNodeFromRightSubtree() ?: return rightChild
            return balance()
        }

        fun removeKey(key: K): Node<K, V>? {
            when {
                key < this.key -> leftChild = leftChild?.removeKey(key)
                key > this.key -> rightChild = rightChild?.removeKey(key)
                else -> {
                    val result: Node<K, V>?
                    val leftChild = leftChild
                    val rightChild = rightChild

                    if (rightChild == null) {
                        result = leftChild
                    } else {
                        val minimum = rightChild.getMinimumNodeFromRightSubtree()
                        minimum.rightChild = rightChild.removeMinimumNodeFromRightSubtree()
                        minimum.leftChild = leftChild
                        result = minimum.balance()
                    }

                    return result
                }
            }

            return balance()
        }
    }
}

/*fun main() {
    val a = AVLSet<Int, Int>()
    for (i in 1..10)
        a.add(i, i*i)
    a.printKeys()
    for (i in 1..10)
        a.removeKey(i)
    a.printKeys()
}*/
