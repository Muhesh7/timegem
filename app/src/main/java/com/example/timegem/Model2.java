package com.example.timegem;



public class Model2 {

    String number;

    String msg;

    String timestring;

    long  time;

    String status;

    public int key;

    public String getTimestring() {
        return timestring;
    }

    public void setTimestring(String timestring) {
        this.timestring = timestring;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Model2() {
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getNumber() {
        return number;
    }

    public String getMsg() {
        return msg;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
