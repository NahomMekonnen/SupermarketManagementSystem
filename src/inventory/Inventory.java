package inventory;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import database.Database;

public class Inventory extends JFrame{


    Connection connection;

    InventoryActions inventoryActions;

    JPanel p1,p2;
    JLabel l1,l2;
    JTextField tf;
    JTextArea TArea;


    public Inventory(){
        Container Inv = getContentPane();
        inventoryActions = new InventoryActions();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,600);
        setLayout(null);
        setLocationRelativeTo(null);
        connection = Database.connection;
        tf=new JTextField(100);
        tf.setBounds(400,200,300,200);
        inventoryActions.Retrieve();
        int count =inventoryActions.Count();
        System.out.println(count);
        Inv.add(tf);
    }






}
