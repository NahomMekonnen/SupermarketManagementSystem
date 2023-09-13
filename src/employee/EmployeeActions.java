package employee;

import com.sun.jdi.IntegerType;
import database.Database;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.sql.*;


public class EmployeeActions {
    int ID, amount, CategoryID;

    String firstName,lastName,phoneNumber, address;
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
    public void Remove(Employee employee)  {
        try {
            PreparedStatement statement=Database.connection.prepareStatement("DELETE FROM employees WHERE employee_id = "+ employee.getEmployee_Id() +" AND employee_firstName = '" + employee.getFirstName() +"'");
            System.out.println("Removed");
            statement.executeUpdate();

        } catch (Exception e){
            System.out.println(e);
        }
    }
    public void Add(Employee employee){
        try{
            PreparedStatement statement= Database.connection.prepareStatement("INSERT INTO employees Values(?,?,?,?)");
            statement.setString(1,employee.getFirstName());
            statement.setString(2,employee.getLastName());
            statement.setString(3,employee.getPhoneNumber());
            statement.setString(4,employee.getAddress());
            System.out.println("Added");
            statement.executeUpdate();


        } catch (Exception e){
            System.out.println(e);
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
                firstName = resultSet.getString(2);
                lastName=resultSet.getString(3);
                phoneNumber = resultSet.getString(4);
                address = resultSet.getString(5);

                System.out.println(ID + " " + firstName + " " + lastName + " " + phoneNumber + " " + " " + address);
                Employee newEmployee = new Employee(ID, firstName,lastName,phoneNumber, address);
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
