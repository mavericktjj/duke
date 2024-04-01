import java.util.ArrayList;
import java.util.List;

public class TaskList {
    final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */

    public TaskList() {
        this.tasks = new ArrayList<>();
    }


    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task at the specified index from the task list.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */

    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index from the task list.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */

    public Task get(int index) {
        return tasks.get(index);
    }


    /**
     * Converts a Task object to a string representation suitable for writing to a file.
     *
     * @param task The task to be converted.
     * @return A string representation of the task for writing to a file.
     */
    public static String toFileStrings(Task task) {
        if (task instanceof Deadline deadline) {
            return "D|" + deadline.description + "|" + deadline.isDone + "|" + deadline.by;
        } else if (task instanceof Event event) {
            return "E|" + event.description + "|" + event.isDone + "|" + event.startTime + "|" + event.endTime;
        } else if (task instanceof Todo todo) {
            return "T|" + todo.description + "|" + todo.isDone;
        } else {
            return "";
        }
    }
}