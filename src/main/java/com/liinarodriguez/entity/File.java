package com.liinarodriguez.entity;

import java.sql.Blob;

public class File {
    public int id;
    public String name;
    public byte[] binary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getBinary() {
        return binary;
    }

    public void setBinary(byte[] binary) {
        this.binary = binary;
    }
    @Override
    public String toString() {
        return "File{" + "id=" + id + ", name=" + name + '}';
    }
}
