package com.android.imageresearcher.DataClass

data class ImageRequest(
    val query:String,
    val sort:String,
    val page:Int,
    val size:Int
)