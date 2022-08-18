package com.nasa.demo.assignment.api.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class ImageResponse(

    @ColumnInfo(name = "copyright")
    @SerializedName("copyright")
    var copyright: String? = null,

    @PrimaryKey
    @ColumnInfo(name = "date")
    @SerializedName("date")
    var date: String = "",

    @ColumnInfo(name = "explanation")
    @SerializedName("explanation")
    var explanation: String? = null,

    @ColumnInfo(name = "hdurl")
    @SerializedName("hdurl")
    var hdurl: String? = null,

    @ColumnInfo(name = "media_type")
    @SerializedName("media_type")
    var mediaType: String? = null,

    @ColumnInfo(name = "service_version")
    @SerializedName("service_version")
    var serviceVersion: String? = null,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: String? = null,

    @ColumnInfo(name = "url")
    @SerializedName("url")
    var url: String? = null
)


