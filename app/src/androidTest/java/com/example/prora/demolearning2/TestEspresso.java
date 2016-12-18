package com.example.prora.demolearning2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.prora.demolearning2.adapter.List2Line;

import org.hamcrest.Description;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.content.ContentValues.TAG;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static org.hamcrest.Matchers.is;

/**
 * Created by prora on 12/18/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestEspresso {

	@Rule
	public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
	Context context;
	String Contacts;
	String Backup;
	String Sms;

	@Before
	public void initVariable() {
		context = InstrumentationRegistry.getTargetContext();
		Contacts = context.getResources().getString(R.string.contact);
		Backup = context.getResources().getString(R.string.backup);
		Sms = context.getResources().getString(R.string.Sms);
	}

	@Test
	public void test() throws InterruptedException {
		Thread.sleep(3000);
		onData(Matchers.allOf(is(getMatcherInListViewMainActivity(Contacts)))).inAdapterView(ViewMatchers.withId(R.id
				.listViewFirstActivity)).perform(click());
		Thread.sleep(3000);
		onData(Matchers.allOf(is(getMatcherInListViewMainActivity(Backup)))).inAdapterView(ViewMatchers.withId(R.id
				.listViewFirstActivity)).perform(click());
		Thread.sleep(5000);
		Espresso.pressBack();
		onView(ViewMatchers.withId(R.id.change_state_item)).perform(click());
		Thread.sleep(3000);
		onView(ViewMatchers.withText(Sms)).perform(click());
		Thread.sleep(3000);
	}

	@NonNull
	private BoundedMatcher<Object, List2Line> getMatcherInListViewMainActivity(final String condition) {
		return new BoundedMatcher<Object, List2Line>(List2Line.class) {
			@Override
			public void describeTo(Description description) {

			}

			@Override
			protected boolean matchesSafely(List2Line item) {
				return item.getLabel().equalsIgnoreCase(condition);
			}
		};
	}

	@After
	public void finishTest() {
		Log.d(TAG, "finishTest");
	}
}
