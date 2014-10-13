import java.util.ArrayList;
import java.util.Iterator;
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
	private TarjanAlgorithm cycleTester;

	private ArrayList<Task> activeTasks;
	private ArrayList<Task> finishedTasks;
	private ArrayList<Task> waitingTasks;

	private int currentTime;
	// Constructor

	public Project(String fileName){
		this.reader = new ProjectReader(fileName);
		this.activeTasks = new ArrayList<Task>();
		this.finishedTasks = new ArrayList<Task>();
		this.waitingTasks = new ArrayList<Task>();
	}

	// Public methods	
	public void initializeProject(){
		reader.generateProject();
		projectTasks = reader.getProjectTasks();
		this.cycleTester = new TarjanAlgorithm(this);
		cycleTester.findStrongCon();
	}

	public void printProjectTasks(){
		// Prints any relevant information about the
		// individual tasks.
		for(Task t : projectTasks){
			t.printTask();
		}

	}

	public void simulate(){
		// Given that the project is realizible, this
		// methods finds the optimal way of realizing
		// the project.
		ArrayList<Task> tempContainer = new ArrayList<>();
		
		for(Task t : projectTasks){
			waitingTasks.add(t);
		}
		currentTime = 1;
		while( !projectDone() ){
			boolean tickTime = false;
			for(Iterator<Task> i = activeTasks.iterator(); i.hasNext();){
				// Increments the current time by one, and check if any task
				// has been completed yet. If so, it adds it to the
				// finishedTasks container.
				Task t = i.next();
				if( t.isComplete() ){
					if(!tickTime){
						System.out.println("=====================");
						System.out.println("Tick: " + currentTime);
						System.out.println("---------------------");
						System.out.println("Active workers: " + gettingActiveWorkers());
					}
					tickTime = true;
					System.out.println("Completed: " + t.getID());
					t.alertDependentTasks(); // Tells the tasks dependent on t that t is done
					finishedTasks.add(t);
					i.remove();
				}	
			}
			for(Iterator<Task> i = waitingTasks.iterator(); i.hasNext(); ){
				// Adds Tasks ready to be completed to the activeTasks container.
				Task t = i.next();
				if ( t.isRunnable() ){
					if(!tickTime){
						System.out.println("=====================");
						System.out.println("Tick: " + currentTime);
						System.out.println("---------------------");
						System.out.println("Active workers: " + gettingActiveWorkers());
					}
					tickTime = true;
					activeTasks.add(t);
					i.remove();
					System.out.println("Starting: " + t.getID());
				}
			}
			// Increment the workTime variable in each active task.
			for(Task t : activeTasks){
				t.incrementWorkTime();	
			}
			currentTime++;
		}
	}
	
	private void generateSlack(){
		// This method generates the slack for each task
		// by traversing the graph once forward and once
		// backwards, taking note of the time each task is
		// accessed at the latest.
		// Requires the variables forwardSlack and backwardSlack
		// in the Task class.
		
		

	}	

	private int gettingActiveWorkers(){
		int workers = 0;
		for(Task t : activeTasks){
			workers = workers + t.getWorkers();	
		}
		return workers;
	}	
	public Task[] getProjectTasks(){
		return projectTasks;
	}

	private boolean projectDone(){
		if( activeTasks.isEmpty() && waitingTasks.isEmpty()){
			return true;
		}
		return false;
	}

}
