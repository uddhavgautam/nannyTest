package com.nexuslab.forensics.grr.nanny.di.component;


import com.nexuslab.forensics.grr.nanny.NannyApplication;
import com.nexuslab.forensics.grr.nanny.di.module.NannyApplicationModule;
import com.nexuslab.forensics.grr.nanny.di.scope.ApplicationScope;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

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

@ApplicationScope
@Singleton
@Component(modules = {NannyApplicationModule.class, AndroidInjectionModule.class})
public interface NannyApplicationComponent {

    /**
     * It injects NannyApplication object into the generated framework of graph
     *
     * @param nannyApplication
     */
    void inject(NannyApplication nannyApplication);


}