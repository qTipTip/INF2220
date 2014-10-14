import java.util.ArrayList; 
import java.util.Iterator;
public class Project{
	// Field declarations
	public ArrayList<Task> projectTasks;	
	public ArrayList<Edge> outEdges;
	private ProjectReader reader;
	private TarjanAlgorithm cycleFinder;
	private ArrayList<Task> activeTasks, finishedTasks, waitingTasks;

	// Public Methods	
	public Project(String fileName){
		this.reader = new ProjectReader(fileName);
		this.activeTasks = new ArrayList<>();
		this.finishedTasks = new ArrayList<>();
		this.waitingTasks = new ArrayList<>();
	}	

	public void initialize(){
		projectTasks = reader.generateProject();
		cycleFinder = new TarjanAlgorithm(this);
	}
	public void simulate(){
		for (Task t : projectTasks){
			waitingTasks.add(t);
		}
		int currentTime = 0;
		while( !projectDone() ){
			String output = "";
			boolean tickTime = false;
			for(Iterator<Task> i = activeTasks.iterator(); i.hasNext();){
				Task t = i.next();
				if (t.isComplete()){
					if(!tickTime){
						output += "Tick: " + currentTime +"\n";
						//System.out.println("Tick: " + currentTime);
					}
					tickTime = true;
					output += "Completed: " + t.id + "\n";
					//System.out.println("Completed: " + t.id);
					t.stop = currentTime;
					t.alertDependentTasks();
					finishedTasks.add(t); 
					i.remove();
				}
			}
			for(Iterator<Task> i = waitingTasks.iterator(); i.hasNext(); ){
				Task t = i.next();
				if( t.isRunnable() ){
					if(!tickTime){
						output += "Tick: " + currentTime + "\n";
						//System.out.println("Tick: " + currentTime);
					}
					tickTime = true;
					activeTasks.add(t);
					t.start = currentTime;
					i.remove();
					output += "Starting: " + t.id + "\n";
					//System.out.println("Starting: " + t.id);
				}
			}
			int activeWorkers = 0;
			for(Task t : activeTasks){
				t.activetime++;
				activeWorkers+=t.staff;
			}
			if(!output.isEmpty()){
				System.out.println();
				System.out.print(output);
				System.out.println("Active workers: "+ activeWorkers);
			}
			currentTime++;
		}
		for(Task t : finishedTasks){
			time(t);
		}
		System.out.println("*** Optimal project completion time: " + (currentTime)+ " ***" );
		System.out.println("*** Following Tasks are CRITICAL and must be completed as soon as possible ***");
		for(Task t : finishedTasks){
			if(t.slack == 0){
				System.out.print(t.id + " ");
			}
		}
		System.out.println();
		System.out.println();
	}
	private boolean projectDone(){

		if( activeTasks.isEmpty() && waitingTasks.isEmpty()){
			return true;
		}
		return false;
	}

	public void printProjectTasks(){
		String output = "";
		for(Task t : projectTasks){
			output += t.printTask();
		}
		System.out.println(output);
	}
	public boolean isCyclic(){
		return cycleFinder.isCyclic();
	}	

	public int time(Task t){
		// Computes the slack for each task.
		int most = 0;
		if(t.dependencyEdges.size() == 0){
			return t.time;
		}
		for(Edge e : t.dependencyEdges){
			Task v = e.destination;
			int tmp = time(v);
			v.start = tmp-v.time;
			if(most < tmp){
				most = tmp;
			}
		}
		for(Edge e : t.dependencyEdges){
			Task v = e.destination;
			v.latestart = most-v.time;
			v.slack = v.latestart - v.start;
		}
		return most+t.time;
	}
}
