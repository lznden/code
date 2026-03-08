package model;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class NextTaskEngine {

    public Task chooseNextTask(TaskList taskList, UserSchedule schedule) {

        // 1. Check if user is busy right now
        if (isUserBusyNow(schedule)) {
            return null; // or return a special "busy" task if you want
        }

        // 2. Normal logic
        List<Task> candidates = taskList.getIncompleteTasks();
        if (candidates.isEmpty()) {
            return null;
        }

        taskList.sortByDueDateThenPriority();

        for (Task t : taskList.getAllTasks()) {
            if (!t.isCompleted()) {
                return t;
            }
        }
        return null;
    }

    private boolean isUserBusyNow(UserSchedule schedule) {
        DayOfWeek day = LocalDate.now().getDayOfWeek();
        int hour = LocalTime.now().getHour();

        // Convert MONDAY → "Monday"
        String dayName = capitalize(day.toString().toLowerCase());

        return schedule.isBlocked(dayName, hour);
    }

    private String capitalize(String s) {
        return s.substring(0,1).toUpperCase() + s.substring(1);
    }
}
