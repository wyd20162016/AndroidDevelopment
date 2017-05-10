package com.example.wyd.simplebroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by wyd on 17-5-10.
 */

public class MyReceiver extends BroadcastReceiver {

    //实现BroadcastReceiver只需要重写onReceive方法即可．
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context, "接受到的Intent的Action为: " + intent.getAction() + "\n消息内容是: " +
        intent.getStringExtra("msg"), Toast.LENGTH_LONG).show();
    }
}
