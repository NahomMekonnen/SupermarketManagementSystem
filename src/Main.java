import database.Database;
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
//        Admin ad=new Admin();
//        ad.setVisible(true);
///        Admin n=new Admin();
//        n.setVisible(true);


    }
}