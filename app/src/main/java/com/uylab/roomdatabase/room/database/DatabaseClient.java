package com.uylab.roomdatabase.room.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {private Context mCtx;
    private static DatabaseClient mInstance;
    private static final String DB_NAME="StudentData";
    //our app database object
    private AppDatabase appDatabase;

    private DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, DB_NAME).build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
