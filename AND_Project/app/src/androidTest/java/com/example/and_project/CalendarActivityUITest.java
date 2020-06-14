package com.example.and_project;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.and_project.calendar.CalendarActivity;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

@RunWith(AndroidJUnit4.class)
public class CalendarActivityUITest
{
    @Rule
    public ActivityTestRule<CalendarActivity> activityRule = new ActivityTestRule<>(CalendarActivity.class);

    @Test
    public void clickConfirmButton_opensHistoricalDataActivity()
    {
        onView(withText("Confirm")).perform(click());
        onView(withId(R.id.data_for_date)).check(matches(isDisplayed()));
    }

    @Test
    public void clickConfirmButton_showsDateInHistoricalDataActivity()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String pickedDate = sdf.format(Calendar.getInstance().getTime());
        onView(withText("Confirm")).perform(click());
        onView(withId(R.id.data_for_date)).check(matches(withText(containsString(pickedDate))));
    }
}
