package com.example.wyd.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by wyd on 17-5-9.
 */

/**
 * IBinder是接口，你看前面有个I，理解为接口，他的实现类必须自己编写代码逻辑来实现功能。
 * Binder是实现了IBinder的具体实现类，他具有具体的功能，继承了Binder的类就是IBinder对象了。
 */
public class BindService extends Service {
    private int count;
    private boolean quit;

    private MyBinder binder = new MyBinder();
    //一般采用继承Binder的方式实现自己的
    public class MyBinder extends Binder
    {
        public int getCount()
        {
            //获取service的运行状态:count
            return count;
        }

    }

    //绑定该service时，会回调该方法．
    @Override
    public IBinder onBind(Intent intent)
    {
        System.out.print("Service is Binded!\n");
        return binder;
    }

    //创建service时，会回调该方法．
    @Override
    public void onCreate()
    {
        super.onCreate();
        System.out.print("Service is Created!\n");

        new Thread()
        {
            public void run()
            {
                while(!quit)
                {
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException ex)
                    {

                    }
                    count++;
                }
            }
        }.start();
    }

    //断开连接该service时，会回调该方法．
    @Override
    public boolean onUnbind(Intent intent)
    {
        System.out.print("Service is Unbinded!\n");
        return true;
    }

    //service关闭之前会回调该方法．
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        this.quit = true;
        System.out.println("Service is Destroyed!");
    }
}
