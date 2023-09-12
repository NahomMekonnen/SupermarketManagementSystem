import database.Database;
import employee.Employee;
import inventory.Inventory;

import javax.swing.*;

public class Main {


    public static void main(String[] args) {

        Database database =new  Database("localhost","SuperMarket","sa","Nani2239");
        database.instantiate();
//        Inventory inventory=new Inventory();
//        inventory.setVisible(true);
        Employee e=new Employee();
        e.setVisible(true);



    }
}