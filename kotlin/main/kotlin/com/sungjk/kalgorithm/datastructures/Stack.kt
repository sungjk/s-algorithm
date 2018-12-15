package com.sungjk.kalgorithm.datastructures

/**
 * Created by jeremy on 11/30/2018.
 */
class Stack<T> {

    private var head: Node<T>? = null
    private var size = 0

    fun push(element: T) {
        head = when (head) {
            null -> Node(element)
            else -> Node(element, head)
        }.also { size++ }
    }

    fun pop(): T? {
        if (size == 0) return null else size--
        return head?.data
            .also { head = head?.next }
    }

    fun contains(element: T): Boolean {
        var curr: Node<T>? = head
        while (curr != null) {
            if (curr.data == element) return true
            else curr = curr.next
        }
        return false
    }

    fun isEmpty(): Boolean = size == 0

    fun size() = size

    inner class Node<T>(val data: T, var next: Node<T>? = null)

}
