/**
 * Represents a command to handle invalid user input in the Duke application.
 * Implements the Command interface.
 */
public class InvalidCommand implements Command {

    String errorMsg;

    public InvalidCommand(String message) {
        this.errorMsg = message;
    }

    /**
     * Executes the command to handle invalid user input.
     * Displays a message indicating that the command is invalid.
     *
     * @param taskList The task list (not used).
     * @param ui       The user interface to display the invalid command message.
     * @param storage  The storage (not used).
     * @return
     */

    @Override
    public String execute(TaskList taskList, Ui ui, Storage stlorage) {
        return ui.showInvalidCommandMessage(this.errorMsg);

    }
}