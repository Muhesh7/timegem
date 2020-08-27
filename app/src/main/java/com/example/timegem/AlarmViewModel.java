package com.example.timegem;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class AlarmViewModel extends AndroidViewModel {
   public MutableLiveData<ArrayList<RoomModel>> mData=new MutableLiveData<>();
    Data data;
   private RoomRepo mRoomRepo;

    public AlarmViewModel(@NonNull Application application) {
        super(application);
      mRoomRepo=new RoomRepo(application);
        data=new Data(application);
        mData.setValue(data.Load());
    }

    public void insert(RoomModel roomModel)
    {
        mRoomRepo.insert(roomModel);
    }
    public void update(RoomModel roomModel)
    {
        mRoomRepo.update(roomModel);
    }

    public MutableLiveData<ArrayList<RoomModel>> getter()
    {
        return mData;
    }

    public void  setter(ArrayList<RoomModel> roomModels)
    {
      data.Save(roomModels);
    }


}
