package com.sungjk.kalgorithm.sort

/**
 * Created by jeremy on 02/25/2019.
 */
// https://github.com/gazolla/Kotlin-Algorithm/blob/master/QuickSort/QuickSort.kt
fun <T:Comparable<T>> quicksort(items:List<T>): List<T> {
    if (items.count() < 2){
        return items
    }
    val pivot = items[items.count()/2]
    val equal = items.filter { it == pivot }
    val less = items.filter { it < pivot }
    val greater = items.filter { it > pivot }
    return quicksort(less) + equal + quicksort(greater)
}

fun main() {
    println("\nOriginal list:")
    val names = listOf("Tim", "Steve", "Zack", "Adam", "John", "Peter", "Clark")
    println(names)
    println("\nOrdered list:")
    val ordered =  quicksort(names)
    println(ordered)
}
