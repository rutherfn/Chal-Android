package com.nicholasrutherford.chal.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "configuration")
data class ConfigurationEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "primaryHeaderTypeface") var primaryHeaderTypeface: String,
    @ColumnInfo(name = "primaryHeaderTypefaceBold") var primaryHeaderTypefaceBold: String,
    @ColumnInfo(name = "subHeaderTypeface") var subHeaderTypeface: String,
    @ColumnInfo(name = "subHeaderTypefaceBold") var subHeaderTypefaceBold: String,
    @ColumnInfo(name = "bodyTypeface") var bodyTypeface: String,
    @ColumnInfo(name = "bodyTypefaceBold") var bodyTypefaceBold: String,
    @ColumnInfo(name = "bodyTypefaceItalic") var bodyTypefaceItalic: String,
    @ColumnInfo(name = "primaryColor") var primaryColor: String,
    @ColumnInfo(name = "secondaryColor") var secondaryColor: String
)