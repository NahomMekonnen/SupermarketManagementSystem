import  java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Login extends JFrame{
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



    Login()
    {
        Container c=getContentPane();
        setSize(650,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("LENNY SUPERMARKET");
        setLocationRelativeTo(null);

        p1=new JPanel(){
        private BufferedImage backgroundImage;

        {
            try {

                backgroundImage = ImageIO.read(new File("C:/Users/Nahom Mekonnen/IdeaProjects/SupermarketManagementSystem/lenny.png"));

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

                    backgroundImage = ImageIO.read(new File("C:/Users/Nahom Mekonnen/IdeaProjects/SupermarketManagementSystem/greyImage.jpeg"));

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
        userType.setBackground(Color.GRAY);

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
