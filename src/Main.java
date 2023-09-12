import database.Database;
import login.*;
import employee.Employee;
import inventory.Inventory;

import javax.swing.*;

public class Main {


    public static void main(String[] args) {
//     Login l=new Login();
//     l.setVisible(true);

        Database database =new  Database("localhost","SuperMarket","sa","Nani2239");
        database.instantiate();

        Login l=new Login();
        l.setVisible(true);


    }
}