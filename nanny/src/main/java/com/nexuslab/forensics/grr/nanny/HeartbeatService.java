package com.nexuslab.forensics.grr.nanny;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.annotation.Nullable;

import lombok.extern.java.Log;

/**
 * @author gaute
 */
@Log
public class HeartbeatService extends Service {
    private ClientController controller;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * {@link HeartbeatService#onStartCommand} is called by AlarmManager in repeat mode
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Start another check unless the previous check finished
        if (controller == null || !controller.isAlive()) {
            try {
                controller = new ClientController(this);
                controller.start();
            } catch (PackageManager.NameNotFoundException e) {
                log.severe("Cannot find the target client " + getString(R.string.client_package_name));
            }
        }

        return START_NOT_STICKY;
    }

    /**
     * mutual saving between Grr_client_android and Grr_nanny_android
     **/
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (controller == null || !controller.isAlive()) {
            try {
                controller = new ClientController(this);
                controller.start();
            } catch (PackageManager.NameNotFoundException e) {
                log.severe("Cannot find the target client " + getString(R.string.client_package_name));
            }
        }
    }
}
