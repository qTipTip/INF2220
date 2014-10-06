import java.util.Stack;
import java.util.ArrayList;

public class TarjanAlgorithm {
	// This is an implementation of Tarjans
	// algorithm for finding the strongly 
	// connected components of a graph.
	//
	// The algorithm partitions a directed
	// graph into the strongly connected
	// components. 
	//
	// Each vertex appears in exactly one of
	// the strongly connected components, 
	// and a single vertex not on a cycle
	// forms a strongly connected cycle by
	// itself.	
	// 
	// Requires from the TaskClass:
	//		- getLowLink()
	//		- getIndex()
	//		- setLowLink()
	//		- setIndex()
	
	// Field declarations
	private Project input;
	private ArrayList<ArrayList<Task>> output = new ArrayList<>();
	private int index = 0;
	private Stack<Task> stack = new Stack<>();

	// Constructor
	public TarjanAlgorithm(Project input){
		this.input = input;	
	}

	// Public methods
	public void findStrongCon(){
		// This method calls the strongConnect function on 
		// all tasks in given project.

		for(Task t : input.getProjectTasks()){
			if(t.getIndex() == -1){
				strongConnect(t);
			}
		}
		// Print any cycles found
		printCycles();
	}

	// Private methods
	
	private void strongConnect(Task t){
		// Set the depth index for Task t to the smallest unused index.
		// and push the task onto the stack.
		t.setIndex(index);
		t.setLowLink(index);
		index++;
		stack.push(t);
		// TestPrints

		// Consider successors of Task t
		for(Edge e : t.getOutEdges()){
			Task successor = e.getDestination();
			if (successor.getIndex() == -1){
				// Successor has not yet been visited, recurse on it
				strongConnect(successor);
				t.setLowLink(Math.min(t.getLowLink(), successor.getLowLink()));
			}
			else if (stack.contains(successor)){
				// Successor is in stack and hence in the current SCC.
				t.setLowLink(Math.min(t.getLowLink(), successor.getIndex()));
			}
		}
		// If Task t is a root task, pop the stack and generate a SCC.
		if(t.getLowLink() == t.getIndex()){
			// Start a new strongly connected component
			ArrayList<Task> newSCC = new ArrayList<>();
			// Adds tasks in the SCC
			while(stack.peek() != t){
				newSCC.add(stack.pop());
			}
			newSCC.add(stack.pop());
			output.add(newSCC);
		}
	}

	private void printCycles(){
		for(ArrayList<Task> cycle : output){
			if(cycle.size() == 1){ continue; } // Ignoring SCCs of only one node.
			System.out.println("Cycle found");
			for(Task t : cycle){
				System.out.print(t.getID());
				if(t != cycle.get(cycle.size()-1)){
					System.out.print("\u2192");
				}
			}
			System.out.println();
		}
	}
}
