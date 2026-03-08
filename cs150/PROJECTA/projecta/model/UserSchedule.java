import java.util.ArrayList;
import java.util.List;

package projecta.model;

public class UserSchedule {
    private List<ScheduleBlock> blocks;

    public UserSchedule() {
        this.blocks = new ArrayList<>();
    }
    public void addBlock(ScheduleBlock block) {
        blocks.add(block);
    }
    public List<ScheduleBlock> getBlocks() {
        return blocks;
    }
    public boolean isBlocked(String day, int hour) {
        for (ScheduleBlock b : blocks) { 
            if (b.getDay().equals(day)) {
                if (hour >= b.getStartHour() && hour < b.getEndHour()) {
                    return true;
                }
            }
        }
        return false;
    }

}
