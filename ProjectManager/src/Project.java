public class Project {
	// This class is the graph implementation of
	// a project. It contains a ProjectReader for
	// generating the graph structure.
	//
	// The generated tasks are stored in Task[] projectTasks
	// ordered after ID from low to high.
	
	// Field declarations
	private ProjectReader reader;
	private Task[] projectTasks;

	// Constructor
	public Project(String fileName){
		this.reader = new ProjectReader(fileName);
	}
	
	// Public methods	
	public void initializeProject(){
		reader.generateProject();
		projectTasks = reader.getProjectTasks();
	}

	public void printProjectTasks(){
		// Prints any relevant information about the
		// individual tasks.
		for(Task t : projectTasks){
			t.printTask();
		}
	}
}
