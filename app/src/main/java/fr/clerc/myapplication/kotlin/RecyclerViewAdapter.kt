package fr.clerc.myapplication.kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.clerc.myapplication.R
import fr.clerc.myapplication.kotlin.model.Feature
import kotlinx.android.synthetic.main.item_feature.view.*

class RecyclerViewAdapter(private val itemList: ArrayList<Feature>, val listener: (Feature) -> Unit): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addFeatureList(list: ArrayList<Feature>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_feature, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val feature = itemList[position]

        holder.name.text = feature.properties.name
        holder.street.text = feature.properties.street
        holder.address.text = feature.properties.postalCode.toString() + " - " + feature.properties.city
        holder.image.setImageResource(R.drawable.metro_logo)

        holder.itemView.setOnClickListener {
            listener(feature)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.feature_name
        val address: TextView = view.feature_address
        val street: TextView = view.feature_street
        val image: ImageView = view.feature_type
    }
}