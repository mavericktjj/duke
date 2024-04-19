import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Parser {
    /**
     * Parses user input and returns the corresponding command.
     *
     * @param input The user input string to be parsed.
     * @return A Command object representing the parsed command.
     */

    public static Command parse(String input) {
        String[] tokens = input.split(" ", 2);
        String commandWord = tokens[0].toLowerCase();
        String argument = tokens.length > 1 ? tokens[1] : null;
        try {
            switch (commandWord) {
                case "find":
                    try {
                        LocalDate date = parseDateTime(tokens[1]).toLocalDate();
                        return new FindCommand(date);
                    } catch (DateTimeParseException e1) {
                        return new FindCommand(argument);
                    } catch (ArrayIndexOutOfBoundsException e2) {
                        return new InvalidCommand("Please provide a valid search parameter.");
                    }
                case "list":
                    if (argument != null) {
                        try {
                            LocalDateTime date = parseDateTime(argument);
                            return new FindCommand(date);
                        } catch (DateTimeParseException e1) {
                            TaskType type = listTaskTypes(argument);
                            if (type != null) {
                                return new ListCommand(type);
                            } else {
                                return new InvalidCommand("Please enter <list> or <list TaskType>");
                            }
                        }
                    }
                    return new ListCommand(null);

                case "todo":
                    return new AddTaskCommand(new Todo(argument));
                case "deadline":
                    String[] deadlineArgs = input.split(" /by ", 2);
                    try {
                        LocalDateTime by = parseDateTime(deadlineArgs[1]);
                        return new AddTaskCommand(new Deadline(deadlineArgs[0].replace("deadline ", ""), by));
                    } catch (DateTimeParseException e1) {
                        return new InvalidCommand("Please enter a valid date syntax e.g. 15/05/2024");
                    }
                case "event":
                    String[] eventArgs = input.split("\\s+/from\\s+|\\s+/to\\s+");
                    try {
                        LocalDateTime from = parseDateTime(eventArgs[1].trim());
                        LocalDateTime to = parseDateTime(eventArgs[2].trim());
                        return new AddTaskCommand(new Event(eventArgs[0].replace("event ", ""), from, to));
                    } catch (DateTimeParseException e1) {
                        return new InvalidCommand("Please enter a valid date syntax e.g. 15/05/2024");
                    }
                case "mark":
                    return new MarkAsDoneCommand(Integer.parseInt(argument));
                case "delete":
                    return new DeleteTaskCommand(Integer.parseInt(argument));
                case "unmark":
                    return new unmarkAsDoneCommand(Integer.parseInt(argument));
                default:
                    return new InvalidCommand("Sorry, I do not understand that command");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ErrorHandling.wrongSyntax();
            return new InvalidCommand("Sorry i do not understand that command");
        } catch (NumberFormatException e1) {
            return new InvalidCommand("Please enter a valid integer for marking/deleting.");

        }
    }

    private static TaskType listTaskTypes(String argument) {
        if (argument.equals("event")) {
            return TaskType.EVENT;
        }
        if (argument.equals("deadline")) {
            return TaskType.DEADLINE;
        }
        if (argument.equals("todo")) {
            return TaskType.TODO;
        }
        return null;
    }


    public static LocalDateTime parseDateTime(String date) throws DateTimeParseException {
        List<String> dateTimePatterns = List.of("M/d/yyyy HHmm", "MM/dd/yyyy HHmm", "yyyy-MM-dd HHmm", "HHmm dd/MM/yyyy", "dd/MM/yyyy HHmm", "dd/MM/yyyy h:mm a", "dd-MM-yyyy h:mm a");
        for (String pattern : dateTimePatterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
                LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
                return dateTime;
            } catch (DateTimeParseException ignored) {
            }
        }
        List<String> datePatterns = List.of("MM/dd/yyyy", "MMMM dd yyyy", "dd MMMM yyyy", "yyyy MMMM dd", "yyyy/MM/dd", "yyyy-MM-dd", "dd/MM/yyyy", "dd/M/yyyy");

        for (String pattern : datePatterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
                LocalDate parsedDate = LocalDate.parse(date.trim(), formatter);
                return parsedDate.atStartOfDay();
            } catch (DateTimeParseException ignored) {
            }
        }

        throw new DateTimeParseException("Unable to parse the date string", date, 0);
    }

    /**
     * Parses a task data string and returns the corresponding Task object.
     *
     * @param line The task data string to be parsed.
     * @return A Task object representing the parsed task data.
     */

    public static Task parseTaskFromString(String line) {
        String[] parts = line.split("\\|");
        String taskType = parts[0].trim();
        String description = parts[1].trim();

        switch (taskType) {
            case "T":
                return new Todo(description);
            case "D":
                try {
                    LocalDateTime by = parseAsLocalDateTime(parts[3].trim());
                    return new Deadline(description, by);
                } catch (DateTimeParseException e2) {
                    System.out.println("Invalid date syntax");
                }
            case "E":
                try {
                    LocalDateTime from = parseAsLocalDateTime(parts[3].trim());
                    LocalDateTime to = parseAsLocalDateTime(parts[4].trim());
                    return new Event(description, from, to);
                } catch (DateTimeParseException e2) {
                    System.out.println("Invalid date syntax");
                }

            default:
                throw new IllegalArgumentException("Unknown task type: " + taskType);
        }
    }

    /**
     * Parses a string into a LocalDateTime object using ISO_LOCAL_DATE_TIME format.
     *
     * @param inputString The string to be parsed.
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws DateTimeParseException If the string cannot be parsed.
     */
    public static LocalDateTime parseAsLocalDateTime(String inputString) throws DateTimeParseException {
        return LocalDateTime.parse(inputString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

}