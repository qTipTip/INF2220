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
	private int index = -1;
	private int lowLink = -1;
	
    // Fields relating to simulation of project
	private int preCount; // Number of predecessors not finished.
	private int workTime; // Number of time units into the work process

    // Constructor
    public Task(int id, String name, int time, int manpower, int[] dependencyIds){
        this.id = id;
        this.time = time;
        this.manpower = manpower;
        this.name = name;
        this.dependencyIds = dependencyIds;
		this.outEdges = new ArrayList<>();
		this.dependencyEdges = new ArrayList<>();
		this.workTime = 0;
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
	public ArrayList<Edge> getOutEdges(){
		return outEdges;
	}
	public int getID(){
		return id;
	}

	public boolean isRunnable(){
		if(preCount == 0){
			return true;
		}
		return false;
	}
	
	public boolean isComplete(){
		if (time-workTime == 0){
			return true;
		}
		return false;
	}

	public void alertDependentTasks(){
		for(Edge e : outEdges){
			e.getDestination().decrementDependecy();
		}
	}

	public void decrementDependecy(){
		if(preCount >= 1){
			preCount--;
		}
		if(preCount < 0){
			System.out.println("There is an error in decrementDependency");
		}
	}
	public void incrementWorkTime(){
		workTime++;
	}

	public void setPreCount(int preCount){
		this.preCount = preCount;	
	}
	// Methods related to the Tarjan Algorithm
	public int getIndex(){
		return index;
	}
	public void setIndex(int i){
		index = i;	
	}

	public int getLowLink(){
		return lowLink;
	}
	public void setLowLink(int i){
		lowLink = i;
	}
}
