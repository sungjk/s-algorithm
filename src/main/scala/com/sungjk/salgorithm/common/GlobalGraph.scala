package com.sungjk.salgorithm.common

object GlobalGraph {
	type NodeId = Int
	type Distance = Int
	type Nodes = Set[Node]
	type Path = (Nodes, Distance)
	type Graph = Map[Edge, Distance]

	case class Node(id: NodeId, dist: Distance = Int.MaxValue, prevNode: Option[Node] = None)
	case class Edge(from: NodeId, to: NodeId)
}
