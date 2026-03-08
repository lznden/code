package projecta.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.*;

import projecta.model.NextTaskEngine;
import projecta.model.PointTracker;
import projecta.model.Task;
import projecta.model.TaskCategory;
import projecta.model.TaskList;

public class MainFrame extends JFrame {

    private final TaskList taskList;
    private final NextTaskEngine engine;
    private final PointTracker points;

    private JLabel nextTaskLabel;
    private JLabel pointsLabel;
//gui creation and main method for testing
    public MainFrame(TaskList taskList, NextTaskEngine engine, PointTracker points) {
        super();
        this.taskList = taskList;
        this.engine = engine;
        this.points = points;

        setTitle("Task Planner");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setupUI();
    }

    private void setupUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        nextTaskLabel = new JLabel("Next Task: (none)", SwingConstants.CENTER);
        nextTaskLabel.setFont(new Font("Arial", Font.BOLD, 16));

        pointsLabel = new JLabel("Points: 0", SwingConstants.CENTER);

        JButton refreshButton = new JButton("Refresh Next Task");
        JButton completeButton = new JButton("Complete Task");
        JButton scheduleButton = new JButton("Edit Schedule");

        scheduleButton.addActionListener(e -> {
            ScheduleView scheduleView = new ScheduleView();
            ScheduleController controller = new ScheduleController(userSchedule, scheduleView);

            JFrame scheduleFrame = new JFrame("Edit Schedule");
            scheduleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            scheduleFrame.setSize(300, 300);
            scheduleFrame.add(scheduleView);
            scheduleFrame.setVisible(true);
        });
        refreshButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updateNextTask();
            }
        });

        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                completeCurrentTask();
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshButton);
        buttonPanel.add(completeButton);

        panel.add(nextTaskLabel, BorderLayout.NORTH);
        panel.add(pointsLabel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void updateNextTask() {
        Task next = engine.chooseNextTask(taskList);
        if (next == null) {
            nextTaskLabel.setText("Next Task: (none)");
        } else {
            nextTaskLabel.setText("Next Task: " + next.getTitle());
        }
        updatePoints();
    }

    private void completeCurrentTask() {
        Task next = engine.chooseNextTask(taskList);
        if (next != null) {
            next.markCompleted();
            points.addPoints(next.getPoints());
            updateNextTask();
        }
    }

    private void updatePoints() {
        pointsLabel.setText("Points: " + points.getPoints());
    }

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        PointTracker pointTracker = new PointTracker();
        NextTaskEngine nextTaskEngine = new NextTaskEngine();

        // Add some sample tasks
        taskList.addTask(new Task("CS HOMEWORK", TaskCategory.HOMEWORK, 60, LocalDate.now().plusDays(1), 1, 50));
        taskList.addTask(new Task("PLAY A GAME WITH FRIENDS", TaskCategory.FUN, 60, LocalDate.now().plusDays(1), 5, 0));
        taskList.addTask(new Task("LAUNDRY", TaskCategory.CHORE, 120, LocalDate.now().plusDays(4), 2, 80));
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(taskList, nextTaskEngine, pointTracker);
            frame.setVisible(true);
        });
    }
}
