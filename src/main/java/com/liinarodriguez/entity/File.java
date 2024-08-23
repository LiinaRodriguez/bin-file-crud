package com.liinarodriguez.entity;


public class File {
    private int id;
    private String name;
    private byte[] binary;
    private String type;

    private File(FileBuilder fileBuilder) {
        this.id = fileBuilder.id;
        this.name = fileBuilder.name;
        this.binary = fileBuilder.binary;
        this.type = fileBuilder.type;
    }
    public int getId() {
        return id;
    }
    public static class FileBuilder{
        private int id;
        private String name;
        private byte[] binary;
        private String type;

        public FileBuilder setId(int id) {
            this.id = id;
            return this;
        }
        public FileBuilder setName(String name) {
            this.name = name;
            return this;
        }
        public FileBuilder setBinary(byte[] binary) {
            this.binary = binary;
            return this;
        }
        public FileBuilder setType(String type) {
            this.type = type;
            return this;
        }
        public File build() {
            return new File(this);
        }
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
