public class Edge{
	// Field declarations
	public Task destination;
	public Task origin;

	public Edge(Task a, Task b){
		this.origin = a;
		this.destination = b;
	}
}
