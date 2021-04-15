package com.theleafapps.pro.rxjavaplusroom.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class StudentEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        @ColumnInfo(name="student_name")
        val studentName:String,
        @ColumnInfo(name="age")
        val age:Int,
        @ColumnInfo(name="subject")
        val subject:String
)
