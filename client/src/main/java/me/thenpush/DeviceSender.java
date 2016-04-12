package me.thenpush;

import android.content.Context;

import me.thenpush.rest.Endpoints;
import me.thenpush.rest.RestApi;
import me.thenpush.rest.models.Device;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by pappacena on 12/04/16.
 */
public class DeviceSender {
    private static DeviceSender instance;
    private Context context;

    private DeviceSender(Context context) {
        this.context = context;
    }

    public static DeviceSender getInstance(Context context) {
        if(DeviceSender.instance == null) {
            DeviceSender.instance = new DeviceSender(context);
        }
        return DeviceSender.instance;
    }

    public void send(Device device, String projectId, Callback<Device> callback) {
        Retrofit retrofit = RestApi.getInstance(this.context).getRetrofit();
        Endpoints endpoints = retrofit.create(Endpoints.class);

        String token = "Token: " + context.getString(R.string.thenpushme_api_token);

        Call<Device> call = endpoints.addDevice(token, projectId, device);
        call.enqueue(callback);
    }
}
