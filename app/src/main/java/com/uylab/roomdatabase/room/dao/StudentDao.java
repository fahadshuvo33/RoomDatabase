package com.uylab.roomdatabase.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.uylab.roomdatabase.model.Student;

import java.util.List;

@Dao
public interface StudentDao {


    @Insert
    public void InsertStudent(Student student);
    @Update
    public void UpdateStudent(Student student);
    @Query ( " DELETE FROM Student WHERE id = :did" )
    public void DeleteStudent(int did);
    @Query (" SELECT * FROM Student")
    public List<Student> getStudent();


}
