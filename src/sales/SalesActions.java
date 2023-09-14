package sales;

import database.Database;
import employee.Employee;

import java.sql.Date;
import java.sql.PreparedStatement;
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

    public void Add(Sales sales){
        try{
            PreparedStatement statement= Database.connection.prepareStatement("INSERT INTO Sales Values(?,?,?)");
            statement.setDate(1,(Date)sales.getSalesDate());
            statement.setInt(2,sales.getSalesQuantity());
            statement.setDouble(3,sales.getTotalMoney());

            System.out.println("Added");
            statement.executeUpdate();


        } catch (Exception e){
            System.out.println(e);
        }

    }
    Sales[] Retrieve(int count) {
        int x=0;
        Sales[] salesArray = new Sales[count];
        try {
            Statement stmt = Database.connection.createStatement();
            String query = "Select * From sales";
            ResultSet resultSet = stmt.executeQuery(query);

            System.out.println("Attempt");

            System.out.println("result " + resultSet);


            while (resultSet.next()) {
                salesID = resultSet.getInt(1);
                salesDate = resultSet.getDate(2);
                salesQuantity = resultSet.getInt(3);
                totalMoney = resultSet.getDouble(4);

                System.out.println(salesID + " " + salesDate + " " + salesQuantity + " " + totalMoney);
                Sales newSales = new Sales(salesID, salesDate, salesQuantity, totalMoney);
                salesArray[x] = newSales;
                x++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salesArray;
    }

}
