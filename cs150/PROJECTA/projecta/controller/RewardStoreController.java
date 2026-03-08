package controller;

import javax.swing.JOptionPane;

import model.PointTracker;
import model.Reward;
import model.RewardStore;
import view.RewardStoreView;

public class RewardStoreController {

    private final RewardStoreView view;
    private final PointTracker points;

    public RewardStoreController(RewardStore store, RewardStoreView view, PointTracker points) {
        this.view = view;
        this.points = points;

        view.setRewards(store.getRewards());

        view.getRedeemButton().addActionListener(e -> redeem());
    }

    private void redeem() {
        Reward selected = view.getSelectedReward();
        if (selected == null) {
            JOptionPane.showMessageDialog(null, "No reward selected.");
            return;
        }

        if (points.getPoints() < selected.getCost()) {
            JOptionPane.showMessageDialog(null, "Not enough points.");
            return;
        }

        points.spendPoints(selected.getCost());
        JOptionPane.showMessageDialog(null, "Redeemed: " + selected.getName());
    }
}
