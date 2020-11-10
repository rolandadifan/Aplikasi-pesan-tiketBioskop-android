package com.movid.mov.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class Film (
    var desc:String ? ="",
    var director:String ? ="",
    var genre:String ? ="",
    var poster:String ? ="",
    var judul:String ? ="",
    var rating:String ? =""
    ) : Parcelable