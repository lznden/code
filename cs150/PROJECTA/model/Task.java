package model;

import java.time.LocalDate;

public class Task {

    private final String title;
    private final TaskCategory category;
    private final int estimatedMinutes;
    private final LocalDate dueDate;  // able to be null
    private final int priority;   // 1-5, 1 highest
    private final int points; //points earned on task completion
    private boolean completed;

    public Task(String title,
            TaskCategory category,
            int estimatedMinutes,
            LocalDate dueDate,
            int priority,
            int points) {
        this.title = title;
        this.category = category;
        this.estimatedMinutes = estimatedMinutes;
        this.dueDate = dueDate;
        this.priority = priority;
        this.points = points;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public int getEstimatedMinutes() {
        return estimatedMinutes;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public int getPoints() {
        return points;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return String.format("%s (%s, %d min, priority %d, %d pts)",
                title, category, estimatedMinutes, priority, points);
    }
}
