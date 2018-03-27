package com.nexuslab.forensics.grr.nanny;

import android.app.Activity;
import android.app.Application;

import com.nexuslab.forensics.grr.nanny.di.component.DaggerNannyApplicationComponent;
import com.nexuslab.forensics.grr.nanny.di.component.NannyApplicationComponent;
import com.nexuslab.forensics.grr.nanny.di.module.NannyApplicationModule;

import dagger.android.AndroidInjector;
import dagger.android.HasActivityInjector;


/**
 * Created by gaute on 3/25/18.
 */

/* Application is always a Singleton */
public class NannyApplication extends Application implements HasActivityInjector {

    private static NannyApplication nannyApplication;

    private NannyApplicationComponent nannyApplicationComponent;

    public static NannyApplication getNannyApplication() {
        return nannyApplication;
    }

    public void setNannyApplication(NannyApplication app) {
        nannyApplication = app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setNannyApplication(this);

        getNannyApplicationComponent().inject(this);
    }

    public NannyApplicationComponent getNannyApplicationComponent() {
        if (nannyApplicationComponent == null) {
            nannyApplicationComponent = DaggerNannyApplicationComponent.builder()
                    .nannyApplicationModule(new NannyApplicationModule(this)).build();
        }
        return nannyApplicationComponent;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return null;
    }
}
