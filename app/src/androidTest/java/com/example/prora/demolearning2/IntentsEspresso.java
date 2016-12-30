package com.example.prora.demolearning2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import com.example.prora.demolearning2.adapter.List2Line;
import org.hamcrest.Description;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.content.ContentValues.TAG;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by prora on 12/18/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.DEFAULT)
public class IntentsEspresso {

	@Rule
	public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(MainActivity.class);
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

	private static final String VALID_PHONE_NUMBER = "01676488895";
	private static final Uri INTENT_DATA_PHONE_NUMBER = Uri.parse("tel:"+VALID_PHONE_NUMBER);
	@Test
	public void testIntent(){
		onView(withId(R.id.edit_input))
				.perform(typeText(VALID_PHONE_NUMBER), closeSoftKeyboard());
		onView(withId(R.id.btn_bottom)).perform(click());
		intended(allOf(
				hasAction(Intent.ACTION_CALL),
				hasData(INTENT_DATA_PHONE_NUMBER)));
	}

	@After
	public void finishTest() {
		Log.d(TAG, "finishTest");
	}
}
