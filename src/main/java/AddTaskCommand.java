public class AddTaskCommand implements Command {
    private final Task task;

    /**
     * Constructs an AddTaskCommand object with the specified task.
     *
     * @param task The task to be added.
     */
    public AddTaskCommand(Task task) {
        this.task = task;
    }


    /**
     * Executes the command to add the task to the task list.
     * If the task is not a duplicate, adds it to the task list, displays a confirmation message,
     * and saves the updated task list to storage. Otherwise, displays a duplicate message.
     *
     * @param taskList The task list to which the task will be added.
     * @param ui       The user interface to display messages.
     * @param storage  The storage to save the updated task list.
     * @return
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert task.description != null : "Task description must not be null";
        if (!ErrorHandling.checkDuplicate(task.description, taskList)) {
            taskList.add(task);
            String concat = ui.showTaskAdded(task, taskList.size());
            storage.saveTasks(taskList);
            return concat;
        } else {
            return ui.showDuplicateMessage();
        }

    }
}
