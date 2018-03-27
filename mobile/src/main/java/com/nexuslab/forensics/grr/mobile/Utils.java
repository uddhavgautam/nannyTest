package com.nexuslab.forensics.grr.mobile;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;

/**
 * @author gaute
 */
public final class Utils {
    private Utils() {
    }

    /**
     * Schedule to start a service on regular time basis
     *
     * @param context  application context
     * @param job      service to be started
     * @param interval minimum time interval between each start
     */
    public static void schedule(Context context, Class<? extends Service> job, int interval) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager == null) {
            throw new RuntimeException("Cannot access AlarmManager service.");
        }
        PendingIntent intent = PendingIntent.getService(context, 0, new Intent(context, job), 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval * 1000, intent);
    }
}