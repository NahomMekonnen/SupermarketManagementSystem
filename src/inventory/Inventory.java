package inventory;
import employee.EmployeeActions;
import product.Product;
import admin.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import database.Database;
public class Inventory extends JFrame{
    Connection connection;
    InventoryActions inventoryActions;

    JLabel l1,l2;
    JTextField textField;

    JComboBox Cb;
    JTable T;
    JButton back,add,remove;
    JScrollPane scroll;
    String[] options= {"ID","Name"};
    JPanel p1,p2;
    JDialog newItem;



    public Inventory(){
        Container Inv = getContentPane();
        inventoryActions = new InventoryActions();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,600);
        setLayout(null);
        setLocationRelativeTo(null);
        setTitle("LENNY SUPERMARKET");
        connection = Database.connection;
        textField=new JTextField(100);
        textField.setBounds(310,25,300,30);



        int count =inventoryActions.Count();
        System.out.println(count);
        Product[] products= inventoryActions.Retrieve(count);

        Cb= new JComboBox();
        Cb.addItem("ID");
        Cb.addItem("Name");
        Cb.setBounds(200,25,100,30);
        l1 = new JLabel("Search item using: ");
        l1.setBounds(50,-10,50,800);
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("Arial", Font.BOLD, 16));



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

        add=new JButton("Add");
        add.setBackground(new Color(40,40,40));
        add.setForeground(Color.WHITE);

        add.setFocusPainted(false);
        add.setFont(new Font("Arial", Font.BOLD, 16));

        add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                add.setBackground(new Color(60,60,60));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                add.setBackground(new Color(40,40,40));
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==add)
                {
                    newItem = new JDialog();

                    newItem.setTitle("Add Item");
                    newItem.setLayout(null);
                    JLabel name=new JLabel("Item name");
                    name.setBounds(25,10,200,30);
                    JLabel price=new JLabel("Item price");
                    price.setBounds(25,50,200,30);
                    JLabel quantity=new JLabel("Item quantity");
                    quantity.setBounds(25,90,200,30);
                    JLabel expiredate=new JLabel("Item Expiredate");
                    expiredate.setBounds(25,130,200,30);
                    JLabel category=new JLabel("Item Category Id");
                    category.setBounds(25,170,200,30);

                    JButton done=new JButton("Done");


                    done.setBackground(new Color(40,40,40));
                    done.setForeground(Color.WHITE);
                    done.setFocusPainted(false);
                    done.setFont(new Font("Arial", Font.BOLD, 16));

                    done.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                            done.setBackground(new Color(60,60,60));
                        }

                        public void mouseExited(java.awt.event.MouseEvent evt) {
                            done.setBackground(new Color(40,40,40));
                        }
                    });
                    done.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if(e.getSource()==done)
                            {
                                dispose();
                                Admin n=new Admin();
                                n.setVisible(true);
                            }
                        }
                    });
                    done.setBounds(300,260,100,30);


                    JTextField jTextField_name=new JTextField(20);
                    jTextField_name.setBounds(225,10,300,30);
                    JTextField jTextField_price=new JTextField(20);
                    jTextField_price.setBounds(225,50,300,30);
                    JTextField jTextField_quantity=new JTextField(20);
                    jTextField_quantity.setBounds(225,90,300,30);
                    JTextField jTextField_expiredate=new JTextField(20);
                    jTextField_expiredate.setBounds(225,130,300,30);
                    JTextField jTextField_categoryId=new JTextField(20);
                    jTextField_categoryId.setBounds(225,170,300,30);

                    newItem.add(name);
                    newItem.add(jTextField_name);
                    newItem.add(price);
                    newItem.add(jTextField_price);
                    newItem.add(quantity);
                    newItem.add(jTextField_quantity);
                    newItem.add(expiredate);
                    newItem.add(jTextField_expiredate);
                    newItem.add(category);
                    newItem.add(jTextField_categoryId);
                    newItem.add(done);
                    newItem.setSize(600, 400);



                    newItem.setLocationRelativeTo(null);
                    newItem.setVisible(true);

                }

            }
        });
        add.setBounds(250,390,100,50);
        //scroll.setBounds(50,15,700,350);

        remove=new JButton("Remove");
        remove.setBackground(new Color(40,40,40));
        remove.setForeground(Color.WHITE);

        remove.setFocusPainted(false);
        remove.setFont(new Font("Arial", Font.BOLD, 16));

        remove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                remove.setBackground(new Color(60,60,60));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                remove.setBackground(new Color(40,40,40));
            }
        });

        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==remove)
                {
                    dispose();
                    Admin n=new Admin();
                    n.setVisible(true);
                }

            }
        });
        remove.setBounds(450,390,100,50);



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
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));


        p1.add(back);
        p1.add(Cb);
        p1.add(textField);
        p2.add(scroll);
        p2.add(add);
        p2.add(remove);
        Inv.add(p1);
        Inv.add(p2);
    }
}
