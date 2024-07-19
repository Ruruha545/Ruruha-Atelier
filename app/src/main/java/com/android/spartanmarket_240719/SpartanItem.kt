package com.android.spartanmarket_240719

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpartanItem(val img_index:Int, val title: String, val promotion: String, val name: String,
                       val location: String, val price: Int, val comment_n: String, val heart_n: String)
    : Parcelable