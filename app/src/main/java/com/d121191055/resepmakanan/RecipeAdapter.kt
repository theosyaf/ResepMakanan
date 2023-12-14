package com.d121191055.resepmakanan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.d121191055.resepmakanan.Recipe
import com.d121191055.resepmakanan.R

class RecipeAdapter(private val recipes: MutableList<Recipe>) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.text_view_recipe_title)
        val descriptionTextView: TextView = itemView.findViewById(R.id.text_view_recipe_description)
        // Tambahkan view lain yang diperlukan
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentRecipe = recipes[position]

        holder.titleTextView.text = currentRecipe.title
        holder.descriptionTextView.text = currentRecipe.description
        // Set nilai untuk view lainnya
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun setRecipes(newRecipes: List<Recipe>) {
        recipes.clear()
        recipes.addAll(newRecipes)
        notifyDataSetChanged()
    }
}
