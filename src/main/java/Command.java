/**
 * The Command interface represents an action to be executed by the Duke application.
 * Implementing classes must define the execute method to perform the desired action.
 */
public interface Command {
    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param taskList The task list on which the command will operate.
     * @param ui       The user interface to display messages or interact with the user.
     * @param storage  The storage to read from or write to, if necessary.
     * @return
     */

    String execute(TaskList taskList, Ui ui, Storage storage);
}