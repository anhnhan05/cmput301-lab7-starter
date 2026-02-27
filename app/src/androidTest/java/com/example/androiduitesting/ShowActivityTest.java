package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ShowActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    private final String testCityName = "Edmonton";

    @Before
    public void setup() {
        Intents.init();

        // Ensure there is at least one city to click
        activityRule.getScenario().onActivity(activity -> {
            activity.addCityForTest(testCityName);
        });
    }

    @After
    public void teardown() {
        Intents.release();
    }

    // 1) Check whether the activity correctly switched
    @Test
    public void clickingCity_switchesToShowActivity() {
        onData(anything())
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        intended(hasComponent(ShowActivity.class.getName()));
    }

    // 2) Test whether the city name is consistent
    @Test
    public void clickedCityName_isDisplayedCorrectly() {
        onData(anything())
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        onView(withId(R.id.tvCityName))
                .check(matches(withText(testCityName)));
    }

    // 3) Test the "back" button
    @Test
    public void backButton_returnsToMainActivity() {
        onData(anything())
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        onView(withId(R.id.btnBack)).perform(click());

        onView(withId(R.id.city_list))
                .check(matches(isDisplayed()));
    }
}
