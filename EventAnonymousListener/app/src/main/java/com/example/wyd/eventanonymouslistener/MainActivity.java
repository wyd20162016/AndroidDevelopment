package com.example.wyd.eventanonymouslistener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //注意：
    //由于变量txt在内部类中被访问，所以需要声明个全局的txt,不能在onCreate()方法中去声明．
    //或者在内部类中声明txt
    Button bn;
    EditText txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         bn = (Button) findViewById(R.id.bn);
         txt = (EditText) findViewById(R.id.txt);

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //EditText txt = (EditText) findViewById(R.id.txt);
                txt.setText("bn被单击了！");

            }
        });
    }
}
