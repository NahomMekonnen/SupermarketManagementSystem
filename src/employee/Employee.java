package employee;
import inventory.InventoryActions;
import product.Product;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Date;


import database.Database;




public class Employee extends JFrame{
    Connection connection;

    EmployeeActions employeeActions;

    JPanel p1,p2;
    JLabel l1,l2;
    JTextField textField,textField2;
    JComboBox Cb;
    JTable T;


    JScrollPane scroll;
    String[] options= {"ID","Name"};
    private String firstName,lastName,adress,phoneNumber;

    private int employee_Id;



    public int getEmployee_Id() {
        return employee_Id;
    }

    public String getAdress() {
        return adress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setEmployee_Id(int employee_Id) {
        this.employee_Id = employee_Id;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public Employee()
    {
        Container emp = getContentPane();
         employeeActions= new EmployeeActions();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,600);
        setLayout(null);
        setLocationRelativeTo(null);
        setTitle("LENNY SUPERMARKET");
        connection = Database.connection;
        textField=new JTextField(100);
        textField.setBounds(310,25,300,30);
        textField2=new JTextField(100);
        textField2.setBounds(310,65,300,30);

        int count =employeeActions.Count();
        System.out.println(count);
        Employee[] employees= employeeActions.Retrieve(count);

        Cb= new JComboBox(options);
        l1 = new JLabel("Search employee using name: ");
        l1.setBounds(50,-10,200,100);
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("Arial", Font.BOLD, 16));

        l2=new JLabel("Search employee using Id: ");
        l2.setBounds(50,30,200,100);
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("Arial", Font.BOLD, 16));

        String[] cNames = new String[]{"ID", "employee_firstName", "employee_lastName", "employee_phone_number", "employee_address"};

        String [][] Values = new String[count][cNames.length];
        for(int i=0;i<count; i++){
            Values[i][0]= String.valueOf(employees[i].getEmployee_Id());
            Values[i][1]= String.valueOf(employees[i].getFirstName());
            Values[i][2]= String.valueOf(employees[i].getLastName());
            Values[i][3]= String.valueOf(employees[i].getPhoneNumber());
            Values[i][4]= String.valueOf(employees[i].getAdress());

        }

        TableModel model= new DefaultTableModel(Values,cNames);
        TableRowSorter sorter=new TableRowSorter(model);
        T= new JTable(model);
        T.setRowSorter(sorter);
        scroll=new JScrollPane(T);

        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(textField.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                search(textField.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                search(textField.getText());
            }
            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    RowFilter rowFilter= RowFilter.regexFilter(textField.getText(),1);
                    sorter.setRowFilter(RowFilter.regexFilter(str));
                }
            }


        });
        textField2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(textField2.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                search(textField2.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                search(textField2.getText());
            }
            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    RowFilter rowFilter= RowFilter.regexFilter(textField2.getText(),0);
                    sorter.setRowFilter(rowFilter);
                }
            }
        });
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

        p2.setPreferredSize(new Dimension(600,400));

        p1.setPreferredSize(new Dimension(600,50));





        scroll.setBounds(450,65,500,150);
        scroll.setBackground(Color.WHITE);





        p1.add(l1);
        p1.add(textField);
        p1.add(l2);
        p1.add(textField2);
        p2.add(scroll);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        emp.add(p1);
        emp.add(p2);

    }
    public Employee(int id, String firstName, String lastName,String phoneNumber,String adress) {
        this.firstName = firstName;
        this.employee_Id = id;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.adress = adress;
    }








}

