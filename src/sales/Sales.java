package sales;

import database.Database;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class Sales extends JFrame {
    SalesActions salesActions;
    JPanel p1, p2;
    JLabel l1, l2, totalSalesLabel;
    JTextField tf, tf2;
    JComboBox Cb;
    JTable T;
    JButton search;
    JScrollPane scroll;

    public Sales() {
        Container salesContainer = getContentPane();
        salesActions = new SalesActions();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,600);
        setLayout(null);
        setLocationRelativeTo(null);

        tf = new JTextField(100);
        tf2 = new JTextField(100);

        tf.setBounds(400,0,300,50);
        tf2.setBounds(400,400,300,50);

        int count = salesActions.Count();
        System.out.println(count);

        SalesActions[] salesArray = salesActions.Retrieve(count);

        Cb = new JComboBox();
        l1 = new JLabel("Search item using: ");
        search = new JButton("search");

        String[] cNames = new String[]{"ID", "Date", "Quantity", "Total Money"};

        String[][] Values = new String[count][cNames.length];
        for (int i=0; i<count; i++) {
            Values[i][0] = String.valueOf(salesArray[i].getSalesID());
            Values[i][1] = String.valueOf(salesArray[i].getSalesDate());
            Values[i][2] = String.valueOf(salesArray[i].getSalesQuantity());
            Values[i][3] = String.valueOf(salesArray[i].getTotalMoney());
        }

        TableModel model= new DefaultTableModel(Values,cNames);
        TableRowSorter sorter=new TableRowSorter(model);
        T= new JTable(model);
        T.setRowSorter(sorter);

        scroll=new JScrollPane(T);

        Cb.addItem("ID");
        Cb.addItem("Date");

        double totalSales = salesActions.calculateTotalSales();
        totalSalesLabel = new JLabel("Total Sales: " + totalSales);
        totalSalesLabel.setBounds(400,450,300,50);

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
                }else if(selected.equals("Date")){
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

        salesContainer.add(Cb);
        salesContainer.add(tf);
        salesContainer.add(tf2);
        salesContainer.add(scroll);
        salesContainer.add(totalSalesLabel);
    }
}
