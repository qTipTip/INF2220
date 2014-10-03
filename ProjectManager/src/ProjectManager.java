public class ProjectManager {
	// This is the class where the main method resides
	// The ProjectManager contains a list of different projects
	// These can be read in as a filenames on the command line
	//
	// A user interface gives allows you to browse through
	// each project, look at details regarding each project
	// and modify each project.

	// Currently only supporting one project at the time
	// and no user interface implemented yet.	
	
	public static void main(String[] args){
		// If no valid file is specified, quit the program and let user try again
		String fileName = "<default>";
		try {
			fileName = args[0];
		} catch(Exception e){
			// Chosing to not print stacktrace - want a clean exit if we do
			// encounter an error.
			System.out.println("No valid input file specifed in terminal");
			System.out.println("Please try again!");
			System.exit(1);
		}
		// Initializing Project object and generating the graph
		Project project = new Project(fileName);
		project.initializeProject();
		project.printProjectTasks();		
	}
}
