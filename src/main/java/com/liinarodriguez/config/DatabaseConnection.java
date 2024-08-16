package com.liinarodriguez.config;

import com.liinarodriguez.utils.PropertiesLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection connection;

    public DatabaseConnection() {
        String url = PropertiesLoader.get("database");
        String username = PropertiesLoader.get("username");
        String password = PropertiesLoader.get("password");
        String driver = PropertiesLoader.get("driver");

        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established successfully");
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Uh-oh! The connection failed");
        }
    }
    public static Connection getConnection() {
        return connection;
    }
    public void startTransaction(){
        try{
            Connection conn = getConnection();
            conn.setAutoCommit(false);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error to init transaction");
        }
    }
    public void commitTransaction(){
        try{
            Connection conn = getConnection();
            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error to commit transaction");
        }
    }
    public void rollbackTransaction(){
        try{
            Connection conn = getConnection();
            conn.rollback();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error to rollback transaction");
        }
    }
    public void closeConnection(){
        try{
            Connection conn = getConnection();
            if(conn != null && !conn.isClosed()){
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error to close connection");
        }
    }

}
