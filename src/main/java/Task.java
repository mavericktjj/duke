public class Task {
    protected String description;

    protected TaskType type;
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description, TaskType type) {
        assert description != null : "description cannot be empty!";
        assert type != null : "task type is null";
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Marks the task as done.
     */

    public void markAsDone() {
        this.isDone = true;
    }


    /**
     * Returns a string representation of the Task object.
     *
     * @return A string representing the task, including its completion status and description.
     */

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[] ") + description;
    }

}