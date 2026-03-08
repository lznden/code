package view;
import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import model.Reward;

public class RewardStoreView extends JPanel {

    private DefaultListModel<Reward> listModel;
    private JList<Reward> rewardList;
    private JButton redeemButton;

    public RewardStoreView() {
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        rewardList = new JList<>(listModel);
        rewardList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(rewardList);

        redeemButton = new JButton("Redeem Reward");

        add(scrollPane, BorderLayout.CENTER);
        add(redeemButton, BorderLayout.SOUTH);
    }

    public void setRewards(java.util.List<Reward> rewards) {
        listModel.clear();
        for (Reward r : rewards) {
            listModel.addElement(r);
        }
    }

    public Reward getSelectedReward() {
        return rewardList.getSelectedValue();
    }

    public JButton getRedeemButton() {
        return redeemButton;
    }
}
