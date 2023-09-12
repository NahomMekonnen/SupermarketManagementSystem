package inventory;

import database.Database;
import product.Product;

import javax.swing.plaf.nimbus.State;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InventoryActions {
    int ID, amount, CategoryID;

    String productName;
    Date date;
    Double money;

    int Count() {
        try {
            Statement stmt = Database.connection.createStatement();
            String query = "SELECT COUNT(product_id) FROM Products";
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                return resultSet.getInt(1);
            }
            System.out.println(amount);
            return amount;

        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }


    Product[] Retrieve(int count) {
        int x=0;
        Product[] products=new Product[count];
        try {
            Statement stmt = Database.connection.createStatement();
            String query = "Select * From Products";
            ResultSet resultSet = stmt.executeQuery(query);

            System.out.println("Attempt");

            System.out.println("result " + resultSet);


            while (resultSet.next()) {


                ID = resultSet.getInt(1);
                productName = resultSet.getString(2);
                money = resultSet.getDouble(3);
                amount = resultSet.getInt(4);
                date = resultSet.getDate(5);
                CategoryID = resultSet.getInt(6);
                System.out.println(ID + " " + productName + " " + date + " " + amount + " " + " " + money);
//                tf.setText(ID+" "+productName+" "+date+  " " + amount + " "+  " "  + money);
                Product newProduct = new Product(ID,productName,money,amount,CategoryID,date);
                products[x]=newProduct;
                x++;
            }


            //pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}
