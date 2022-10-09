package com.adityabugalia.itunespublicapi.ui

import android.content.Intent
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.adityabugalia.itunespublicapi.R
import com.adityabugalia.itunespublicapi.viewholders.MainListDisplayViewHolder
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    private val nameToSearch = "rihana"


    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {
    }

    @Test
    fun performSearch() {
        var mainActivity: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)
        mainActivity.launchActivity(Intent())
        // search api
        onView(withId(R.id.search_src_text)).perform(typeText(nameToSearch));
        Thread.sleep(10000)

        // click row to check dialog
        onView(withId(R.id.mainDisplayRV))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<MainListDisplayViewHolder>(
                        5,
                        clickItemWithId(R.id.itemMainListCL, 1)
                    )
            )
        Thread.sleep(10000)

    }

    fun clickItemWithId(id: Int, intcase: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return ""
            }

            override fun perform(uiController: UiController, view: View) {
                when (intcase) {

                    0 -> {
                        val v = view.findViewById<View>(id) as SearchView
                        v.setQuery(nameToSearch, true)
                    }
                    1 -> {
                        val v = view.findViewById<View>(id) as ConstraintLayout
                        v.performClick()
                    }


                }


            }
        }
    }
}