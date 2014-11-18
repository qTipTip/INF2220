public class ProjectManager{
	public static void main(String[] args){
		String fileName = "<default>";
		try {
			fileName = args[0];
		} catch(Exception e){
			System.out.println("No valid input file specified in terminal");
			System.out.println("Please try again!");
			System.exit(1);
		}

		Project project = new Project(fileName);
		project.initialize();
		if(project.isCyclic()){
			System.out.println("Project dependencies are cyclic, therefore the project is not realizable");
		}else{
			project.simulate();
		}
		project.printProjectTasks();
	}
}
