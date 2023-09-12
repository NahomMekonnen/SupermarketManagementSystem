package database;

import java.sql.DriverManager;
import java.sql.*;

public class Database {
    private static String hostname;
    private static String username;

    private static String password;
    private static String databaseName;
    public static Connection connection;

    public Database(String hostname,String databaseName,String username,String password){
        Database.hostname = hostname;
        Database.username = username;
        Database.password = password;
        Database.databaseName = databaseName;
    }
    public  void instantiate(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbhm = "jdbc:sqlserver://"+hostname+";databaseName="+databaseName+";encrypt=true;trustServerCertificate=true;User="+username+";Password="+password;
            connection = DriverManager.getConnection(dbhm);
            System.out.println("success");
        }catch (Exception e){
           System.out.println(e);
        }
    }
}
