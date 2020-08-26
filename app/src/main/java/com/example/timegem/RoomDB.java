package com.example.timegem;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {RoomModel.class},version = 3)
public abstract class RoomDB extends RoomDatabase {

    private static RoomDB instance;
    public abstract DAO mDAO();
    public static RoomDB getInstance(Context context)
    {
        if(instance==null)
        {
            instance= Room.databaseBuilder(context,RoomDB.class,"alarm_table")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
