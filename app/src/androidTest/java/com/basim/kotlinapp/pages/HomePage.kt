package com.basim.kotlinapp.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.basim.kotlinapp.R


class HomePage {

    /**
     * This function is used to verify if home page is loaded properly
     **/
    fun verifyHomePageOpened(){
        onView(withId(R.id.gallery_list))
            .check(
                matches(
                withEffectiveVisibility(
                    Visibility.VISIBLE)
                )
            )
    }

    /**
     * This function is used to verify FAB
     **/
    fun verifyFabButton(){
        onView(withId(R.id.fab))
            .check(
                matches(
                    withEffectiveVisibility(
                        Visibility.VISIBLE)
                )
            )
    }

    /**
     * This function is used to verify FAB tap
     **/
    fun tapAndVerifyAboutPage(){
        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.aboutTitletextView))
            .check(
                matches(
                    withEffectiveVisibility(
                        Visibility.VISIBLE)))
    }

    /**
     * This function is used to verify categories
     **/
    fun verifyCategory(menuId:Int){
        onView(withId(menuId))
            .perform(click())
        onView(withId(R.id.gallery_list))
            .check(
                matches(
                    withEffectiveVisibility(
                        Visibility.VISIBLE)
                )
            )
    }

}