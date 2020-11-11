package com.example.application2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_history.*


class HistoryActivity : AppCompatActivity() {
    private var adapter: HistoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val intent = intent
        adapter = HistoryAdapter(ArrayList())
        if (intent != null && intent.hasExtra(MainActivity.HISTORY_KEY)) {
            val history = intent.getParcelableArrayListExtra<HistoryItem>(MainActivity.HISTORY_KEY)
            if (history != null) {
                adapter!!.initialize(history)
            }
        }

        history_list.layoutManager = LinearLayoutManager(this)
        history_list.adapter = adapter
    }
}