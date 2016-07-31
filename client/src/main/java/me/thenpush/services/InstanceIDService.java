package me.thenpush.services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import me.thenpush.DeviceSender;
import me.thenpush.SettingsManager;
import me.thenpush.rest.models.Device;

/**
 * Created by pappacena on 31/07/16.
 */
public class InstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("thenpush.me", "Refreshed token: " + refreshedToken);

        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String token) {
        SettingsManager manager = SettingsManager.getInstance(this);
        manager.setRegistrationId(token);

        DeviceSender sender = DeviceSender.getInstance(this);
        Device device = new Device(token);
        sender.send(device);
    }
}
