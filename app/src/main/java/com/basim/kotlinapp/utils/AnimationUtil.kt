package com.basim.kotlinapp.utils

import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.DecelerateInterpolator

/**
 * Starts circular reveal animation
 * [fromLeft] if `true` then start animation from the bottom left of the [View] else start from the bottom right
 */
fun View.startCircularReveal(fromLeft: Boolean) {
    addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
        override fun onLayoutChange(v: View, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int,
                                    oldRight: Int, oldBottom: Int) {
            v.removeOnLayoutChangeListener(this)
            val cx = if (fromLeft) v.left else v.right
            val cy = v.bottom
            val radius = Math.hypot(right.toDouble(), bottom.toDouble()).toInt()
            ViewAnimationUtils.createCircularReveal(v, cx, cy, 0f, radius.toFloat()).apply {
                interpolator = DecelerateInterpolator(2f)
                duration = 1000
                start()
            }
        }
    })
}