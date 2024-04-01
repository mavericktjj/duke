/**
 * Represents a command to list all tasks in the Duke application.
 * Implements the Command interface.
 */
public class ListCommand implements Command {

    /**
     * Executes the command to list all tasks.
     * Displays the list of tasks through the user interface.
     *
     * @param taskList The task list to be listed.
     * @param ui       The user interface to display the list of tasks.
     * @param storage  The storage (not used).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        ui.showTaskList(taskList);
    }


}