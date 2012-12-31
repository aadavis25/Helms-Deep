/**
 * @author Aaron M Davis
 * last edited: 11/19/11
 */
import java.util.ArrayList;
import java.util.Iterator;

 public class DijAlg {
	 //internal class of objects to operate on, in order to keep GVertex neat
	public class DijObj{
		/*Data members
		 * @node is the GVertex 
		 * @known is whether the object has been "dealt with" ie visited by the algorithm
		 * @dist is the current weight of the shortest path known to the node
		 * @from is the node that is previous this one in the shortest path
		 */
		private TheGraph.GVertex node;
		private boolean known = false;
		private int dist = (int) Math.pow(12, 12);
		private TheGraph.GVertex from;
		//constructor
		private DijObj(TheGraph.GVertex n ){
			node = n;
		}
		//getters
		public TheGraph.GVertex getNode() {return node;}
		public TheGraph.GVertex getFrom() {return from;}
	}
	
	/*Data members
	 * @dijList is the list of DijObj's to keep and return
	 * @que is the priority que to add the items to
	 */
	 ArrayList<DijObj> dijList = new ArrayList<DijObj>();
	 PriQueue que = new PriQueue();
	/**
	 * @param graph is the graph to perform the Dijkstra's algorithm on
	 * @param start is the node to start at
	 * @return an array of DijObj (Dijkstra's Objects) with Dijkstra's Alg completed
	 */
	public ArrayList<DijObj> dijkstras(TheGraph graph, TheGraph.GVertex start){
		//iterate on the graph's list of nodes and turn them into DijObjs and add to list
		Iterator itr = graph.getListNames().keySet().iterator();
		while (itr.hasNext()){
			TheGraph.GVertex v = graph.getListVert().get((Integer) graph.getListNames().get(itr.next()));
			v.setObj( new DijObj(v));
			dijList.add(v.getObj());
		}
		//initialize start to 0 dist to itself and set from itself, enque start
		start.getObj().dist = 0; 
		start.getObj().from = start;
		que.enq(start, 0);
		//while there are items in the queue...
		while (que.size() > 0){
			TheGraph.GVertex currNode = que.deq().getNode(); 
			//deque a node and if it's still false...
			if (currNode.getObj().known == false){
				Iterator itr2 = currNode.getListAdj().iterator();
				//iterate on it's adjacency list...
				while(itr2.hasNext()){
					TheGraph.GVertex.Adjac edg = (TheGraph.GVertex.Adjac)itr2.next();
					DijObj testObj = edg.getVertex().getObj();
					//and for each adjacency, check if the object of its node is false, and if the current path followed
					//is shorter, then change the from and dist accordingly and add the new item to the queue
					if((currNode.getObj().dist + edg.getWeight() < testObj.dist) && (testObj.known == false)){
						testObj.from = currNode;
						testObj.dist = currNode.getObj().dist + edg.getWeight();
						que.enq(edg.getVertex(), testObj.dist);
					}
				}
			}
			//set the node iterated on to true; it's been ''dealt with"
			currNode.getObj().known = true;
		}		
		/*
		if(skynet == true)
		 	break
		 */
		
		//return dijList
		return dijList;
	}
	public ArrayList<String> tracePaths(TheGraph graph, String start){
		ArrayList<DijAlg.DijObj> dijList = dijkstras(graph, graph.getVert(start));
		ArrayList<String> list = new ArrayList<String>();
		//go to every object and trace backwards, printing out the paths
		for(int index = 0; index < dijList.size(); index++){
			String s = "";
			DijAlg.DijObj dij = dijList.get(index);
			while (dij.getNode().getElt().compareTo(start) != 0){
				s = "->" + dij.getNode().getElt() + s;
				dij = dij.getFrom().getObj();
			}
			System.out.println(start + s);
			list.add(s);
		}
		return list;
	}
}
