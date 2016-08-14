package me.thenpush.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import me.thenpush.PushReceiver;
import me.thenpush.R;
import me.thenpush.iamessage.MessageViewer;

/**
 * Created by pappacena on 31/07/16.
 */
public class MessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d("thenpush.me", "From: " + remoteMessage.getFrom());

        String stepUuid = remoteMessage.getData().get("thenpush_step_uuid");
        if (stepUuid == null) {
            Log.d("thenpush.me", "Not a ThenPush notification. Skipping.");
            return;
        }

        Log.d("thenpush.me", "Showing notification for step " + stepUuid);

        this.notifyReceipt(remoteMessage);
        this.showNotification(remoteMessage);
    }

    private void notifyReceipt(RemoteMessage remoteMessage) {
        PushReceiver receiver = PushReceiver.getInstance(this);
        receiver.notifyReceipt(remoteMessage.getFrom(), remoteMessage.getData());
    }

    private void showNotification(RemoteMessage remoteMessage) {
        String title = remoteMessage.getData().get("notification_title");
        String text = remoteMessage.getData().get("notification_text");

        int defaults = 0;
        defaults = defaults | Notification.DEFAULT_LIGHTS;
        defaults = defaults | Notification.DEFAULT_VIBRATE;
        defaults = defaults | Notification.DEFAULT_SOUND;

        NotificationCompat.Builder notifBuilder =
                new NotificationCompat.Builder(this)
                        .setContentTitle(title)
                        .setContentText(text)
                        .setDefaults(defaults)
                        .setSmallIcon(R.drawable.ic_thenpush_notification)
                        .setAutoCancel(true);

        String url = remoteMessage.getData().get("url");
        Intent intent = new Intent(this, MessageViewer.class);
        intent.putExtra("url", url);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Build the stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // stackBuilder.addParentStack(this.getMainActivityClass());
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
            0,
            PendingIntent.FLAG_UPDATE_CURRENT
        );
        notifBuilder.setContentIntent(resultPendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notifBuilder.build());
    }

    private Class getMainActivityClass() {
        Context context = this.getApplicationContext();
        String packageName = context.getPackageName();
        Intent mainIntent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        try {
            return Class.forName(mainIntent.getComponent().getClassName());
        } catch (ClassNotFoundException e) {
            Log.e("thenpush.me", "Could not get launcher for " + packageName);
        }
        return null;
    }
}
