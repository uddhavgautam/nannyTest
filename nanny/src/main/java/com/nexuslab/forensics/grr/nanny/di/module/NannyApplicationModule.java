package com.nexuslab.forensics.grr.nanny.di.module;

import com.nexuslab.forensics.grr.nanny.NannyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gaute on 3/25/18.
 */

@Module
public class NannyApplicationModule {

    private final NannyApplication nannyApplication;

    public NannyApplicationModule(NannyApplication nannyApplication) {
        this.nannyApplication = nannyApplication;
    }

    @Singleton
    @Provides
    public NannyApplication provideNannyApplication() {
        return this.nannyApplication;
    }
}


