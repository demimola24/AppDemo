package com.example.androidapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.any
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.greaterThan
import org.hamcrest.StringDescription
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get : Rule
    var mActivityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_recyclerview_has_data() {
        onView(withId(R.id.recyclerView)).perform(
            waitUntil(hasItemCount(greaterThan(0))),
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8)
        )
    }

    private fun waitUntil(matcher: Matcher<View>): ViewAction = object : ViewAction {

        override fun getConstraints(): Matcher<View> {
            return any(View::class.java)
        }

        override fun getDescription(): String {
            return StringDescription().let {
                matcher.describeTo(it)
                "wait until: $it"
            }
        }

        override fun perform(uiController: UiController, view: View) {
            if (!matcher.matches(view)) {
                ViewPropertyChangeCallback(matcher, view).run {
                    try {
                        IdlingRegistry.getInstance().register(this)
                        view.viewTreeObserver.addOnDrawListener(this)
                        uiController.loopMainThreadUntilIdle()
                    } finally {
                        view.viewTreeObserver.removeOnDrawListener(this)
                        IdlingRegistry.getInstance().unregister(this)
                    }
                }
            }
        }
    }

    private fun hasItemCount(itemCount: Matcher<Int>): BoundedMatcher<View, RecyclerView> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun matchesSafely(view: RecyclerView): Boolean {
                return view.adapter?.let {
                    itemCount.matches(it.itemCount)
                } ?: false
            }

            override fun describeTo(description: Description?) {
                description?.appendText("has item count: ")
                itemCount.describeTo(description)
            }
        }
    }
}