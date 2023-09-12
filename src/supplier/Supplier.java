package supplier;

import database.Database;
import inventory.InventoryActions;
import product.Product;

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
    JTextField tf,tf2;
    JTextArea TArea;
    JComboBox Cb;
    JTable T;
    JButton newSupplier,supplyProducts,removeSupplier;
    JScrollPane scroll;
    JDialog newSupply,prodSupply,removeSupply;
    String[] options= {"ID","Name"};
    public Supplier(){
        Container Sup = getContentPane();
        supplierActions = new SupplierActions();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,600);
        setLayout(null);
        setLocationRelativeTo(null);
        connection = Database.connection;
        tf=new JTextField(100);
        tf2=new JTextField(100);

        tf.setBounds(400,0,300,50);
        tf2.setBounds(400,400,300,50);
        int count = supplierActions.Count();
        System.out.println(count);
        Supplier[] suppliers= supplierActions.Retrieve(count);

        Cb= new JComboBox();
        l1 = new JLabel("Search item using: ");


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
        Cb.addItem("ID");
        Cb.addItem("Name");


        newSupplier= new JButton("Add Supplier");
        supplyProducts= new JButton("Restock");
        removeSupplier=new JButton("Remove Supplier");

        tf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(tf.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(tf.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(tf.getText());
            }
            private void search(String str){
                String selected=(String)Cb.getSelectedItem();
                if(selected.equals("ID")){
                    if (str.length() == 0) {
                        sorter.setRowFilter(null);
                    } else {
                        RowFilter rowFilter= RowFilter.regexFilter(tf.getText(),0);
                        sorter.setRowFilter(rowFilter);
                    }
                }else if(selected.equals("Name")){
                    if (str.length() == 0) {
                        sorter.setRowFilter(null);
                    } else {
                        RowFilter rowFilter= RowFilter.regexFilter(tf.getText(),1);
                        sorter.setRowFilter(rowFilter);
                    }
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


        scroll.setBounds(450,65,500,150);
        scroll.setBackground(Color.WHITE);
        newSupplier.setBounds(100,450,200,50);
        supplyProducts.setBounds(400,450, 200, 50);
        removeSupplier.setBounds(700,450, 200, 50);
        Cb.setBounds(1,2,100,100);
        Sup.add(newSupplier);
        Sup.add(supplyProducts);
        Sup.add(removeSupplier);
        Sup.add(Cb);
        Sup.add(tf);
        Sup.add(tf2);
        Sup.add(scroll);

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

