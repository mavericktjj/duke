public class HelpPage {

    public static String show() {

        String stringBuilder = "Welcome to Duke!\n" +
                "Duke is a simple task management application.\n" +
                "Here are the available commands:\n" +
                "- help: shows a help page, the one you're seeing now!\n" +
                "- todo <description>: Add a new todo task\n" +
                "- event <description> /from <startDateTime> /to <endDateTime>: Add a new event task\n" +
                "- deadline <description> /by <dueDateTime>: Add a new deadline task\n" +
                "- list: List all tasks\n || list<TaskType>" +
                "- find <description> or <date>: find tasks that contains the description given or tasks that occurs in range of date given\n" +
                "- mark <taskNumber>: Mark a task as done\n" +
                "- unmark <taskNumber>: Unmark a task as done\n" +
                "- delete <taskNumber>: Delete a task\n" +
                "- bye: Exit the application\n";

        return stringBuilder;
    }
}
