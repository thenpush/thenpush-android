package me.thenpush;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import me.thenpush.rest.Endpoints;
import me.thenpush.rest.RestApi;
import me.thenpush.rest.models.PushReceipt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by pappacena on 13/04/16.
 */
public class PushReceiver {
    private Context context;
    private static PushReceiver instance;

    private PushReceiver(Context context) {
        this.context = context;
    }

    public static PushReceiver getInstance(Context context) {
        if(PushReceiver.instance == null) {
            PushReceiver.instance = new PushReceiver(context);
        }

        return PushReceiver.instance;
    }

    public void notifyReceipt(String from, Map<String, String> data) {
        this.notifyReceipt(from, data, null);
    }

    public void notifyReceipt(String from, Map<String, String> data, Callback<PushReceipt> callback) {
        Retrofit retrofit = RestApi.getInstance(this.context).getRetrofit();
        Endpoints endpoints = retrofit.create(Endpoints.class);

        // Get uuid from Bundle, and device registration ID
        SettingsManager manager = SettingsManager.getInstance(this.context);
        String uuid = data.get("thenpush_uuid");
        String registrationId = manager.getRegistrationId();

        if(uuid == null) {
            Log.e("thenpush.me", "ThenPush.me token not found. Was this push sent using thenpush.me? " + uuid);
            return;
        }

        if(registrationId == null) {
            return;
        }

        // send it
        PushReceipt pushReceipt = new PushReceipt(uuid, registrationId);
        Call<PushReceipt> call = endpoints.notifyReceipt(pushReceipt);
        call.enqueue(callback);
    }
}
