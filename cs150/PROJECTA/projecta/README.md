# Task Planner Project

A Java Swing application for task planning and reward management.

## Quick Start

1. **Install JDK 8+** (see SETUP_INSTRUCTIONS.md if you don't have it)
2. **Build the project**: Press `Ctrl+Shift+B` in VS Code
3. **Run the application**: The build script will automatically launch it

## Project Structure

```
├── model/               # Core business logic
│   ├── Task.java       # Individual task data
│   ├── TaskList.java   # Collection management
│   ├── NextTaskEngine.java  # Smart task selection
│   ├── UserSchedule.java    # Schedule management
│   ├── PointTracker.java    # Points system
│   ├── RewardStore.java     # Reward inventory
│   └── ...
├── view/                # Swing GUI
│   ├── MainFrame.java   # Main window (entry point)
│   ├── AddTaskView.java # Add task dialog
│   ├── TaskListView.java # Task list display
│   ├── ScheduleView.java # Schedule editor
│   └── RewardStoreView.java # Reward display
├── controller/          # Event handlers
│   ├── AddTaskController.java
│   ├── TaskListController.java
│   ├── ScheduleController.java
│   └── RewardStoreController.java
└── target/classes/      # Compiled files (auto-generated)
```

## Features

- ✓ Add, complete, and track tasks
- ✓ Categorize tasks (Homework, Chore, Fun, etc.)
- ✓ Earn points for completing tasks
- ✓ View rewards available for redeem
- ✓ Manage schedule with time blocks
- ✓ Smart next task recommendation

## Build Commands

### PowerShell
```powershell
.\build.ps1
```

### Command Prompt  
```cmd
compile.bat
```

### Manual
```bash
javac -d target/classes -encoding UTF-8 model/*.java controller/*.java view/*.java
java -cp target/classes view.MainFrame
```

## Requirements

- Java Development Kit (JDK) 8 or later
- Windows 7+ / macOS / Linux

## No External Dependencies

This project uses only the Java standard library (Swing for GUI). No Maven, no additional libraries needed.
