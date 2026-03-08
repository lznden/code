package controller;
import javax.swing.JOptionPane;

import model.ScheduleBlock;
import model.UserSchedule;
import view.ScheduleView;
public class ScheduleController {
    private UserSchedule schedule;
    private ScheduleView view;

    public ScheduleController(UserSchedule schedule, ScheduleView view) {
        this.schedule = schedule;
        this.view = view;

        view.getAddButton().addActionListener(e -> addBlock());
    }
    private void addBlock() {
        String day = view.getSelectedDay();
        Integer startHour = view.getSelectedStartHour();
        Integer endHour = view.getSelectedEndHour();
        String type = view.getSelectedType();

        if (endHour <= startHour) {
            JOptionPane.showMessageDialog(view, "End hour must be after start hour.", "Invalid Time", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ScheduleBlock block = new ScheduleBlock(day, startHour, endHour, type);
        schedule.addBlock(block);

        JOptionPane.showMessageDialog(null,"Added: " + block.toString(), "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
