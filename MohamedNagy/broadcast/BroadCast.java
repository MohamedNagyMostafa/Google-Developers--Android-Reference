package com.adja.apps.mohamednagy.myapplication.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by Mohamed Nagy on 1/26/2018.
 */

public class BroadCast {
    private IntentFilter myBroadCastIntentFilter;
    private MyBroadCastReceiver myBroadCastReceiver;

    public BroadCast(String... actions) {
        //Ex. Intent.ACTION_POWER_CONNECTED
        //    Intent.ACTION_POWER_DISC ONNECTED
        for (String action : actions) {
            myBroadCastIntentFilter.addAction(action);
        }

        myBroadCastReceiver = new MyBroadCastReceiver();


    }

    private class MyBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // Check condition Intent.ACTION_POWER_CONNECTED or ntent.ACTION_POWER_DISCONNECTED
        }
    }

    private void execute(Context context){
        context.registerReceiver(myBroadCastReceiver, myBroadCastIntentFilter);
    }

    private void stop(Context context){
        context.unregisterReceiver(myBroadCastReceiver);
    }
}
