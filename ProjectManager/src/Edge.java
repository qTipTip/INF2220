public class Edge {
    // This class connects two Tasks with an edge.
    
    // Field declarations
    private Task origin;
    private Task destination;
    
    // Constructor
    public Edge(Task origin, Task destination){
        this.origin = origin;
        this.destination = destination;
    }

	// Public method
	public Task getDestination(){
		return destination;
	}
	public Task getOrigin(){
		return origin;
	}

}
