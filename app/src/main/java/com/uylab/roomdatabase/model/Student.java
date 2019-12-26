package com.uylab.roomdatabase.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Student {
    @PrimaryKey
    private int id;
    private String name;
    private String dept;
    private String address;

    public Student() {
    }

    @Ignore
    public Student(int id, String name, String dept, String address) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.address = address;
    }

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

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
