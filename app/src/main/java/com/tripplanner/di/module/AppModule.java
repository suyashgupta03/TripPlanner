package com.tripplanner.di.module;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tripplanner.data.AppDataManager;
import com.tripplanner.data.DataManager;
import com.tripplanner.data.processing.ApiProcesser;
import com.tripplanner.data.processing.AppApiProcessor;
import com.tripplanner.data.remote.ApiHelper;
import com.tripplanner.data.remote.AppApiHelper;
import com.tripplanner.utils.rx.AppSchedulerProvider;
import com.tripplanner.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by suyashg on 09/06/18.
 */
@Module
public class AppModule {


    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    ApiProcesser provideApiProcessor(AppApiProcessor appApiProcessor) {
        return appApiProcessor;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }
}
