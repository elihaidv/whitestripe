package com.elihai.onTop;

import android.content.*;

public class MyReciver extends BroadcastReceiver
 {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Intent serviceIntent = new Intent(context, top.class);
            context.startService(serviceIntent);
        }
    }
}