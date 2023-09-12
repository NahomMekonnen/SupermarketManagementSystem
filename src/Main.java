import database.Database;
import employee.Employee;
import inventory.Inventory;

import javax.swing.*;

public class Main {


    public static void main(String[] args) {
//     Login l=new Login();
//     l.setVisible(true);

        Database database =new  Database("localhost","SuperMarket","sa","Nohom420");
        database.instantiate();
        Inventory inventory=new Inventory();
        inventory.setVisible(true);


    //    Supplier supplier = new Supplier();

  //      JFrame frame = new JFrame("Suppliers");
    //    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        supplier.addComponentsToPane(frame.getContentPane());

      //  frame.setSize(400, 400);
       // frame.setVisible(false);

//        Admin ad=new Admin();
//        ad.setVisible(true);
///        Admin n=new Admin();
//        n.setVisible(true);
        Employee e=new Employee();
        e.setVisible(true);


        Sales sales = new Sales(10);
        sales.addTransaction("zaza", 12, 69.4, 0.15); //this is just an example to test stuff out, you can import the actual data from the DB


        double totalPrice = sales.calculateTotalPrice();
        System.out.println("Total price: $" + totalPrice);

        sales.generateReport();

    }
}