package com.example.wyd.simpleservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by wyd on 17-5-9.
 */

public class Simpleservice extends Service {
    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        System.out.print("Service is Created!\n");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int starting)
    {
        System.out.print("Service is started!\n");
        return START_STICKY;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        System.out.print("Service is Destroyed!\n");
    }
}
