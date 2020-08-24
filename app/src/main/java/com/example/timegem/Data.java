package com.example.timegem;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Data {
    SharedPreferences mSharedPref;
    ArrayList<Model2> mModels;

    public Data(Context context) {
        mSharedPref=context.getSharedPreferences("MyHeros",Context.MODE_PRIVATE);
    }

    public void Save(ArrayList<Model2> superHeroes)
    {
        this.mModels =superHeroes;
        SharedPreferences.Editor editor = mSharedPref.edit();
        Gson gson=new Gson();
        String mine=gson.toJson(superHeroes);
        editor.putString("MyHeroes",mine);
        editor.apply();
    }

    public  ArrayList<Model2> Load()
    {    Gson gson =new Gson();
        String mine =mSharedPref.getString("MyHeroes",null);
        Type type = new TypeToken<ArrayList<Model2>>() {}.getType();
        mModels =gson.fromJson(mine,type);
        if(mModels ==null)
        {
            mModels =new ArrayList<>();
        }

        return mModels;
    }
}
