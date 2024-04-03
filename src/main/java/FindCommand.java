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
     * @return
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        // Create a list to store matched tasks
        List<Task> matchedTasks = new ArrayList<>();

        // Sort the task list using a custom comparator
        taskList.tasks.sort(new TaskComparator());

        // Check if the task list is empty
        if (taskList.size() == 0) {
            System.out.println("No tasks added yet.");
        } else {
            // Iterate through the task list to find matching tasks
            for (int i = 0; i < taskList.size(); i++) {
                if (searchParameter instanceof String) {
                    // If the search parameter is a String, search by description
                    String inputDescription = (String) this.searchParameter;
                    if (taskList.get(i).description.contains(inputDescription)) {
                        matchedTasks.add(taskList.get(i));
                    }
                }
                if (searchParameter instanceof LocalDate) {
                    // If the search parameter is a LocalDate, search by deadline or event date
                    if (taskList.get(i) instanceof Deadline) {
                        LocalDate date = (LocalDate) this.searchParameter;
                        int comparison = date.compareTo(((Deadline) taskList.get(i)).by.toLocalDate());
                        if (comparison == 0) {
                            matchedTasks.add(taskList.get(i));
                        }
                    }
                    if (taskList.get(i) instanceof Event) {
                        LocalDate fromDate = ((Event) taskList.get(i)).startTime.toLocalDate();
                        LocalDate toDate = ((Event) taskList.get(i)).endTime.toLocalDate();
                        LocalDate date = (LocalDate) this.searchParameter;
                        boolean isInRange = (date.isEqual(fromDate) || date.isAfter(fromDate)) && (date.isEqual(toDate) || date.isBefore(toDate));

                        if (isInRange) {
                            matchedTasks.add(taskList.get(i));
                        }
                    }
                }
            }

            // Display the matched tasks or a message indicating no matching tasks found
            StringBuilder stringBuilder = new StringBuilder();
            if (!matchedTasks.isEmpty()) {
                stringBuilder.append("Matching tasks found:\n");
                for (int i = 0; i < matchedTasks.size(); i++) {
                    stringBuilder.append(String.format("%d. %s\n", i + 1, matchedTasks.get(i)));
                }
                return stringBuilder.toString();
            } else {
                stringBuilder.append("No matching tasks found.");
                return stringBuilder.toString();
            }


        }
        return null;
    }
}