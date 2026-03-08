package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.TaskCategory;

public class AddTaskView extends JPanel {

    private final JTextField titleField;
    private final JComboBox<TaskCategory> categoryBox;
    private final JTextField minutesField;
    private final JTextField dueDateField;
    private final JComboBox<Integer> priorityBox;
    private final JTextField pointsField;
    private final JButton addButton;

    public AddTaskView() {
        setLayout(new GridLayout(7, 2, 10, 10));
        titleField = new JTextField(20);
        categoryBox = new JComboBox<>(TaskCategory.values());
        minutesField = new JTextField(5);
        dueDateField = new JTextField(10);
        priorityBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        pointsField = new JTextField(5);
        addButton = new JButton("Add Task");

        add(new JLabel("Title:"));
        add(titleField);

        add(new JLabel("Category:"));
        add(categoryBox);

        add(new JLabel("Estimated Minutes:"));
        add(minutesField);

        add(new JLabel("Due Date (YYYY-MM-DD or blank):"));
        add(dueDateField);

        add(new JLabel("Priority (1= highest):"));
        add(priorityBox);

        add(new JLabel("Points:"));
        add(pointsField);

        add(new JLabel(""));
        add(addButton);
    }
    public JButton getAddButton() {
        return addButton;
    }
    public String getTitleInput() {
        return titleField.getText();
    }
    public TaskCategory getCategoryInput() {
        return (TaskCategory) categoryBox.getSelectedItem();
    }
    public String getMinutesInput() {
        return minutesField.getText();
    }
    public String getDueDateInput() {
        return dueDateField.getText();
    }
    public int getPriorityInput() {
        return (Integer) priorityBox.getSelectedItem();
    }
    public String getPointsInput() {
        return pointsField.getText();
    }


}
