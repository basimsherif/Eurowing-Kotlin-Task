package com.basim.kotlinapp.utils

/**
 * Created by Basim Sherif on 5/12/2018.
 */

import android.app.Activity
import android.os.Handler
import android.os.Looper
import androidx.test.runner.lifecycle.ActivityLifecycleMonitor
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage

import java.util.ArrayList
import java.util.EnumSet

/**
 * This helper function is used to clear activities from stack
 */
class ActivityFinisher : Runnable {

    private val activityLifecycleMonitor: ActivityLifecycleMonitor

    init {
        this.activityLifecycleMonitor = ActivityLifecycleMonitorRegistry.getInstance()
    }

    override fun run() {
        val activities = ArrayList<Activity>()

        for (stage in EnumSet.range(Stage.CREATED, Stage.STOPPED)) {
            activities.addAll(activityLifecycleMonitor.getActivitiesInStage(stage))
        }

        for (activity in activities) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
    }

    companion object {

        fun finishOpenActivities() {
            Handler(Looper.getMainLooper()).post(ActivityFinisher())
        }
    }
}
