
package projecta.model;

import java.time.LocalDate;

public class PlannerTest {

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        PointTracker pointTracker = new PointTracker();
        NextTaskEngine nextTaskEngine = new NextTaskEngine();

        taskList.addTask(new Task("CS HOMEWORK", TaskCategory.HOMEWORK, 60, LocalDate.now().plusDays(1), 1, 50));
        taskList.addTask(new Task("PLAY A GAME WITH FRIENDS", TaskCategory.FUN, 60, LocalDate.now().plusDays(1), 5, 0));
        taskList.addTask(new Task("LAUNDRY", TaskCategory.CHORE, 120, LocalDate.now().plusDays(4), 2, 80));

        Task next = nextTaskEngine.chooseNextTask(taskList);
        System.out.println("Next task: " + next);

        if (next != null) {
            next.markCompleted();
            pointTracker.addPoints(next.getPoints());
        }
        System.out.println("Points: " + pointTracker.getPoints());
        System.out.println(taskList.getAllTasks());
    }
}
