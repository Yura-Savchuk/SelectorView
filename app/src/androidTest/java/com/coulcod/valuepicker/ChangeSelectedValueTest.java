package com.coulcod.valuepicker;

import android.support.test.rule.ActivityTestRule;

import com.coulcod.selectorview.CheckableString;
import com.coulcod.selectorview.SelectorView;
import com.coulcod.selectorview.SelectorViewAdapter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by seotm on 04.04.17.
 */

public class ChangeSelectedValueTest {

    private static final List<CheckableString> testList = new ArrayList<CheckableString>() {{
        add(new CheckableString("Cow"));
        add(new CheckableString("Rabbit"));
        add(new CheckableString("Fox"));
        add(new CheckableString("Dog"));
        add(new CheckableString("Lion"));
        add(new CheckableString("Puma"));
        add(new CheckableString("Bear"));
        add(new CheckableString("Cat"));
        add(new CheckableString("Mouse"));
        add(new CheckableString("Chicken"));
    }};

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void initSelectorView() {
        final MainActivity activity = mActivityRule.getActivity();
        final SelectorView selectorView = (SelectorView) activity.findViewById(R.id.selectorView);
        final SelectorViewAdapter adapter = new SelectorViewAdapter(activity, testList);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                selectorView.setAdapter(adapter);
            }
        });
    }

    @Test
    public void changeValue() {
        //check is no one value selected
        withId(android.R.id.text2).matches(withText(""));
        //click on selector view
        onView(withId(R.id.selectorView)).perform(click());
        //check is dialog shown
        onView(allOf(withText(R.string.animals), not(withId(android.R.id.text1)))).check(matches(isDisplayed()));
    }


}
