package controller;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import model.Task;
import model.TaskCategory;
import model.TaskList;
import view.AddTaskView;

public class AddTaskController {
    private final TaskList taskList;
    private final AddTaskView view;

    public AddTaskController(TaskList taskList, AddTaskView view) {
        this.taskList = taskList;
        this.view = view;

        view.getAddButton().addActionListener(e -> addTask());
    }

    private void addTask() {
        String title = view.getTitleInput();
        TaskCategory category = view.getCategoryInput();
        String minutesText = view.getMinutesInput();
        String dueDateText = view.getDueDateInput();
        int priority = view.getPriorityInput();
        String pointsText = view.getPointsInput();

        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Title cannot be empty.");
            return;
        }
        int minutes;
        int points;

        try {
            minutes = Integer.parseInt(minutesText);
            points = Integer.parseInt(pointsText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Minutes and Points must be valid integers.");
            return;
        }
        LocalDate dueDate = null;
        if (!dueDateText.isEmpty()) {
            try {
                dueDate = LocalDate.parse(dueDateText);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Due Date must be in YYYY-MM-DD format.");
                return;
            }
        }
        Task newTask = new Task(title, category, minutes, dueDate, priority, points);
        taskList.addTask(newTask);

        JOptionPane.showMessageDialog(view, "Task added successfully!");
    }
}