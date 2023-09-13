package sales;

import database.Database;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class SalesActions {
    private int salesID, salesQuantity;
    private Date salesDate;
    private Double totalMoney;


    int Count() {
        try {
            Statement stmt = Database.connection.createStatement();
            String query = "SELECT COUNT(sale_id) FROM sales";
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                return resultSet.getInt(1);
            }
            return 0;

        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    sales.Sales[] Retrieve(int count) {
        int x=0;
        sales.Sales[] salesArray = new sales.Sales[count];
        try {
            Statement stmt = Database.connection.createStatement();
            String query = "Select * From sales";
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                salesID = resultSet.getInt(1);
                salesDate = resultSet.getDate(2);
                salesQuantity = resultSet.getInt(3);
                totalMoney = resultSet.getDouble(4);

                sales.Sales newSales = new sales.Sales(salesID, salesDate, salesQuantity, totalMoney);
                salesArray[x] = newSales;
                x++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salesArray;
    }
}
