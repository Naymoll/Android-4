package com.example.application2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var INT_STATE = 1
    private var STRING_STATE = 2
    private var state: Int = INT_STATE

    companion object {
        val HISTORY_KEY = "History"
    }

    private var STATE_KEY: String = "State"

    private var history: ArrayList<HistoryItem>? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        history = ArrayList()

        if (savedInstanceState != null) {
            state = savedInstanceState.getInt(STATE_KEY)
            history = savedInstanceState.getParcelableArrayList(HISTORY_KEY)
        }

        if (fragment_place != null) {
            setFragment(IntSum.newInstance())
            state = INT_STATE

            switch_fragment_button.setOnClickListener {
                if (state == INT_STATE) {
                    setFragment(StringSum.newInstance())
                    state = STRING_STATE
                }
                else if (state == STRING_STATE) {
                    setFragment(IntSum.newInstance())
                    state = INT_STATE
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_KEY, state)
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(HISTORY_KEY, history)
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(fragment_place.id, fragment).commit()
    }

    fun addToHistory(item: HistoryItem) {
        history?.add(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.open_history_item -> {
                val intent = Intent(this, HistoryActivity::class.java)
                intent.putParcelableArrayListExtra(HISTORY_KEY, history)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}