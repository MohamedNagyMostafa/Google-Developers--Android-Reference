package com.adja.apps.mohamednagy.myapplication;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by Mohamed Nagy on 1/22/2018.
 */

public class MyIntentService extends IntentService {
    public static final String SERVICE_NAME = "MY_SERVICE_NAME";

    public MyIntentService() {
        super(SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getAction();

        MyReminderTask myReminderTask = new MyReminderTask();

        myReminderTask.executeTask(action);

        myReminderTask.response();
    }
}
