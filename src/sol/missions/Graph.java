package sol.missions;

import java.util.*;

public class Graph<T extends Compromisable> {
	private Node<T> root;
	private List<Node<T>> nodes;
	private List<Edge> edges;
	
	public Node<T> getRoot() { return root; }
	public List<Node<T>> getNodes() { return nodes; }	
	public T getIndex(int i) { return nodes.get(i).getData(); }
	public void setRootData(T rootData) { root = new Node<T>(rootData); }
		
	public Graph() {
		root = null;
		nodes = new ArrayList<Node<T>>(); 
		edges = new ArrayList<Edge>();		
	}
	
	public void add(T data) {
		nodes.add(new Node<T>(data));
	}
	
	// this throws away duplicate connections
	private void addConnection(Node<T> nodeA, Node<T> nodeB) {		
		
		for (Edge e : edges) {
			if (e.contains(nodeA) && e.contains(nodeB)) {
				return;
			}
		}
		edges.add(new Edge(nodeA, nodeB));
	}
	
	public void addConnection(T dataA, T dataB) {		
		addConnection(new Node<T>(dataA), new Node<T>(dataB));
	}
	
	public List<T> getAll() {
		List<T> dataList = new ArrayList<T>();		
		for (Node<T> n : nodes) {
			dataList.add(n.getData());
		}		
		return dataList;
	}	
	
	public List<T> getReachable() {
		List<T> reachable = new ArrayList<T>();
		
		List<Node<T>> visited = new ArrayList<Node<T>>();
		Stack<Node<T>> toSearch = new Stack<Node<T>>();
		
		Node<T> currentNode;
		toSearch.push(root);
		//System.out.println(String.format("pushing %s", root.getData().getName()));
		while (!toSearch.isEmpty()) {			
			currentNode = toSearch.pop();
			//System.out.println(String.format("popping %s", currentNode.getData().getName()));
			
			visited.add(currentNode);
			reachable.add(currentNode.getData());
			
			List<Node<T>> neighbours = getReachableNeighbours(currentNode);			
			for (Node<T> neighbour : neighbours) {
				if (!visited.contains(neighbour)) {
					toSearch.push(neighbour);
					//System.out.println(String.format("pushing %s", neighbour.getData().getName()));					
				}
			}
		}	
		
		return reachable;
	}
	
	private boolean directlyConnected(Node<T> nodeA, Node<T> nodeB) {
		for (Edge e : edges) {			
			if (e.contains(nodeA) && e.contains(nodeB)) { 
				return true;
			}
		}		
		return false;
	}
	
	public boolean directlyConnected(T dataA, T dataB) {
		return directlyConnected(new Node<T>(dataA), new Node<T>(dataB));
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
			for (Node<T> edgeNode : e.getNodes()) {
				if (edgeNode.data.equals(node.data)) {
					neighbours.add(e.getOtherNode(node).getData());
				}
			}
		}
		
		
		return neighbours;
	}
	
	public List<Node<T>> getNeighbourNodes(Node<T> node) {
		List<Node<T>> neighbours = new ArrayList<Node<T>>();
		
		for (Edge e : edges) {			
			for (Node<T> edgeNode : e.getNodes()) {
				if (edgeNode.data.equals(node.data)) {
					neighbours.add(e.getOtherNode(node));
				}
			}
		}		
		
		return neighbours;
	}
	
	public List<T> getNeighbours(T data) {
		return getNeighbours(new Node<T>(data));
	}
		
	private List<Node<T>> getReachableNeighbours(Node<T> node) {
		List<Node<T>> reachableNeighbours = new ArrayList<Node<T>>();
		
		for (Edge e : edges) {			
			if (e.isTraversable() && e.contains(node)) {
				reachableNeighbours.add(e.getOtherNode(node));
			}
		}
		
		return reachableNeighbours;
	}
	
	public void updateEdges() {
		for (Node<T> node : nodes) {
			if (node.getData().isCompromised()) {
				for (Edge e : edges) {
					if (e.contains(node)) {
						e.setTraversable(true);
					}
				}
			}
		}
	}
		
	public class Node<T extends Compromisable> {
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
		
		private Node<T> getNodeA() { return nodeA; }
		private Node<T> getNodeB() { return nodeB; }
		private Node<T> getOtherNode(Node<T> node) { return node.equals(nodeA) ? nodeB : nodeA; }
		private Set<Node<T>> getNodes() { return nodes; }
		private boolean contains(Node<T> node) { return nodes.contains(node); }
		private boolean isTraversable() { return traversable; }
		private void setTraversable(boolean traversable) { this.traversable = traversable; }
		 
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
