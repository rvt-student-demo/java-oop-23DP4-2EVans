package rvt.todo;

import java.util.ArrayList;

public class Todolist {
private ArrayList<String> tasks;
private final String FILE_NAME = "todo.csv";

public Todolist() {
    this.tasks = new ArrayList<>();
    loadFromFile();
}
// Izlasīt todo.scv failu
// Un papildināt tasks ArrayList ar datiem
// no faila
private void loadFromFile() {
    try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(FILE_NAME))) {
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            // Expecting lines like: <id>,<task>
            String[] parts = line.split(",", 2);
            if (parts.length == 2) {
                String task = parts[1].trim();
                tasks.add(task);
            } else {
                // If no ID present, use whole line as task
                tasks.add(line);
            }
        }
    } catch (java.io.FileNotFoundException e) {
        // It's fine if the file doesn't exist yet.
    } catch (java.io.IOException e) {
        e.printStackTrace();
    }
}
// Pievienot metodi TodoList klasei
// kura atgriež pēdējās aktivitātes "id" vērtību
private int getLastId() {
    int lastId = 0;
    try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(FILE_NAME))) {
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split(",", 2);
            try {
                int id = Integer.parseInt(parts[0].trim());
                if (id > lastId) lastId = id;
            } catch (Exception ex) {
                // ignore malformed id
            }
        }
    } catch (java.io.FileNotFoundException e) {
        return 0;
    } catch (java.io.IOException e) {
        e.printStackTrace();
    }
    return lastId;
}
// Pievienojot jauno aktivitāti
// Rediģet esošo add() metodi
public void add(String task) {
    this.tasks.add(task);
    int id = getLastId() + 1;
    String record = id + "," + task;
    try (java.io.FileWriter fw = new java.io.FileWriter(FILE_NAME, true);
         java.io.BufferedWriter bw = new java.io.BufferedWriter(fw);
         java.io.PrintWriter out = new java.io.PrintWriter(bw)) {
        out.println(record);
    } catch (java.io.IOException e) {
        e.printStackTrace();
    }
}
// Pievienot updateFile() metodi
// Kura atjauno/pārraksta .csv failu ar jauniem datiem
// Izmantojot esošo tasks ArrayList masīvu
private boolean updateFile() {
    try (java.io.FileWriter fw = new java.io.FileWriter(FILE_NAME, false);
         java.io.BufferedWriter bw = new java.io.BufferedWriter(fw);
         java.io.PrintWriter out = new java.io.PrintWriter(bw)) {
        for (int i = 0; i < tasks.size(); i++) {
            int id = i + 1;
            out.println(id + "," + tasks.get(i));
        }
        return true;
    } catch (java.io.IOException e) {
        e.printStackTrace();
        return false;
    }
}

// Rediģēt remove() metodi
public void remove(int id) {
    int index = id - 1; // ids are 1-based in the csv
    if (index >= 0 && index < tasks.size()) {
        tasks.remove(index);
        updateFile();
    }
}

public void printTasks() {
    if (tasks.isEmpty()) {
        System.out.println("No tasks.");
        return;
    }
    for (int i = 0; i < tasks.size(); i++) {
        System.out.println((i + 1) + ": " + tasks.get(i));
    }
}

public static void main(String[] args) {
    Todolist tl = new Todolist();
    if (args.length == 0) {
        System.out.println("Usage: java Todolist add|remove|list <task|id>");
        tl.printTasks();
        return;
    }
    String cmd = args[0];
    switch (cmd) {
        case "add":
            if (args.length < 2) {
                System.out.println("Provide a task to add.");
                break;
            }
            String task = String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length));
            tl.add(task);
            System.out.println("Added: " + task);
            break;
        case "remove":
            if (args.length < 2) {
                System.out.println("Provide the id to remove.");
                break;
            }
            try {
                int id = Integer.parseInt(args[1]);
                tl.remove(id);
                System.out.println("Removed id: " + id);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid id: " + args[1]);
            }
            break;
        case "list":
            tl.printTasks();
            break;
        default:
            System.out.println("Unknown command: " + cmd);
            System.out.println("Usage: java TodoList add|remove|list <task|id>");
    }
}
}