package projecta.model;

import java.time.LocalDate;
import java.util.List;

public class NextTaskEngine {

    public Task chooseNextTask(TaskList taskList) {
        List<Task> candidates = taskList.getIncompleteTasks();
        if (candidates.isEmpty()) return null;

        candidates.sort((t1, t2) -> {
   
            LocalDate d1 = t1.getDueDate();
            LocalDate d2 = t2.getDueDate();

            if (d1 == null && d2 == null)
                return Integer.compare(t1.getPriority(), t2.getPriority());
            if (d1 == null) return 1;
            if (d2 == null) return -1;

            int cmp = d1.compareTo(d2);
            if (cmp != 0) return cmp;

            return Integer.compare(t1.getPriority(), t2.getPriority());
        });

        return candidates.get(0);
    }
}
