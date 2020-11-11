package com.example.application2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_string_sum.*

private const val STRING_KEY = "String_Result"

class StringSum : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_string_sum, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null) {
            resultText.text = savedInstanceState.getString(STRING_KEY)
        }

        sumButton.setOnClickListener {
            if (firstText.text.isEmpty() || secondText.text.isEmpty()) {
                showSnackbar(it, getString(R.string.empty_field_err))
                return@setOnClickListener
            }

            val firstStr =  firstText.text.toString()
            val secondStr = secondText.text.toString()

            val result = getString(R.string.result) + firstStr + secondStr
            resultText.text = result

            addHistoryItem(firstStr, secondStr)
            showSnackbar(it, result)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            StringSum()
    }

    private fun showSnackbar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(STRING_KEY, resultText.text.toString())
        super.onSaveInstanceState(outState)
    }

    private fun addHistoryItem(first: String, second: String) {
        val parent = activity as MainActivity
        parent.addToHistory(HistoryItem(first, second, first + second, "StringSum"))
    }
}