package com.sungjk.kalgorithm.search

/**
 * Created by jeremy on 08/11/2018.
 */
class Node(val left: Node, val right: Node, val label: String) {
    fun getChildren(): List<Node> = listOfNotNull(left, right)

    fun dfs(): List<Node> {
        val visited: List<Node> = listOf()
        val unvisited: List<Node> = listOf()
        unvisited.plus(this)

        while (!unvisited.isEmpty()) {
            val curr: Node = unvisited.first()
            val newNodes: List<Node> = curr.getChildren()
                .filterNot { node -> visited.contains(node) }
            unvisited.drop(0).plus(newNodes)
            visited.plus(curr)
        }

        return visited
    }

}
