package inventory;
import product.Product;
import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

import database.Database;

public class Inventory extends JFrame{


    Connection connection;

    InventoryActions inventoryActions;

    JPanel p1,p2;
    JLabel l1,l2;
    JTextField tf;
    JTextArea TArea;
    JComboBox Cb;
    JTable T,T2;
    JButton search;

    JScrollPane scroll;
    String[] options= {"ID","Name"};



    public Inventory(){
        Container Inv = getContentPane();
        inventoryActions = new InventoryActions();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,600);
        setLayout(null);
        setLocationRelativeTo(null);
        connection = Database.connection;
        tf=new JTextField(100);
        tf.setBounds(400,200,300,50);
        int count =inventoryActions.Count();
        System.out.println(count);
        Product[] products= inventoryActions.Retrieve(count);

        Cb= new JComboBox(options);
        l1 = new JLabel("Search item using: ");
        search= new JButton("search");

        String[] cNames = new String[]{"ID", "Name", "Price", "Quantity", "Exp-Date", "Category-ID"};

        String [][] Values = new String[count][cNames.length];
        for(int i=0;i<count; i++){
            Values[i][0]= String.valueOf(products[i].getProduct_id());
            Values[i][1]= products[i].getProduct_name();
            Values[i][2]= String.valueOf(products[i].getPrice());
            Values[i][3]= String.valueOf(products[i].getAmount());
            Values[i][4]= String.valueOf(products[i].getDate());
            Values[i][5]= String.valueOf(products[i].getCategory_id());
        }

        T= new JTable(Values,cNames);
        scroll=new JScrollPane(T);
        search.addActionListener(new ActionListener() {
            String[][] Value=new String[1][cNames.length];

            @Override
            public void actionPerformed(ActionEvent e) {
                Product product=inventoryActions.Search(tf.getText());
                System.out.println(product.getProduct_name());
                Value[0][0]= String.valueOf(product.getProduct_id());
                Value[0][1]= product.getProduct_name();
                Value[0][2]= String.valueOf(product.getPrice());
                Value[0][3]= String.valueOf(product.getAmount());
                Value[0][4]= String.valueOf(product.getDate());
                Value[0][5]= String.valueOf(product.getCategory_id());
                T2= new JTable(Value,cNames);

            }
        });

        scroll.setBounds(450,65,500,150);
        scroll.setBackground(Color.WHITE);

        search.setBounds(0,0,100,100);

        Inv.add(tf);
        Inv.add(search);

        Inv.add(scroll);


    }






}
