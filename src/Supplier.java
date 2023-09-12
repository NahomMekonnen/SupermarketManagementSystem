import javax.swing.*;
import java.awt.*;

public class Supplier {
    String name;
    String phone;
    String beverage;

    public Supplier(String name, String phone, String beverage) {
        this.name = name;
        this.phone = phone;
        this.beverage = beverage;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getBeverage() {
        return beverage;
    }
    //the getters and setters can be replaced with the sql queries
}

 class GUI extends JFrame {
    private JPanel panel;
    public GUI(Supplier supplier) {
        panel = new JPanel(new GridLayout(3,2));
        panel.add(new JLabel("Name:"));
        panel.add(new JLabel(supplier.getName()));
        panel.add(new JLabel("Phone:"));
        panel.add(new JLabel(supplier.getPhone()));
        panel.add(new JLabel("Beverage:"));
        panel.add(new JLabel(supplier.getBeverage()));
        add(panel);
        setTitle("Supplier Information");
        setSize(300,150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }}