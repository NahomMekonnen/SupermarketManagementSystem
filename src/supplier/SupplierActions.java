package supplier;

import database.Database;
import inventory.InventoryActions;
import product.Product;


import java.sql.*;

public class SupplierActions {
    int id,category;
    String firstName,lastName,phoneNumber,address;

 public   void Add(Supplier supplier)  {
        try {

            PreparedStatement statement= Database.connection.prepareStatement("INSERT INTO suppliers VALUES(?,?,?,?,?)");
            statement.setString(1,supplier.getFirstName());
            statement.setString(2,supplier.getLastName());
            statement.setString(3,supplier.getPhoneNumber());
            statement.setInt(4,supplier.getCategory_id());
            statement.setString(5,supplier.getAddress());
            System.out.println("Added");
            statement.executeUpdate();

        }catch (Exception e){
            System.out.println(e);
        }

    }
    public void Update(Product product){
        try {
            PreparedStatement statement=Database.connection.prepareStatement("UPDATE Products SET product_quantity = "+ product.getAmount()  +" WHERE product_id = " + product.getProduct_id() );
            System.out.println("Updated");
            statement.executeUpdate();

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void Remove(Product product)  {
        try {
            PreparedStatement statement=Database.connection.prepareStatement("DELETE FROM Products WHERE product_id = "+ product.getProduct_id() +" AND product_name = '" + product.getProduct_name() +"'");
            System.out.println("Removed");
            statement.executeUpdate();

        } catch (Exception e){
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
                        PreparedStatement statement= Database.connection.prepareStatement("INSERT INTO products VALUES(?,?,?,?,?)");
                        statement.setString(1,product.getProduct_name());
                        statement.setDouble(2,product.getPrice());
                        statement.setInt(3,product.getAmount());
                        statement.setDate(4, (Date) product.getDate());
                        statement.setInt(5,product.getCategory_id());
                        System.out.println("Added");
                        statement.executeUpdate();

                        InventoryActions inventoryActions=new InventoryActions();
                        int num = inventoryActions.Count();
                        Product[] products=inventoryActions.Retrieve(num);
                        for (int i=0;i<num;i++){
                            for(int j=i+1;j<num;j++){
                                if(products[i].getProduct_name()==products[j].getProduct_name()&&products[i].getCategory_id()==products[i].getCategory_id()){
                                    products[i].setAmount(products[i].getAmount()+products[j].getAmount());
                                    Update(products[i]);
                                    Remove(products[j]);
                                }
                            }
                        }
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
                category = resultSet.getInt(5);
                address = resultSet.getString(6);
                System.out.println(id + " " + firstName + " " + lastName + " " + phoneNumber + " " +  address + " " + category);

                Supplier newSupply = new Supplier(id,firstName,lastName,phoneNumber,category,address);
                suppliers[x]=newSupply;
                x++;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return suppliers;
    }
}
