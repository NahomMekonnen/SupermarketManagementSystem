package supplier;

import database.Database;
import product.Product;


import java.sql.*;

public class SupplierActions {
    int id;
    String firstName,lastName,phoneNumber,itemSupplied,address;

 public   void Add(Supplier supplier)  {
        try {

            PreparedStatement statement= Database.connection.prepareStatement("Insert into suppliers C(?,?,?,?,?)");
            statement.setString(1,supplier.getFirstName());
            statement.setString(2,supplier.getLastName());
            statement.setString(3,supplier.getPhoneNumber());
            statement.setString(4,supplier.getItemSupplied());
            statement.setString(5,supplier.getAddress());
            System.out.println("Added");
            statement.executeUpdate();

        }catch (Exception e){
            System.out.println(e);
        }

    }
    public void Remove(Supplier supplier)
    {
        try{
            PreparedStatement statement=Database.connection.prepareStatement("DELETE FROM suppliers WHERE supplier_id= "+ supplier.getSupplier_id()+"AND supplier_firstName= '"+ supplier.getFirstName()+"'");
            System.out.println("Removed");
            statement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void Supply(Product product){
        try {

            PreparedStatement statement= Database.connection.prepareStatement("INSERT INTO products VALUES(?,?,?,?,?,?)");
            statement.setInt(1,product.getProduct_id());
            statement.setString(2,product.getProduct_name());
            statement.setDouble(3,product.getPrice());
            statement.setInt(4,product.getAmount());
            statement.setDate(5, (Date) product.getDate());
            statement.setInt(6,product.getCategory_id());
            System.out.println("Added");
            statement.executeUpdate();

        }catch (Exception e){
            System.out.println(e);
        }

    }

    int Count() {
        int num = 0;
        try {
            Statement stmt = Database.connection.createStatement();
            String query = "SELECT COUNT(supplier_id) FROM Suppliers";
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                return resultSet.getInt(1);
            }
            System.out.println(num);
            return num;

        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }


    Supplier[] Retrieve(int count) {
        int x=0;
        Supplier[] suppliers=new Supplier[count];
        try {
            Statement statement = Database.connection.createStatement();
            String query = "Select * From Suppliers";
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("Attempt");

            System.out.println("result " + resultSet);


            while (resultSet.next()) {


                id = resultSet.getInt(1);
                firstName = resultSet.getString(2);
                lastName = resultSet.getString(3);
                phoneNumber = resultSet.getString(4);
                itemSupplied = resultSet.getString(5);
                address = resultSet.getString(6);
                System.out.println(id + " " + firstName + " " + lastName + " " + phoneNumber + " " +  address + " " + itemSupplied);

                Supplier newSupply = new Supplier(id,firstName,lastName,phoneNumber,itemSupplied,address);
                suppliers[x]=newSupply;
                x++;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return suppliers;
    }
}
