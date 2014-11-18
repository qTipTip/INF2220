import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
public class ProjectReader{
  private Scanner scan;	
  private ArrayList<Task> projectTasks;

  public ProjectReader(String fileName){
    try {
      scan = new Scanner(new File(fileName));
      System.out.println(fileName + " read successfully!");
    } catch(Exception e){
      System.out.println("No file with given filename found in directory:" + fileName);
    }
  }

  public ArrayList<Task> generateProject(){
    projectTasks = new ArrayList<>();
    scan.nextInt();
    while(scan.hasNext()){
      String currentLine = scan.nextLine();
      if(currentLine.isEmpty()){
        // Ignoring blank lines
        continue;
      }
      projectTasks.add(lineToTask(currentLine));
    }
    generateEdges();
    return projectTasks;
  }

  private Task lineToTask(String line){
    String[] entries = line.split("\\s+");
    int id = Integer.parseInt(entries[0]);
    String name = entries[1];
    int time = Integer.parseInt(entries[2]);
    int staff = Integer.parseInt(entries[3]);
    int[] dependencyIds = new int[entries.length-5];
    int j = 0;
    for (int i = 4; i < entries.length-1; i++) {
      dependencyIds[j++] = Integer.parseInt(entries[i]);
    }
    return new Task(id, name, time, staff, dependencyIds, dependencyIds.length);
  }	

  private void generateEdges(){
    for(Task t : projectTasks){
      if(t.dependencies.length == 0) {continue;} // No dependencies
      for(int id : t.dependencies){
        Task tmp = projectTasks.get(id-1);
        tmp.outEdges.add(new Edge(tmp, t));
        t.dependencyEdges.add(new Edge(t, tmp));
      }
    }	
  }
}
