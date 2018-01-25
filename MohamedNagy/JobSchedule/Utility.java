package com.adja.apps.mohamednagy.myapplication.job.schedule;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TimeUtils;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;

/**
 * Created by Mohamed Nagy on 1/25/2018.
 */

public class Utility {
    private static final int DELAY_START_MINITS = 15;
    private static final int INTERVAL_SECONDS = (int) (TimeUnit.MINUTES.toSeconds(DELAY_START_MINITS));
    private static final int SYNC_SECONDS = INTERVAL_SECONDS;

    private static final String JOB_TAG = "job tag";
    private static boolean isSetup;

    synchronized public static void schedule(@NonNull final Context context){
        if(isSetup) return;
        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher firebaseJobDispatcher = new FirebaseJobDispatcher(driver);
        Job constraint = firebaseJobDispatcher.newJobBuilder()
                .setService(MyFirebaseJobService.class)
                .setConstraints(Constraint.DEVICE_CHARGING)
                .setLifetime(Lifetime.FOREVER) /// work after reboot
                .setRecurring(true)
                .setTag(JOB_TAG)
                .setTrigger(Trigger.executionWindow(
                        INTERVAL_SECONDS,
                        INTERVAL_SECONDS + SYNC_SECONDS
                ))
                .setReplaceCurrent(true) //remade .. replace the previous one.
                .build();

        firebaseJobDispatcher.schedule(constraint);

        isSetup = true;

    }
}
