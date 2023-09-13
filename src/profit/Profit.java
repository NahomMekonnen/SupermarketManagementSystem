package profit;

import javax.swing.*;
import java.awt.*;

public class Profit extends JFrame {
    ProfitActions profitActions;
    JLabel profitLabel;

    public Profit() {
        Container profitContainer = getContentPane();
        profitActions = new ProfitActions();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,200);
        setLayout(null);
        setLocationRelativeTo(null);

        double profit = profitActions.calculateProfit();
        profitLabel = new JLabel("Total Profit: " + profit);
        profitLabel.setBounds(50,50,300,50);

        profitContainer.add(profitLabel);
    }
}
