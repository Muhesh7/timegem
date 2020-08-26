package com.example.timegem;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

public class sound {

    private AudioAttributes mAttributes;
   private static int tickSound;
   private final int max=1;
   private static SoundPool mSoundpool;

   public sound(Context context)
   {

           mAttributes=new AudioAttributes.Builder()
                   .setUsage(AudioAttributes.USAGE_GAME)
                   .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                   .build();
           mSoundpool=new SoundPool.Builder().setMaxStreams(max)
                   .setAudioAttributes(mAttributes)
                   .build();


       tickSound=mSoundpool.load(context,R.raw.click,1);
   }
   public  void tick()
   {
       mSoundpool.play(tickSound,1.0f,1.0f,0,0,1.0f);

   }
    public  void stoptick()
    {
        mSoundpool.stop(tickSound);
    }

}
