package sales;

import admin.Admin;
import database.Database;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;

public class Sales extends JFrame {
    Connection connection;
    SalesActions salesActions;

    JPanel p1, p2;
    JLabel l1;
    JTextField textField;
    JComboBox Cb;
    JTable T;
    JButton back;
    JScrollPane scroll;
    private int salesID, salesQuantity;
    private Date salesDate;
    private Double totalMoney;

    public int getSalesID() {
        return salesID;
    }

    public int getSalesQuantity() {
        return salesQuantity;
    }

    public Date getSalesDate() {
        return salesDate;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }
    public Sales(int salesID, Date salesDate, int salesQuantity, double totalMoney){
        this.salesDate=salesDate;
        this.salesID=salesID;
        this.salesQuantity=salesQuantity;
        this.totalMoney=totalMoney;

    }

    public Sales() {
        Container salesContainer = getContentPane();
        salesActions = new SalesActions();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,600);
        setLayout(null);
        setLocationRelativeTo(null);
        setTitle("LENNY SUPERMARKET");
        setResizable(false);

        connection=Database.connection;

        textField= new JTextField(100);
        textField.setBounds(310,25,300,30);


        int count = salesActions.Count();
        System.out.println(count);

        Sales[] salesArray = salesActions.Retrieve(count);

        Cb = new JComboBox();
        Cb.addItem("ID");
        Cb.addItem("Date");
        Cb.setBounds(200,25,100,30);

        l1 = new JLabel("Search item using: ");
        l1.setBounds(50,-10,50,800);
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("Arial", Font.BOLD, 16));

        String[] cNames = new String[]{"ID", "Date", "Quantity", "Total Money"};

        String[][] Values = new String[count][cNames.length];
        for (int i=0; i<count; i++) {
            Values[i][0] = String.valueOf(salesArray[i].getSalesID());
            Values[i][1] = String.valueOf(salesArray[i].getSalesDate());
            Values[i][2] = String.valueOf(salesArray[i].getSalesQuantity());
            Values[i][3] = String.valueOf(salesArray[i].getTotalMoney());
        }

        DefaultTableModel model= new DefaultTableModel(Values,cNames);
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
                }else if(selected.equals("Date")){
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



        p1.add(back);
        p1.add(Cb);
        p1.add(textField);
        p2.add(scroll);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        salesContainer.add(p1);
        salesContainer.add(p2);
    }
}
