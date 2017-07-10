package com.example.mukhter.bakingrecipe;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.mukhter.bakingrecipe.adapter.Adapter;
import com.example.mukhter.bakingrecipe.ui.MainActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by MUKHTER on 09/07/2017.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityRecyclerTest {
    @Rule public ActivityTestRule<MainActivity>mainActivityActivityTestRule=
            new ActivityTestRule<MainActivity>(MainActivity.class);


    @Test
    public void setMainActivityActivityTestRule() {

        onView(withId(R.id.cardList)).perform(RecyclerViewActions.scrollToPosition(3));
    }


    public static Matcher<RecyclerView.ViewHolder> withHolderTimeView(final String text) {
        return new BoundedMatcher<RecyclerView.ViewHolder, Adapter.Viewholder>(Adapter.Viewholder.class) {

            @Override
            public void describeTo(org.hamcrest.Description description) {
                description.appendText("No ViewHolder found with text: " + text);
            }

            @Override
            protected boolean matchesSafely(Adapter.Viewholder item) {
                TextView ViewText = (TextView) item.itemView.findViewById(R.id.title);
                if (ViewText == null) {
                    return false;
                }
                return ViewText.getText().toString().contains(text);

            }

        };


    }


}
