package com.example.timegem;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DAO {

    @Insert
    void insert(RoomModel model);

    @Delete
    void delete(RoomModel model);

    @Update
    void update(RoomModel model);

    @Query("SELECT * FROM RoomModel")
    List<RoomModel> getAll();

    @Query("SELECT * FROM RoomModel")
    LiveData<List<RoomModel>> getLiveAll();
}
