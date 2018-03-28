package com.nexuslab.forensics.grr.nanny.di.module;

import com.nexuslab.forensics.grr.nanny.NannyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;

@Module(includes = AndroidInjectionModule.class)
public class NannyApplicationModule {

    @Provides
    @Singleton
    NannyApplication getNannyApplication(NannyApplication nannyApplication) {
        return nannyApplication;
    }

}
