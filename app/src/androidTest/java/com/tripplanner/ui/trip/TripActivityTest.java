package com.tripplanner.ui.trip;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.widget.DatePicker;

import com.tripplanner.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.tripplanner.ui.trip.AppTestConstants.ERROR_DEPARTURE_DATE_MSG;

/**
 * Created by suyashg on 13/06/18.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
@SuppressWarnings("deprecation")
public class TripActivityTest {


    @Rule
    public ActivityTestRule<TripActivity> rule = new ActivityTestRule<>(TripActivity.class);
    private IdlingResource mIdlingResource;

    @Before
    public void registerIdlingResource() {
        mIdlingResource = rule.getActivity().getTripListIdlingResource();
        // To prove that the test fails, omit this call:
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }

    @Test
    public void shouldBeAbleToLoadTrips() throws InterruptedException {
        onView(withId(R.id.rl_trip_details)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldBeAbleToScrollDown() throws InterruptedException {
        onView(withId(R.id.rl_trip_details)).check(matches(isDisplayed()));
        onView(withId(R.id.rl_trip_details)).perform(swipeUp());
        onView(withId(R.id.rl_trip_details)).perform(swipeUp());
    }

    @Test
    public void shouldBeAbleToChangeDepartureDate() throws InterruptedException {
        onView(withId(R.id.rl_trip_details)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_departure_date)).perform(click());
        Calendar c = AppTestConstants.getValidDepartureDate();
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)));
        onView(withId(android.R.id.button1)).perform(click());
    }

    public static Matcher<View> matchesDate(final int year, final int month, final int day) {
        return new BoundedMatcher<View, DatePicker>(DatePicker.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("matches date:");
            }

            @Override
            protected boolean matchesSafely(DatePicker item) {
                return (year == item.getYear() && month == item.getMonth() && day == item.getDayOfMonth());
            }
        };
    }

    @Test
    public void changeDepartureDateToThrowError() throws InterruptedException {
        onView(withId(R.id.rl_trip_details)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_departure_date)).perform(click());
        Calendar c = AppTestConstants.getInValidDepartureDate();
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.tv_message)).check(matches(withText(ERROR_DEPARTURE_DATE_MSG)));
    }

}
