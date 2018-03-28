package com.nexuslab.forensics.grr.nanny.di.module;

import com.nexuslab.forensics.grr.nanny.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ApplicationBindingModule {

    @ContributesAndroidInjector
    MainActivity mainActivity();
}
