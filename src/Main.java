import cashier.Cashier;
import login.Login;
import profit.Profit;
import sales.Sales;
import database.Database;
import employee.Employee;
import inventory.Inventory;
import supplier.Supplier;

import javax.swing.*;

public class Main {


    public static void main(String[] args) {



        Database database =new  Database("localhost","SuperMarket1","sa","Nani2239");
        database.instantiate();

        Login l=new Login();
        l.setVisible(true);
    }
}