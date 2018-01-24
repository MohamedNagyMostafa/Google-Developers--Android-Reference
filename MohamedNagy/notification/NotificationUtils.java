package com.adja.apps.mohamednagy.myapplication.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.adja.apps.mohamednagy.myapplication.MainActivity;
import com.adja.apps.mohamednagy.myapplication.R;

/**
 * Created by Mohamed Nagy on 1/23/2018.
 */

public class NotificationUtils {

    private static final int PENDING_INTENT_ID = 1;
    private static final String NOTIFICATION_ID = "my notification";
    private static final String NOTIFICATION_CHANNEL_NAME = "my notification channel";

    private static PendingIntent contentIntent(Context context){
        Intent notificationActivity = new Intent(context, MainActivity.class);

        // FLAG_CANCEL_CURRENT: if the described PendingIntent already exists,
        // the current one should be canceled before generating a new one.

        // FLAG_IMMUTABLE:  the created PendingIntent should be immutable. This means that
        // the additional intent argument passed to the send methods to fill in unpopulated
        // properties of this intent will be ignored.

        // FLAG_NO_CREATE: if the described PendingIntent does not already exist,
        // then simply return null instead of creating it.

        // FLAG_ONE_SHOT: this PendingIntent can be used only once.

        // FLAG_UPDATE_CURRENT: if the described PendingIntent already exists,
        // then keep it but replace its extra data with what is in this new Intent.

        return PendingIntent.getActivity(
                context,
                PENDING_INTENT_ID,
                notificationActivity,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }

    private static Bitmap notificationIconDecode(Context context){
        Resources resources = context.getResources();

        Bitmap notificationIcon = BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_background);

        return notificationIcon;
    }


    public static void pushNotification(Context context){
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        // In case of android Oreo we have set notification at channel.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel =
                    new NotificationChannel(
                            NOTIFICATION_ID,
                            NOTIFICATION_CHANNEL_NAME,
                            NotificationManager.IMPORTANCE_HIGH // work on O or later
                    );
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context,
                        NOTIFICATION_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(notificationIconDecode(context))
                .setContentTitle("notification title")
                .setContentText("notification text")
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(NOTIFICATION_CHANNEL_NAME))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .setAutoCancel(true); // remove notification when user clicks on it.

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN &&
                Build.VERSION.SDK_INT < Build.VERSION_CODES.O){
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }

        notificationManager.notify(PENDING_INTENT_ID, notificationBuilder.build());
    }
}
