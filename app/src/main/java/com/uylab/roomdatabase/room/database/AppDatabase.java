package com.uylab.roomdatabase.room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.uylab.roomdatabase.model.Student;
import com.uylab.roomdatabase.room.dao.StudentDao;

@Database(entities = {Student.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract StudentDao studentDao();

}
