package com.nexuslab.forensics.grr.nanny.di.component;


import com.nexuslab.forensics.grr.nanny.NannyApplication;
import com.nexuslab.forensics.grr.nanny.di.module.NannyApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

/**
 * Created by gaute on 3/25/18.
 */

/**
 * Every type annotated with @Component must contain at least one abstract component method.
 * Component methods may have any name, but must have signatures that conform to either provision or members-injection contracts.
 * <p>
 * <p>
 * Provision methods:
 * SomeType getSomeType();
 * Provider<SomeType> getSomeTypeProvider();
 * Lazy<SomeType> getLazySomeType();
 * <p>
 * Members-injection methods
 * void injectSomeType(SomeType someType);
 * SomeType injectAndReturnSomeType(SomeType someType);
 */

@Singleton
@Component(modules = NannyApplicationModule.class)
public interface NannyApplicationComponent extends AndroidInjector<NannyApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<NannyApplication> {
    }
}