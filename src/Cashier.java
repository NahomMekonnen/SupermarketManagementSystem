import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static jdk.jfr.internal.StringPool.reset;

public class Cashier extends JFrame implements ActionListener {


    private Inventory inventory;


    private Customer customer;


    private double total;


    private int items;

    // GUI components
    private JLabel welcomeLabel;
    private JButton newCustomerButton;
    private JButton existingCustomerButton;
    private JLabel customerLabel;
    private JButton viewInventoryButton;
    private JButton buyItemButton;
    private JButton checkoutButton;
    private JLabel purchaseLabel;
    private JTextArea purchaseArea;
    private JLabel totalLabel;
    private JLabel pointsLabel;


    public Cashier() {
        inventory = new Inventory();
        customer = null;
        total = 0.0;
        items = 0;


        welcomeLabel = new JLabel("Welcome to Supermarket System");
        newCustomerButton = new JButton("New Customer");
        existingCustomerButton = new JButton("Existing Customer");
        customerLabel = new JLabel("No customer selected");
        viewInventoryButton = new JButton("View Inventory");
        buyItemButton = new JButton("Buy Item");
        checkoutButton = new JButton("Checkout");
        purchaseLabel = new JLabel("Current Purchase:");
        purchaseArea = new JTextArea(10, 20);
        totalLabel = new JLabel("Total: $0.00");
        pointsLabel = new JLabel("Points: 0");


        newCustomerButton.addActionListener(this);
        existingCustomerButton.addActionListener(this);
        viewInventoryButton.addActionListener(this);
        buyItemButton.addActionListener(this);
        checkoutButton.addActionListener(this);


        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 2));
        topPanel.add(welcomeLabel);
        topPanel.add(newCustomerButton);
        topPanel.add(existingCustomerButton);
        topPanel.add(customerLabel);


        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(1, 3));
        middlePanel.add(viewInventoryButton);
        middlePanel.add(buyItemButton);
        middlePanel.add(checkoutButton);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(purchaseLabel, BorderLayout.NORTH);
        bottomPanel.add(new JScrollPane(purchaseArea), BorderLayout.CENTER);
        bottomPanel.add(totalLabel, BorderLayout.SOUTH);


        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(2, 1));
        rightPanel.add(pointsLabel);


        setTitle("Cashier");
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == newCustomerButton) {
            createNewCustomer();
        } else if (source == existingCustomerButton) {
            selectExistingCustomer();
        } else if (source == viewInventoryButton) {
            viewInventory();
        } else if (source == buyItemButton) {
            buyItem();
        } else if (source == checkoutButton) {
            checkout();
        }
    }


    public void createNewCustomer() {
        // TODO: we add a dialog box that asks for the name and phone number of the customer and creates a new Customer object with those parameters

    }


    public void selectExistingCustomer() {
        // TODO: we must add some sorta variable that stores all the customers, such as an array, and a dialog box that allows the cashier to choose one of them

    }




    public void buyItem() {
        if (customer == null) {
            JOptionPane.showMessageDialog(this, "Please select or create a customer first.");
            return;
        }

        String name = JOptionPane.showInputDialog(this, "Enter the name of the item you want to buy:");
        Item item = inventory.findItem(name);

        if (item == null) {
            JOptionPane.showMessageDialog(this, "Sorry, we could not find this item in our inventory. Please try again.");
            return;
        }

        int quantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter how many units of " + name + " you want to buy:"));

        if (quantity <= 0) {
            JOptionPane.showMessageDialog(this, "Please enter a positive number.");
            return;
        }

        if (quantity > item.getQuantity()) {
            JOptionPane.showMessageDialog(this, "Sorry, we only have " + item.getQuantity() + " units of " + name + " left. Please enter a smaller number.");
            return;
        }

        double price = item.getPrice();
        double subtotal = price * quantity;
        total += subtotal;
        items += quantity;
        inventory.updateItem(name, -quantity);
        customer.addPoints((int) subtotal);

        purchaseArea.append(name + "\t" + quantity + "\t" + price + "\t" + subtotal + "\n");
        totalLabel.setText("Total: $" + String.format("%.2f", total));
        pointsLabel.setText("Points: " + customer.getPoints());
    }




    public void checkout() {
        if (customer == null) { // Chekc if there is a customer selected
            JOptionPane.showMessageDialog(this, "Please select or create a customer first.");
            return;
        }

        if (items == 0) { // Check if there are any items purchased
            JOptionPane.showMessageDialog(this, "Please buy some items first.");
            return;
        }

        double tax = total * 0.15;
        double grandTotal = total + tax;

        JFrame receiptFrame = new JFrame("Receipt");
        JTextArea receiptArea = new JTextArea(20, 30);
        receiptArea.setEditable(false);

        receiptArea.append("Supermarket System\n"); // Append the name of the system to the receipt area
        receiptArea.append("Customer: " + customer.getName() + "\n"); // Append the name of the customer to the receipt area
        receiptArea.append("Phone: " + customer.getPhone() + "\n");
        receiptArea.append("Points: " + customer.getPoints() + "\n");
        receiptArea.append("Items purchased: " + items + "\n");
        receiptArea.append("Subtotal: $" + String.format("%.2f", total) + "\n");
        receiptArea.append("Tax: $" + String.format("%.2f", tax) + "\n");
        receiptArea.append("Grand Total: $" + String.format("%.2f", grandTotal) + "\n");

        receiptFrame.add(new JScrollPane(receiptArea));
        receiptFrame.pack();
        receiptFrame.setVisible(true);

        reset(); //resets the cashier object for a new purchase
    }
}