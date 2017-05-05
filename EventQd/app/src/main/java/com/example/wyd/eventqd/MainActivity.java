package com.example.wyd.eventqd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//这个例子是用来说明　Android事件处理的，采用的是基于监听的事件处理（采用内部类形式实现）
//基于监听的事件处理有四种方法：
//内部类形式：一般常用
//外部类形式：少见
//Activity本身作为监听器类：不常用
//匿名类：常用

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取事件源，事件源
        Button bn = (Button) findViewById(R.id.bn);
        //注册事件监听器
        bn.setOnClickListener(new MyClickListener());
    }

    //采用内部类的形式实现监听器
    class MyClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            EditText txt = (EditText) findViewById(R.id.txt);
            txt.setText("bn按钮被点击了！");
        }
    }
}
