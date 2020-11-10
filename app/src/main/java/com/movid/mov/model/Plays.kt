package com.movid.mov.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class Plays (
    var nama:String ? ="",
    var url:String ? =""
    ) : Parcelable