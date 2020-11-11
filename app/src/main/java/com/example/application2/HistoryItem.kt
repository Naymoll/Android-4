package com.example.application2

import android.os.Parcel
import android.os.Parcelable

class HistoryItem : Parcelable {
    private var first: String
    private var second: String
    private var function: String
    private var result: String

    constructor(first: String, second: String, result: String, function: String) {
        this.first = first
        this.second = second
        this.result = result
        this.function = function
    }

    constructor(input: Parcel) {
        first = input.readString()!!
        second = input.readString()!!
        function = input.readString()!!
        result = input.readString()!!
    }

    fun getTextRepresentation(): String {
        return String.format("Result of %s and %s of %s is %s",
            first, second, function, result)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(first)
        parcel.writeString(second)
        parcel.writeString(function)
        parcel.writeString(result)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HistoryItem> {
        override fun createFromParcel(parcel: Parcel): HistoryItem {
            return HistoryItem(parcel)
        }

        override fun newArray(size: Int): Array<HistoryItem?> {
            return arrayOfNulls(size)
        }
    }
}