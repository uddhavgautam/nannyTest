package com.nexuslab.forensics.grr.nanny;

import android.app.Application;

import com.nexuslab.forensics.grr.nanny.di.component.DaggerNannyApplicationComponent;
/**
 * Created by gaute on 3/25/18.
 */

public class NannyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerNannyApplicationComponent.builder().create(this);
    }
}
