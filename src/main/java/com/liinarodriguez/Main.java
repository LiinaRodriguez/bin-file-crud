package com.liinarodriguez;

import com.liinarodriguez.config.DatabaseConnection;
import com.liinarodriguez.entity.File;
import com.liinarodriguez.repository.FileRepository;
import com.liinarodriguez.FileProcessor.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");
        DirectoryScanner directoryScanner = new DirectoryScanner();

        DatabaseConnection db = DatabaseConnection.getInstance();
        FileRepository fr = new FileRepository(db.getConnection());

        List<File> toSave = directoryScanner.ScanDirectory("./test_files/");

        try {
            db.startTransaction();
            fr.CreateFileTable();
            fr.delete(0);

            //Saving all img and txt files from the given path
            for (File file : toSave) {
                assert file != null;
                fr.save(file);
            }

           File id_5 = fr.findById(5);
           System.out.println(id_5.toString());

            List<File> files = fr.findAll();
            files.forEach(fl -> System.out.println(fl.toString()));
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
