package com.example.androidapp

import android.view.View
import android.view.ViewTreeObserver
import androidx.test.espresso.IdlingResource
import org.hamcrest.Matcher

class ViewPropertyChangeCallback(private val matcher: Matcher<View>, private val view: View) : IdlingResource, ViewTreeObserver.OnDrawListener {

    private lateinit var callback: IdlingResource.ResourceCallback
    private var matched = false

    override fun getName() = "View property change callback"

    override fun isIdleNow() = matched

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        this.callback = callback
    }

    override fun onDraw() {
        matched = matcher.matches(view)
        callback.onTransitionToIdle()
    }
}