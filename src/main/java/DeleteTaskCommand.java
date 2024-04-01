public class DeleteTaskCommand implements Command {
    private final int index;

    /**
     * Constructs a DeleteTaskCommand object with the specified index.
     *
     * @param index The index of the task to be deleted.
     */

    public DeleteTaskCommand(int index) {
        this.index = index;
    }


    /**
     * Executes the command to delete the task from the task list.
     * If the index is valid, deletes the task at the specified index,
     * displays a deletion confirmation message, and saves the updated task list to storage.
     * If the index is out of range, displays an error message.
     *
     * @param taskList The task list from which the task will be deleted.
     * @param ui       The user interface to display messages.
     * @param storage  The storage to save the updated task list.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task deletedTask = taskList.remove(index - 1);
            ui.showTaskDeleted(deletedTask, taskList.size());
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            ErrorHandling.outOfRange();
        }
    }
}
