package com.skyscanner.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by suyashg on 10/06/18.
 */

public interface SchedulerProvider {

    Scheduler io();

    Scheduler ui();
}
