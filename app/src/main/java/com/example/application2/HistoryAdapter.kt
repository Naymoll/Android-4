package com.example.application2

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar


class HistoryAdapter (var history: ArrayList<HistoryItem>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    fun initialize(history: ArrayList<HistoryItem>) {
        this.history = history
        notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun removeExpect(item: HistoryItem) {
        this.history.removeIf { t -> t != item }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_list_item, parent, false)
        return HistoryViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = this.history[position]

        holder.historyText.text = item.getTextRepresentation()
        holder.historyButton.setOnClickListener {
            Snackbar.make(it, item.getTextRepresentation(), Snackbar.LENGTH_LONG).show()
        }
        holder.historyCheckBox.setOnClickListener {
            if (holder.historyCheckBox.isChecked) {
                removeExpect(item)
            }
        }
    }

    override fun getItemCount() = history.size

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var historyText: TextView = view.findViewById(R.id.history_text)
        var historyButton: Button = view.findViewById(R.id.history_button)
        var historyCheckBox: CheckBox = view.findViewById(R.id.history_checkbox)
    }
}