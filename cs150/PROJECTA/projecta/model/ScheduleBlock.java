package model;

public class ScheduleBlock {
    private String day;
    private int startHour;
    private int endHour;
    private String type;

    public ScheduleBlock(String day, int startHour, int endHour, String type) {
        this.day = day;
        this.startHour = startHour;
        this.endHour = endHour;
        this.type = type;
    } 
    public String getDay() { return day; }
    public int getStartHour() { return startHour; }
    public int getEndHour() { return endHour; }
    public String getType() { return type; }

    @Override
    public String toString() {
        return day + ": " + startHour + ":00-" + endHour + ":00 (" + type + ")";
    }
}
