package com.example.wyd.simpleservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

//点击start和stop按钮，观察logcat界面．
//Android系统运行service有两种方式，这个例子是采用的是：
//通过调用Context中的startService()方法．访问者（activity）退出后，service也仍然运行．

public class MainActivity extends AppCompatActivity {

    //Button start,stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = (Button) findViewById(R.id.start);
        Button stop = (Button) findViewById(R.id.stop);

        final Intent intent = new Intent(this, Simpleservice.class);
        //点击start，会创建和启动service，调用onCreate()方法和onStartCommand()方法．
        //当service启动后并没有销毁时，再点击start时，只会调用onStartCommand()方法．
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //由于service和activity一样，都是从Context派生出来的．
                //startService()方法是在Context中定义的．
                startService(intent);
            }
        });
        //点击stop时，会调用onDestroy()方法．
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });

    }
}
