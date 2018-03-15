package com.nexuslab.forensics.grr.nanny;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author gaute
 */
public class StartOnBootBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            // can't start the service via BroadcastReceiver. Service is started from UI components only.
            Intent activityIntent = new Intent(context, MainActivity.class);
            activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(activityIntent);
        }
    }
}
