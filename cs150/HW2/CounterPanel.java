public class CounterPanel {
    private int countA;
    private int countB;
    private int countC;

    private static int totalClicks = 0;

    public void clickA() {
        countA++;
        totalClicks++;
    }
    public void clickB() {
        countB++;
        totalClicks++;
    }
    public void clickC() {
        countC++;
        totalClicks++;
    }
    public int getCountA() {
        return countA;
    }
    public int getCountB() {
        return countB;
    }
    public int getCountC() {
        return countC;
    }
    public int getTotalClicks() {
        return totalClicks;
    }
}
