import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start time and end time in the Duke application.
 * Extends the Task class.
 */
public class Event extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    /**
     * Constructs an Event object with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param startTime   The start time of the event.
     * @param endTime     The end time of the event.
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description, TaskType.EVENT);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a string representation of the Event object.
     * Format: [E][Status] Description (from: Start Time to: End Time)
     *
     * @return A string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDateTime(startTime) + " to: " + formatDateTime(endTime) + ")";
    }

    /**
     * Formats the LocalDateTime object into a string with a specific format.
     *
     * @param dateTime The LocalDateTime object to be formatted.
     * @return A string representation of the LocalDateTime object in the specified format.
     */
    private String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
    }
}