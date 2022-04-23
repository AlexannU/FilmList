package com.example.filmlist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.filmlist.R
import com.example.filmlist.data.remote.responses.Film
import com.example.filmlist.databinding.FilmItemBinding

class FilmRecyclerViewAdapter : PagingDataAdapter<Film,FilmRecyclerViewAdapter.FilmViewHolder>(FilmDiffCallback()) {

    private lateinit var recyclerView: RecyclerView

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }


    inner class FilmViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val binding = FilmItemBinding.bind(itemView)
        fun bind(film: Film){
            binding.poster.load(film.multimedia.src)
            binding.title.text = film.displayTitle
            binding.description.text = film.description
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.film_item,parent,false)
        return FilmViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

}

class FilmDiffCallback : DiffUtil.ItemCallback<Film>(){
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.displayTitle == newItem.displayTitle
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }

}