public class Todo extends Task {

    /**
     * Constructs a Todo object with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns a string representation of the Todo object.
     *
     * @return A string representing the todo task, including its completion status and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}