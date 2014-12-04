package sol.missions;

import java.util.*;

public class Graph<T> {
	private Node<T> root;
	private List<Node<T>> nodes;
	private List<Edge> edges;
	
	public Node<T> getRoot() { return root; }
	public List<Node<T>> getNodes() { return nodes; }	
	public T getIndex(int i) { return nodes.get(i).getData(); }
		
	public Graph() {
		nodes = new ArrayList<Node<T>>(); 
		edges = new ArrayList<Edge>();		
	}
	
	public Graph(T rootData) {
		root = new Node<T>(rootData);
		nodes = new ArrayList<Node<T>>(); 
		edges = new ArrayList<Edge>();
	}
	
	public void add(T data) {
		nodes.add(new Node(data));
	}
	
	public void addConnection(Node<T> nodeA, Node<T> nodeB) {
		edges.add(new Edge(nodeA, nodeB));
	}
	
	public List<T> getAll() {
		List<T> dataList = new ArrayList<T>();		
		for (Node<T> n : nodes) {
			dataList.add(n.getData());
		}		
		return dataList;
	}	
	
	private boolean connected(Node<T> nodeA, Node<T> nodeB) {
		Node<T> currentNode = nodeA;
		
		
		
		
		return false;
	}
	
	private boolean connectedToRoot(Node<T> node) {
		return connected(root, node);
	}
	
	private boolean directlyConnected(Node<T> nodeA, Node<T> nodeB) {
		for (Edge e : edges) {			
			if (e.getNodes().contains(nodeA) && e.getNodes().contains(nodeB)) { 
				return true;
			}
		}
		
		return false;
	}
	
	public boolean directlyConnected(T dataA, T dataB) {
		return directlyConnected(new Node(dataA), new Node(dataB));
	}
	
	public Node<T> getNode(T data) { 
		for (Node<T> node : nodes) {
			if (node.getData().equals(data)) {
				return node;
			}
		}		
		return null;
	}
	
	public List<T> getNeighbours(Node<T> node) {
		List<T> neighbours = new ArrayList<T>();
		
		for (Edge e : edges) {
			/*
			if (e.getNodes().contains(node)) {
				neighbours.add(e.getOtherNode(node).getData());
			}
			*/
			for (Node<T> edgeNode : e.getNodes()) {
				if (edgeNode.data.equals(node.data)) {
					neighbours.add(e.getOtherNode(node).getData());
				}
			}
		}
		
		
		return neighbours;
	}
	
	public List<T> getNeighboursFromData(T data) {
		return getNeighbours(new Node<T>(data));
	}
		
	private List<Node<T>> getReachableNeighbours(Node<T> node) {
		List<Node<T>> reachableNeighbours = new ArrayList<Node<T>>();
		
		for (Edge e : edges) {			
			if (e.isTraversable() && e.getNodes().contains(node)) {
				reachableNeighbours.add(e.getOtherNode(node));
			}
		}
		
		return reachableNeighbours;
	}
		
	public class Node<T> {
		private T data;
		
		public Node(T data) {
			this.data = data;
		}
		
		public T getData() { return data; }
		
		@Override
		public boolean equals(Object object) {
			if (object instanceof Node) {
				Node<T> otherNode = (Node<T>) object;
				return this.data.equals(otherNode.getData());
			}
			else {
				return false;
			}
		}
		
		@Override
		public int hashCode() {
			return data.hashCode();
		}
	}
	
	private class Edge {
		private Node<T> nodeA;
		private Node<T> nodeB;
		private Set<Node<T>> nodes;		
		private boolean traversable;
		private boolean visited;
		
		private Node<T> getNodeA() { return nodeA; }
		private Node<T> getNodeB() { return nodeB; }
		private Node<T> getOtherNode(Node<T> node) { return node.equals(nodeA) ? nodeB : nodeA; }
		private Set<Node<T>> getNodes() { return nodes; }		
		private boolean isTraversable() { return traversable; }
		private boolean isVisited() { return visited; }		
		private void setTraversable(boolean traversable) { this.traversable = traversable; }
		private void setVisited(boolean visited) { this.visited = visited; }
		 
		private Edge(Node<T> nodeA, Node<T> nodeB) {
			this.nodeA = nodeA;
			this.nodeB = nodeB;
			nodes = new HashSet<Node<T>>();
			nodes.add(nodeA);
			nodes.add(nodeB);
			traversable = false;
		}
	}
}
