package com.example.application2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar


class HistoryAdapter (private var history: ArrayList<HistoryItem>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    fun initialize(history: ArrayList<HistoryItem>) {
        this.history = history
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): HistoryViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_list_item, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(history[position])
    }

    override fun getItemCount() = history.size

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var historyText: TextView = view.findViewById(R.id.history_text)
        private var historyButton: Button = view.findViewById(R.id.history_button)

        fun bind(item: HistoryItem) {
            historyText.text = item.getTextRepresentation()
            historyButton.setOnClickListener {
                Snackbar.make(it, item.getTextRepresentation(), Snackbar.LENGTH_LONG).show()
            }
        }
    }
}