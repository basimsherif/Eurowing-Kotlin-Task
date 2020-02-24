package com.basim.kotlinapp.ui.about
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.basim.kotlinapp.R
import com.basim.kotlinapp.databinding.AboutFragmentBinding
import com.basim.kotlinapp.utils.startCircularReveal

/**
 * A Fragment for about page
*/
class AboutFragment : Fragment() {

    private lateinit var binding : AboutFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater!!, R.layout.about_fragment,container , false)
        var myView : View  = binding.root
        setViews()
        animateViews()
        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.startCircularReveal(false)
    }

    fun setViews(){
        val manager = context?.packageManager
        val versionInfo = manager?.getPackageInfo(context?.packageName, PackageManager.GET_ACTIVITIES)
        binding.versionTextView.text = "Version "+versionInfo?.versionName
    }

    fun animateViews(){
        val moveY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 200f,0f)
        val alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 0f,1f)
        ObjectAnimator.ofPropertyValuesHolder(binding.aboutTitletextView,moveY,alpha).apply {
            duration = 1000
            interpolator = OvershootInterpolator()
            start()
        }
        ObjectAnimator.ofPropertyValuesHolder(binding.cardView,moveY,alpha).apply {
            duration = 1000
            interpolator = OvershootInterpolator()
            startDelay = 500
            start()
        }
        ObjectAnimator.ofPropertyValuesHolder(binding.developedByTitle,moveY,alpha).apply {
            duration = 1000
            interpolator = OvershootInterpolator()
            startDelay = 1000
            start()
        }
        ObjectAnimator.ofPropertyValuesHolder(binding.authornameTextVIew,moveY,alpha).apply {
            duration = 1000
            interpolator = OvershootInterpolator()
            startDelay = 1200
            start()
        }
        ObjectAnimator.ofPropertyValuesHolder(binding.versionTextView,moveY,alpha).apply {
            duration = 1000
            interpolator = OvershootInterpolator()
            startDelay = 800
            start()
        }
        ObjectAnimator.ofPropertyValuesHolder(binding.linkedInButton,moveY,alpha).apply {
            duration = 1000
            interpolator = OvershootInterpolator()
            startDelay = 1400
            start()
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear()
    }

}