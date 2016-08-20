package me.thenpush.services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import me.thenpush.DeviceSender;
import me.thenpush.Flow;
import me.thenpush.SettingsManager;
import me.thenpush.rest.models.Device;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        final SettingsManager manager = SettingsManager.getInstance(this);
        manager.setRegistrationId(token);

        DeviceSender sender = DeviceSender.getInstance(this);
        Device device = new Device(token);
        sender.send(device, new Callback<Device>() {
            @Override
            public void onResponse(Call<Device> call, Response<Device> response) {
                Log.d("thenpush.me", "Device created (deviceId=" + response.body().getId() + ")");
                manager.setDeviceId(response.body().getId());

                Flow.startPendingFlows(getApplicationContext());
            }

            @Override
            public void onFailure(Call<Device> call, Throwable t) {
                Log.d("thenpush.me", "Device not created: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
