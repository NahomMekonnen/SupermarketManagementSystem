package supplier;

import admin.Admin;
import database.Database;
import inventory.InventoryActions;
import product.Product;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;

public class Supplier extends JFrame{
    private int supplier_id;
    private String firstName,lastName,phoneNumber,itemSupplied,address;

    Supplier(int id,String fName,String lName,String phone,String items,String Address){
        supplier_id=id;
        firstName=fName;
        lastName=lName;
        phoneNumber=phone;
        itemSupplied=items;
        address=Address;
    }
    Connection connection;
    SupplierActions supplierActions;
    JPanel p1,p2;
    JLabel l1,l2;
    JTextField textField;

    JComboBox Cb;
    JTable T;
    JButton newSupplier,supplyProducts,removeSupplier,back;
    JScrollPane scroll;
    JDialog newSupply,prodSupply,removeSupply;
    String[] options= {"ID","Name"};
    public Supplier(){
        Container Sup = getContentPane();
        supplierActions = new SupplierActions();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,600);
        setLayout(null);
        setLocationRelativeTo(null);
        connection = Database.connection;
        textField=new JTextField(100);


        textField.setBounds(310,25,300,30);

        int count = supplierActions.Count();
        System.out.println(count);
        Supplier[] suppliers= supplierActions.Retrieve(count);

        Cb= new JComboBox();
        Cb.addItem("ID");
        Cb.addItem("Name");
        Cb.setBounds(200,25,100,30);
        l1 = new JLabel("Search item using: ");
        l1.setBounds(50,-10,50,800);
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("Arial", Font.BOLD, 16));


        String[] cNames = new String[]{"ID", "FirstName", "LastName", "Phone_No", "Item_Supplied", "Address"};

        String [][] Values = new String[count][cNames.length];
        for(int i=0;i<count; i++){
            Values[i][0]= String.valueOf(suppliers[i].getSupplier_id());
            Values[i][1]= suppliers[i].getFirstName();
            Values[i][2]= suppliers[i].getLastName();
            Values[i][3]= suppliers[i].getPhoneNumber();
            Values[i][4]= suppliers[i].getItemSupplied();
            Values[i][5]= suppliers[i].getAddress();
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
            private void search(String str){
                String selected=(String)Cb.getSelectedItem();
                if(selected.equals("ID")){
                    if (str.length() == 0) {
                        sorter.setRowFilter(null);
                    } else {
                        RowFilter rowFilter= RowFilter.regexFilter(textField.getText(),0);
                        sorter.setRowFilter(rowFilter);
                    }
                }else if(selected.equals("Name")){
                    if (str.length() == 0) {
                        sorter.setRowFilter(null);
                    } else {
                        RowFilter rowFilter= RowFilter.regexFilter(textField.getText(),1);
                        sorter.setRowFilter(rowFilter);
                    }
                }
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
                    Admin n=new Admin();
                    n.setVisible(true);
                }

            }
        });
        back.setBounds(10,10,80,20);


        newSupplier= new JButton("Add Supplier");
        newSupplier.setBackground(new Color(40,40,40));
        newSupplier.setForeground(Color.WHITE);

        newSupplier.setFocusPainted(false);
        newSupplier.setFont(new Font("Arial", Font.BOLD, 16));

        newSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                newSupplier.setBackground(new Color(60,60,60));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                newSupplier.setBackground(new Color(40,40,40));
            }
        });

        newSupplier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==newSupplier)
                {
                    dispose();
                    Admin n=new Admin();
                    n.setVisible(true);
                }

            }
        });
        newSupplier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newSupply= new JDialog();
// I stopped here
            }
        });
        newSupplier.setBounds(250,390,100,50);


        supplyProducts= new JButton("Restock");
        supplyProducts.setBackground(new Color(40,40,40));
        supplyProducts.setForeground(Color.WHITE);

        supplyProducts.setFocusPainted(false);
        supplyProducts.setFont(new Font("Arial", Font.BOLD, 16));

        supplyProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                supplyProducts.setBackground(new Color(60,60,60));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                supplyProducts.setBackground(new Color(40,40,40));
            }
        });

        supplyProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==supplyProducts)
                {
                    dispose();
                    Admin n=new Admin();
                    n.setVisible(true);
                }

            }
        });
        supplyProducts.setBounds(350,390,100,50);
        removeSupplier=new JButton("Remove Supplier");
        removeSupplier.setBackground(new Color(40,40,40));
        removeSupplier.setForeground(Color.WHITE);

        removeSupplier.setFocusPainted(false);
        removeSupplier.setFont(new Font("Arial", Font.BOLD, 16));

        removeSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                removeSupplier.setBackground(new Color(60,60,60));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                removeSupplier.setBackground(new Color(40,40,40));
            }
        });

        removeSupplier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==removeSupplier)
                {
                    dispose();
                    Admin n=new Admin();
                    n.setVisible(true);
                }

            }
        });
        removeSupplier.setBounds(450,390,100,50);
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

        p2.setPreferredSize(new Dimension(600,400));

        p1.setPreferredSize(new Dimension(600,50));




        scroll.setBounds(50,15,700,350);
        scroll.setBackground(Color.WHITE);

      //  p1.add(back);
        p1.add(Cb);
        p1.add(textField);
        p1.add(back);
        p2.add(scroll);
        p2.add(removeSupplier);
        p2.add(newSupplier);
        p2.add(supplyProducts);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        Sup.add(p1);
        Sup.add(p2);

    }

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }  public String getItemSupplied(){
        return itemSupplied;
    }
    public String getAddress(){
        return address;
    }
    public int getSupplier_id(){
        return supplier_id;
    }

    public void setSupplier_id(int id)
    {
        supplier_id=id;
    }
    public void setFirstName(String fName)
    {
        firstName=fName;
    }
    public void setLastName(String lName)
    {
        lastName=lName;
    }
    public void setPhoneNumber(String phone)
    {
        phoneNumber=phone;
    }
    public void setItemSupplied(String item)
    {
        itemSupplied=item;
    }
    public void setAddress(String Address)
    {
        address=Address;
    }





}

