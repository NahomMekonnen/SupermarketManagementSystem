package cashier;

import inventory.InventoryActions;
import login.Login;
import product.Product;
import sales.Sales;
import sales.SalesActions;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//public class Cashier extends JFrame implements ActionListener {
//
//
//    private Inventory inventory;
//
//
//    private Customer customer;
//
//
//    private double total;
//
//
//    private int items;
//
//    // GUI components
//    private JLabel welcomeLabel;
//    private JButton newCustomerButton;
//    private JButton existingCustomerButton;
//    private JLabel customerLabel;
//    private JButton viewInventoryButton;
//    private JButton buyItemButton;
//    private JButton checkoutButton;
//    private JLabel purchaseLabel;
//    private JTextArea purchaseArea;
//    private JLabel totalLabel;
//    private JLabel pointsLabel;
//
//
//    public Cashier() {
//        inventory = new Inventory();
//        customer = null;
//        total = 0.0;
//        items = 0;
//
//
//        welcomeLabel = new JLabel("Welcome to Supermarket System");
//        newCustomerButton = new JButton("New Customer");
//        existingCustomerButton = new JButton("Existing Customer");
//        customerLabel = new JLabel("No customer selected");
//        viewInventoryButton = new JButton("View Inventory");
//        buyItemButton = new JButton("Buy Item");
//        checkoutButton = new JButton("Checkout");
//        purchaseLabel = new JLabel("Current Purchase:");
//        purchaseArea = new JTextArea(10, 20);
//        totalLabel = new JLabel("Total: $0.00");
//        pointsLabel = new JLabel("Points: 0");
//
//
//        newCustomerButton.addActionListener(this);
//        existingCustomerButton.addActionListener(this);
//        viewInventoryButton.addActionListener(this);
//        buyItemButton.addActionListener(this);
//        checkoutButton.addActionListener(this);
//
//
//        JPanel topPanel = new JPanel();
//        topPanel.setLayout(new GridLayout(2, 2));
//        topPanel.add(welcomeLabel);
//        topPanel.add(newCustomerButton);
//        topPanel.add(existingCustomerButton);
//        topPanel.add(customerLabel);
//
//
//        JPanel middlePanel = new JPanel();
//        middlePanel.setLayout(new GridLayout(1, 3));
//        middlePanel.add(viewInventoryButton);
//        middlePanel.add(buyItemButton);
//        middlePanel.add(checkoutButton);
//
//        JPanel bottomPanel = new JPanel();
//        bottomPanel.setLayout(new BorderLayout());
//        bottomPanel.add(purchaseLabel, BorderLayout.NORTH);
//        bottomPanel.add(new JScrollPane(purchaseArea), BorderLayout.CENTER);
//        bottomPanel.add(totalLabel, BorderLayout.SOUTH);
//
//
//        JPanel rightPanel = new JPanel();
//        rightPanel.setLayout(new GridLayout(2, 1));
//        rightPanel.add(pointsLabel);
//
//
//        setTitle("Cashier");
//        setLayout(new BorderLayout());
//        add(topPanel, BorderLayout.NORTH);
//        add(middlePanel, BorderLayout.CENTER);
//        add(bottomPanel, BorderLayout.WEST);
//        add(rightPanel, BorderLayout.EAST);
//        pack();
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//    }
//
//    public void actionPerformed(ActionEvent e) {
//        Object source = e.getSource();
//        if (source == newCustomerButton) {
//            createNewCustomer();
//        } else if (source == existingCustomerButton) {
//            selectExistingCustomer();
//        } else if (source == viewInventoryButton) {
//            viewInventory();
//        } else if (source == buyItemButton) {
//            buyItem();
//        } else if (source == checkoutButton) {
//            checkout();
//        }
//    }
//
//
//    public void createNewCustomer() {
//        // TODO: we add a dialog box that asks for the name and phone number of the customer and creates a new Customer object with those parameters
//
//    }
//
//
//    public void selectExistingCustomer() {
//        // TODO: we must add some sorta variable that stores all the customers, such as an array, and a dialog box that allows the cashier to choose one of them
//
//    }
//
//
//
//
//    public void buyItem() {
//        if (customer == null) {
//            JOptionPane.showMessageDialog(this, "Please select or create a customer first.");
//            return;
//        }
//
//        String name = JOptionPane.showInputDialog(this, "Enter the name of the item you want to buy:");
//        Item item = inventory.findItem(name);
//
//        if (item == null) {
//            JOptionPane.showMessageDialog(this, "Sorry, we could not find this item in our inventory. Please try again.");
//            return;
//        }
//
//        int quantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter how many units of " + name + " you want to buy:"));
//
//        if (quantity <= 0) {
//            JOptionPane.showMessageDialog(this, "Please enter a positive number.");
//            return;
//        }
//
//        if (quantity > item.getQuantity()) {
//            JOptionPane.showMessageDialog(this, "Sorry, we only have " + item.getQuantity() + " units of " + name + " left. Please enter a smaller number.");
//            return;
//        }
//
//        double price = item.getPrice();
//        double subtotal = price * quantity;
//        total += subtotal;
//        items += quantity;
//        inventory.updateItem(name, -quantity);
//        customer.addPoints((int) subtotal);
//
//        purchaseArea.append(name + "\t" + quantity + "\t" + price + "\t" + subtotal + "\n");
//        totalLabel.setText("Total: $" + String.format("%.2f", total));
//        pointsLabel.setText("Points: " + customer.getPoints());
//    }
//
//
//
//
//    public void checkout() {
//        if (customer == null) { // Chekc if there is a customer selected
//            JOptionPane.showMessageDialog(this, "Please select or create a customer first.");
//            return;
//        }
//
//        if (items == 0) { // Check if there are any items purchased
//            JOptionPane.showMessageDialog(this, "Please buy some items first.");
//            return;
//        }
//
//        double tax = total * 0.15;
//        double grandTotal = total + tax;
//
//        JFrame receiptFrame = new JFrame("Receipt");
//        JTextArea receiptArea = new JTextArea(20, 30);
//        receiptArea.setEditable(false);
//
//        receiptArea.append("Supermarket System\n"); // Append the name of the system to the receipt area
//        receiptArea.append("Customer: " + customer.getName() + "\n"); // Append the name of the customer to the receipt area
//        receiptArea.append("Phone: " + customer.getPhone() + "\n");
//        receiptArea.append("Points: " + customer.getPoints() + "\n");
//        receiptArea.append("Items purchased: " + items + "\n");
//        receiptArea.append("Subtotal: $" + String.format("%.2f", total) + "\n");
//        receiptArea.append("Tax: $" + String.format("%.2f", tax) + "\n");
//        receiptArea.append("Grand Total: $" + String.format("%.2f", grandTotal) + "\n");
//
//        receiptFrame.add(new JScrollPane(receiptArea));
//        receiptFrame.pack();
//        receiptFrame.setVisible(true);
//
//        reset(); //resets the cashier object for a new purchase
//    }
//}
public class Cashier extends JFrame{
    JLabel itemId,itemName,quantity,singlePrice,totalPrice;
    InventoryActions inventoryActions;


    int amountBought,totalAmount;
    JSpinner amountOfItems;
    JTextField itemIdTextField,itemNameTextField,price,singlePriceTextField,totalPriceTextField;

    JButton add,print,clear,back;
    JTextArea textArea;
    JPanel p1;
    JPanel p2;
    JDialog receipt;

    double totalVat,priceTotal;



    public Cashier()
    {

        Container cash=getContentPane();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,600);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

         final double taxRate= 0.15;

        inventoryActions= new InventoryActions();
        int size= inventoryActions.Count();
        Product[] totalProducts=inventoryActions.Retrieve(size);

        double individualPrice,priceSum;
        totalVat=0;
        priceTotal=0;
        totalAmount=0;

        itemId=new JLabel("Item Id: ");

        itemId.setBounds(10,60,100,20);
        itemId.setForeground(Color.WHITE);
        itemId.setFont(new Font("Arial", Font.BOLD, 16));


        itemName=new JLabel("Item Name: ");

        itemName.setBounds(10,90,100,20);
        itemName.setForeground(Color.WHITE);
        itemName.setFont(new Font("Arial", Font.BOLD, 16));


        singlePrice=new JLabel("Single Price: ");
        singlePrice.setBounds(10,120 ,140,20);
        singlePrice.setForeground(Color.WHITE);
        singlePrice.setFont(new Font("Arial", Font.BOLD, 16));

        singlePriceTextField=new JTextField();
        singlePriceTextField.setBounds(160,120,100,20);

        singlePriceTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                highLight();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                highLight();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                highLight();
            }

            public void highLight(){
                if(singlePriceTextField.getText().isEmpty()){
                    singlePriceTextField.setBackground(Color.lightGray);
                }else{
                    singlePriceTextField.setBackground(Color.white);

                }
            }

        });

        quantity=new JLabel("Quantity: ");

        quantity.setBounds(10,150 ,100,20);
        quantity.setForeground(Color.WHITE);
        quantity.setFont(new Font("Arial", Font.BOLD, 16));



        itemIdTextField=new JTextField();
        itemIdTextField.setBounds(160,60,200,20);

        itemNameTextField=new JTextField();
        itemNameTextField.setBounds(160,90,200,20);

        itemIdTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                highLight();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                highLight();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                highLight();
            }

            public void highLight(){
                if(itemIdTextField.getText().isEmpty()){
                    itemIdTextField.setBackground(Color.lightGray);
                }else{
                    itemIdTextField.setBackground(Color.white);
//
                }
            }

        });

        itemNameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                highLight();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                highLight();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                highLight();
            }

            public void highLight(){
                if(itemNameTextField.getText().isEmpty()){
                    itemNameTextField.setBackground(Color.lightGray);
                }else{
                    itemNameTextField.setBackground(Color.white);

                }
            }

        });


        SpinnerNumberModel model=new SpinnerNumberModel(1,1,500,1);

        amountOfItems =new JSpinner(model);
        amountOfItems.setBounds(160,150,50,20);


        amountOfItems.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                   amountBought=(int) amountOfItems.getValue();
                    System.out.println(amountBought);

                    if(!singlePrice.getText().isEmpty()&&!itemIdTextField.getText().isEmpty()&&!itemNameTextField.getText().isEmpty()){
                        price.setText(String.valueOf(amountBought*Double.valueOf(singlePriceTextField.getText())));
                    }

            }
        });

        totalPrice=new JLabel("Total Price: ");




        totalPrice.setBounds(10,180 ,100,20);
        totalPrice.setForeground(Color.WHITE);
        totalPrice.setFont(new Font("Arial", Font.BOLD, 16));

        price=new JTextField();
        price.setBounds(160,180,100,20);


        add=new JButton("Add");
        add.setBounds(130,240,100,30);
        add.setBackground(new Color(40,40,40));
        add.setForeground(Color.WHITE);

        add.setFocusPainted(false);
        add.setFont(new Font("Arial", Font.BOLD, 16));

        textArea=new JTextArea(10,20);
        textArea.setBounds(20,20,380,450);
        add.addActionListener(new ActionListener() {
            double individualPrice;
            @Override
            public void actionPerformed(ActionEvent e) {

                    for(int i=0;i<size;i++){

                        if(totalProducts[i].getProduct_name().equals(itemNameTextField.getText())&&totalProducts[i].getProduct_id()==Integer.valueOf(itemIdTextField.getText())){


                            if(Integer.valueOf(singlePriceTextField.getText())!=totalProducts[i].getPrice()){
                                if(amountBought>=totalProducts[i].getAmount()){
                                    amountBought=totalProducts[i].getAmount();
                                }
                                this.individualPrice=totalProducts[i].getPrice();
                                String toTheReceipt=totalProducts[i].getProduct_name()+"\tPrice : " + individualPrice + "\tAmount: " +amountBought +  "\tTotal = " + String.valueOf(amountBought*individualPrice)+ "\n";
                                System.out.println(toTheReceipt);
                                textArea.append(toTheReceipt);
                                totalVat+=amountBought*individualPrice*taxRate;
                                priceTotal+=amountBought*individualPrice;
                                totalAmount+=amountBought;

                            }else {
                                String toTheReceipt=totalProducts[i].getProduct_name()+"\tPrice : " + singlePriceTextField.getText() + "\tAmount: " +amountBought +  "\tTotal = " + String.valueOf(amountBought*Integer.valueOf(singlePriceTextField.getText()))+ "\n";
                                System.out.println(toTheReceipt);
                                textArea.append(toTheReceipt);
                                totalVat+=amountBought*Integer.valueOf(singlePriceTextField.getText())*taxRate;
                                priceTotal+=amountBought*Integer.valueOf(singlePriceTextField.getText());
                                totalAmount+=amountBought;
                            }


                            if(amountBought>=totalProducts[i].getAmount()){
                                inventoryActions.Remove(totalProducts[i]);
                            }else if(amountBought<totalProducts[i].getAmount()){
                                totalProducts[i].setAmount(totalProducts[i].getAmount()-amountBought);
                                inventoryActions.Update(totalProducts[i]);
                            }
                        }
                    }


            }

        });


        add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                add.setBackground(new Color(60,60,60));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                add.setBackground(new Color(40,40,40));
            }
        });



        print=new JButton("Print");
        print.setBounds(90,490,100,30);
        print.setBackground(new Color(40,40,40));
        print.setForeground(Color.WHITE);

        print.setFocusPainted(false);
        print.setFont(new Font("Arial", Font.BOLD, 16));

        print.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                print.setBackground(new Color(60,60,60));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                print.setBackground(new Color(40,40,40));
            }
        });

        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==print)
                {
                    receipt=new JDialog();
                    receipt.setTitle("Receipt");
                    receipt.setSize(400,600);

                    receipt.setLocationRelativeTo(null);
                    receipt.setLayout(new BorderLayout());
                    JTextArea txt=new JTextArea();
                    txt.setText("                              **********    LENNY SUPERMARKET   **********       ");
                    txt.append("\n                             ***********       Thank You      ***********           ");
                    txt.append("\n"+textArea.getText());
                    txt.append("\n\tSub-Total= " + priceTotal + "\t Vat = " + totalVat);
                    double finalPrice= priceTotal + totalVat;
                    txt.append("\n \t\tTotal= " + finalPrice );

                    LocalDate currentDate= LocalDate.now();
                    int year=currentDate.getYear();
                    int month=currentDate.getMonthValue();
                    int day=currentDate.getDayOfMonth();
                    String date=String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);
                    System.out.println(date);
                    Sales newSale=new Sales(Date.valueOf(date),totalAmount,finalPrice);
                    SalesActions salesActions=new SalesActions();
                    salesActions.Add(newSale);
                    receipt.add(txt,BorderLayout.NORTH);
                    receipt.setVisible(true);
                }
            }
        });



        clear=new JButton("Clear");
        clear.setBounds(200,490,100,30);
        clear.setBackground(new Color(40,40,40));
        clear.setForeground(Color.WHITE);
        clear.setFocusPainted(false);
        clear.setFont(new Font("Arial", Font.BOLD, 16));

        clear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                clear.setBackground(new Color(60,60,60));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                clear.setBackground(new Color(40,40,40));
            }
        });



        back=new JButton("Back");
        back.setBackground(new Color(40,40,40));
        back.setForeground(Color.WHITE);

        back.setFocusPainted(false);
        back.setFont(new Font("Arial", Font.BOLD, 16));

        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                back.setBackground(new Color(60,60,60));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                back.setBackground(new Color(40,40,40));
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==back)
                {
                    dispose();

                    Login l=new Login();
                    l.setVisible(true);
                }

            }
        });
        back.setBounds(10,10,80,20);








        p1=new JPanel(null){
            private BufferedImage backgroundImage;

            {
                try {

                    backgroundImage = ImageIO.read(new File("C:/Users/mekon/IdeaProjects/SupermarketManagementSystem/greyImage.jpeg"));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
                }
            }
        };


        p2=new JPanel(null){
            private BufferedImage backgroundImage;

            {
                try {

                    backgroundImage = ImageIO.read(new File("C:/Users/mekon/IdeaProjects/SupermarketManagementSystem/greyImage.jpeg"));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
                }
            }
        };
        p2.setPreferredSize(new Dimension(50,600));


        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));



        p1.add(itemId);
        p1.add(itemIdTextField);
        p1.add(itemName);
        p1.add(itemNameTextField);
        p1.add(singlePrice);
        p1.add(singlePriceTextField);
        p1.add(quantity);
        p1.add(amountOfItems);
        p1.add(totalPrice);
        p1.add(price);
        p1.add(add);
        p1.add(back);
        p2.add(textArea);
        p2.add(print);
        p2.add(clear);
        cash.add(p1);
        cash.add(p2);




    }


}