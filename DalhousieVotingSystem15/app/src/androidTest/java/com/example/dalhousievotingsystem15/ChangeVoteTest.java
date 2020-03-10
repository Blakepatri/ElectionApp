package com.example.dalhousievotingsystem15;

import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ChangeVoteTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);



    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.dalhousievotingsystem15", appContext.getPackageName());

        onView(withId(R.id.NetID)).perform(replaceText("ljia"));

        onView(withId(R.id.Password)).perform(replaceText("ps4saigao"));

        onView(withId(R.id.StudentcheckBox)).perform(click());

        onView(withId(R.id.logIn)).perform(click());

        onView(withId(R.id.CID)).perform(replaceText("trump"));

        onView(withId(R.id.VOTE)).perform(click());

        onView(withId(R.id.CID)).perform(replaceText("lliu"));

        onView(withId(R.id.VOTE)).perform(click());

    }
}
