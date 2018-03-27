package com.nexuslab.forensics.grr.mobile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

/**
 * @author ppoffice
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermissions();
    }

    /**
     * Scan for all the permissions that are not granted, and pop up dialog to ask user for permissions
     */
    private void checkPermissions() {
        onRequestPermissionsResult(0, new String[0], new int[0]);
    }

    /**
     * Whitelist the client in doze mode and App standby.
     */
    @SuppressLint("BatteryLife")
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setIgnorePowerOptimization() {
        Intent intent = new Intent();
        String packageName = getPackageName();
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        if (pm == null) {
            intent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
        } else if (!pm.isIgnoringBatteryOptimizations(packageName)) {
            intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + packageName));
        }
        if (intent.getAction() != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] result) {
        startService(new Intent(getApplicationContext(), GrrService.class));
        Utils.schedule(getApplicationContext(), HeartbeatService.class, Constants.WRITE_TIMESTAMP_INTERVAL);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setIgnorePowerOptimization();
        }
        finishAndRemoveTask();
    }
}

