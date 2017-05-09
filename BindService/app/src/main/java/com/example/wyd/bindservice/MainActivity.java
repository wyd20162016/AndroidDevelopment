package com.example.wyd.bindservice;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//通过观察logcat来理解调用流程
public class MainActivity extends AppCompatActivity {

    Button bind, unbind, getstatus;
    BindService.MyBinder binder;

    //定义一个ServiceConnection对象
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        //当访问者(activity)与Service连接成功时，回调该方法．
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("---service connected---");
            //获取Service的onBind方法所返回的MyBinder对象．
            binder = (BindService.MyBinder) service;
        }

        //当访问者(activity)与Service异常终止断开时，回调该方法．
        @Override
        public void onServiceDisconnected(ComponentName name) {

            System.out.println("---service disconnected!---");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bind = (Button) findViewById(R.id.bind);
        unbind = (Button) findViewById(R.id.unbind);
        getstatus = (Button) findViewById(R.id.getstatus);

        final Intent intent = new Intent(this, BindService.class);

        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent, conn, Service.BIND_AUTO_CREATE);
            }
        });

        unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(conn);
            }
        });

        getstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Service的count值为:" + binder.getCount(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
