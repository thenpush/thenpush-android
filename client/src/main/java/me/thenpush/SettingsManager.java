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

    private String getString(String key, String defaultValue) {
        return this.getSharedPreferences().getString(key, defaultValue);
    }

    private void setString(String key, String value) {
        SharedPreferences.Editor editor = this.getSharedPreferences().edit();
        editor.putString(key, value);
        editor.commit();
    }

}
