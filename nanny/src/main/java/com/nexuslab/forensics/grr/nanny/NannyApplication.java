package com.nexuslab.forensics.grr.nanny;

import com.nexuslab.forensics.grr.nanny.di.component.DaggerNannyApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by gaute on 3/25/18.
 */

public class NannyApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * applicationInjector() gets called inside onCreate()
     */
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerNannyApplicationComponent
                .builder()
                .create(this);
    }
}
