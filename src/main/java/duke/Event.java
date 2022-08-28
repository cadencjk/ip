package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a event task which will occur on a certain date.
 */
public class Event extends Task {
    private LocalDate atWhen = null;

    public Event() {
        super();
    }

    /**
     * Adds the description and the deadline of a event.
     * The userInput argument must contain a description of the event, followed by a
     * date in the format yyyy-mm-dd or yyyy/mm/dd.
     * <p>
     * If the date format is not recognised, or the description is empty, the function will throw a DukeException.
     *
     * @param userInput a String containing the description and date of an event
     * @throws DukeException
     */
    public void addName(String userInput) throws DukeException {
        if (userInput.length() <= 6) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        }
        int index = userInput.indexOf("/at") - 1;
        if (index <= 5) {
            throw new DukeException("OOPS!!! Please indicate when the event is happening with '/at'.");
        }
        super.addName(userInput.substring(6, index));
        this.atWhen = LocalDate.parse(userInput.substring(index + 5).replace('/', '-'));
    }

    /**
     * Returns a String in a format that will be stored in the specified directory.
     * The String will contain the type of task, the current status (marked or unmarked) of the task and
     * the description of the task.
     *
     * @return the details of the task for Storage
     */
    @Override
    public String getTask() {
        return String.format("E | " + super.getTask() + " | " + this.atWhen);
    }

    /**
     * Returns a String that will display the task.
     * The String will contain the type of task, the current status (marked or unmarked) of
     * the task and the description of the task.
     *
     * @return the details of the task for display
     */
    @Override
    public String getStatus() {
        return String.format("[E]%s (at: %s)", super.getStatus(),
                this.atWhen.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
