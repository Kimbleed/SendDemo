package com.example.awesoman.senddemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Awesome on 2017/12/13.
 */

public class ResponseBroadcastReceiver  extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        long response = intent.getLongExtra("response",-1);
        Intent newIntent = new Intent(context,MainActivity.class);
        newIntent.putExtra("response",response);
        context.startActivity(newIntent);
    }
}
