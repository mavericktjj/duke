/**
 * The Ui class handles interactions with the user interface of the application.
 */
public class Ui {

    /**
     * Displays a welcome message when the application starts.
     *
     * @return A welcome message.
     */
    public String showWelcomeMessage() {
        return ("Hello! I'm Maverick!" + "\n" + "What can i do for you?");
    }

    /**
     * Displays the list of tasks.
     *
     * @param taskList The task list to be displayed.
     * @return A string representing the task list.
     */
    public String showTaskList(TaskList taskList) {
        taskList.tasks.sort(new TaskComparator());
        if (taskList.size() == 0) {
            return ("No tasks added yet.");
        } else {
            StringBuilder concat = new StringBuilder("Tasks:");
            for (int i = 0; i < taskList.size(); i++) {
                int j = i + 1;
                concat.append("\n").append(j).append(". ").append(taskList.get(i));
            }
            return concat.toString();
        }
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task       The task that has been added.
     * @param totalTasks The total number of tasks after adding the new task.
     * @return A message indicating the task addition.
     */
    public String showTaskAdded(Task task, int totalTasks) {
        return "Got it, I've added this task:" + "\n" + task + "\n" + "Now you have " + totalTasks + " tasks in the list.";
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return A message indicating the task completion.
     */
    public String showTaskMarkedAsDone(Task task) {
        return "Nice! I've marked this task as done: " + "\n" + task;
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task           The task that has been deleted.
     * @param remainingTasks The number of tasks remaining after deleting the task.
     * @return A message indicating the task deletion.
     */
    public String showTaskDeleted(Task task, int remainingTasks) {
        return "Noted. I've removed this task: " + "\n" + "Now you have " + remainingTasks + " in the list.";
    }

    /**
     * Displays a goodbye message when the application exits.
     *
     * @return A goodbye message.
     */
    public String showGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays a message indicating that the user inputted an invalid command.
     *
     * @return An invalid command message.
     */
    public String showInvalidCommandMessage() {
        return "Sorry, I don't understand that command.";
    }

    /**
     * Displays a message indicating that a task description already exists in the list.
     *
     * @return A message indicating duplicate task description.
     */
    public String showDuplicateMessage() {
        return "Description already exists in the list";
    }

}