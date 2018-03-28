package com.nexuslab.forensics.grr.nanny.di.component;


import com.nexuslab.forensics.grr.nanny.NannyApplication;
import com.nexuslab.forensics.grr.nanny.di.module.NannyApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

/**
 * Created by gaute on 3/25/18.
 */

@Singleton
@Component(modules = NannyApplicationModule.class)
public interface NannyApplicationComponent extends AndroidInjector<NannyApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<NannyApplication> {
    }
}