package com.nexuslab.forensics.grr.mobile;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import lombok.extern.java.Log;

/**
 * @author ppoffice
 */
@Log
public class HeartbeatService extends Service {
    private NannyController nannyController;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try (FileOutputStream output = openFileOutput(getString(R.string.shared_timestamp_file), MODE_PRIVATE)) {
            OutputStreamWriter writer = new OutputStreamWriter(output);
            writer.write(String.valueOf(System.currentTimeMillis()));
            writer.flush();
            writer.close();
            log.info("Updated timestamp file.");
        } catch (IOException e) {
            log.warning(String.format("Cannot write to timestamp file.\n%s", e.getMessage()));
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (nannyController == null) {
            try {
                nannyController = new NannyController(this);
                nannyController.restartNanny();
            } catch (PackageManager.NameNotFoundException e) {
                log.severe("Cannot find the target client " + getString(R.string.nanny_package_name));
            }
        }
    }
}
