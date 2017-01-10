package com.example.prora.demolearning2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.util.Log;
import android.view.View;

import com.example.prora.demolearning2.adapter.List2Line;
import com.squareup.spoon.Spoon;

import org.hamcrest.Description;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import static android.content.ContentValues.TAG;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.runner.lifecycle.Stage.RESUMED;
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
		takeScreenshot("1", getActivityInstance());
		Thread.sleep(3000);
		onData(Matchers.allOf(is(getMatcherInListViewMainActivity(Contacts)))).inAdapterView(ViewMatchers.withId(R.id
				.listViewFirstActivity)).perform(click());
		takeScreenshot("2", getActivityInstance());
		Thread.sleep(3000);
		onData(Matchers.allOf(is(getMatcherInListViewMainActivity(Backup)))).inAdapterView(ViewMatchers.withId(R.id
				.listViewFirstActivity)).perform(click());
		Thread.sleep(5000);
		Espresso.pressBack();
		onView(ViewMatchers.withId(R.id.change_state_item)).perform(click());
		Thread.sleep(3000);
		takeScreenshot("3", getActivityInstance());
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


	private Activity currentActivity;

	public Activity getActivityInstance(){
		getInstrumentation().runOnMainSync(new Runnable() {
			public void run() {
				Collection resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(RESUMED);
				if (resumedActivities.iterator().hasNext()){
					currentActivity = (Activity) resumedActivities.iterator().next();
				}
			}
		});
		return currentActivity;
	}


	@After
	public void finishTest() {
		Log.d(TAG, "finishTest");
	}

	public void takeScreenshot(String name, Activity activity)
	{
		Spoon.screenshot(activity, name);
		/*// In Testdroid Cloud, taken screenshots are always stored
		// under /test-screenshots/ folder and this ensures those screenshots
		// be shown under Test Results
		File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/testScreen");
		boolean success = true;
		if (!folder.exists()) {
			success = folder.mkdirs();
		}
		if (success) {
			String path =
					Environment.getExternalStorageDirectory().getAbsolutePath() + "/testScreen/" + name + ".png";

			View scrView = activity.getWindow().getDecorView().getRootView();
			scrView.setDrawingCacheEnabled(true);
			Bitmap bitmap = Bitmap.createBitmap(scrView.getDrawingCache());
			scrView.setDrawingCacheEnabled(false);

			OutputStream out = null;
			File imageFile = new File(path);

			Log.d(TAG, "takeScreenshot: " + path);
			try {
				Log.d(TAG, "takeScreenshot: 1");
				out = new FileOutputStream(imageFile);
				bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
				out.flush();
			} catch (FileNotFoundException e) {
				Log.d(TAG, "takeScreenshot: 2");
				// exception
			} catch (IOException e) {
				Log.d(TAG, "takeScreenshot: 3");
				// exception
			} finally {

				try {
					if (out != null) {
						out.close();
					}

				} catch (Exception exc) {
				}

			}
		} else {
			// Do something else on failure
		}*/

	}

}
