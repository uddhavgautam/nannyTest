package com.nexuslab.forensics.grr.nanny.di.component;


import com.nexuslab.forensics.grr.nanny.NannyApplication;
import com.nexuslab.forensics.grr.nanny.di.module.ApplicationBindingModule;
import com.nexuslab.forensics.grr.nanny.di.module.NannyApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by gaute on 3/25/18.
 */

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ApplicationBindingModule.class,
        NannyApplicationModule.class
})
public interface NannyApplicationComponent extends AndroidInjector<NannyApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<NannyApplication> {
    }
}