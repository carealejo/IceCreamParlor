package com.acr.icecreamparlor.domain.model

import android.os.Parcel
import android.os.Parcelable

data class IceCream (
    val name1: String?,
    val name2: String?,
    val price: String?,
    val bg_color: String?,
    val type: String?,
    var counter: Int = 0
): Parcelable {
    val completeName
        get() = "$name1 $name2"

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name1)
        parcel.writeString(name2)
        parcel.writeString(price)
        parcel.writeString(bg_color)
        parcel.writeString(type)
        parcel.writeInt(counter)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IceCream> {
        override fun createFromParcel(parcel: Parcel): IceCream {
            return IceCream(parcel)
        }

        override fun newArray(size: Int): Array<IceCream?> {
            return arrayOfNulls(size)
        }
    }
}