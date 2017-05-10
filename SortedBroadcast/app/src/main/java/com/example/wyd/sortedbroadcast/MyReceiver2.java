package com.example.wyd.sortedbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by wyd on 17-5-10.
 */

public class MyReceiver2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
        //getResultExtras()是BroadcastReceiver中的一个方法，返回一个Bundle对象．
        //该方法的作用是检索　由之前BroadcastReceiver设置的（setResultExtras）　当前结果
	//获取上一个接受者存入的数据．
        Bundle bundle = getResultExtras(true);
        String first = bundle.getString("first");

        Toast.makeText(context, "Boradcast发送的Intent的Action: " +intent.getAction()+
                "\n消息内容是: " +intent.getStringExtra("msg") +
                "\n第一个BoradcastReciver存入的消息为: " + first,
                Toast.LENGTH_LONG).show();
    }
}
