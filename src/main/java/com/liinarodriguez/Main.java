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

        DatabaseConnection db = DatabaseConnection.getInstance();
        FileRepository fr = new FileRepository(db.getConnection());

        File file = fp.processFile("./test_files/info.txt");
        if(file == null ){
            System.out.println("Archivo no válido");
        }
        try {
            db.startTransaction();
            fr.CreateFileTable();
            fr.delete(0);
            assert file != null;
            fr.save(file);
            file.setName("UpdatedName");
            fr.update(file, 4);
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
