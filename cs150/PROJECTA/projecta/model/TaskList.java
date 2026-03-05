package projecta.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskList {

    private List<Task> tasks; //ensures that the list only contains task objects

    public TaskList() {
        this.tasks = new ArrayList<>(); // initializes an empty list that can be resized
    }

    public void addTask(Task task) {
        if (task != null) {
            tasks.add(task);
        }
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public List<Task> getAllTasks() {
        return Collections.unmodifiableList(tasks); //prevents external modification of the task list
    }

    public List<Task> getIncompleteTasks() {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (!t.isCompleted()) {
                result.add(t);
            }
        }
        return result;
    }

    public void sortByDueDateThenPriority() {
        tasks.sort((t1, t2) -> {
            LocalDate d1 = t1.getDueDate();
            LocalDate d2 = t2.getDueDate();

            if (d1 == null && d2 == null) {
                return Integer.compare(t1.getPriority(), t2.getPriority());
            }
            if (d1 == null) {
                return 1;
            }

            if (d2 == null) {
                return -1;
            }

            int cmp = d1.compareTo(d2);
            if (cmp != 0) {
                return cmp;
            }

            return Integer.compare(t1.getPriority(), t2.getPriority());
        });
    }
}
