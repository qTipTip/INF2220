import java.util.Stack;
import java.util.ArrayList;

public class TarjanAlgorithm {
	// An implementation of the Tarjan Algorithm for finding
	// strongly connected components in a graph.
	//
	// Complexity - O(n)

	// Field declarations

	private Project input;
	private ArrayList<ArrayList<Task>> output = new ArrayList<>();
	private int index = 0;	
	private Stack<Task> stack = new Stack<>();

	// Public methods

	public TarjanAlgorithm(Project input){
		this.input = input;
	}

	public boolean isCyclic(){
		findStrongCon();
		return printCycles();
	}

	// Private methods 

	private void findStrongCon(){
		for(Task t : input.projectTasks){
			if(t.index == -1){
				strongConnect(t);
			}
		}
	}

	private void strongConnect(Task t){
		// Setting depth index for t to the smallest unused index
		// and push the task onto the stack.
		t.index = index;
		t.lowlink = index;
		index++;
		stack.push(t);
	
		// Considering successors of t
		for(Edge e : t.outEdges){
			Task v = e.destination;
			if(v.index == -1){
				// Successor not yet visited, recursing
				strongConnect(v);
				t.lowlink = Math.min(t.lowlink, v.lowlink);
			}
			else if (stack.contains(v)){
				// Successor is in stack and hence in current SCC
				t.lowlink = Math.min(t.lowlink, v.index);
			}
		}
		// If t is a root task, pop stack and generate SCC
		if(t.lowlink == t.index){
			ArrayList<Task> newSCC = new ArrayList<>();
			while(stack.peek() != t){
				newSCC.add(stack.pop());
			}
			newSCC.add(stack.pop());
			output.add(newSCC);
		}
	}

	private boolean printCycles(){
		boolean cyclic = false;
		for(ArrayList<Task> cycle : output){
			if(cycle.size() == 1){ continue; } // Ignoring SCCs of only one Task
			cyclic = true;	
			for(Task t : cycle){
				System.out.print(t.id);
				if(t != cycle.get(cycle.size()-1)){
					System.out.print("\u2192");
				}
			}
		}
		System.out.println();
		return cyclic;
	}
}
