package projecta.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import projecta.model.NextTaskEngine;

public class MainFrame extends JFrame {
    private TaskList taskList; 
    private NextTaskEngine engine;
    private PointTracker points;

    private JLabel nextTaskLabel;
    private JLabel pointsLabel;

    public MainFrame(TaskList taskList, NextTaskEngine engine, PointTracker points)
    this.taskList = taskList;
    this.engine = engine;
    this.points = points;

    setTitle("Task Planner");
    setSize(400,200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    
    setupUI();

}
