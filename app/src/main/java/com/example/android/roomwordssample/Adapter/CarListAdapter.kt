package com.example.android.roomwordssample.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.roomwordssample.Entities.Car
import com.example.android.roomwordssample.Entities.Word
import com.example.android.roomwordssample.R
import kotlinx.android.synthetic.main.recyclercars.view.*
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class CarListAdapter (): RecyclerView.Adapter<CarListAdapter.WordViewHolder>() {

    //private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var cars = emptyList<Car>() // Cached copy of words

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind (item: Car) = with(itemView) {
            brand.text = item.brand
            model.text = item.model
            price.text ="$" + item.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclercars, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) = holder.bind(cars[position]) /*{
        val current = words[position]
        holder.wordItemView.text = current.word
    }*/

    internal fun setCars(cars: List<Car>) {
        this.cars = cars
        notifyDataSetChanged()
    }

    override fun getItemCount() = cars.size
}