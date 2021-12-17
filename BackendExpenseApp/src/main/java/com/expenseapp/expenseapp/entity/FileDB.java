package com.expenseapp.expenseapp.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Arrays;


@Entity
    @Table(name = "files")
    public class FileDB {
        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        @Column(name = "id")
        private String id;

        @Column(name = "name")
        private String name;

        @Column(name = "type")
        private String type;

        @Lob
        @Column(name = "data")
        private byte[] data;

        @Column(name = "rb_id")
        private int rid;

    public FileDB(String name, String type, byte[] data, int rid) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.rid = rid;
    }

    public FileDB() {
        }

        public FileDB(String id, String name, String type, byte[] data, int rid) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.data = data;
            this.rid = rid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public byte[] getData() {
            return data;
        }

        public void setData(byte[] data) {
            this.data = data;
        }

        public int getRid() {
            return rid;
        }

        public void setRid(int rid) {
            this.rid = rid;
        }

    @Override
    public String toString() {
        return "FileDB{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", data=" + Arrays.toString(data) +
                ", rid=" + rid +
                '}';
    }
}
