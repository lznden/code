package controller;

import javax.swing.JOptionPane;

import model.Task;
import model.TaskList;
import view.TaskListView;

public class TaskListController {

    private final TaskList taskList;
    private final TaskListView view;

    public TaskListController(TaskList taskList, TaskListView view) {
        this.taskList = taskList;
        this.view = view;

        view.getRefreshButton().addActionListener(e -> refresh());
        view.getSortButton().addActionListener(e -> sort());
        view.getDeleteButton().addActionListener(e -> deleteSelected());

        refresh();
    }

    private void refresh() {
        view.setTasks(taskList.getAllTasks());
    }

    private void sort() {
        taskList.sortByDueDateThenPriority();
        refresh();
    }

    private void deleteSelected() {
        Task selected = view.getSelectedTask();
        if (selected == null) {
            JOptionPane.showMessageDialog(null, "No task selected.");
            return;
        }

        taskList.removeTask(selected);
        refresh();
    }
}
