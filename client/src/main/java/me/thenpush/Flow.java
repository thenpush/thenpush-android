package me.thenpush;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;

import me.thenpush.rest.Endpoints;
import me.thenpush.rest.RestApi;
import me.thenpush.rest.models.Device;
import me.thenpush.rest.models.ResponseMessage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by pappacena on 20/08/16.
 */

public class Flow {
    private static HashMap<String, Flow> instances = new HashMap<String, Flow>();
    private String flowId;
    private Context context;

    private Flow(Context context, String flowId) {
        this.context = context;
        this.flowId = flowId;
    }

    public static Flow getInstance(Context context, String flowId) {
        if (!Flow.instances.containsKey(flowId)) {
            Flow flow = new Flow(context, flowId);
            Flow.instances.put(flowId, flow);
        }
        return Flow.instances.get(flowId);
    }

    public String getFlowId() {
        return this.flowId;
    }

    private String getDeviceId() {
        return SettingsManager.getInstance(context).getDeviceId();
    }

    /**
     * Starts all pending flows
     * @param context
     */
    public synchronized static void startPendingFlows(Context context) {
        SettingsManager settingsManager = SettingsManager.getInstance(context);
        String[] pending = settingsManager.getPendingFlows();
        if (pending.length == 0) {
            Log.d("thenpush.me", "No pending flow to start. Skipping.");
            return;
        }

        for(int i=0 ; i<pending.length ; i++) {
            Log.d("thenpush.me", "Starting pending flow " + pending[i]);
            Flow f = Flow.getInstance(context, pending[i]);
            f.start();
        }

        settingsManager.clearPendingFlows();
    }

    public void start() {
        Retrofit retrofit = RestApi.getInstance(this.context).getRetrofit();
        Endpoints endpoints = retrofit.create(Endpoints.class);
        final String deviceId = this.getDeviceId();
        final String flowId = this.getFlowId();
        Log.d("thenpush.me", "Device ID " + deviceId + " starting flow " + flowId);

        if(deviceId == null) {
            SettingsManager settingsManager = SettingsManager.getInstance(this.context);
            settingsManager.addPendingFlow(flowId);
            return;
        }

        Call<ResponseMessage> call = endpoints.startFlow(deviceId, flowId);
        call.enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                Log.d("thenpush.me", "Flow " + flowId + " started: " + response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {
                Log.e("thenpush.me", "Fail to start flow " + flowId + ": " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
