import java.util.ArrayList;

public class Task{
    // This class keeps track of all information
    // regarding each individual task in the project.
    // Each Task instance is generated from the input file
    // in the ProjectReader class and stored in
    // the Project class.
    
    // Field declarations
    private int id;
    private int time;
    private int manpower;
    private String name;    
    private int[] dependencyIds;
    private ArrayList<Edge> outEdges;
    private ArrayList<Edge> dependencyEdges;

    // Fields related to the Tajran-algorithm
    
    // Constructor
    public Task(int id, String name, int time, int manpower, int[] dependencyIds){
        this.id = id;
        this.time = time;
        this.manpower = manpower;
        this.name = name;
        this.dependencyIds = dependencyIds;
		this.outEdges = new ArrayList<>();
		this.dependencyEdges = new ArrayList<>();
    }

    // Public Methods
    public void addOutEdge(Task destination){
        outEdges.add(new Edge(this, destination));  
    }

    public void addDependencyEdge(Task destination){
        dependencyEdges.add(new Edge(this, destination));
    }
	
	public void printTask(){
		// This method prints the details of Task.

		String headerString = "TASK ID: " + id + " TASK NAME: " + name;
		String headerBorder = new String(new char[headerString.length()]).replace("\0", "=");
		String smallBorder  = new String(new char[headerString.length()]).replace("\0", "-");
	
		System.out.println();	
		System.out.println(headerBorder);
		System.out.println(headerString);
		System.out.println(headerBorder);

		System.out.println("Time  needed: " + time);
		System.out.println("Staff needed: " + manpower);
		System.out.println("DEPENDENCIES: ");
		System.out.println(smallBorder);
		for(int i : dependencyIds){
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println(headerBorder);

	}	
    public int[] getDependencies(){
        return dependencyIds;
    }
}
