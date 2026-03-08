package model;
import java.util.ArrayList;
import java.util.List;

public class RewardStore {
    private List<Reward> rewards;

    public RewardStore() {
        rewards = new ArrayList<>();
    }

    public void addReward(Reward reward) {
        rewards.add(reward);
    }

    public List<Reward> getRewards() {
        return rewards;
    }
}
