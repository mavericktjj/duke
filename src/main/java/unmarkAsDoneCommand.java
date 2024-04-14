/**
 * Represents a command to unmark a task as done in the Duke application.
 * Implements the Command interface.
 */
public class unmarkAsDoneCommand implements Command {
    private final int index;

    public unmarkAsDoneCommand(int index) {
        this.index = index;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList.get(index - 1) != null : "Task does not exist!";
        try {
            Task task = taskList.get(index - 1);
            task.unmarkAsDone();
            String concat = ui.showTaskUnmarkedAsDone(task);
            storage.saveTasks(taskList);
            return concat;
        } catch (IndexOutOfBoundsException e) {
            ErrorHandling.outOfRange();
        }
        return null;
    }
}
