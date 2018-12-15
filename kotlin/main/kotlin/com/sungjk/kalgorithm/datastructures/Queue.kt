package com.sungjk.kalgorithm.datastructures

/**
 * Created by jeremy on 12/15/2018.
 */
class WeirdQueue<T> {

    private val stackNewest: Stack<T> = Stack() // new element on top
    private val stackOldest: Stack<T> = Stack() // old element on top

    fun size(): Int = stackNewest.size() + stackOldest.size()

    fun add(data: T) = stackNewest.push(data)

    fun peek(): T? {
        shiftStack()
        return stackOldest.peek()
    }

    fun pop(): T? {
        shiftStack()
        return stackOldest.pop()
    }

    fun shiftStack() {
        if (stackOldest.isEmpty()) {
            while (!stackNewest.isEmpty()) {
                stackOldest.push(stackNewest.pop()!!)
            }
        }
    }

    inner class Stack<T> {
        private var head: StackNode<T>? = null
        private var size: Int = 0

        fun isEmpty(): Boolean = this.size == 0

        fun size(): Int = this.size

        fun push(data: T) =
            this.head?.let {
                this.head = StackNode(data, this.head)
            } ?: run {
                this.head = StackNode(data)
            } .also {
                this.size++
            }

        fun peek(): T? = this.head?.let { it.data } ?: run { null }

        fun pop(): T? =
            this.head?.let {
                val headNode = it
                this.head = headNode.next
                this.size--
                headNode.data
            } ?: run {
                null
            }
    }

    inner class StackNode<T>(var data: T, var next: StackNode<T>? = null)
}
