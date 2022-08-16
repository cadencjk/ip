import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        List<Task> list = new ArrayList<Task>();

        System.out.println(reply("Hello! I'm Duke. What can I do for you?"));
        Scanner userInput = new Scanner(System.in);
        while (true) {
            Task newTask = null;
            String userText = userInput.nextLine();
            boolean understood = false;
            if (userText.equals("bye")) {
                System.out.println(reply("Bye. Hope to see you again soon!"));
                break;
            } else if (userText.equals("list")) {
                System.out.println(reply(getList(list)));
                understood = true;
            } else if (userText.length() >= 4 ) {
                String commandWord = userText.substring(0,4);
                if (commandWord.equals("mark")) {
                    Task selectedTask = list.get(Integer.valueOf(userText.substring(5)) - 1);
                    selectedTask.markAsDone();
                    System.out.println(reply("Nice! I've marked this task as done:\n" + selectedTask.getStatus()));
                    understood = true;
                } else if (commandWord.equals("todo")) {
                    newTask = new ToDo();
                    understood = true;
                }

                if (userText.length() >= 6 && userText.substring(0, 6).equals("unmark")) {

                    Task selectedTask = list.get(Integer.valueOf(userText.substring(7)) - 1);
                    selectedTask.markAsUndone();
                    System.out.println(reply("OK, I've marked this task as not done yet:\n" + selectedTask.getStatus()));
                    understood = true;
                }

                if (userText.length() >= 8 && userText.substring(0, 8).equals("deadline")) {
                    newTask = new DeadLine();
                    understood = true;
                }

                if (userText.length() >= 5 && userText.substring(0, 5).equals("event")) {
                    newTask = new Event();
                    understood = true;
                }

                if (userText.length() >= 6 && userText.substring(0, 6).equals("delete")) {
                    Task selectedTask = list.remove(Integer.valueOf(userText.substring(7)) - 1);
                    System.out.println(reply("Noted. I've removed this task:\n" + selectedTask.getStatus()
                            + String.format("\nNow you have %d tasks in the list.", list.size())));
                    understood = true;
                }


            }

            if (understood == false) {
                System.out.println(reply("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
            }

            if (newTask != null) {
                try {
                    newTask.addName(userText);
                    list.add(newTask);
                    System.out.println(reply("Got it. I've added this task:\n" + newTask.getStatus() +
                        String.format("\nNow you have %d tasks in the list.", list.size())));
                } catch (Exception e) {
                    System.out.println(reply(e.getMessage()));
                }
            }
        }
    }

    public static String getList(List<Task> list) {
        String listString = "";
        for (int i = 0; i < list.size(); i++) {
            listString += String.format("%d. %s", i + 1, list.get(i).getStatus());
            if (i != list.size() - 1) {
                listString += "\n";
            }
        }
        return listString;
    }

    public static String reply(String s) {
        String horizontalLine = "____________________________________________________________\n";
        return horizontalLine + s +'\n' + horizontalLine;
    }
}
