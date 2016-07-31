package me.thenpush.services;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import me.thenpush.iamessage.MessageViewer;

/**
 * Created by pappacena on 31/07/16.
 */
public class MessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d("thenpush.me", "From: " + remoteMessage.getFrom());
        Log.d("thenpush.me", "Notification Message Body: " + remoteMessage.getNotification().getBody());

        String url = remoteMessage.getData().get("url");
        Intent intent = new Intent(this, MessageViewer.class);
        intent.putExtra("url", url);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
