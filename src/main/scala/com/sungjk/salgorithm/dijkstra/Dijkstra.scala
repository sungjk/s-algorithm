package com.sungjk.salgorithm.dijkstra

import com.sungjk.salgorithm.common.GlobalGraph._
import com.sungjk.salgorithm.common.Utils.PriorityQueue

import scala.annotation.tailrec

object Dijkstra {

	def shortestDistance(start: Node, end: Node, graph: Graph): Distance = ???

	def shortestPath(start: Node, end: Node, nodes: Nodes, graph: Graph): Path = {
		@tailrec
		def go(curr: Node, nodes: Nodes, pq: PriorityQueue[Node], visited: Nodes): Path = {
			if (curr.id == end.id) (path(curr) + curr, curr.dist) else {
				println(s"${curr.id}: ${curr.dist}")

				val neighbors = getNeighbors(curr, graph)
				val updatedVisited = visited + curr
				val updatedNodes = transformNeighbors(curr, neighbors, nodes)
				val updatedPq = transformPriorityQueue(
					updatedNodes filter (n => neighbors.keys.toList.contains(n.id)),
					updatedVisited,
					pq filterNot { _.id == curr.id }
				)

				go(updatedPq.head, updatedNodes, updatedPq, updatedVisited)
			}
		}

		go(start, nodes, Seq(start), Set.empty)
	}

	def path(curr: Node): Nodes = curr.prevNode match {
		case Some(node) => path(node) + node
		case None => Set.empty
	}

	def getNeighbors(node: Node, graph: Graph): Map[NodeId, Distance] = {
		graph filterKeys (e => e.from == node.id) map (c => (c._1.to, c._2))
	}

	def transformNeighbors(curr: Node, neighbors: Map[NodeId, Distance], nodes: Nodes): Nodes = nodes map { node =>
		if (neighbors.keys.toList.contains(node.id) && (neighbors(node.id) + curr.dist < node.dist))
			Node(node.id, neighbors(node.id) + curr.dist, Some(curr))
		else
			node
	}

	def orderPriorityQueue(priorityQueue: PriorityQueue[Node]): PriorityQueue[Node] = {
		priorityQueue sortWith { _.dist < _.dist }
	}

	def transformPriorityQueue(nodes: Set[Node], visitedNodes: Nodes, priorityQueue: PriorityQueue[Node]): PriorityQueue[Node] = {
		orderPriorityQueue(priorityQueue ++ (nodes diff visitedNodes filterNot priorityQueue.contains))
	}
}
