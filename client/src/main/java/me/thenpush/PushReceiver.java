package me.thenpush;

import android.content.Context;
import android.os.Bundle;

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

    public void notifyReceipt(String from, Bundle data) {
        // TODO
    }
}
