import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a Deadline object with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline object.
     * Format: [D][Status] Description (by: Deadline)
     *
     * @return A string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")) + ")";
    }
}
