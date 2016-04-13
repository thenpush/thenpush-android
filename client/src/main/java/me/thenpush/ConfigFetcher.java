package me.thenpush;

import android.content.Context;
import android.util.Log;

/**
 * Created by pappacena on 13/04/16.
 */
public class ConfigFetcher {
    public static String getToken(Context context) {
        String token = context.getString(R.string.thenpushme_api_token);
        if (token.trim().length() == 0) {
            String msg = "String thenpushme_api_token not set.\n"
                    + "ThenPush.me services will not work until you add your API auth token.\n"
                    + "You can find it at https://thenpush.me";
            Log.e("thenpush.me", msg);
            return null;
        }
        return "Token " + token;
    }

    public static String getProjectId(Context context) {
        String projectId = context.getString(R.string.thenpushme_project_id);

        if (projectId.trim().length() == 0) {
            String msg = "String thenpushme_project_id not set.\n"
                    + "ThenPush.me services will not work until you add your project ID.\n"
                    + "You can find it at https://thenpush.me";
            Log.e("thenpush.me", msg);
            return null;
        }
        return projectId;
    }
}
