package projecta.controller;
import javax.swing.*;
public class ScheduleController {
    private UserSchedule schedule;
    private ScheduleView view;

    public ScheduleController(UserSchedule schedule, ScheduleView view) {
        this.schedule = schedule;
        this.view = view;

        view.getAddButton().addActionListener(e -> addBlock());
    }
    private void add
}
