package br.android.cericatto.idling_resource;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * MainActivityTest.java.
 *
 * @author Rodrigo Cericatto
 * @since Oct 12, 2016
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    private IntentServiceIdlingResource mIdlingResource;

    //--------------------------------------------------
    // JUnit
    //--------------------------------------------------

    @Before
    public void registerIntentServiceIdlingResource() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        mIdlingResource = new IntentServiceIdlingResource(instrumentation.getTargetContext());
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @After
    public void unregisterIntentServiceIdlingResource() {
        Espresso.unregisterIdlingResources(mIdlingResource);
    }

    @Test
    public void hello() {
        onView(withId(R.id.input)).perform(typeText("hello"));
        onView(withId(R.id.repeat_button)).perform(click());
        onView(withId(R.id.output)).check(matches(withText("hello\nhello")));
    }
}