package com.tripplanner.di.component;

import android.app.Application;

import com.tripplanner.BaseApp;
import com.tripplanner.di.builder.ActivityBuilder;
import com.tripplanner.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by suyashg on 09/06/18.
 */

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    void inject(BaseApp app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
