import java.io.File;
import java.util.Scanner;

public class ProjectReader {
    /*
    * This class is responsible for reading the input files
    * that describe each project, and generate the required
    * objects. 
    *
    * The ProjectReader will for each line in the input file
    * create a new Task.
    *
    * It will also before returning the list of Tasks generate
    * all the required edges between two Tasks. This is what
    * defines the dependencies in the project. The dependencies
    * will be represented using the Edge class.
    *
    */
    // Field declarations
    private Scanner scan;
    private String fileName;
    private Task[] projectTasks;

    // Constructor
    public ProjectReader(String fileName){
        // Sets up the required Scanner object
        // for file reading.
        
        this.fileName = fileName;

        try {
            scan = new Scanner(new File(fileName));
            System.out.println(fileName + " read successfully!");
        } catch(Exception e){
            System.out.println("No file with given fileName found in directory: " + fileName);
            e.printStackTrace();
        }
    }
    public void generateProject(){
        // The public method makes calls to all the different
        // private helper methods for reading the input file.
        
        projectTasks = new Task[scan.nextInt()];
        int taskCounter = 0;
        
        while (scan.hasNext()){
            String currentLine = scan.nextLine();
            if(currentLine.isEmpty()){
                // Ignoring blank lines
                continue;
            }
            // Generating new Task and adding to container
            Task newTask = lineToTask(currentLine);
            projectTasks[taskCounter++] = newTask;
        }
        // Generate all edges
        generateEdges();        
    }

    // PRIVATE HELPER METHODS FOR GENERATING PROJECT

    private Task lineToTask(String line){
        // This method takes a line defining a task
        // and generates a task object based on it.
        // The Edge dependencies will be generated
        // in another method.
        
        String[] entries = line.split("\\s+");

        int id = Integer.parseInt(entries[0]);
        String name = entries[1];
        int time = Integer.parseInt(entries[2]);
        int manpower = Integer.parseInt(entries[3]);

        int[] dependencyIds = new int[entries.length-5];
        int j = 0;
        for (int i = 4; i < entries.length-1; i++) {
            dependencyIds[j++] = Integer.parseInt(entries[i]); 
        }

        return new Task(id, name, time, manpower, dependencyIds);

    }

    private void generateEdges(){
        // This method genererates the Edge connections between two tasks.
        // Method is ran after the entire input file has been read
        // and all the tasks has been created.
        // Task B is dependent on A
        // There will be generated an Edge outEdge from A to B
        // and an Edge dependecyEdge from B to A

        for(Task t : projectTasks){
            if(t.getDependencies().length == 0){ continue; } // No dependencies.
            for(int id : t.getDependencies()){
                generateOutEdge(projectTasks[id-1], t);
                generateDependencyEdge(t, projectTasks[id-1]);
            }
        }
    }

    private void generateOutEdge(Task origin, Task destination){
        // Assuming function call goes: origin.addOutEdge(destination)
        origin.addOutEdge(destination);
    }
    private void generateDependencyEdge(Task origin, Task destination){
        // Assuming function call goes: origin.addDependencyEdge(destination)
        origin.addDependencyEdge(destination);
    }
    
    
    // END OF HELPER METHODS

    public Task[] getProjectTasks(){
        // Method for retrieving the projectTasks in the Project Class.
        return projectTasks;
    }
}
