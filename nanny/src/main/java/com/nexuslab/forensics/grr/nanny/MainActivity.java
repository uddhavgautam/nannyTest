package com.nexuslab.forensics.grr.nanny;

import android.support.v7.app.AppCompatActivity;

/**
 * @author gaute
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();
        Utils.schedule(getApplicationContext(), HeartbeatService.class, Constants.HEARTBEAT_CHECK_INTERVAL);
        finishAndRemoveTask();
    }
}
