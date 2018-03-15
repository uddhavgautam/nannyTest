package com.nexuslab.forensics.grr.nanny;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import lombok.extern.java.Log;

/**
 * @author gaute
 */
@Log
class ClientController extends Thread {
    private final String filename;
    private final String targetIntentAction;
    private Context context;
    private Context targetContext;

    ClientController(HeartbeatService service) throws PackageManager.NameNotFoundException {
        context = service;
        filename = context.getString(R.string.shared_timestamp_file);
        targetIntentAction = context.getString(R.string.client_intent_action);
        targetContext = context.createPackageContext(context.getString(R.string.client_package_name),
                Context.CONTEXT_IGNORE_SECURITY);
    }

    @Override
    public void run() {
        try {
            if (isClientRestartNeeded()) {
                sleep(Constants.CLIENT_RESURRECTION_PERIOD * 1000);
                restartClient();
            }
        } catch (InterruptedException ignored) {
        }
    }

    private boolean isClientRestartNeeded() {
        Long now = System.currentTimeMillis();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(targetContext.openFileInput(filename)))) {
            // only read the first line
            String lastTimestamp = br.readLine();
            // restart client if timestamp file is empty
            if (lastTimestamp == null || "".equals(lastTimestamp.trim())) {
                throw new IOException("file is empty");
            }
            if (now - Long.valueOf(lastTimestamp) < Constants.CLIENT_UNRESPONSIVE_TIME) {
                log.info("Client timestamp is up-to-date.");
                return false;
            }
            log.info("It has been " + (now - Long.valueOf(lastTimestamp)) / 1000 + "s since last " +
                    "response from client, restarting...");
        } catch (FileNotFoundException e) {
            log.warning("Timestamp file is not found, will restart client!");
        } catch (IOException e) {
            log.warning("Timestamp file is corrupted, will restart client! (" + e.getMessage() + ")");
        }
        return true;
    }

    private void restartClient() {
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
                log.info("Client restarted.");
            } else {
                log.warning("Failed to restart the client because no App in this device can" +
                        "handle intent " + targetIntentAction + ".");
            }
        } catch (Exception e) {
            log.warning("Failed to restart the client due to: " + e.getMessage());
        }
    }
}
