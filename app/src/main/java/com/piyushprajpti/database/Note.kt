package com.piyushprajpti.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Note(
    @PrimaryKey
    @SerialName("_id")
    @ColumnInfo(name = "noteid")
    val noteid: String,

    @ColumnInfo(name = "userid")
    @SerialName("userid")
    val userid: String,

    @ColumnInfo(name = "title")
    @SerialName("title")
    val title: String,

    @ColumnInfo(name = "description")
    @SerialName("description")
    val description: String
)