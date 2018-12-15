package com.sungjk.kalgorithm.datastructures

/**
 * Created by jeremy on 11/30/2018.
 */
class BinarySearchTree<T :Comparable<T>> {
    private var root: Node<T>? = null

    fun insert(element: T, node: Node<T>? = root): Unit =
        node?.let {
            when (element > node.data) {
                true -> node.right?.let { insert(element, node.right) } ?: run { node.right = Node(element) }
                false -> node.left?.let { insert(element, node.left) } ?: run { node.left = Node(element) }
            }
        } ?: run {
            root = Node(element)
        }

    fun delete(element: T) =
        root?.let {
            val currentRoot = root as Node<T>
            if (currentRoot.data == element) {
                when (currentRoot.countChild()) {
                    0 -> root = null
                    1 -> root = if (currentRoot.left != null) currentRoot.left else currentRoot.right
                    else -> { // 2
                        val minValue = currentRoot.right!!.minValue()
                        currentRoot.right!!.removeChild(minValue, currentRoot)
                        currentRoot.data = minValue
                    }
                }
            } else {
                currentRoot.removeChild(element)
            }
        }

    fun contains(element: T, node: Node<T>? = root): Boolean {
        if (node == null) return false
        if (node.data == element) return true
        return if (element > node.data) contains(element, node.right) else contains(element, node.left)
    }

    fun getMinValue(): T? {
        if (root == null) return null

        return root!!.left?.let {
            var curr = root
            while (curr?.left != null) {
                curr = curr.left
            }
            curr!!.data
        } ?: run {
            root!!.data
        }
    }

    inner class Node<T : Comparable<T>>(var data: T) {
        var left: Node<T>? = null
        var right: Node<T>? = null

        fun removeChild(value: T, parent: Node<T> = Node(value)) {
            val left = left
            val right = right

            if (value < this.data) {
                left?.removeChild(value, this)
            } else if (value > this.data) {
                right?.removeChild(value, this)
            } else {
                if (left != null && right != null) {
                    this.data = right.minValue()
                    right.removeChild(this.data, this)
                } else if (parent.left == this) {
                    parent.left = left ?: right
                } else if (parent.right == this) {
                    parent.right = left ?: right
                }
            }
        }

        fun countChild(): Int {
            var count = 0
            if (left != null) count++
            if (right != null) count++
            return count
        }

        fun minValue(): T = when (left) {
            null -> data
            else -> left!!.minValue()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            other as Node<*>
            if (data != other.data) return false
            return true
        }

        override fun hashCode(): Int {
            return data.hashCode()
        }
    }
}
