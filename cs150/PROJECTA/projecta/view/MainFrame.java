package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import controller.AddTaskController;
import controller.RewardStoreController;
import controller.ScheduleController;
import controller.TaskListController;
import model.NextTaskEngine;
import model.PointTracker;
import model.Reward;
import model.RewardStore;
import model.Task;
import model.TaskCategory;
import model.TaskList;
import model.UserSchedule;

public class MainFrame extends JFrame {

    private final TaskList taskList;
    private final NextTaskEngine engine;
    private final PointTracker points;
    private final UserSchedule userSchedule;

    private final JFrame addTaskFrame;
    private final JFrame scheduleFrame;

    private final RewardStoreView rewardStoreView;
    private final TaskListView taskListView;
    
    @SuppressWarnings("unused")
    private final AddTaskController addTaskController;
    @SuppressWarnings("unused")
    private final ScheduleController scheduleController;
    @SuppressWarnings("unused")
    private final TaskListController taskListController;
    @SuppressWarnings("unused")
    private final RewardStoreController rewardStoreController;

    private JLabel nextTaskLabel;
    private JLabel pointsLabel;

    // refreshing through tasks
    private List<Task> refreshCandidates = new ArrayList<>();
    private int refreshIndex = -1;
    private boolean canRefresh = true;

    public MainFrame(TaskList taskList, NextTaskEngine engine, PointTracker points, UserSchedule userSchedule,
            AddTaskView addTaskView, AddTaskController addTaskController,
            ScheduleView scheduleView, ScheduleController scheduleController,
            TaskListView taskListView, TaskListController taskListController,
            RewardStoreView rewardStoreView, RewardStoreController rewardStoreController) {
        super();
        this.taskList = taskList;
        this.engine = engine;
        this.points = points;
        this.userSchedule = userSchedule;
        this.rewardStoreView = rewardStoreView;
        this.taskListView = taskListView;
        this.addTaskController = addTaskController;
        this.scheduleController = scheduleController;
        this.taskListController = taskListController;
        this.rewardStoreController = rewardStoreController;

        setTitle("Task Planner");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.addTaskFrame = new JFrame("Add New Task");
        this.addTaskFrame.setLocationRelativeTo(null);
        this.addTaskFrame.setSize(350, 350);
        this.addTaskFrame.add(addTaskView);

        this.scheduleFrame = new JFrame("Edit Schedule");
        this.scheduleFrame.setLocationRelativeTo(null);
        this.scheduleFrame.setSize(300, 300);
        this.scheduleFrame.add(scheduleView);

        setupUI();
    }

    private void setupUI() {
        Font modernFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font boldFont = new Font("Segoe UI", Font.BOLD, 16);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 18);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new javax.swing.border.EmptyBorder(15, 15, 15, 15));

        // Top panel for next task and points
        JPanel topPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        topPanel.setBorder(new javax.swing.border.EmptyBorder(0, 0, 10, 0));

        nextTaskLabel = new JLabel("Next Task: (none)", SwingConstants.CENTER);
        nextTaskLabel.setFont(titleFont);
        nextTaskLabel.setForeground(new java.awt.Color(0, 123, 255)); // Blue color

        pointsLabel = new JLabel("Points: 0", SwingConstants.CENTER);
        pointsLabel.setFont(boldFont);
        pointsLabel.setForeground(new java.awt.Color(34, 139, 34)); // Green color

        topPanel.add(nextTaskLabel);
        topPanel.add(pointsLabel);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBorder(new javax.swing.border.EmptyBorder(10, 0, 10, 0));

        JButton refreshButton = new JButton("Refresh Next Task");
        JButton completeButton = new JButton("Complete Task");
        JButton scheduleButton = new JButton("Edit Schedule");
        JButton addTaskButton = new JButton("Add Task");

        refreshButton.setFont(modernFont);
        completeButton.setFont(modernFont);
        scheduleButton.setFont(modernFont);
        addTaskButton.setFont(modernFont);

        // Style buttons with modern look
        java.awt.Color buttonColor = new java.awt.Color(70, 130, 180);
        refreshButton.setBackground(buttonColor);
        refreshButton.setForeground(java.awt.Color.WHITE);
        completeButton.setBackground(buttonColor);
        completeButton.setForeground(java.awt.Color.WHITE);
        scheduleButton.setBackground(buttonColor);
        scheduleButton.setForeground(java.awt.Color.WHITE);
        addTaskButton.setBackground(buttonColor);
        addTaskButton.setForeground(java.awt.Color.WHITE);

        refreshButton.setFocusPainted(false);
        completeButton.setFocusPainted(false);
        scheduleButton.setFocusPainted(false);
        addTaskButton.setFocusPainted(false);

        addTaskButton.addActionListener(e -> {
            addTaskFrame.setVisible(true);
        });

        scheduleButton.addActionListener(e -> {
            scheduleFrame.setVisible(true);
        });
        
        refreshButton.addActionListener(e -> refreshNextTask());

        completeButton.addActionListener(e -> completeCurrentTask());

        buttonPanel.add(refreshButton);
        buttonPanel.add(completeButton);
        buttonPanel.add(scheduleButton);
        buttonPanel.add(addTaskButton);

        // Side panels for lists
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(new javax.swing.border.TitledBorder("Reward Store"));
        JScrollPane rewardScroll = new JScrollPane(rewardStoreView);
        rewardScroll.setBorder(null);
        leftPanel.add(rewardScroll, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(new javax.swing.border.TitledBorder("Task List"));
        JScrollPane taskScroll = new JScrollPane(taskListView);
        taskScroll.setBorder(null);
        rightPanel.add(taskScroll, BorderLayout.CENTER);

        // Split pane for left and right
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(300);
        splitPane.setResizeWeight(0.5);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(splitPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void updateNextTask() {
        Task next = engine.chooseNextTask(taskList, userSchedule);
        // refreshCandidates should mirror engine result
        refreshCandidates.clear();
        taskList.sortByDueDateThenPriority();
        for (Task t : taskList.getAllTasks()) {
            if (!t.isCompleted()) {
                refreshCandidates.add(t);
            }
        }
        refreshIndex = (next == null ? -1 : refreshCandidates.indexOf(next));
        // allow refresh again now that task changed
        canRefresh = true;

        if (next == null) {
            nextTaskLabel.setText("Next Task: (none)");
        } else {
            nextTaskLabel.setText("Next Task: " + next.getTitle());
        }
        updatePoints();
    }

    private void completeCurrentTask() {
        Task next = engine.chooseNextTask(taskList, userSchedule);
        if (next != null) {
            next.markCompleted();
            points.addPoints(next.getPoints());
            updateNextTask();
        }
    }

    private void updatePoints() {
        pointsLabel.setText("Points: " + points.getPoints());
    }

    private void refreshNextTask() {
        // recalc candidate list if empty or all tasks completed
        taskList.sortByDueDateThenPriority();
        List<Task> list = new ArrayList<>();
        for (Task t : taskList.getAllTasks()) {
            if (!t.isCompleted()) {
                list.add(t);
            }
        }
        if (list.isEmpty()) {
            refreshCandidates.clear();
            refreshIndex = -1;
            nextTaskLabel.setText("Next Task: (none)");
            return;
        }
        // if candidates changed in size or content, reset
        if (!refreshCandidates.equals(list)) {
            refreshCandidates = new ArrayList<>(list);
            refreshIndex = -1;
        }
        if (!canRefresh) {
            return; // ignore additional clicks until task completes
        }
        // advance index
        refreshIndex++;
        if (refreshIndex >= refreshCandidates.size()) {
            refreshIndex = 0;
        }
        Task next = refreshCandidates.get(refreshIndex);
        nextTaskLabel.setText("Next Task: " + next.getTitle());
        updatePoints();
        canRefresh = false;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException e) {
            e.getMessage();
        }

        TaskList taskList = new TaskList();
        PointTracker pointTracker = new PointTracker();
        NextTaskEngine nextTaskEngine = new NextTaskEngine();
        UserSchedule userSchedule = new UserSchedule();
        RewardStore rewardStore = new RewardStore();

        // Add some sample tasks
        taskList.addTask(new Task("CS HOMEWORK", TaskCategory.HOMEWORK, 60, LocalDate.now().plusDays(1), 1, 50));
        taskList.addTask(new Task("PLAY A GAME WITH FRIENDS", TaskCategory.FUN, 60, LocalDate.now().plusDays(1), 5, 0));
        taskList.addTask(new Task("LAUNDRY", TaskCategory.CHORE, 120, LocalDate.now().plusDays(4), 2, 80));

        // Add some sample rewards
        rewardStore.addReward(new Reward("Extra Screen Time", 100));
        rewardStore.addReward(new Reward("Favorite Snack", 50));
        rewardStore.addReward(new Reward("Movie Night", 200));

        // Create views and controllers
        AddTaskView addTaskView = new AddTaskView();
        AddTaskController addTaskController = new AddTaskController(taskList, addTaskView);
        ScheduleView scheduleView = new ScheduleView();
        ScheduleController scheduleController = new ScheduleController(userSchedule, scheduleView);
        TaskListView taskListView = new TaskListView();
        TaskListController taskListController = new TaskListController(taskList, taskListView);
        RewardStoreView rewardStoreView = new RewardStoreView();
        RewardStoreController rewardStoreController = new RewardStoreController(rewardStore, rewardStoreView, pointTracker);

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(taskList, nextTaskEngine, pointTracker, userSchedule,
                    addTaskView, addTaskController, scheduleView, scheduleController,
                    taskListView, taskListController, rewardStoreView, rewardStoreController);
            frame.setVisible(true);
        });
    }
}
