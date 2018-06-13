package com.skyscanner.data;

import com.skyscanner.data.processing.ApiProcesser;
import com.skyscanner.data.remote.ApiHelper;

/**
 * Created by suyashg on 10/06/18.
 */

public interface DataManager extends ApiHelper, ApiProcesser {

    //Common methods goes here we are usable for all modules in App
}
