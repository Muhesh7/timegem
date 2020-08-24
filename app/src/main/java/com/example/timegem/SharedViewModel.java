package com.example.timegem;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    MutableLiveData<String> Start=new MutableLiveData<>();

    public void putData(String s)
    {
        Start.setValue(s);
    }
    public MutableLiveData<String> getData()
    {
        return Start;
    }

}
