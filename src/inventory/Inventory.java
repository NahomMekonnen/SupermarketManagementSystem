package inventory;
import product.Product;
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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import database.Database;
public class Inventory extends JFrame{
    Connection connection;
    InventoryActions inventoryActions;
    JPanel p1,p2;
    JLabel l1,l2;
    JTextField tf,tf2;
    JTextArea TArea;
    JComboBox Cb;
    JTable T;
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
        tf2=new JTextField(100);

        tf.setBounds(400,0,300,50);
        tf2.setBounds(400,400,300,50);
        int count =inventoryActions.Count();
        System.out.println(count);
        Product[] products= inventoryActions.Retrieve(count);

        Cb= new JComboBox();
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

        TableModel model= new DefaultTableModel(Values,cNames);
        TableRowSorter sorter=new TableRowSorter(model);
        T= new JTable(model);
        T.setRowSorter(sorter);
        scroll=new JScrollPane(T);
        Cb.addItem("ID");
        Cb.addItem("Name");

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


        scroll.setBounds(450,65,500,150);
        scroll.setBackground(Color.WHITE);
        search.setBounds(0,0,100,100);
        Cb.setBounds(1,2,100,100);
        Inv.add(Cb);
        Inv.add(tf);
        Inv.add(tf2);
        Inv.add(scroll);
    }
}
