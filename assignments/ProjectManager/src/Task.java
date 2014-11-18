import java.util.ArrayList;

public class Task{
	
	// Field declarations
	public String name;
	public int id;
	public int index = -1;
	public int lowlink = -1;

	public int time;
	public int start;
	public int stop = 0;
	public int earlystart;
	public int latestart;	
	public int activetime = 0;
	public int slack = 0;
	
	public int staff;

	public ArrayList<Edge> outEdges = new ArrayList<>();
	public ArrayList<Edge> dependencyEdges = new ArrayList<>();
	public int[] dependencies;

	public int preCount;

	public Task(int id, String name, int time, int staff, int[] dependencies, int preCount){
		this.id = id;
		this.name = name;
		this.time = time;
		this.staff = staff;
		this.dependencies = dependencies;
		this.preCount = preCount;
	}

	public boolean isRunnable(){
		return preCount == 0;
	}
	public boolean isComplete(){
		return (time-activetime == 0);
	}
	
	public void alertDependentTasks(){
		for (Edge e : outEdges){
			e.destination.preCount--;
		}
	}

	public String printTask(){
		String details = "Name: " + name + " Id: " + id + " Time: " + time + " Staff: " + staff + " lateStart: " + latestart + " Slack: " + slack + "\n" + "Dependencies: ";
		for(int i : dependencies){
			details += i + " " ;
		}
		return details + "\n" + "\n";
	}
}
