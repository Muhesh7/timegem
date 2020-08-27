package com.example.timegem;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Data {
    SharedPreferences mSharedPref;
    ArrayList<RoomModel> mModels;

    public Data(Context context) {
        mSharedPref=context.getSharedPreferences("Mys",Context.MODE_PRIVATE);
    }

    public void Save(ArrayList<RoomModel> superHeroes)
    {
        this.mModels =superHeroes;
        SharedPreferences.Editor editor = mSharedPref.edit();
        Gson gson=new Gson();
        String mine=gson.toJson(superHeroes);
        editor.putString("M",mine);
        editor.apply();
    }

    public  ArrayList<RoomModel> Load()
    {    Gson gson =new Gson();
        String mine =mSharedPref.getString("M",null);
        Type type = new TypeToken<ArrayList<RoomModel>>() {}.getType();
        mModels =gson.fromJson(mine,type);
        if(mModels ==null)
        {
            mModels =new ArrayList<>();
        }

        return mModels;
    }

}
