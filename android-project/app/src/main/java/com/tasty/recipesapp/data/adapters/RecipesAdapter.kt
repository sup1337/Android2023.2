package com.tasty.recipesapp.data.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.databinding.RecipeItemBinding

interface OnItemClickListener {
    fun onItemClick(item: RecipeModel)
}

class RecipesAdapter(val onClickListener: OnItemClickListener) :
    RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {

    private var dataSet = mutableListOf<RecipeModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<RecipeModel>?){
        dataSet.clear()
        dataSet.addAll(list!!)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: RecipeItemBinding) : RecyclerView.ViewHolder(binding.root),  View.OnClickListener{
        init{
            binding.root.setOnClickListener(this)
            binding.detailsButton.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val position: Int = adapterPosition
            if( position != RecyclerView.NO_POSITION) {
                onClickListener.onItemClick(dataSet[position])
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RecipeItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val recipeItem = dataSet[position]

        with(viewHolder) {
            Glide.with(viewHolder.itemView.context)
                .load(recipeItem.thumbnailUrl)
                .into(binding.recipeImage)

            binding.recipeName.text = recipeItem.name
            binding.recipeDescription.text = recipeItem.description
            binding.recipeRating.text = recipeItem.userRatings.score.toString()
        }
    }
}