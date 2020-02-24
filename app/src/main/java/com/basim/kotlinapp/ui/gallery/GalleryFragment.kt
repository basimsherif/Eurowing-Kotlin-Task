package com.basim.kotlinapp.ui.gallery
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.basim.kotlinapp.R
import com.basim.kotlinapp.data.model.Category
import com.basim.kotlinapp.databinding.GalleryListFragmentBinding
import com.basim.kotlinapp.ui.GalleryViewModel
import com.basim.kotlinapp.ui.gallery.gallerydetail.GalleryDetailActivity
import com.basim.kotlinapp.utils.Constants
import com.basim.kotlinapp.utils.Constants.Companion.BUNDLE_EXTRA_GALLERY_PARAM
import com.basim.kotlinapp.utils.Constants.Companion.BUNDLE_EXTRA_PARAM
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.gallery_list_fragment.*

/**
 * A Fragment for holding category list
 */
class GalleryFragment : Fragment() {

    private lateinit var galleryListViewModel: GalleryViewModel
    private var errorSnackbar: Snackbar? = null
    private lateinit var binding : GalleryListFragmentBinding
    private var layoutManager: GridLayoutManager? = null
    private lateinit var menu: Menu

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater!!, R.layout.gallery_list_fragment,container , false)
        var myView : View  = binding.root
        layoutManager = GridLayoutManager(activity, 2)
        binding.galleryList.layoutManager = layoutManager
        var categoryName: String = arguments?.getString(ARG_CATEGORY_NAME)!!.toLowerCase()
        galleryListViewModel = ViewModelProviders.of(this,GalleryViewModelFactory(categoryName)).get(GalleryViewModel::class.java)
        binding.viewModel = galleryListViewModel
        val galleryListAdapter = GalleryListAdapter(galleryListViewModel, layoutManager)
        galleryListViewModel.galleryList.observe(this, Observer{
            if (it != null) {
                galleryListAdapter.updateGalleryList(it.data)
                binding.galleryList.adapter = galleryListAdapter
            }
        })
        galleryListViewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        galleryListViewModel.viralStatus.observe(this, Observer {
                viralStatus -> if(viralStatus) menu.findItem(R.id.viral).icon.alpha = 255 else menu.findItem(R.id.viral).icon.alpha = 130
        })
        galleryListViewModel.selectItemEvent.observe(this, Observer{
            if (it != null) {
                val (galleryItem, sharedViews) = it
                var imagePair = androidx.core.util.Pair(sharedViews[0], sharedViews[0].transitionName)
                var titlePair = androidx.core.util.Pair(sharedViews[1], sharedViews[1].transitionName)
                val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, imagePair)
                val intent = Intent(context, GalleryDetailActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelable(BUNDLE_EXTRA_GALLERY_PARAM, galleryItem)
                intent.putExtra(BUNDLE_EXTRA_PARAM,bundle)
                startActivity(intent, optionsCompat.toBundle())
            }
        })
        galleryListViewModel.loadingVisibility.observe(this, Observer {
                visibility -> if(visibility == View.VISIBLE) binding.animationView.playAnimation() else binding.animationView.progress = 0f
        })
        galleryListViewModel.flameVisibility.observe(this, Observer {
                visibility -> if(visibility == View.VISIBLE) binding.flameAnimationView.playAnimation() else binding.flameAnimationView.progress = 0f
        })
        return myView
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_CATEGORY_NAME = "category_name"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(mCategory: Category): GalleryFragment {
            return GalleryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CATEGORY_NAME, mCategory.title)
                }
            }
        }
    }

    /**
     * Method to show error in a Snackbar
     */
    private fun showError(@StringRes errorMessage:Int){
        val errorClickListener = View.OnClickListener { hideError() }
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.dismiss, errorClickListener)
        errorSnackbar?.show()
    }

    /**
     * Method to hide Snackbar
     */
    private fun hideError(){
        errorSnackbar?.dismiss()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.viral).icon.alpha = 130
        this.menu = menu
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return (when(item.itemId) {
            R.id.list -> {
                layoutManager?.spanCount = 1
                binding.galleryList.layoutManager = layoutManager
                binding.galleryList.adapter?.notifyItemRangeChanged(0, binding.galleryList.adapter?.itemCount ?: 0)
                true
            }R.id.grid -> {
                layoutManager?.spanCount = 2
                binding.galleryList.layoutManager = layoutManager
                binding.galleryList.adapter?.notifyItemRangeChanged(0, binding.galleryList.adapter?.itemCount ?: 0)
                true
            }R.id.staggered -> {
                layoutManager?.spanCount = 3
                var staggeredGridLayoutManager = StaggeredGridLayoutManager(3, LinearLayout.VERTICAL)
                staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
                binding.galleryList.layoutManager = staggeredGridLayoutManager
                binding.galleryList.adapter?.notifyItemRangeChanged(0, binding.galleryList.adapter?.itemCount ?: 0)
                true
            }
            R.id.viral -> {
                if (galleryListViewModel.viralStatus?.value!!) galleryListViewModel.loadGalleries() else galleryListViewModel.loadViralGalleries()
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        })
    }
}