import java.util.ArrayList;
import java.util.Scanner;

// Task class
class Task {
    private int id;
    private String title;
    private String description;
    private boolean completed;

    // Constructor
    public Task(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = false;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    // Mark task as completed
    public void markCompleted() {
        completed = true;
    }

    // Display task
    public void displayTask() {
        String status = completed ? "Completed" : "Pending";

        System.out.println("----------------------------------------");
        System.out.println("ID          : " + id);
        System.out.println("Title       : " + title);
        System.out.println("Description : " + description);
        System.out.println("Status      : " + status);
        System.out.println("----------------------------------------");
    }
}


// Main application class
public class Main {

    static ArrayList<Task> tasks = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int nextId = 1;

    public static void main(String[] args) {

        boolean running = true;

        System.out.println("========================================");
        System.out.println("       JAVA TO-DO LIST APPLICATION");
        System.out.println("========================================");

        while (running) {

            displayMenu();

            int choice = getIntegerInput("Enter your choice: ");

            switch (choice) {

                case 1:
                    addTask();
                    break;

                case 2:
                    viewTasks();
                    break;

                case 3:
                    completeTask();
                    break;

                case 4:
                    deleteTask();
                    break;

                case 5:
                    searchTask();
                    break;

                case 6:
                    running = false;
                    System.out.println("\nThank you for using the To-Do List!");
                    System.out.println("Program closed successfully.");
                    break;

                default:
                    System.out.println("\nInvalid choice!");
                    System.out.println("Please select an option from 1 to 6.");
            }
        }

        scanner.close();
    }


    // Display main menu
    public static void displayMenu() {

        System.out.println("\n========================================");
        System.out.println("              TO-DO MENU");
        System.out.println("========================================");
        System.out.println("1. Add Task");
        System.out.println("2. View Tasks");
        System.out.println("3. Mark Task as Completed");
        System.out.println("4. Delete Task");
        System.out.println("5. Search Task");
        System.out.println("6. Exit");
        System.out.println("========================================");
    }


    // Add a new task
    public static void addTask() {

        System.out.println("\n---------- ADD TASK ----------");

        System.out.print("Enter task title: ");
        String title = scanner.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("Task title cannot be empty.");
            return;
        }

        System.out.print("Enter task description: ");
        String description = scanner.nextLine().trim();

        Task task = new Task(nextId, title, description);

        tasks.add(task);
        nextId++;

        System.out.println("\nTask added successfully!");
        System.out.println("Task ID: " + task.getId());
    }


    // View all tasks
    public static void viewTasks() {

        System.out.println("\n---------- ALL TASKS ----------");

        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        for (Task task : tasks) {
            task.displayTask();
        }
    }


    // Mark a task as completed
    public static void completeTask() {

        System.out.println("\n------ COMPLETE TASK ------");

        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        int id = getIntegerInput("Enter task ID: ");

        Task task = findTaskById(id);

        if (task == null) {
            System.out.println("Task with ID " + id + " not found.");
            return;
        }

        if (task.isCompleted()) {
            System.out.println("This task is already completed.");
        } else {
            task.markCompleted();
            System.out.println("Task marked as completed successfully!");
        }
    }


    // Delete a task
    public static void deleteTask() {

        System.out.println("\n--------- DELETE TASK ---------");

        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        int id = getIntegerInput("Enter task ID: ");

        Task task = findTaskById(id);

        if (task == null) {
            System.out.println("Task with ID " + id + " not found.");
            return;
        }

        tasks.remove(task);

        System.out.println("Task deleted successfully!");
    }


    // Search for a task
    public static void searchTask() {

        System.out.println("\n---------- SEARCH TASK ----------");

        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine().trim().toLowerCase();

        if (keyword.isEmpty()) {
            System.out.println("Search keyword cannot be empty.");
            return;
        }

        boolean found = false;

        for (Task task : tasks) {

            if (task.getTitle().toLowerCase().contains(keyword)
                    || task.getDescription().toLowerCase().contains(keyword)) {

                task.displayTask();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching tasks found.");
        }
    }


    // Find task by ID
    public static Task findTaskById(int id) {

        for (Task task : tasks) {

            if (task.getId() == id) {
                return task;
            }
        }

        return null;
    }


    // Get valid integer input
    public static int getIntegerInput(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println("Invalid input!");
                System.out.println("Please enter a valid number.");
            }
        }
    }
}