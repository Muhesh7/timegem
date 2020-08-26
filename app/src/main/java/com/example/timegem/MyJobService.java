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

public class MyJobService extends JobService {

   MediaPlayer mPlayer;

    public void alarm(JobParameters params) {

         RoomRepo roomRepo=new RoomRepo(getApplication());
        RoomModel model=roomRepo.getModel(params.getJobId());
        if(model!=null) {
            int id = params.getJobId();
            Uri uri = Uri.parse(model.getSong());
            mPlayer = MediaPlayer.create(this, uri);
            Calendar calendar = Calendar.getInstance();
            CharSequence charSequence = DateFormat.format("hh:mm", calendar);
            String CurrentDate = charSequence.toString();
            RoomModel roomModel = new RoomModel();
            roomModel.setKey(id);
            roomModel.setSong(model.getSong());
            roomModel.setTime(model.getTime());
            roomModel.setStatus("Stop");
            roomRepo.update(roomModel);
            if (model.getTime().equals(CurrentDate)) {
                mPlayer.setLooping(true);
                mPlayer.start();
                pushNoti.push(this).pushNotification(charSequence.toString(), id);
            }
        }
    }


    @Override
    public boolean onStartJob(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                alarm(params);
            }
        }).start();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        mPlayer.stop();
        return true;
    }
}
