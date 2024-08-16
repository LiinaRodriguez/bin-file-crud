package com.liinarodriguez.repository;

import com.liinarodriguez.config.DatabaseConnection;
import com.liinarodriguez.entity.File;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileRepository implements IFileRepository {
    private Connection conn;

    public FileRepository(Connection connection) {
        this.conn = connection;
    }
    @Override
    public void save(File file) throws SQLException {
        String query = "INSERT INTO files(name, binary) VALUES (?, ?)";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, file.getName());
            stmt.setBytes(2, file.getBinary());
            stmt.executeUpdate();
            System.out.println("File saved"+ file.getName());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public File findById(int id) throws SQLException{
        String query = "SELECT * FROM files WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { // Check if there is a result
                    File file = new File();
                    file.setId(rs.getInt("id"));
                    file.setName(rs.getString("name"));
                    file.setBinary(rs.getBytes("binary"));
                    System.out.println("File " + file.getName() + " found");
                    return file;
                } else {
                    System.out.println("No file found with id " + id);
                    return null; // Or throw an exception if you prefer
                }
            }
        }
    }
    @Override
    public List<File> findAll() throws SQLException{
        String query = "SELECT * FROM files";
        List<File> files = new ArrayList<>();
        try(PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()){
            System.out.println("List");
            while(rs.next()){
                File file = new File();
                file.setId(rs.getInt("id"));
                file.setName(rs.getString("name"));
                file.setBinary(rs.getBytes("binary"));
                files.add(file);
            }
            System.out.println(files.size() + "files found");
        }
        return files;
    }
    @Override
    public void delete(int id) throws SQLException{
        String query = "DELETE FROM files WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("File"+ id + "deleted successfully");
        }
    }
    @Override
    public void update(File file, int id) throws SQLException{
        String query = "UPDATE files SET name = ?, binary = ? WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, file.getName());
            stmt.setBytes(2, file.getBinary());
            stmt.setInt(3, id);
            stmt.executeUpdate();
            System.out.println("File"+ file.getName() + " updated");
        }
    }
    public void CreateFileTable(){
        String query =  "CREATE TABLE IF NOT EXISTS files (" +
                "id int PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR (255)," +
                "binary LONGBLOB" +
                ");";
        try(Statement statement = conn.createStatement()) {
            statement.execute( query);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
