package com.basim.kotlinapp

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.basim.kotlinapp.base.BaseTest
import com.basim.kotlinapp.pages.HomePage
import com.basim.kotlinapp.utils.idlingresource.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 * This class consists of all test cases related to home page
 */
@RunWith(AndroidJUnit4::class)
class HomeTest: BaseTest() {

    private val homePage: HomePage = HomePage()
    private val idlingResource = EspressoIdlingResource.getIdlingResource()

    /**
     * This method will be executed before each test. We will register idling resource here
     */
    @Before
    fun beforeTest() {
        IdlingRegistry.getInstance().register(idlingResource)
    }

    /**
     * This testcase will verify About page
     * Test ID:01
     * Author:Basim Sherif
     * **/
    @Test
    fun testAboutPage() {
        homePage.verifyHomePageOpened()
        homePage.verifyFabButton()
        homePage.tapAndVerifyAboutPage()
    }

    /**
     * This testcase will verify categories by tapping BottomNavigation items
     * Test ID:02
     * Author:Basim Sherif
     * **/
    @Test
    fun testCategory() {
        homePage.verifyCategory(R.id.navigation_hot)
        homePage.verifyCategory(R.id.navigation_top)
    }

    /**
     * This method will be executed after each test. We will un-register idling resource here
     */
    @After
    fun unregisterIdlingResource() {
        if (idlingResource != null) {
            IdlingRegistry.getInstance().unregister(idlingResource)
        }
    }
}
