package com.akila.epic.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static Connection connection=null;
    public static Connection getDB(){
        if(connection!=null){
            return connection;
        }else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection= DriverManager.getConnection("jdbc:mysql://localhost/epic?useSSL=false","root","");

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

}
