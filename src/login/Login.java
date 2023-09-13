package login;

import admin.Admin;

import  java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import cashier.*;
import  admin.*;
import database.Database;
import  sales.*;

public class Login extends JFrame{
    Connection connection;
    JLabel choose;
    JLabel userID;
    JLabel password;
    JTextField idTextField;
    JComboBox userType;
    JPasswordField passwordField;

    JButton login;
    JButton clear;
    JPanel p1;
    JPanel p2;



    public  Login()
    {
        Container c=getContentPane();
        setSize(650,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("LENNY SUPERMARKET");
        setLocationRelativeTo(null);
        setResizable(false);


        connection= Database.connection;


        p1=new JPanel(){
        private BufferedImage backgroundImage;

        {
            try {

                backgroundImage = ImageIO.read(new File("C:/Users/mekon/IdeaProjects/SupermarketManagementSystem/lenny.png"));

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

        p2=new JPanel(){
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

        p2.setPreferredSize(new Dimension(200,400));



        choose=new JLabel("User Type");
        choose.setForeground(Color.white);


        String str[]={"Admin","User"};
        userType=new JComboBox(str);
        userType.setBackground(Color.gray);

        userID=new JLabel("User ID");
        userID.setForeground(Color.white);
        idTextField=new JTextField();
        password=new JLabel("password");
        password.setForeground(Color.white);

        passwordField=new JPasswordField();
        login=new JButton("Login");

        login.setBackground(new Color(40,40,40));
        login.setForeground(Color.WHITE);
        login.setFocusPainted(false);
        login.setFont(new Font("Arial", Font.BOLD, 16));

        login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                login.setBackground(new Color(60,60,60));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                login.setBackground(new Color(40,40,40));
            }
        });
        login.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==login&& userType.getSelectedItem()=="Admin")
                {
                    dispose();
                    if(idTextField.getText().isEmpty()==false&&passwordField.getText().isEmpty()==false){
                        String username = null, password = null;
                        try {
                            Statement statement = Database.connection.createStatement();
                            String query = "SELECT username,password FROM loginInfo WHERE id=1 ";
                            ResultSet resultSet = statement.executeQuery(query);
                            while (resultSet.next()){
                                username=resultSet.getString(1);
                                password=resultSet.getString(2);
                            }
                            
                            if(username.equals(idTextField.getText())&&password.equals(passwordField.getText())){
                                Admin n = new Admin();
                                n.setVisible(true);
                            }else{

                                Login n=new Login();
                                n.setVisible(true);
                            }
                        } catch (Exception exception) {
                            System.out.println(exception);
                        }

                        
                    }

                } else if (e.getSource()==login&& userType.getSelectedItem()=="User") {
                    dispose();
                    if(idTextField.getText().isEmpty()==false&&passwordField.getText().isEmpty()==false){
                        String username = null, password = null;
                        try {
                            Statement statement = Database.connection.createStatement();
                            String query = "SELECT username,password FROM loginInfo WHERE id=2 ";
                            ResultSet resultSet = statement.executeQuery(query);
                            while (resultSet.next()){
                                username=resultSet.getString(1);
                                password=resultSet.getString(2);
                            }

                            if(username.equals(idTextField.getText())&&password.equals(passwordField.getText())){
                                cashier.Cashier c=new cashier.Cashier();
                                c.setVisible(true);

                            }else{

                                Login n=new Login();
                                n.setVisible(true);
                            }
                        } catch (Exception exception) {
                            System.out.println(exception);
                        }


                    }

                }
            }
        });
        clear=new JButton("Clear");

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
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==clear)
                {
                    idTextField.setText("");
                    passwordField.setText("");

                }
            }
        });


        p2.setLayout(null);
        choose.setBounds(70,40,200,100);
        userType.setBounds(160,70,200,40);
        userID.setBounds(70,110,200,100);
        idTextField.setBounds(160,140,200,40);

        password.setBounds(70,180,200,100);
        passwordField.setBounds(160,210,200,40);


        login.setBounds(100,280,100,40);
        clear.setBounds(250,280,100,40);




        p2.add(choose);
        p2.add(userType);
        p2.add(userID);
        p2.add(idTextField);
        p2.add(password);
        p2.add(passwordField);
        p2.add(login);
        p2.add(clear);


        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        c.add(p1);
        c.add(p2);
    }

}
