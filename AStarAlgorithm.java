import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;


public class AStarAlgorithm {
	public static void main(String args[]){
		
		final long startTime = System.nanoTime();
		
		Node[] states = new Node[4];
		Node goalNodeFound = new Node();
		goalNodeFound = null;
		Stack stack = new Stack();
		Node current = new Node();
		LinkedList<ArrayList<?>> visited = new LinkedList<ArrayList<?>>();
		int count = 0;
	
		//Creating start node
		Node start = new Node();
		ArrayList<Integer> startState = new ArrayList<>();
		
		startState.add(3);
		startState.add(2);
		startState.add(0);
		startState.add(6);
		startState.add(1);
		startState.add(5);
		startState.add(7);
		startState.add(4);
		startState.add(8);
		
		start.state = startState;
		start.parent = null;
		start.move = null;
		start.priority = 0;
		start.distance = -1;
				
		//Creating goal node
		Node goal = new Node();
		ArrayList<Integer> goalState = new ArrayList<>();
		
		goalState.add(0);
		goalState.add(1);
		goalState.add(2);
		goalState.add(3);
		goalState.add(4);
		goalState.add(5);
		goalState.add(6);
		goalState.add(7);
		goalState.add(8);
		
		
		//Creating goal node
		goal.state = goalState;
		goal.parent = null;
		goal.distance = -1;
		goal.move = null;
		
		Comparator<Node> comparator = new NodeCompare();
		PriorityQueue<Node> pQ = new PriorityQueue<Node>(100,comparator);
		pQ.add(start);
		visited.add(start.state);
		
		while(!pQ.isEmpty()){
			count++;
			current = pQ.remove();
			states = findStates(current);
			
			for(int i = 0; i<=3; i++){
				if(states[i] != null){
						if (states[i].state.equals(goal.state)){
							goalNodeFound = states[i];
							break;
						}
						else{
							if(!visited.contains(states[i].state)){
							states[i].distance = current.distance + 1;	
							visited.add(states[i].state);
							states[i].priority = costFunction(states[i],goal);
							pQ.add(states[i]);
							}
						}
				} 
			}
			if(goalNodeFound != null)
				break;
			
		}
		
		while (goalNodeFound.parent != null){
			if(goalNodeFound.move != null){
				stack.push(goalNodeFound.move);
			}
			goalNodeFound = goalNodeFound.parent;
		}
		
		while(!stack.isEmpty()){
			System.out.println(stack.pop());
		}
		
		System.out.println(count + " Nodes expanded.");
		final long duration = System.nanoTime() - startTime;
		System.out.println(duration/1000000000.0 + " s");
		
	}

	private static int costFunction(Node node, Node goal) {
		// TODO Auto-generated method stub
		
		int priority;
		int count = 0;
		
		//Heuristic Function Calculation
		
		for(int i=0; i<9; i++){
			if(node.state.get(i) != goal.state.get(i)){
				count++;
			}
		}
		
		priority = node.distance + count; 
		return priority;
	}
	
	private static int costFunctionLength(Node node, Node goal) {
		// TODO Auto-generated method stub
		
		int priority;
		int count = 0;
		int index;
		//Heuristic Function Calculation
		
		for(int i=0; i<9; i++){
		index =	goal.state.indexOf(node.state.get(i));
		count = count + Math.abs(index - i);
		}
		
		priority = node.distance + count; 
		return priority;
	}


	private static Node[] findStates(Node state) {
		// TODO Auto-generated method stub
		Node state1,state2,state3,state4;
		
		state1 = moveUP(state);
		state2 = moveDOWN(state);
		state3 = moveLEFT(state);
		state4 = moveRIGHT(state);
		
		Node[] states = {state1, state2, state3, state4};
		
		return states;
	}
	


	private static Node moveRIGHT(Node node) {
		// TODO Auto-generated method stub
		int space = node.state.indexOf(0);
		ArrayList<Integer> childState;
		int temp;
		Node childNode = new Node();
		
		if (space != 2 && space != 5 && space != 8) {
			childState = (ArrayList<Integer>) node.state.clone();
			temp = childState.get(space+1);
			childState.set(space+1,0);
			childState.set(space,temp);			
			childNode.state = childState;
			childNode.parent = node;
			childNode.distance = node.distance + 1;
			childNode.move = "RIGHT";
			return childNode;
		}
		else{
			return null;
		}
	}

	private static Node moveLEFT(Node node) {
		// TODO Auto-generated method stub
		int space = node.state.indexOf(0);
		ArrayList<Integer> childState;
		int temp;
		Node childNode = new Node();
		
		if (space != 0 && space != 3 && space != 6) {
			childState = (ArrayList<Integer>) node.state.clone();
			temp = childState.get(space-1);
			childState.set(space-1,0);
			childState.set(space,temp);			
			childNode.state = childState;
			childNode.parent = node;
			childNode.distance = node.distance + 1;
			childNode.move = "LEFT";
			return childNode;
		}
		else{
			return null;
		}
	}

	private static Node moveDOWN(Node node) {
		// TODO Auto-generated method stub
		int space = node.state.indexOf(0);
		ArrayList<Integer> childState;
		int temp;
		Node childNode = new Node();
		
		if (space <= 5) {
			childState = (ArrayList<Integer>) node.state.clone();
			temp = childState.get(space+3);
			childState.set(space+3,0);
			childState.set(space,temp);			
			childNode.state = childState;
			childNode.parent = node;
			childNode.distance = node.distance + 1;
			childNode.move = "DOWN";
			return childNode;
		}
		else{
			return null;
		}
	}

	private static Node moveUP(Node node) {
		// TODO Auto-generated method stub
		int space = node.state.indexOf(0);
		ArrayList<Integer> childState;
		int temp;
		Node childNode = new Node();
		
		if (space > 2) {
			childState = (ArrayList<Integer>) node.state.clone();
			temp = childState.get(space-3);
			childState.set(space-3,0);
			childState.set(space,temp);			
			childNode.state = childState;
			childNode.parent = node;
			childNode.distance = node.distance + 1;
			childNode.move = "UP";
			return childNode;
		}
		else{
			return null;
		}
	}
	
}
