package com.example.timegem;

import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.telephony.SmsManager;
import android.text.format.DateFormat;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

public class MyJobService extends BroadcastReceiver {

   MediaPlayer mPlayer;
   Data mData;

    public void alarm(Context context,int id) {
         if(mPlayer!=null)
         {
             mPlayer.release();
         }
        mData=new Data(context);
        ArrayList<RoomModel> models=mData.Load();
        RoomModel model=models.get(id);
        if(model!=null) {
            Uri uri = Uri.parse(model.getSong());
            mPlayer = MediaPlayer.create(context, uri);
            Calendar calendar = Calendar.getInstance();
            CharSequence charSequence = DateFormat.format("hh:mm", calendar);
            String CurrentDate = charSequence.toString();
            Log.d("DDD",CurrentDate);
                RoomModel roomModel = new RoomModel();
                roomModel.setKey(id);
                roomModel.setSong(model.getSong());
                roomModel.setTime(model.getTime());
                roomModel.setStatus("Stop");
                models.set(id,roomModel);
                mData.Save(models);
                mPlayer.setLooping(true);
                mPlayer.start();
                pushNoti.push(context).pushNotification(charSequence.toString(), id);

        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final int id=intent.getIntExtra("id",0);
        final Context context1=context;
        new Thread(new Runnable() {
            @Override
            public void run() {
                alarm(context1,id);
            }
        }).start();
    }
}
