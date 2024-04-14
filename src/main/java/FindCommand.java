import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command to find tasks based on search parameters.
 * This command searches for tasks in the task list that match the specified search parameter.
 * Implements the Command interface, allowing it to be executed as part of the Duke application.
 */
public class FindCommand implements Command {

    private final Object searchParameter; // The search parameter used to find tasks.

    /**
     * Constructs a FindCommand object with the specified search parameter.
     *
     * @param searchParameter The search parameter used to find tasks. It can be a String representing
     *                        the description to match, or a LocalDate representing the date to match.
     */
    public FindCommand(Object searchParameter) {
        this.searchParameter = searchParameter;
    }

    /**
     * Executes the find command, searching for tasks in the task list based on the search parameter.
     * If the search parameter is a String, it searches for tasks whose descriptions contain the specified string.
     * If the search parameter is a LocalDate, it searches for tasks with deadlines or events happening on the specified date.
     * Displays matched tasks or a message indicating no matching tasks found.
     *
     * @param taskList The TaskList containing all tasks.
     * @param ui       The Ui object for interacting with the user.
     * @param storage  The Storage object for saving tasks to a file.
     * @return A string representing the result of the command execution.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> matchedTasks = findMatchingTasks(taskList);

        if (matchedTasks.isEmpty()) {
            return "No matching tasks found.";
        } else {
            return formatMatchingTasks(matchedTasks);
        }
    }

    /**
     * Finds tasks in the task list that match the search parameter.
     *
     * @param taskList The TaskList containing all tasks.
     * @return A list of tasks that match the search parameter.
     */
    private List<Task> findMatchingTasks(TaskList taskList) {
        List<Task> matchedTasks = new ArrayList<>();
        taskList.tasks.sort(new TaskComparator());

        for (Task task : taskList.tasks) {
            if (searchParameter instanceof String && task.description.contains((String) searchParameter)) {
                matchedTasks.add(task);
            } else if (searchParameter instanceof LocalDate && matchesDate(task)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }

    /**
     * Checks if the given task matches the search parameter's date.
     *
     * @param task The task to check.
     * @return True if the task's date matches the search parameter, false otherwise.
     */
    private boolean matchesDate(Task task) {
        LocalDate date = (LocalDate) searchParameter;
        if (task instanceof Deadline) {
            return date.isEqual(((Deadline) task).by.toLocalDate());
        } else if (task instanceof Event) {
            LocalDate fromDate = ((Event) task).startTime.toLocalDate();
            LocalDate toDate = ((Event) task).endTime.toLocalDate();
            return (date.isEqual(fromDate) || date.isAfter(fromDate)) && (date.isEqual(toDate) || date.isBefore(toDate));
        }
        return false;
    }

    /**
     * Formats the list of matching tasks into a string.
     *
     * @param matchedTasks The list of matching tasks.
     * @return A string representation of the matching tasks.
     */
    private String formatMatchingTasks(List<Task> matchedTasks) {
        StringBuilder stringBuilder = new StringBuilder("Matching tasks found:\n");
        for (int i = 0; i < matchedTasks.size(); i++) {
            stringBuilder.append(String.format("%d. %s\n", i + 1, matchedTasks.get(i)));
        }
        return stringBuilder.toString();
    }
}