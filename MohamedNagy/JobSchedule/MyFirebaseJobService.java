package com.adja.apps.mohamednagy.myapplication.job.schedule;

import android.app.job.JobParameters;

import com.adja.apps.mohamednagy.myapplication.Intent.service.MyReminderTask;
import com.firebase.jobdispatcher.JobService;
import android.content.Context;
import android.os.AsyncTask;

import com.adja.apps.mohamednagy.myapplication.Intent.service.MyIntentService;
import com.adja.apps.mohamednagy.myapplication.MainActivity;

/**
 * Created by Mohamed Nagy on 1/25/2018.
 */
// It work on mainthread but we are going to make it work on background thread.
    //com.firebase.jobdispatcher.JobService
public class MyFirebaseJobService extends JobService {

    private AsyncTask mBackgroundTask;


    @Override
    public boolean onStartJob(final com.firebase.jobdispatcher.JobParameters job) {
        mBackgroundTask = new AsyncTask() {
            // we have to announce jobservice when the thread done.
            @Override
            protected void onPostExecute(Object o) {
                jobFinished(job,false);
                // false as we don't need reschedule
            }

            @Override
            protected Object doInBackground(Object[] objects) {
                Context context = MyFirebaseJobService.this;
                // Insert wanted code.
                // use context if it require.
                return null;

            }
        };
        mBackgroundTask.execute();
        // true mean that there's a job is doing.
        return true;
    }
    /// called when job is intrupted by something such as wifi disconnected during downloading.
    @Override
    public boolean onStopJob(com.firebase.jobdispatcher.JobParameters job) {

        if(mBackgroundTask != null)
            mBackgroundTask.cancel(true);
        // true mean resume the job if the intruppted is done.

        return true;
    }
}
