package me.thenpush;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by pappacena on 16/04/16.
 */
public class SettingsManager {
    private static SettingsManager instance;
    private static String sharedPrefsKey = "me.thenpush.sharedprefs";
    private static SharedPreferences prefs;

    private Context context;

    private SettingsManager(Context ctx) {
        this.context = ctx;
    }


    public static SettingsManager getInstance(Context ctx) {
        if(SettingsManager.instance == null) {
            SettingsManager.instance= new SettingsManager(ctx);
        }
        return SettingsManager.instance;
    }

    private SharedPreferences getSharedPreferences() {
        if(prefs == null) {
            SettingsManager.prefs = context.getSharedPreferences(this.sharedPrefsKey, context.MODE_PRIVATE);
        }

        return SettingsManager.prefs;
    }

    public void setRegistrationId(String registrationId) {
        this.setString("registration_id", registrationId);
    }

    public String getRegistrationId() {
        String registrationId = this.getString("registration_id", null);
        if(registrationId == null) {
            Log.e("thenpush.me", "Tring  to get stored registration ID before sending it to thenpush.me backend");
        }

        return registrationId;
    }

    public void setDeviceId(String deviceId) {
        this.setString("deviceId", deviceId);
    }

    public String getDeviceId() {
        String deviceId = this.getString("deviceId", null);
        if(deviceId == null) {
            Log.e("thenpush.me", "Trying to get deviceId before assignment on backend");
        }
        return deviceId;
    }

    private String getString(String key, String defaultValue) {
        return this.getSharedPreferences().getString(key, defaultValue);
    }

    public synchronized void addPendingFlow(String flowId) {
        String current = this.getString("pending_flows", "");
        String newPending = current;
        if (current.length() != 0) {
            newPending += ";";
        }
        newPending += flowId;
        this.setString("pending_flows", newPending);
    }

    public synchronized void clearPendingFlows() {
        this.setString("pending_flows", null);
    }

    public synchronized String[] getPendingFlows() {
        return this.getString("pending_flows", "").split(";");
    }

    private void setString(String key, String value) {
        SharedPreferences.Editor editor = this.getSharedPreferences().edit();
        editor.putString(key, value);
        editor.commit();
    }

}
