
package model;

public class PointTracker {

    private int points;

    public PointTracker() {
        this.points = 0;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int amount) {
        if (amount > 0) {
            this.points += amount;
        }
    }

    public boolean spendPoints(int amount) {
        if (amount > 0 && amount <= this.points) {
            this.points -= amount;
            return true;
        }
        return false;
    }

}
