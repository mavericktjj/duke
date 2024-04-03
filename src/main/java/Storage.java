import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where tasks are stored.
     */

    public Storage(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Loads tasks from a file and adds them to the given task list.
     *
     * @param filePath The file path to load tasks from.
     * @param taskList The task list to which loaded tasks will be added.
     */

    public static void loadTasksFromFile(String filePath, TaskList taskList) {
        File file = new File(filePath);
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Task task = Parser.parseTaskFromString(line);
                    taskList.add(task);


                }
            } catch (IOException e) {
                System.out.println("Error reading the file");
            }
        }


    }


    /**
     * Saves tasks from the given task list to the file.
     *
     * @param taskList The task list containing tasks to be saved.
     */

    public void saveTasks(TaskList taskList) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : taskList.tasks) {
                String data = TaskList.toFileStrings(task);
                writer.write(data + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file");
        }
    }
}