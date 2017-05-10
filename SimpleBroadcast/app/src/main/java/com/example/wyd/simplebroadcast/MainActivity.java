package com.example.wyd.simplebroadcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//这个例子采用的是在AndroidManifest.xml文件中设置BroadcastReceiver信息
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button send = (Button) findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //设置intent
                intent.setAction("com.example.wyd.SIMPLE_BROADCAST");
                intent.putExtra("msg", "简单的消息");

                //发送广播
                sendBroadcast(intent);
            }
        });
    }
}
