package com.liinarodriguez;

import com.liinarodriguez.config.DatabaseConnection;
import com.liinarodriguez.entity.File;
import com.liinarodriguez.repository.FileRepository;
import com.liinarodriguez.utils.FileProcessor;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");
        FileProcessor fp = new FileProcessor();

        DatabaseConnection db = new DatabaseConnection();
        FileRepository fr = new FileRepository(DatabaseConnection.getConnection());

        File file = fp.processFile("./test_files/info.txt");
        try {
            db.startTransaction();
            fr.CreateFileTable();
            fr.delete(0);
            fr.save(file);
            List<File> files = fr.findAll();

            db.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                db.rollbackTransaction();
            } finally {
                try {
                    db.closeConnection();
                } catch (RuntimeException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
