package com.nexuslab.forensics.grr.nanny;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;


/**
 * @author gaute
 */
public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    NannyApplication nannyApplication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
    }

    /**
     * In each screen rotation, the AlarmManager schedule happens. Because, we use AlarmManager
     * to start Service. From API 26, We need GUI components to start background service.
     *
     * @see <a href="https://goo.gl/tgGiHU">StartingAService</a>
     * <p>
     * Because of the singleInstance mode of the service by default, there is no performance
     * loss on creating/destroying services because everything is taking care by the system.
     */
    @Override
    protected void onStart() {
        super.onStart();
        Utils.schedule(nannyApplication, HeartbeatService.class, Constants.HEARTBEAT_CHECK_INTERVAL);
        finishAndRemoveTask();
    }

}
