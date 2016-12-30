package com.example.prora.demolearning2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import com.example.prora.demolearning2.adapter.List2Line;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.content.ContentValues.TAG;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.*;
import static android.support.test.espresso.intent.Intents.intended;

/**
 * Created by prora on 12/18/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.DEFAULT)
public class TestEspresso {

	@Rule
	public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);
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
	public void test1() throws InterruptedException {
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
		Espresso.pressBack();
	}

	@Test
	public void test2() throws Exception {
		onView(withId(R.id.edit_input)).check(matches(isDisplayed()));
		onView(withId(R.id.edit_input)).perform(typeText("Nguyen Duc Dat"), closeSoftKeyboard());
		onView(withId(R.id.btn_submit)).perform(click());
		Thread.sleep(1000);
		onData(Matchers.allOf(is(getMatcherInListViewMainActivity(Contacts + " 9")))).inAdapterView(ViewMatchers.withId(R.id
				.listViewFirstActivity)).perform(click());

		onView(withId(R.id.edit_input)).check(matches(withText("Nguyen Duc Dat")));
		Thread.sleep(3000);
		onView(withId(R.id.edit_input)).perform(clearText());
		onView(withId(R.id.edit_input)).perform(typeText("Rubycell"), closeSoftKeyboard());
		Thread.sleep(3000);
		onView(withText("Submit")).perform(click());
		Thread.sleep(3000);
		onView(withText("Submit")).perform(pressKey(KeyEvent.KEYCODE_VOLUME_UP));
		Thread.sleep(1000);

		Thread.sleep(1000);
		onView(withText("Submit")).perform(pressKey(KeyEvent.KEYCODE_VOLUME_DOWN));
		Thread.sleep(1000);
	}

	@Test
	public void testActionBarOverflow() throws InterruptedException {
		// Click the item.
		onView(withId(R.id.notify)).perform(click());
		Thread.sleep(1000);
		openActionBarOverflowOrOptionsMenu(mainActivityActivityTestRule.getActivity());
		Thread.sleep(1000);
		// Verify that we have really clicked on the icon by checking the TextView content.
		onView(withText("Browser")).perform(click());
		Thread.sleep(1000);
	}

	@Test
	public void customViewMatcher() throws InterruptedException {
		Thread.sleep(1000);
		onView(withId(R.id.edit_input)).check(matches(testHint(anyOf(
				startsWith("Hint"), endsWith("rubycell")))));
		onView(withId(R.id.edit_input)).check(matches(testHint(both(containsString("ruby")).and(containsString("cell")))));
	}

	static Matcher<View> testHint(final Matcher<String> stringMatcher) {
		return new BoundedMatcher<View, EditText>(EditText.class) {

			@Override
			public boolean matchesSafely(EditText view) {
				final CharSequence hint = view.getHint();
				return hint != null && stringMatcher.matches(hint.toString());
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("with hint: ");
				stringMatcher.describeTo(description);
			}
		};
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
