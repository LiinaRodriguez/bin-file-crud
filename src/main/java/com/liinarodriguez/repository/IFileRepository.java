package com.liinarodriguez.repository;

import com.liinarodriguez.entity.File;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IFileRepository {
    void save(File file) throws SQLException;
    File findById(String id) throws SQLException;
    List<File> findAll() throws SQLException;
    void delete(String id) throws SQLException;
    void update(File file) throws SQLException;
}
