package com.example.awesoman.senddemo;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText et,et1;
    TextView tv;

    ResponseBroadcastReceiver responseBroadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.et);
        et1 = (EditText) findViewById(R.id.et1);
        tv = (TextView)findViewById(R.id.tv);

        IntentFilter intentFilter = new IntentFilter("android.intent.action.ResponseBroadcastReceiver");
        responseBroadcastReceiver =new ResponseBroadcastReceiver();
        registerReceiver(responseBroadcastReceiver,intentFilter);


        /*
            1.
            在输入框 et  和  et1 中输入数字
            点击btn按钮，将框内字符转成数字，
            作值放到intent中，发送广播
         */
        ((Button)findViewById(R.id.btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long num = Long.parseLong(et.getText().toString());
                long num1 = Long.parseLong(et1.getText().toString());
                Intent intent = new Intent();
                intent.setAction("android.intent.action.MyBroadcastReceiver");
                intent.putExtra("a",num);
                intent.putExtra("b",num1
                );
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("cen","onNewIntent");
        /*
               5.终点，从ResponseBroadcastReceiver 接收数据
         */
        long response = intent.getLongExtra("response",-1);
        tv.setText("请岑俊辉吃饭"+response+"次");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(responseBroadcastReceiver);
    }
}
