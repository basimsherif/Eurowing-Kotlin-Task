package com.basim.kotlinapp.base

import com.basim.kotlinapp.MainActivity
import com.basim.kotlinapp.rules.RetryActivityTestRule
import org.junit.Rule
import org.junit.rules.TestName

open class BaseTest {

    var retryCount: Int = 3

    @get:Rule
    open var mActivityRule = RetryActivityTestRule<MainActivity>(MainActivity::class.java, true, true, retryCount)

    @get:Rule
    open var currentTestName = TestName()
}