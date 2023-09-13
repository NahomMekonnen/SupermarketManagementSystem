package profit;

import database.Database;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProfitActions {
    private double totalSales;
    private final double TOTAL_SALARIES = 9000.0; // we can replace this with the actual total salaries

    double calculateTotalSales() {
        try {
            Statement stmt = Database.connection.createStatement();
            String query = "SELECT SUM(total_amount) FROM sales";
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                return resultSet.getDouble(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    double calculateProfit() {
        totalSales = calculateTotalSales();
        return totalSales - TOTAL_SALARIES;
    }
}
