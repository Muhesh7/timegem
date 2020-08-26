package com.example.timegem;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class AlarmViewModel extends AndroidViewModel {
   public MutableLiveData<List<RoomModel>> mData=new MutableLiveData<>();
   private RoomRepo mRoomRepo;

    public AlarmViewModel(@NonNull Application application) {
        super(application);
      mRoomRepo=new RoomRepo(application);
      mData.setValue(mRoomRepo.mList);
    }

    public void insert(RoomModel roomModel)
    {
        mRoomRepo.insert(roomModel);
    }
    public void update(RoomModel roomModel)
    {
        mRoomRepo.update(roomModel);
    }


}
