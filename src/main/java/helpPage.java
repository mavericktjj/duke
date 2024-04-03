public class helpPage {

    public static String show() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Welcome to Duke!\n");
        stringBuilder.append("Duke is a simple task management application.\n");
        stringBuilder.append("Here are the available commands:\n");
        stringBuilder.append("- help: shows a help page, the one you're seeing now!\n");
        stringBuilder.append("- todo <description>: Add a new todo task\n");
        stringBuilder.append("- event <description> /from <startDateTime> /to <endDateTime>: Add a new event task\n");
        stringBuilder.append("- deadline <description> /by <dueDateTime>: Add a new deadline task\n");
        stringBuilder.append("- list: List all tasks\n");
        stringBuilder.append("- find <description> or <date>: find tasks that contains the description given or tasks that occurs in range of date given\n");
        stringBuilder.append("- mark <taskNumber>: Mark a task as done\n");
        stringBuilder.append("- delete <taskNumber>: Delete a task\n");
        stringBuilder.append("- bye: Exit the application\n");

        return stringBuilder.toString();
    }
}
