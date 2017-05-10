package com.example.wyd.sortedbroadcast;

import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by wyd on 17-5-10.
 */

public class MyReceiver1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context , Intent intent)
    {
        Toast.makeText(context, "接收到的Intent的Action为: " + intent.getAction() +
         "\n消息的内容是: " + intent.getStringExtra("msg"), Toast.LENGTH_LONG).show();

        //Intent一般通过Bundle对象来携带数据

        //创建一个Bundle对象，存入数据
        Bundle bundle = new Bundle();
        bundle.putString("first", "我是第一个BroadcastRecevier发来的.");

        //BroadcastReceiver类中的一个方法，只用于有序广播（sendOrderedBroadcast）
        //该方法的作用是改变当前广播（Broadcast）的结果．将结果存入Broadcast中．
        setResultExtras(bundle);

        //这个方法可以终止Broadcast的传播，比该BroadcastReceiver优先级低的都不会收到信息了
        // 优先级的范围是：-1000~1000
        //abortBroadcast();
    }
}
