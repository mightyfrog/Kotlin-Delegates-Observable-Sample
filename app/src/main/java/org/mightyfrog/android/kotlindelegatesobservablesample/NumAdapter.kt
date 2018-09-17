package org.mightyfrog.android.kotlindelegatesobservablesample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * @author Shigehiro Soejima
 */
class NumAdapter(private val list: List<Int>) : RecyclerView.Adapter<NumAdapter.NumViewHolder>() {

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumViewHolder {
        return NumViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder, parent, false))
    }

    override fun onBindViewHolder(holder: NumViewHolder, position: Int) {
        holder.tv.text = list[position].toString()
    }

    class NumViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv = view.findViewById<TextView>(R.id.text)!!
    }
}
