package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScheduleView extends JPanel {

    private JComboBox<String> dayBox;
    private JComboBox<Integer> startHourBox;
    private JComboBox<Integer> endHourBox;
    private JComboBox<String> typeBox;
    private JButton addButton;

    public ScheduleView() {
        Integer[] hours = new Integer[24];
        for (int i = 0; i < 24; i++) {
            hours[i] = i;
        }

        setLayout(new GridLayout(5, 2, 10, 10));

        dayBox = new JComboBox<>(new String[]{
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
        });

        startHourBox = new JComboBox<>(hours);
        endHourBox = new JComboBox<>(hours);

        typeBox = new JComboBox<>(new String[]{
            "Class", "Work", "Personal", "Other"
        });

        addButton = new JButton("Add Block");

        add(new JLabel("Day:"));
        add(dayBox);
        add(new JLabel("Start Hour:"));
        add(startHourBox);
        add(new JLabel("End Hour:"));
        add(endHourBox);
        add(new JLabel("Type:"));
        add(typeBox);
        add(new JLabel(""));
        add(addButton);
    }
    public JButton getAddButton() { return addButton; }
    public String getSelectedDay() { return (String) dayBox.getSelectedItem(); }
    public Integer getSelectedStartHour() { return (Integer) startHourBox.getSelectedItem(); }
    public Integer getSelectedEndHour() { return (Integer) endHourBox.getSelectedItem(); }
    public String getSelectedType() { return (String) typeBox.getSelectedItem(); }

}
