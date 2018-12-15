package com.sungjk.kalgorithm.datastructures

/**
 * Created by jeremy on 12/09/2018.
 */
class LinkedList<T> {

    private var head: Node<T>? = null

    fun append(data: T) {
        var current = head
        while (current?.next != null) {
            current = current.next!!
        }
        current?.let { it.next = Node(data) } ?: run { current = Node(data) }
    }

    fun isPalindrome(list: LinkedList<T>): Boolean {
        var slow = list.head
        var fast = list.head
        val stack = Stack<T>()

        while (fast != null && fast.next != null) {
            stack.push(slow!!.data)
            slow = slow!!.next
            fast = fast!!.next!!.next
        }

        if (fast != null) {
            slow = slow!!.next
        }

        while (slow != null) {
            if (stack.pop() != slow.data) {
                return false
            }
            slow = slow!!.next
        }
        return true
    }

    inner class Node<T>(val data: T, var next: Node<T>? = null)

}
