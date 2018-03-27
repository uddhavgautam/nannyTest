package com.nexuslab.forensics.grr.nanny;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

/**
 * @author gaute
 */
public class MainActivity extends AppCompatActivity {

    @Inject
    NannyApplication nannyApplication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Utils.schedule(nannyApplication, HeartbeatService.class, Constants.HEARTBEAT_CHECK_INTERVAL);
        finishAndRemoveTask();
    }

}
