package com.app.hrcomposeapp.database

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "travels")
data class Travel(

    @PrimaryKey(autoGenerate = false)
//    @NonNull

    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "destination")
    var destination: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "country")
    var country: String,

    @ColumnInfo(name = "category")
    var category: String,

    @ColumnInfo(name = "duration")
    var duration: String,

    @ColumnInfo(name = "price")
    var price: Int,

    ) : Parcelable

@Parcelize
data class Category(
    @ColumnInfo(name = "category")
    var category: String,
) : Parcelable

data class TravelJSON(
    val id: String,
    val destination: String,
    val name: String,
    val country: String,
    val category: String,
    val duration: String,
    val price: String,
    )
