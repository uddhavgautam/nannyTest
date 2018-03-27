package com.nexuslab.forensics.grr.mobile;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import java.util.List;

import lombok.extern.java.Log;

/**
 * @author gaute
 */
@Log
class NannyController {
    private final String targetIntentAction;
    private Context context;

    NannyController(HeartbeatService service) throws PackageManager.NameNotFoundException {
        context = service;
        targetIntentAction = context.getString(R.string.nanny_intent_action);
    }

    /**
     * restarts the nanny using intent
     */
    public void restartNanny() {
        try {
            Intent intent = new Intent();
            intent.setAction(targetIntentAction);
            // start an activity outside of an Activity context requires the FLAG_ACTIVITY_NEW_TASK flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // NOTE: Can't use explicit intent to point MainActivity of Grr_client_android
            // NOTE: Verify that there is an App to receive the intent
            // CAUTION: If you invoke an intent and there is no app available on the device that can
            //          handle the intent, your app will crash.
            PackageManager packageManager = context.getPackageManager();
            List activities = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            boolean isIntentSafe = activities.size() > 0;

            if (isIntentSafe) {
                context.getApplicationContext().startActivity(intent);
                log.info("Nanny restarted.");
            } else {
                log.warning("Failed to restart the nanny because no App in this device can" +
                        "handle intent " + targetIntentAction + ".");
            }
        } catch (Exception e) {
            log.warning("Failed to restart the nanny due to: " + e.getMessage());
        }
    }
}
