package com.liinarodriguez;

import com.liinarodriguez.config.DatabaseConnection;
import com.liinarodriguez.entity.File;
import com.liinarodriguez.repository.FileRepository;
import com.liinarodriguez.utils.FileProcessor;

public class Main {
    public static  void main(String[] args){
        System.out.println("Hello world");
        FileProcessor fp = new FileProcessor();
        FileRepository fr = new FileRepository();
        DatabaseConnection db = new DatabaseConnection();

        File file = fp.processFile("./test_files/info.txt");
        try{
            db.startTransaction();
            fr.save(file);
            fr.findAll();
            db.commitTransaction();
        }catch (Exception e){

            }
        }


    }
