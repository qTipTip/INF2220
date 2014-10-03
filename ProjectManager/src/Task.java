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
    }

    // Methods
    public void addOutEdge(Task destination){
        outEdges.add(new Edge(this, destination));  
    }
    public void addDependencyEdge(Task destination){
        dependencyEdges.add(new Edge(this, destination));
    }
    // Getters
    public int[] getDependencies(){
        return dependencyIds;
    }
}
