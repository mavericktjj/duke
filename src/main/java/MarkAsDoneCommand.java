/**
 * Represents a command to mark a task as done in the Duke application.
 * Implements the Command interface.
 */
public class MarkAsDoneCommand implements Command {
    private final int index;

    public MarkAsDoneCommand(int index) {
        this.index = index;
    }

    /**
     * Constructs a MarkAsDoneCommand object with the specified index.
     *
     * @param index The index of the task to be marked as done.
     */
    /**
     * Executes the command to mark the task as done.
     * If the index is valid, marks the task at the specified index as done,
     * displays a confirmation message, and saves the updated task list to storage.
     * If the index is out of range, displays an error message.
     *
     * @param taskList The task list containing the task to be marked as done.
     * @param ui       The user interface to display messages.
     * @param storage  The storage to save the updated task list.
     * @return
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList.get(index-1) != null : "Task does not exist!";
        try {
            Task task = taskList.get(index - 1);
            task.markAsDone();
            String concat = ui.showTaskMarkedAsDone(task);
            storage.saveTasks(taskList);
            return concat;
        } catch (IndexOutOfBoundsException e) {
            ErrorHandling.outOfRange();
        }
        return null;
    }
}
