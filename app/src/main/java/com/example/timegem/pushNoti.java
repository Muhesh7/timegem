package com.example.timegem;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class pushNoti {
    private static final String HeroID="BOSS";
    private static final int notId=125;

    private static pushNoti sPushNotification;
    private Context mContext;

    private pushNoti(Context context) {
        this.mContext = context;
    }

    public static pushNoti push(Context context) {
     if(sPushNotification==null)
     {
         sPushNotification=new pushNoti(context);
     }
     return sPushNotification;
    }
    public void pushNotification(String s,int id)
    { createNotificationChannel();
        Intent intent=new Intent(mContext.getApplicationContext(),MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(mContext.getApplicationContext(),0,intent,0);


        Notification notification=new NotificationCompat.Builder(mContext,"BOSS")
                .setSmallIcon(R.drawable.sms)
                .setContentText("Alarm at \n"+s)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .build();
        NotificationManagerCompat.from(mContext).notify(notId,notification);
    }
    private void createNotificationChannel()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            String name= "SMS";
            String desc="Message received";
            int importance= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel  channel=new NotificationChannel(HeroID,name,importance);
            channel.setDescription(desc);
            NotificationManager notificationManager=(NotificationManager)
                    mContext.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
