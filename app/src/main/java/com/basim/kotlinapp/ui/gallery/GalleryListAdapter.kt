package com.basim.kotlinapp.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.basim.kotlinapp.R
import com.basim.kotlinapp.data.model.Gallery
import com.basim.kotlinapp.databinding.ItemGalleryGridBinding
import com.basim.kotlinapp.databinding.ItemGalleryListBinding
import com.basim.kotlinapp.databinding.ItemGalleryStaggeredBinding
import com.basim.kotlinapp.ui.GalleryViewModel
import kotlinx.android.synthetic.main.item_gallery_grid.view.*

/**
 * Adapter for Gallery List
 */
class GalleryListAdapter(val viewModel: GalleryViewModel, private val layoutManager: GridLayoutManager? = null): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var galleryList:List<Gallery>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val bindingGrid: ItemGalleryGridBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_gallery_grid, parent, false)
        val bindingList: ItemGalleryListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_gallery_list, parent, false)
        val bindingStaggered: ItemGalleryStaggeredBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_gallery_staggered, parent, false)
        return when (viewType) {
            ViewType.LIST.ordinal -> ListViewHolder(bindingList)
            ViewType.STAGGERED.ordinal -> StaggeredViewHolder(bindingStaggered)
            else -> GridViewHolder(bindingGrid)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GridViewHolder -> holder.bind(galleryList[position])
            is ListViewHolder -> holder.bind(galleryList[position])
            is StaggeredViewHolder -> holder.bind(galleryList[position])
        }
        holder.itemView.setOnClickListener {
            val viewsList = listOf(holder.itemView.image, holder.itemView.titleTextView)
            viewModel.selectItemEvent.value = Pair(galleryList[position], viewsList)
        }
    }

    override fun getItemCount(): Int {
        return if(::galleryList.isInitialized) galleryList.size else 0
    }

    fun updateGalleryList(galleryList:List<Gallery>){
        this.galleryList = galleryList
        notifyDataSetChanged()
    }

    class GridViewHolder(private val binding: ItemGalleryGridBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = GalleryItemViewModel()
        fun bind(gallery: Gallery){
            viewModel.bind(gallery)
            binding.viewModel = viewModel
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            layoutManager?.spanCount == 1 -> ViewType.LIST.ordinal
            layoutManager?.spanCount == 2 -> ViewType.GRID.ordinal
            else -> ViewType.STAGGERED.ordinal
        }
    }

    class ListViewHolder(private val binding: ItemGalleryListBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = GalleryItemViewModel()
        fun bind(gallery: Gallery){
            viewModel.bind(gallery)
            binding.viewModel = viewModel
        }
    }

    class StaggeredViewHolder(private val binding: ItemGalleryStaggeredBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = GalleryItemViewModel()
        fun bind(gallery: Gallery){
            viewModel.bind(gallery)
            binding.viewModel = viewModel
        }
    }

    enum class ViewType {
        GRID,
        LIST,
        STAGGERED
    }
}