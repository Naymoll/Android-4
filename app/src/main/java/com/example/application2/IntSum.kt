package com.example.application2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_int_sum.*

private const val INT_KEY = "Int_Result"

class IntSum : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_int_sum, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null) {
            resultText.text = savedInstanceState.getString(INT_KEY)
        }

        sumButton.setOnClickListener {
            if (firstNumber.text.isEmpty() || secondNumber.text.isEmpty()) {
                showSnackbar(it, getString(R.string.empty_field_err))
                return@setOnClickListener
            }

            val firstValStr = firstNumber.text.toString()
            val firstVal = firstValStr.toIntOrNull(10)

            val secondValStr = secondNumber.text.toString()
            val secondVal = secondValStr.toIntOrNull(10)

            if (firstVal == null || secondVal == null) {
                showSnackbar(it, getString(R.string.wrong_number_err))
                return@setOnClickListener
            }

            val resultStr = (firstVal + secondVal).toString()
            val result = getString(R.string.result) + resultStr
            resultText.text = result

            addHistoryItem(firstValStr, secondValStr, resultStr)
            showSnackbar(it, result)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            IntSum()
    }

    private fun showSnackbar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(INT_KEY, resultText.text.toString())
        super.onSaveInstanceState(outState)
    }

    private fun addHistoryItem(first: String, second: String, result: String) {
        val parent = activity as MainActivity
        parent.addToHistory(HistoryItem(first, second, result, "IntSum"))
    }
}