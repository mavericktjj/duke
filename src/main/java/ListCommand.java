import java.time.LocalDate;

/**
 * Represents a command to list all tasks in the Duke application.
 * Implements the Command interface.
 */
public class ListCommand implements Command {

    private final TaskType type;

    public ListCommand(TaskType type) {
        this.type = type;

    }

    /**
     * Executes the command to list all tasks.
     * Displays the list of tasks through the user interface.
     *
     * @param taskList The task list to be listed.
     * @param ui       The user interface to display the list of tasks.
     * @param storage  The storage (not used).
     * @return
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.type != null) {
            return ui.showTaskTypeList(taskList, this.type);

        }
        return ui.showTaskList(taskList);

    }


}