package view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import model.Task;

public class TaskListView extends JPanel {

    private final DefaultListModel<Task> listModel;
    private final JList<Task> taskJList;
    private final JButton deleteButton;
    private final JButton refreshButton;
    private final JButton sortButton;

    public TaskListView() {
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        taskJList = new JList<>(listModel);
        taskJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(taskJList);

        deleteButton = new JButton("Delete Task");
        refreshButton = new JButton("Refresh");
        sortButton = new JButton("Sort by Due Date");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(deleteButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JButton getDeleteButton() { return deleteButton; }
    public JButton getRefreshButton() { return refreshButton; }
    public JButton getSortButton() { return sortButton; }

    public void setTasks(List<Task> tasks) {
        listModel.clear();
        for (Task t : tasks) {
            listModel.addElement(t);
        }
    }

    public Task getSelectedTask() {
        return taskJList.getSelectedValue();
    }
}
