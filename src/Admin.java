import inventory.Inventory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Admin extends JFrame {


    JPanel p1,p2;
    JButton b1,b2,b3,b4,b5;

    JLabel Welcome;


    Admin(){

        Container home=getContentPane();
        setSize(1000,600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);



        p1=new JPanel();
        p2=new JPanel(null);
        p2.setBounds(0,0,400,600);
        Welcome=new JLabel("Welcome Admin");
        Welcome.setBounds(200,300,100,50);
        p2.add(Welcome);
        p2.setBackground(Color.LIGHT_GRAY);
        p1.setBounds(450,10,500,550);
        p1.setLayout(new GridLayout(5,1,400,10));
        b1=new JButton("inventory");
        b2=new JButton("Employees");
        b3=new JButton("Sales");
        b4=new JButton("Profits");
        b5=new JButton("Suppliers");

        b1.setBackground(Color.lightGray);
        b2.setBackground(Color.lightGray);
        b3.setBackground(Color.lightGray);
        b4.setBackground(Color.lightGray);
        b5.setBackground(Color.lightGray);

        b1.setBorderPainted(false);
        b2.setBorderPainted(false);
        b3.setBorderPainted(false);
        b4.setBorderPainted(false);
        b5.setBorderPainted(false);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    dispose();
                    Inventory inventory=new Inventory();
                    inventory.setVisible(true);
            }
        });
        p1.add(b1);
        p1.add(b2);
        p1.add(b3);
        p1.add(b4);
        p1.add(b5);


        home.add(p1);
        home.add(p2);




    }





}
