
package admin;
import employee.Employee;
import inventory.Inventory;
import login.*;
import supplier.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Admin extends JFrame {


    JPanel p1,p2;
    JButton b1,b2,b3,b4,b5,back;



   public  Admin(){

        Container home=getContentPane();
        setSize(650,400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("LENNY SUPERMARKET");



        p1=new JPanel()
        {
            private BufferedImage backgroundImage;

            {
                try {

                    backgroundImage = ImageIO.read(new File("C:/Users/mekon/IdeaProjects/SupermarketManagementSystem/Welcome.png"));

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

        p2=new JPanel(null)
        {
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



        p1.setBackground(Color.LIGHT_GRAY);
        p2.setPreferredSize(new Dimension(200,300));


        p1.setLayout(null);
        b1=new JButton("Inventory");
        b1.setBackground(new Color(40,40,40));
        b1.setForeground(Color.WHITE);

        b1.setFocusPainted(false);
        b1.setFont(new Font("Arial", Font.BOLD, 16));

        b1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b1.setBackground(new Color(60,60,60));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b1.setBackground(new Color(40,40,40));
            }
        });

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==b1)
                {
                    dispose();
                    Inventory inventory=new Inventory();
                    inventory.setVisible(true);
                }
            }
        });

        b2=new JButton("Employees");
        b2.setBackground(new Color(40,40,40));
        b2.setForeground(Color.WHITE);

        b2.setFocusPainted(false);
        b2.setFont(new Font("Arial", Font.BOLD, 16));

        b2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b2.setBackground(new Color(60,60,60));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b2.setBackground(new Color(40,40,40));
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==b2)
                {
                    dispose();
                    Employee employee=new Employee();
                    employee.setVisible(true);
                }
            }
        });

        b3=new JButton("Sales");
        b3.setBackground(new Color(40,40,40));
        b3.setForeground(Color.WHITE);

        b3.setFocusPainted(false);
        b3.setFont(new Font("Arial", Font.BOLD, 16));

        b3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b3.setBackground(new Color(60,60,60));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b3.setBackground(new Color(40,40,40));
            }
        });


        b4=new JButton("Profits");
        b4.setBackground(new Color(40,40,40));
        b4.setForeground(Color.WHITE);

        b4.setFocusPainted(false);
        b4.setFont(new Font("Arial", Font.BOLD, 16));

        b4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b4.setBackground(new Color(60,60,60));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b4.setBackground(new Color(40,40,40));
            }
        });

        b5=new JButton("Suppliers");
        b5.setBackground(new Color(40,40,40));
        b5.setForeground(Color.WHITE);

        b5.setFocusPainted(false);
        b5.setFont(new Font("Arial", Font.BOLD, 16));

        b5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b5.setBackground(new Color(60,60,60));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b5.setBackground(new Color(40,40,40));
            }
        });
       b5.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if(e.getSource()==b5)
               {
                   dispose();
                   Supplier supplier=new Supplier();
                   supplier.setVisible(true);
               }
           }
       });

        b4=new JButton("Profits");
        b4.setBackground(new Color(40,40,40));
        b4.setForeground(Color.WHITE);

        b4.setFocusPainted(false);
        b4.setFont(new Font("Arial", Font.BOLD, 16));

        b4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b4.setBackground(new Color(60,60,60));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b4.setBackground(new Color(40,40,40));
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
                    Login n=new Login();
                    n.setVisible(true);
                }

            }
        });


        b1.setBounds(20,50,150,60);
        b2.setBounds(20,170,150,60);
        b3.setBounds(240,50,150,60);
        b4.setBounds(240,170,150,60);
        b5.setBounds(130,270,150,60);
        back.setBounds(10,10,80,20);



        p1.add(back);
        p2.add(b1);
        p2.add(b2);
        p2.add(b3);
        p2.add(b4);
        p2.add(b5);


        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        home.add(p1);
        home.add(p2);




    }





}
