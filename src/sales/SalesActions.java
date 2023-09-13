package sales;

import database.Database;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class SalesActions {
    private int salesID, salesQuantity;
    private Date salesDate;
    private Double totalMoney;

    public SalesActions(int salesID, Date salesDate, int salesQuantity, Double totalMoney) {
    }

    public SalesActions() {

    }

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

    Sales[] Retrieve(int count) {
        int x=0;
        Sales[] salesArray = new Sales[count];
        try {
            Statement stmt = Database.connection.createStatement();
            String query = "Select * From sales";
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                salesID = resultSet.getInt(1);
                salesDate = resultSet.getDate(2);
                salesQuantity = resultSet.getInt(3);
                totalMoney = resultSet.getDouble(4);

                Sales newSales = new Sales(salesID, salesDate, salesQuantity, totalMoney);
                salesArray[x] = newSales;
                x++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salesArray;
    }

    double calculateTotalSales() {
        double totalSales = 0;
        int count = Count();
        Sales[] salesArray = Retrieve(count);
        for (Sales sale : salesArray) {
            totalSales += sale.getTotalMoney();
        }
        return totalSales;
    }
}
