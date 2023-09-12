package employee;

import database.Database;
import product.Product;

import javax.swing.plaf.nimbus.State;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class EmployeeActions {
    int ID, amount, CategoryID;

    String firsttName,lastName,phoneNumber,adress;
    Date date;
    Double money;

    int Count() {
        try {
            Statement stmt = Database.connection.createStatement();
            String query = "SELECT COUNT(employee_id) FROM employees";
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


    Employee[] Retrieve(int count) {
        int x=0;
        Employee[] employees=new Employee[count];
        try {
            Statement stmt = Database.connection.createStatement();
            String query = "Select * From employees";
            ResultSet resultSet = stmt.executeQuery(query);

            System.out.println("Attempt");

            System.out.println("result " + resultSet);


            while (resultSet.next()) {


                ID = resultSet.getInt(1);
                firsttName = resultSet.getString(2);
                lastName=resultSet.getString(3);
                phoneNumber = resultSet.getString(4);
                adress = resultSet.getString(5);

                System.out.println(ID + " " + firsttName + " " + lastName + " " + phoneNumber + " " + " " + adress);
                Employee newEmployee = new Employee(ID,firsttName,lastName,phoneNumber,adress);
                employees[x]=newEmployee;
                x++;
            }


            //pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }
}
