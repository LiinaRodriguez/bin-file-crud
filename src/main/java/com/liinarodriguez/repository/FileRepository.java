package com.liinarodriguez.repository;

import com.liinarodriguez.config.DatabaseConnection;
import com.liinarodriguez.entity.File;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileRepository implements IFileRepository {
    @Override
    public void save(File file) throws SQLException {
        String query = "INSERT INTO files(id, name, binary) VALUES (?, ?, ?)";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, file.getId());
            stmt.setString(2, file.getName());
            stmt.setBytes(3, file.getBinary());
            stmt.executeUpdate();
            System.out.println("File saved"+ file.getName());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public File findById(String id) throws SQLException{
        String query = "SELECT * FROM files WHERE id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery(query)){
            File file = new File();
            file.setId(rs.getString("id"));
            file.setName(rs.getString("name"));
            file.setBinary(rs.getBytes("binary"));
            System.out.println("File "+ file.getName() + " found");
            return file;
        }
    }
    @Override
    public List<File> findAll() throws SQLException{
        String query = "SELECT * FROM files";
        List<File> files = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()){
            while(rs.next()){
                File file = new File();
                file.setId(rs.getString("id"));
                file.setName(rs.getString("name"));
                file.setBinary(rs.getBytes("binary"));
                files.add(file);
            }
            System.out.println(files.size() + "files found");
        }
        return files;
    }
    @Override
    public void delete(String id) throws SQLException{
        String query = "DELETE FROM files WHERE id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("File"+ id + "deleted successfully");
        }
    }
    @Override
    public void update(File file) throws SQLException{
        String query = "UPDATE files SET name = ?, binary = ? WHERE id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, file.getName());
            stmt.setBytes(2, file.getBinary());
            stmt.setString(3, file.getId());
            stmt.executeUpdate();
            System.out.println("File"+ file.getName() + " updated");
        }
    }
    public void CreateFileTable(){
        String query =  "CREATE TABLE IF NOT EXISTS files (" +
                "id VARCHAR (255) PRIMARY KEY," +
                "name VARCHAR (255)," +
                "binary LONGBLOB" +
                ");";
        try(Connection conn = DatabaseConnection.getConnection();
            Statement statement = conn.createStatement()) {
            statement.execute( query);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
