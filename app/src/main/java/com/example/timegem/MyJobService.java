package com.example.timegem;

import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;

public class MyJobService extends JobService {

    sound sound;
    @Override
    public boolean onStartJob(final JobParameters params) {
        sound=new sound(MyJobService.this);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sendsms(params);
                }
            }).start();

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        sound.stoptick();
        return true;
    }
    public void sendsms(JobParameters params) {
     sound.tick();
    }
}
