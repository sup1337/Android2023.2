package com.tasty.recipesapp.data.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.databinding.MyRecipeItemBinding
import com.tasty.recipesapp.ui.profile.ProfileFragment

interface onDeleteClickListener {
    fun onDeleteClick(id: Long)
}

class ProfileAdapter(val onClickListener: OnItemClickListener ,val onDeleteClickListener: onDeleteClickListener): RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    private var dataSet = mutableListOf<RecipeModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<RecipeModel>?){
        dataSet.clear()
        dataSet.addAll(list!!)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: MyRecipeItemBinding) : RecyclerView.ViewHolder(binding.root),  View.OnClickListener{
        init{
            binding.root.setOnClickListener(this)
            binding.detailsButton.setOnClickListener(this)
            binding.deleteButton.setOnClickListener {
                val position: Int = adapterPosition
                if( position != RecyclerView.NO_POSITION) {
                    onDeleteClickListener.onDeleteClick(dataSet[position].id.toLong())
                }
            }
        }
        override fun onClick(p0: View?) {
            val position: Int = adapterPosition
            if( position != RecyclerView.NO_POSITION) {
                onClickListener.onItemClick(dataSet[position])
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ProfileAdapter.ViewHolder {
        val binding =
            MyRecipeItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(viewHolder: ProfileAdapter.ViewHolder, position: Int) {
        val recipeItem = dataSet[position]

        with(viewHolder) {
            Glide.with(viewHolder.itemView.context)
                .load(recipeItem.thumbnailUrl)
                .into(binding.recipeImage)

            binding.recipeName.text = recipeItem.name
            binding.recipeDescription.text = recipeItem.description
        }
    }

}