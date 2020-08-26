package com.example.timegem;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class RoomModel {
    @ColumnInfo(name = "song")
    String song;
    @ColumnInfo(name = "time")
    String time;
    @ColumnInfo(name = "status")
    String status;
    @PrimaryKey(autoGenerate = false)
    int key;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
